package com.fiap.pagamento.usecase;

import com.fiap.pagamento.domain.Payment;
import com.fiap.pagamento.exception.RequestPaymentException;
import com.fiap.pagamento.gateway.ProcessPaymentGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestPaymentUseCaseTest {

    @Mock
    private ProcessPaymentGateway processPaymentGateway;

    @InjectMocks
    private RequestPaymentUseCase requestPaymentUseCase;

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment();
        payment.setOrderId(UUID.randomUUID());
        payment.setCustomerName("John Doe");
        payment.setCustomerCpf("12345678901");
        payment.setCardNumber("1234567890123456");
        payment.setPaymentAmount(new BigDecimal("100.50"));
    }

    @Test
    void shouldReturnPaymentIdWhenRequestSuccessful() {
        UUID expectedId = UUID.randomUUID();
        when(processPaymentGateway.requestPayment(payment)).thenReturn(expectedId);

        UUID result = requestPaymentUseCase.execute(payment);

        assertEquals(expectedId, result);
        verify(processPaymentGateway).requestPayment(payment);
    }

    @Test
    void shouldThrowRequestPaymentExceptionWhenGatewayReturnsNull() {
        when(processPaymentGateway.requestPayment(payment)).thenReturn(null);

        RequestPaymentException exception = assertThrows(RequestPaymentException.class,
            () -> requestPaymentUseCase.execute(payment));

        assertEquals("External service unavailable", exception.getMessage());
        verify(processPaymentGateway).requestPayment(payment);
    }

    @Test
    void shouldCallProcessPaymentGatewayWithCorrectPayment() {
        UUID expectedId = UUID.randomUUID();
        when(processPaymentGateway.requestPayment(payment)).thenReturn(expectedId);

        requestPaymentUseCase.execute(payment);

        verify(processPaymentGateway).requestPayment(payment);
    }
}
