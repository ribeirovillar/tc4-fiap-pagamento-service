package com.fiap.pagamento.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentNotFoundExceptionTest {

    @Test
    void testConstructorWithMessage() {
        String message = "Payment not found";
        PaymentNotFoundException exception = new PaymentNotFoundException(message);

        assertEquals(message, exception.getMessage());
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void testConstructorWithNullMessage() {
        PaymentNotFoundException exception = new PaymentNotFoundException(null);
        assertNull(exception.getMessage());
    }

    @Test
    void testConstructorWithEmptyMessage() {
        String message = "";
        PaymentNotFoundException exception = new PaymentNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }
}
