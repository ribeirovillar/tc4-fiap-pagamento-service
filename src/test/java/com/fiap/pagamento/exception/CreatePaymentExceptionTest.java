package com.fiap.pagamento.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatePaymentExceptionTest {

    @Test
    void testConstructorWithMessage() {
        String message = "Payment creation failed";
        CreatePaymentException exception = new CreatePaymentException(message);

        assertEquals(message, exception.getMessage());
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void testConstructorWithNullMessage() {
        CreatePaymentException exception = new CreatePaymentException(null);
        assertNull(exception.getMessage());
    }

    @Test
    void testConstructorWithEmptyMessage() {
        String message = "";
        CreatePaymentException exception = new CreatePaymentException(message);
        assertEquals(message, exception.getMessage());
    }
}
