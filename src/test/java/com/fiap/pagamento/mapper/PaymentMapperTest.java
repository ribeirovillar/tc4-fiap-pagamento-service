package com.fiap.pagamento.mapper;

import com.fiap.pagamento.controller.json.PaymentDTO;
import com.fiap.pagamento.domain.Payment;
import com.fiap.pagamento.gateway.database.jpa.entity.PaymentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentMapperTest {

    @Autowired
    private PaymentMapper paymentMapper;

    private Payment payment;
    private PaymentDTO paymentDTO;
    private PaymentEntity paymentEntity;

    @BeforeEach
    void setUp() {
        UUID id = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();

        payment = new Payment();
        payment.setId(id);
        payment.setOrderId(orderId);
        payment.setCustomerName("John Doe");
        payment.setCustomerCpf("12345678901");
        payment.setCardNumber("1234567890123456");
        payment.setPaymentAmount(new BigDecimal("100.50"));

        paymentDTO = new PaymentDTO();
        paymentDTO.setId(id);
        paymentDTO.setOrderId(orderId);
        paymentDTO.setCustomerName("John Doe");
        paymentDTO.setCustomerCpf("12345678901");
        paymentDTO.setCardNumber("1234567890123456");
        paymentDTO.setPaymentAmount(new BigDecimal("100.50"));

        paymentEntity = new PaymentEntity();
        paymentEntity.setId(id);
        paymentEntity.setOrderId(orderId);
        paymentEntity.setCustomerName("John Doe");
        paymentEntity.setCustomerCpf("12345678901");
        paymentEntity.setCardNumber("1234567890123456");
        paymentEntity.setPaymentAmount(new BigDecimal("100.50"));
    }

    @Test
    void shouldMapPaymentToDTO() {
        PaymentDTO result = paymentMapper.mapToDto(payment);

        assertNotNull(result);
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getOrderId(), result.getOrderId());
        assertEquals(payment.getCustomerName(), result.getCustomerName());
        assertEquals(payment.getCustomerCpf(), result.getCustomerCpf());
        assertEquals(payment.getCardNumber(), result.getCardNumber());
        assertEquals(payment.getPaymentAmount(), result.getPaymentAmount());
    }

    @Test
    void shouldMapDTOToPaymentIgnoringId() {
        Payment result = paymentMapper.map(paymentDTO);

        assertNotNull(result);
        assertNull(result.getId());
        assertEquals(paymentDTO.getOrderId(), result.getOrderId());
        assertEquals(paymentDTO.getCustomerName(), result.getCustomerName());
        assertEquals(paymentDTO.getCustomerCpf(), result.getCustomerCpf());
        assertEquals(paymentDTO.getCardNumber(), result.getCardNumber());
        assertEquals(paymentDTO.getPaymentAmount(), result.getPaymentAmount());
    }

    @Test
    void shouldMapEntityToPayment() {
        Payment result = paymentMapper.map(paymentEntity);

        assertNotNull(result);
        assertEquals(paymentEntity.getId(), result.getId());
        assertEquals(paymentEntity.getOrderId(), result.getOrderId());
        assertEquals(paymentEntity.getCustomerName(), result.getCustomerName());
        assertEquals(paymentEntity.getCustomerCpf(), result.getCustomerCpf());
        assertEquals(paymentEntity.getCardNumber(), result.getCardNumber());
        assertEquals(paymentEntity.getPaymentAmount(), result.getPaymentAmount());
    }

    @Test
    void shouldMapPaymentToEntity() {
        PaymentEntity result = paymentMapper.mapToEntity(payment);

        assertNotNull(result);
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getOrderId(), result.getOrderId());
        assertEquals(payment.getCustomerName(), result.getCustomerName());
        assertEquals(payment.getCustomerCpf(), result.getCustomerCpf());
        assertEquals(payment.getCardNumber(), result.getCardNumber());
        assertEquals(payment.getPaymentAmount(), result.getPaymentAmount());
    }

    @Test
    void shouldHandleNullPayment() {
        PaymentDTO result = paymentMapper.mapToDto(null);
        assertNull(result);
    }

    @Test
    void shouldHandleNullPaymentDTO() {
        Payment result = paymentMapper.map((PaymentDTO) null);
        assertNull(result);
    }

    @Test
    void shouldHandleNullPaymentEntity() {
        Payment result = paymentMapper.map((PaymentEntity) null);
        assertNull(result);
    }

    @Test
    void shouldHandleNullPaymentForEntity() {
        PaymentEntity result = paymentMapper.mapToEntity(null);
        assertNull(result);
    }
}
