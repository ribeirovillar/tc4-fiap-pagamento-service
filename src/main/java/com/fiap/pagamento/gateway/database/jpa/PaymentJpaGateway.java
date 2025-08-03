package com.fiap.pagamento.gateway.database.jpa;

import com.fiap.pagamento.domain.Payment;
import com.fiap.pagamento.gateway.PaymentGateway;
import com.fiap.pagamento.gateway.database.jpa.repository.PaymentRepository;
import com.fiap.pagamento.mapper.PaymentMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentJpaGateway implements PaymentGateway {

    PaymentRepository repository;
    PaymentMapper mapper;

    @Override
    public Optional<Payment> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::map);
    }

    @Override
    public Optional<Payment> save(Payment payment) {
        return Optional.of(repository.save(mapper.mapToEntity(payment)))
                .map(mapper::map);
    }

    @Override
    public List<Payment> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public Optional<Payment> findByOrderId(UUID orderId) {
        return repository.findByOrderId(orderId).map(mapper::map);
    }

}
