package com.fiap.pagamento.usecase;

import com.fiap.pagamento.domain.Payment;
import com.fiap.pagamento.exception.CreatePaymentException;
import com.fiap.pagamento.gateway.PaymentGateway;
import com.fiap.pagamento.usecase.validation.CreatePaymentStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CreatePaymentUseCase {

    private final PaymentGateway gateway;
    private final RequestPaymentUseCase requestPaymentUseCase;
    private final List<CreatePaymentStrategy> validations;

    public CreatePaymentUseCase(PaymentGateway gateway, RequestPaymentUseCase requestPaymentUseCase, List<CreatePaymentStrategy> validations) {
        this.gateway = gateway;
        this.requestPaymentUseCase = requestPaymentUseCase;
        this.validations = validations;
    }

    public Payment execute(Payment payment) {
        log.info("Creating payment {}", payment);

        validations.forEach(validation -> validation.validate(payment));

        return gateway.findByOrderId(payment.getOrderId())
                .orElseGet(() -> {
                    payment.setId(requestPaymentUseCase.execute(payment));
                    return gateway.save(payment)
                            .orElseThrow(() -> {
                                log.error("Payment could not be saved: {}", payment);
                                return new CreatePaymentException("Payment could not be saved");
                            });
                });


    }

}
