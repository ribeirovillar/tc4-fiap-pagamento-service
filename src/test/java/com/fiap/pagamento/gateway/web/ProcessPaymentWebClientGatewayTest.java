package com.fiap.pagamento.gateway.web;

import com.fiap.pagamento.domain.Payment;
import com.fiap.pagamento.gateway.web.client.ProcessPaymentWebClient;
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
class ProcessPaymentWebClientGatewayTest {

    @Mock
    private ProcessPaymentWebClient client;

    @InjectMocks
    private ProcessPaymentWebClientGateway gateway;

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
    void shouldRequestPaymentSuccessfully() {
        UUID expectedId = UUID.randomUUID();
        when(client.requestPayment(payment)).thenReturn(expectedId);

        UUID result = gateway.requestPayment(payment);

        assertEquals(expectedId, result);
        verify(client).requestPayment(payment);
    }

    @Test
    void shouldReturnNullWhenClientReturnsNull() {
        when(client.requestPayment(payment)).thenReturn(null);

        UUID result = gateway.requestPayment(payment);

        assertNull(result);
        verify(client).requestPayment(payment);
    }

    @Test
    void shouldPassPaymentToClient() {
        UUID expectedId = UUID.randomUUID();
        when(client.requestPayment(payment)).thenReturn(expectedId);

        gateway.requestPayment(payment);

        verify(client).requestPayment(payment);
    }
}
