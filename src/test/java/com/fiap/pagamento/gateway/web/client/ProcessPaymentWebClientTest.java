package com.fiap.pagamento.gateway.web.client;

import com.fiap.pagamento.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProcessPaymentWebClientTest {

    @InjectMocks
    private ProcessPaymentWebClient webClient;

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
    void shouldReturnUUIDWhenProcessingPayment() {
        UUID result = webClient.requestPayment(payment);

        assertNotNull(result);
        assertTrue(result instanceof UUID);
    }

    @Test
    void shouldReturnDifferentUUIDsForMultipleCalls() {
        UUID result1 = webClient.requestPayment(payment);
        UUID result2 = webClient.requestPayment(payment);

        assertNotNull(result1);
        assertNotNull(result2);
        assertNotEquals(result1, result2);
    }

    @Test
    void shouldHandleNullPayment() {
        UUID result = webClient.requestPayment(null);

        assertNotNull(result);
        assertTrue(result instanceof UUID);
    }

    @Test
    void shouldHandlePaymentWithNullFields() {
        Payment emptyPayment = new Payment();

        UUID result = webClient.requestPayment(emptyPayment);

        assertNotNull(result);
        assertTrue(result instanceof UUID);
    }
}
