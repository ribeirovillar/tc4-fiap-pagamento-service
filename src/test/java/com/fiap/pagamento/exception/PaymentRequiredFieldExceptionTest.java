package com.fiap.pagamento.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRequiredFieldExceptionTest {

    @Test
    void testConstructorWithMessage() {
        String message = "Required field missing";
        PaymentRequiredFieldException exception = new PaymentRequiredFieldException(message);

        assertEquals(message, exception.getMessage());
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void testConstructorWithNullMessage() {
        PaymentRequiredFieldException exception = new PaymentRequiredFieldException(null);
        assertNull(exception.getMessage());
    }

    @Test
    void testConstructorWithEmptyMessage() {
        String message = "";
        PaymentRequiredFieldException exception = new PaymentRequiredFieldException(message);
        assertEquals(message, exception.getMessage());
    }
}
