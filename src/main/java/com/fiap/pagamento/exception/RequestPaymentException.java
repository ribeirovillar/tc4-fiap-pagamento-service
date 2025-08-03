package com.fiap.pagamento.exception;

public class RequestPaymentException extends RuntimeException {
    public RequestPaymentException(String message) {
        super(message);
    }
}
