package com.fiap.pagamento.gateway.database.jpa;

import com.fiap.pagamento.domain.Payment;
import com.fiap.pagamento.gateway.database.jpa.entity.PaymentEntity;
import com.fiap.pagamento.gateway.database.jpa.repository.PaymentRepository;
import com.fiap.pagamento.mapper.PaymentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentJpaGatewayTest {

    @Mock
    private PaymentRepository repository;

    @Mock
    private PaymentMapper mapper;

    @InjectMocks
    private PaymentJpaGateway paymentJpaGateway;

    private Payment payment;
    private PaymentEntity entity;
    private UUID paymentId;

    @BeforeEach
    void setUp() {
        paymentId = UUID.randomUUID();

        payment = new Payment();
        payment.setId(paymentId);
        payment.setOrderId(UUID.randomUUID());
        payment.setCustomerName("John Doe");
        payment.setCustomerCpf("12345678901");
        payment.setCardNumber("1234567890123456");
        payment.setPaymentAmount(new BigDecimal("100.50"));

        entity = new PaymentEntity();
        entity.setId(paymentId);
        entity.setOrderId(payment.getOrderId());
        entity.setCustomerName(payment.getCustomerName());
        entity.setCustomerCpf(payment.getCustomerCpf());
        entity.setCardNumber(payment.getCardNumber());
        entity.setPaymentAmount(payment.getPaymentAmount());
    }

    @Test
    void shouldFindByIdSuccessfully() {
        when(repository.findById(paymentId)).thenReturn(Optional.of(entity));
        when(mapper.map(entity)).thenReturn(payment);

        Optional<Payment> result = paymentJpaGateway.findById(paymentId);

        assertTrue(result.isPresent());
        assertEquals(payment, result.get());
        verify(repository).findById(paymentId);
        verify(mapper).map(entity);
    }

    @Test
    void shouldReturnEmptyWhenPaymentNotFound() {
        when(repository.findById(paymentId)).thenReturn(Optional.empty());

        Optional<Payment> result = paymentJpaGateway.findById(paymentId);

        assertFalse(result.isPresent());
        verify(repository).findById(paymentId);
        verify(mapper, never()).map(any(PaymentEntity.class));
    }

    @Test
    void shouldSavePaymentSuccessfully() {
        when(mapper.mapToEntity(payment)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.map(entity)).thenReturn(payment);

        Optional<Payment> result = paymentJpaGateway.save(payment);

        assertTrue(result.isPresent());
        assertEquals(payment, result.get());
        verify(mapper).mapToEntity(payment);
        verify(repository).save(entity);
        verify(mapper).map(entity);
    }

    @Test
    void shouldFindAllPaymentsSuccessfully() {
        Payment payment2 = new Payment();
        payment2.setId(UUID.randomUUID());
        PaymentEntity entity2 = new PaymentEntity();
        entity2.setId(payment2.getId());

        List<PaymentEntity> entities = Arrays.asList(entity, entity2);
        when(repository.findAll()).thenReturn(entities);
        when(mapper.map(entity)).thenReturn(payment);
        when(mapper.map(entity2)).thenReturn(payment2);

        List<Payment> result = paymentJpaGateway.findAll();

        assertEquals(2, result.size());
        assertEquals(payment, result.get(0));
        assertEquals(payment2, result.get(1));
        verify(repository).findAll();
        verify(mapper, times(2)).map(any(PaymentEntity.class));
    }

    @Test
    void shouldReturnEmptyListWhenNoPaymentsFound() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<Payment> result = paymentJpaGateway.findAll();

        assertTrue(result.isEmpty());
        verify(repository).findAll();
        verify(mapper, never()).map(any(PaymentEntity.class));
    }
}
