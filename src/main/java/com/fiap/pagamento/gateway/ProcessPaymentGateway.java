package com.fiap.pagamento.gateway;

import com.fiap.pagamento.domain.Payment;

import java.util.UUID;

public interface ProcessPaymentGateway {

    UUID requestPayment(Payment payment);

}
