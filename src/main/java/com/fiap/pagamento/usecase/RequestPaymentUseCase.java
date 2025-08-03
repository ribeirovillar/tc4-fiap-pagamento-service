package com.fiap.pagamento.usecase;

import com.fiap.pagamento.domain.Payment;
import com.fiap.pagamento.exception.RequestPaymentException;
import com.fiap.pagamento.gateway.ProcessPaymentGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class RequestPaymentUseCase {

    ProcessPaymentGateway processPaymentGateway;

    public RequestPaymentUseCase(ProcessPaymentGateway processPaymentGateway) {
        this.processPaymentGateway = processPaymentGateway;
    }

    public UUID execute(Payment payment) {
        return Optional.ofNullable(processPaymentGateway.requestPayment(payment)).orElseThrow(() -> new RequestPaymentException("External service unavailable"));
    }

}
