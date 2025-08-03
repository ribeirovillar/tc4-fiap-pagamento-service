package com.fiap.pagamento.gateway;

import com.fiap.pagamento.domain.Payment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentGateway {
    Optional<Payment> findById(UUID id);

    Optional<Payment> save(Payment payment);

    List<Payment> findAll();

    Optional<Payment> findByOrderId(UUID orderId);

}
