package com.fiap.pagamento.gateway.database.jpa.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PaymentEntityTest {

    @Test
    void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        String customerName = "John Doe";
        String customerCpf = "12345678901";
        String cardNumber = "1234567890123456";
        BigDecimal paymentAmount = new BigDecimal("100.50");

        PaymentEntity entity = new PaymentEntity(id, orderId, customerName, customerCpf, cardNumber, paymentAmount);

        assertEquals(id, entity.getId());
        assertEquals(orderId, entity.getOrderId());
        assertEquals(customerName, entity.getCustomerName());
        assertEquals(customerCpf, entity.getCustomerCpf());
        assertEquals(cardNumber, entity.getCardNumber());
        assertEquals(paymentAmount, entity.getPaymentAmount());
    }

    @Test
    void testNoArgsConstructor() {
        PaymentEntity entity = new PaymentEntity();

        assertNull(entity.getId());
        assertNull(entity.getOrderId());
        assertNull(entity.getCustomerName());
        assertNull(entity.getCustomerCpf());
        assertNull(entity.getCardNumber());
        assertNull(entity.getPaymentAmount());
    }

    @Test
    void testSettersAndGetters() {
        PaymentEntity entity = new PaymentEntity();
        UUID id = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        String customerName = "Jane Doe";
        String customerCpf = "98765432101";
        String cardNumber = "9876543210987654";
        BigDecimal paymentAmount = new BigDecimal("200.75");

        entity.setId(id);
        entity.setOrderId(orderId);
        entity.setCustomerName(customerName);
        entity.setCustomerCpf(customerCpf);
        entity.setCardNumber(cardNumber);
        entity.setPaymentAmount(paymentAmount);

        assertEquals(id, entity.getId());
        assertEquals(orderId, entity.getOrderId());
        assertEquals(customerName, entity.getCustomerName());
        assertEquals(customerCpf, entity.getCustomerCpf());
        assertEquals(cardNumber, entity.getCardNumber());
        assertEquals(paymentAmount, entity.getPaymentAmount());
    }
}
