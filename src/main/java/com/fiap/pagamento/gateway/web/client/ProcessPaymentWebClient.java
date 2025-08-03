package com.fiap.pagamento.gateway.web.client;

import com.fiap.pagamento.domain.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ProcessPaymentWebClient {

    public UUID requestPayment(Payment payment) {
        log.info("Processing Payment {}", payment);
        return UUID.randomUUID();
    }
}
