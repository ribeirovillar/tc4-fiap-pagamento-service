package com.fiap.pagamento.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        String customerName = "John Doe";
        String customerCpf = "12345678901";
        String cardNumber = "1234567890123456";
        BigDecimal paymentAmount = new BigDecimal("100.50");

        Payment payment = new Payment(id, orderId, customerName, customerCpf, cardNumber, paymentAmount);

        assertEquals(id, payment.getId());
        assertEquals(orderId, payment.getOrderId());
        assertEquals(customerName, payment.getCustomerName());
        assertEquals(customerCpf, payment.getCustomerCpf());
        assertEquals(cardNumber, payment.getCardNumber());
        assertEquals(paymentAmount, payment.getPaymentAmount());
    }

    @Test
    void testNoArgsConstructor() {
        Payment payment = new Payment();

        assertNull(payment.getId());
        assertNull(payment.getOrderId());
        assertNull(payment.getCustomerName());
        assertNull(payment.getCustomerCpf());
        assertNull(payment.getCardNumber());
        assertNull(payment.getPaymentAmount());
    }

    @Test
    void testSettersAndGetters() {
        Payment payment = new Payment();
        UUID id = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        String customerName = "Jane Doe";
        String customerCpf = "98765432101";
        String cardNumber = "9876543210987654";
        BigDecimal paymentAmount = new BigDecimal("200.75");

        payment.setId(id);
        payment.setOrderId(orderId);
        payment.setCustomerName(customerName);
        payment.setCustomerCpf(customerCpf);
        payment.setCardNumber(cardNumber);
        payment.setPaymentAmount(paymentAmount);

        assertEquals(id, payment.getId());
        assertEquals(orderId, payment.getOrderId());
        assertEquals(customerName, payment.getCustomerName());
        assertEquals(customerCpf, payment.getCustomerCpf());
        assertEquals(cardNumber, payment.getCardNumber());
        assertEquals(paymentAmount, payment.getPaymentAmount());
    }
}
