package com.fiap.pagamento.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestPaymentExceptionTest {

    @Test
    void testConstructorWithMessage() {
        String message = "External service unavailable";
        RequestPaymentException exception = new RequestPaymentException(message);

        assertEquals(message, exception.getMessage());
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void testConstructorWithNullMessage() {
        RequestPaymentException exception = new RequestPaymentException(null);
        assertNull(exception.getMessage());
    }

    @Test
    void testConstructorWithEmptyMessage() {
        String message = "";
        RequestPaymentException exception = new RequestPaymentException(message);
        assertEquals(message, exception.getMessage());
    }
}
