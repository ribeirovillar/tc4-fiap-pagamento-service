package com.fiap.pagamento.gateway.web;

import com.fiap.pagamento.domain.Payment;
import com.fiap.pagamento.gateway.ProcessPaymentGateway;
import com.fiap.pagamento.gateway.web.client.ProcessPaymentWebClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProcessPaymentWebClientGateway implements ProcessPaymentGateway {

    ProcessPaymentWebClient client;

    @Override
    public UUID requestPayment(Payment payment) {
        return client.requestPayment(payment);
    }

}
