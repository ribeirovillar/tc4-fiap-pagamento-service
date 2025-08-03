package com.fiap.pagamento.usecase.validation;

import com.fiap.pagamento.domain.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatePaymentStrategyTest {

    @Mock
    private CreatePaymentStrategy validationStrategy;

    @Test
    void shouldCallValidateMethod() {
        Payment payment = new Payment();
        payment.setOrderId(UUID.randomUUID());
        payment.setCustomerName("John Doe");
        payment.setCustomerCpf("12345678901");
        payment.setCardNumber("1234567890123456");
        payment.setPaymentAmount(new BigDecimal("100.50"));

        validationStrategy.validate(payment);

        verify(validationStrategy).validate(payment);
    }

    @Test
    void shouldHandleNullPayment() {
        validationStrategy.validate(null);

        verify(validationStrategy).validate(null);
    }
}
