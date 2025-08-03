package com.fiap.pagamento.controller;

import com.fiap.pagamento.controller.json.PaymentDTO;
import com.fiap.pagamento.mapper.PaymentMapper;
import com.fiap.pagamento.usecase.CreatePaymentUseCase;
import com.fiap.pagamento.usecase.RetrieveAllPaymentsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class PaymentController {

    PaymentMapper mapper;
    CreatePaymentUseCase createPaymentUseCase;
    RetrieveAllPaymentsUseCase retrieveAllPaymentsUseCase;

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.mapToDto(createPaymentUseCase.execute(mapper.map(paymentDTO))));
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> retrieveAllPayments() {
        List<PaymentDTO> payments = retrieveAllPaymentsUseCase.execute()
                .stream()
                .map(mapper::mapToDto)
                .toList();
        return ResponseEntity.ok(payments);
    }

}
