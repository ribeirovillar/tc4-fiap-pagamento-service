package com.fiap.pagamento.usecase;

import com.fiap.pagamento.domain.Payment;
import com.fiap.pagamento.gateway.PaymentGateway;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetrieveAllPaymentsUseCaseTest {

    @Mock
    private PaymentGateway gateway;

    @InjectMocks
    private RetrieveAllPaymentsUseCase retrieveAllPaymentsUseCase;

    private Payment payment1;
    private Payment payment2;

    @BeforeEach
    void setUp() {
        payment1 = new Payment();
        payment1.setId(UUID.randomUUID());
        payment1.setOrderId(UUID.randomUUID());
        payment1.setCustomerName("John Doe");
        payment1.setCustomerCpf("12345678901");
        payment1.setCardNumber("1234567890123456");
        payment1.setPaymentAmount(new BigDecimal("100.50"));

        payment2 = new Payment();
        payment2.setId(UUID.randomUUID());
        payment2.setOrderId(UUID.randomUUID());
        payment2.setCustomerName("Jane Smith");
        payment2.setCustomerCpf("98765432101");
        payment2.setCardNumber("9876543210987654");
        payment2.setPaymentAmount(new BigDecimal("200.75"));
    }

    @Test
    void shouldReturnAllPayments() {
        List<Payment> expectedPayments = Arrays.asList(payment1, payment2);
        when(gateway.findAll()).thenReturn(expectedPayments);

        List<Payment> result = retrieveAllPaymentsUseCase.execute();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedPayments, result);
        verify(gateway).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoPayments() {
        when(gateway.findAll()).thenReturn(Collections.emptyList());

        List<Payment> result = retrieveAllPaymentsUseCase.execute();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(gateway).findAll();
    }

    @Test
    void shouldReturnSinglePayment() {
        List<Payment> expectedPayments = Collections.singletonList(payment1);
        when(gateway.findAll()).thenReturn(expectedPayments);

        List<Payment> result = retrieveAllPaymentsUseCase.execute();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(payment1, result.getFirst());
        verify(gateway).findAll();
    }
}
