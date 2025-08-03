package com.fiap.pagamento.usecase;

import com.fiap.pagamento.domain.Payment;
import com.fiap.pagamento.exception.CreatePaymentException;
import com.fiap.pagamento.gateway.PaymentGateway;
import com.fiap.pagamento.usecase.validation.CreatePaymentStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatePaymentUseCaseTest {

    @Mock
    private PaymentGateway gateway;

    @Mock
    private RequestPaymentUseCase requestPaymentUseCase;

    @Mock
    private CreatePaymentStrategy validationStrategy1;

    @Mock
    private CreatePaymentStrategy validationStrategy2;

    private CreatePaymentUseCase createPaymentUseCase;

    private Payment payment;

    @BeforeEach
    void setUp() {
        List<CreatePaymentStrategy> validations = Arrays.asList(validationStrategy1, validationStrategy2);
        createPaymentUseCase = new CreatePaymentUseCase(gateway, requestPaymentUseCase, validations);

        payment = new Payment();
        payment.setId(UUID.randomUUID());
        payment.setOrderId(UUID.randomUUID());
        payment.setCustomerName("John Doe");
        payment.setCustomerCpf("12345678901");
        payment.setCardNumber("1234567890123456");
        payment.setPaymentAmount(new BigDecimal("100.50"));
    }

    @Test
    void shouldCreatePaymentSuccessfully() {
        UUID requestId = UUID.randomUUID();
        Payment savedPayment = new Payment();
        savedPayment.setId(requestId);
        savedPayment.setOrderId(payment.getOrderId());
        savedPayment.setCustomerName(payment.getCustomerName());
        savedPayment.setCustomerCpf(payment.getCustomerCpf());
        savedPayment.setCardNumber(payment.getCardNumber());
        savedPayment.setPaymentAmount(payment.getPaymentAmount());

        doNothing().when(validationStrategy1).validate(payment);
        doNothing().when(validationStrategy2).validate(payment);
        when(requestPaymentUseCase.execute(payment)).thenReturn(requestId);
        when(gateway.save(any(Payment.class))).thenReturn(Optional.of(savedPayment));

        Payment result = createPaymentUseCase.execute(payment);

        assertNotNull(result);
        assertEquals(requestId, result.getId());
        assertEquals(payment.getOrderId(), result.getOrderId());
        assertEquals(payment.getCustomerName(), result.getCustomerName());
        verify(validationStrategy1).validate(payment);
        verify(validationStrategy2).validate(payment);
        verify(requestPaymentUseCase).execute(payment);
        verify(gateway).save(any(Payment.class));
    }

    @Test
    void shouldThrowCreatePaymentExceptionWhenGatewaySaveFails() {
        UUID requestId = UUID.randomUUID();

        doNothing().when(validationStrategy1).validate(payment);
        doNothing().when(validationStrategy2).validate(payment);
        when(requestPaymentUseCase.execute(payment)).thenReturn(requestId);
        when(gateway.save(any(Payment.class))).thenReturn(Optional.empty());

        CreatePaymentException exception = assertThrows(CreatePaymentException.class,
            () -> createPaymentUseCase.execute(payment));

        assertEquals("Payment could not be saved", exception.getMessage());
        verify(validationStrategy1).validate(payment);
        verify(validationStrategy2).validate(payment);
        verify(requestPaymentUseCase).execute(payment);
        verify(gateway).save(any(Payment.class));
    }

    @Test
    void shouldSetPaymentIdFromRequestPaymentUseCase() {
        UUID requestId = UUID.randomUUID();
        Payment savedPayment = new Payment();
        savedPayment.setId(requestId);

        doNothing().when(validationStrategy1).validate(payment);
        doNothing().when(validationStrategy2).validate(payment);
        when(requestPaymentUseCase.execute(payment)).thenReturn(requestId);
        when(gateway.save(any(Payment.class))).thenReturn(Optional.of(savedPayment));

        createPaymentUseCase.execute(payment);

        assertEquals(requestId, payment.getId());
        verify(validationStrategy1).validate(payment);
        verify(validationStrategy2).validate(payment);
    }

    @Test
    void shouldExecuteAllValidationStrategies() {
        UUID requestId = UUID.randomUUID();
        Payment savedPayment = new Payment();
        savedPayment.setId(requestId);

        doNothing().when(validationStrategy1).validate(payment);
        doNothing().when(validationStrategy2).validate(payment);
        when(requestPaymentUseCase.execute(payment)).thenReturn(requestId);
        when(gateway.save(any(Payment.class))).thenReturn(Optional.of(savedPayment));

        createPaymentUseCase.execute(payment);

        verify(validationStrategy1, times(1)).validate(payment);
        verify(validationStrategy2, times(1)).validate(payment);
    }
}
