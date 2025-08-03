package com.fiap.pagamento.controller.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDTOTest {

    @Test
    void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        String customerName = "John Doe";
        String customerCpf = "12345678901";
        String cardNumber = "1234567890123456";
        BigDecimal paymentAmount = new BigDecimal("100.50");

        PaymentDTO paymentDTO = new PaymentDTO(id, orderId, customerName, customerCpf, cardNumber, paymentAmount);

        assertEquals(id, paymentDTO.getId());
        assertEquals(orderId, paymentDTO.getOrderId());
        assertEquals(customerName, paymentDTO.getCustomerName());
        assertEquals(customerCpf, paymentDTO.getCustomerCpf());
        assertEquals(cardNumber, paymentDTO.getCardNumber());
        assertEquals(paymentAmount, paymentDTO.getPaymentAmount());
    }

    @Test
    void testNoArgsConstructor() {
        PaymentDTO paymentDTO = new PaymentDTO();

        assertNull(paymentDTO.getId());
        assertNull(paymentDTO.getOrderId());
        assertNull(paymentDTO.getCustomerName());
        assertNull(paymentDTO.getCustomerCpf());
        assertNull(paymentDTO.getCardNumber());
        assertNull(paymentDTO.getPaymentAmount());
    }

    @Test
    void testSettersAndGetters() {
        PaymentDTO paymentDTO = new PaymentDTO();
        UUID id = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        String customerName = "Jane Doe";
        String customerCpf = "98765432101";
        String cardNumber = "9876543210987654";
        BigDecimal paymentAmount = new BigDecimal("200.75");

        paymentDTO.setId(id);
        paymentDTO.setOrderId(orderId);
        paymentDTO.setCustomerName(customerName);
        paymentDTO.setCustomerCpf(customerCpf);
        paymentDTO.setCardNumber(cardNumber);
        paymentDTO.setPaymentAmount(paymentAmount);

        assertEquals(id, paymentDTO.getId());
        assertEquals(orderId, paymentDTO.getOrderId());
        assertEquals(customerName, paymentDTO.getCustomerName());
        assertEquals(customerCpf, paymentDTO.getCustomerCpf());
        assertEquals(cardNumber, paymentDTO.getCardNumber());
        assertEquals(paymentAmount, paymentDTO.getPaymentAmount());
    }

    @Test
    void testJsonSerialization() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        paymentDTO.setOrderId(UUID.fromString("123e4567-e89b-12d3-a456-426614174001"));
        paymentDTO.setCustomerName("John Doe");
        paymentDTO.setCustomerCpf("12345678901");
        paymentDTO.setCardNumber("1234567890123456");
        paymentDTO.setPaymentAmount(new BigDecimal("100.50"));

        String json = objectMapper.writeValueAsString(paymentDTO);
        assertNotNull(json);
        assertTrue(json.contains("John Doe"));
        assertTrue(json.contains("12345678901"));
    }

    @Test
    void testJsonDeserialization() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"id\":\"123e4567-e89b-12d3-a456-426614174000\",\"orderId\":\"123e4567-e89b-12d3-a456-426614174001\",\"customerName\":\"John Doe\",\"customerCpf\":\"12345678901\",\"cardNumber\":\"1234567890123456\",\"paymentAmount\":100.50}";

        PaymentDTO paymentDTO = objectMapper.readValue(json, PaymentDTO.class);

        assertNotNull(paymentDTO);
        assertEquals("John Doe", paymentDTO.getCustomerName());
        assertEquals("12345678901", paymentDTO.getCustomerCpf());
        assertEquals("1234567890123456", paymentDTO.getCardNumber());
        assertEquals(new BigDecimal("100.50"), paymentDTO.getPaymentAmount());
    }

    @Test
    void testJsonIgnoreUnknownProperties() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonWithUnknownField = "{\"id\":\"123e4567-e89b-12d3-a456-426614174000\",\"customerName\":\"John Doe\",\"unknownField\":\"should be ignored\"}";

        PaymentDTO paymentDTO = objectMapper.readValue(jsonWithUnknownField, PaymentDTO.class);

        assertNotNull(paymentDTO);
        assertEquals("John Doe", paymentDTO.getCustomerName());
    }
}
