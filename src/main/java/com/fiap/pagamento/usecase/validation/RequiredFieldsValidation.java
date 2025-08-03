package com.fiap.pagamento.usecase.validation;

import com.fiap.pagamento.domain.Payment;
import com.fiap.pagamento.exception.PaymentRequiredFieldException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RequiredFieldsValidation implements CreatePaymentStrategy {
    @Override
    public void validate(Payment payment) {
        if (payment.getOrderId() == null) {
            throw new PaymentRequiredFieldException("Order ID is required");
        }
        if (payment.getCardNumber() == null || payment.getCardNumber().isEmpty()) {
            throw new PaymentRequiredFieldException("Card number is required");
        }
        if (payment.getPaymentAmount() == null || payment.getPaymentAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentRequiredFieldException("Amount must be greater than zero");
        }
        if (payment.getCustomerName() == null || payment.getCustomerName().isEmpty()) {
            throw new PaymentRequiredFieldException("Customer name is required");
        }
        if (payment.getCustomerCpf() == null || payment.getCustomerCpf().isEmpty()) {
            throw new PaymentRequiredFieldException("Customer CPF is required");
        }
    }
}
