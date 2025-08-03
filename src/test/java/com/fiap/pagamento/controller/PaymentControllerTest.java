package com.fiap.pagamento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.pagamento.controller.json.PaymentDTO;
import com.fiap.pagamento.domain.Payment;
import com.fiap.pagamento.mapper.PaymentMapper;
import com.fiap.pagamento.usecase.CreatePaymentUseCase;
import com.fiap.pagamento.usecase.RetrieveAllPaymentsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentMapper mapper;

    @MockBean
    private RetrieveAllPaymentsUseCase retrieveAllPaymentsUseCase;


    private PaymentDTO paymentDTO;
    private Payment payment;

    @BeforeEach
    void setUp() {
        UUID id = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();

        paymentDTO = new PaymentDTO();
        paymentDTO.setId(id);
        paymentDTO.setOrderId(orderId);
        paymentDTO.setCustomerName("John Doe");
        paymentDTO.setCustomerCpf("12345678901");
        paymentDTO.setCardNumber("1234567890123456");
        paymentDTO.setPaymentAmount(new BigDecimal("100.50"));

        payment = new Payment();
        payment.setId(id);
        payment.setOrderId(orderId);
        payment.setCustomerName("John Doe");
        payment.setCustomerCpf("12345678901");
        payment.setCardNumber("1234567890123456");
        payment.setPaymentAmount(new BigDecimal("100.50"));
    }

    @Test
    void shouldRetrieveAllPaymentsSuccessfully() throws Exception {
        List<Payment> payments = Arrays.asList(payment);

        when(retrieveAllPaymentsUseCase.execute()).thenReturn(payments);
        when(mapper.mapToDto(any(Payment.class))).thenReturn(paymentDTO);

        mockMvc.perform(get("/payments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(paymentDTO.getId().toString()))
                .andExpect(jsonPath("$[0].orderId").value(paymentDTO.getOrderId().toString()))
                .andExpect(jsonPath("$[0].customerName").value(paymentDTO.getCustomerName()));

        verify(retrieveAllPaymentsUseCase).execute();
        verify(mapper).mapToDto(any(Payment.class));
    }

    @Test
    void shouldReturnEmptyListWhenNoPayments() throws Exception {
        when(retrieveAllPaymentsUseCase.execute()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/payments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));

        verify(retrieveAllPaymentsUseCase).execute();
    }
}
