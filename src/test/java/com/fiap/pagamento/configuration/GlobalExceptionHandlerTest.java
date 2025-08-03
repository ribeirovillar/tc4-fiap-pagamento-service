package com.fiap.pagamento.configuration;

import com.fiap.pagamento.exception.PaymentNotFoundException;
import com.fiap.pagamento.exception.PaymentRequiredFieldException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void shouldHandlePaymentNotFoundException() {
        String message = "Payment not found";
        PaymentNotFoundException exception = new PaymentNotFoundException(message);

        ResponseEntity<ProblemDetail> response = globalExceptionHandler.handlePaymentNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Not Found", response.getBody().getTitle());
        assertEquals(message, response.getBody().getDetail());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatus());
    }

    @Test
    void shouldHandlePaymentRequiredFieldException() {
        String message = "Required field missing";
        PaymentRequiredFieldException exception = new PaymentRequiredFieldException(message);

        ResponseEntity<ProblemDetail> response = globalExceptionHandler.handlePaymentRequiredFieldException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Bad Request", response.getBody().getTitle());
        assertEquals(message, response.getBody().getDetail());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
    }

    @Test
    void shouldHandleRuntimeException() {
        String message = "Runtime error occurred";
        RuntimeException exception = new RuntimeException(message);

        ResponseEntity<ProblemDetail> response = globalExceptionHandler.handleRuntimeException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Internal Server Error", response.getBody().getTitle());
        assertEquals("An unexpected error occurred while processing your request", response.getBody().getDetail());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().getStatus());
    }

    @Test
    void shouldHandleGenericException() {
        String message = "Generic error occurred";
        Exception exception = new Exception(message);

        ResponseEntity<ProblemDetail> response = globalExceptionHandler.handleGenericException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Unexpected Error", response.getBody().getTitle());
        assertEquals("An unexpected error occurred while processing your request", response.getBody().getDetail());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().getStatus());
    }

    @Test
    void shouldHandlePaymentNotFoundExceptionWithNullMessage() {
        PaymentNotFoundException exception = new PaymentNotFoundException(null);

        ResponseEntity<ProblemDetail> response = globalExceptionHandler.handlePaymentNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Not Found", response.getBody().getTitle());
        assertNull(response.getBody().getDetail());
    }

    @Test
    void shouldHandlePaymentRequiredFieldExceptionWithEmptyMessage() {
        String message = "";
        PaymentRequiredFieldException exception = new PaymentRequiredFieldException(message);

        ResponseEntity<ProblemDetail> response = globalExceptionHandler.handlePaymentRequiredFieldException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Bad Request", response.getBody().getTitle());
        assertEquals(message, response.getBody().getDetail());
    }
}
