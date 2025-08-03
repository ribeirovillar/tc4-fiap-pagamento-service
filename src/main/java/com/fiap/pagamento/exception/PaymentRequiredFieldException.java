package com.fiap.pagamento.exception;

public class PaymentRequiredFieldException extends RuntimeException {
    public PaymentRequiredFieldException(String message) {
        super(message);
    }
}
