package com.fiap.pagamento.usecase;

import com.fiap.pagamento.domain.Payment;
import com.fiap.pagamento.gateway.PaymentGateway;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetrieveAllPaymentsUseCase {

    private final PaymentGateway gateway;

    public RetrieveAllPaymentsUseCase(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public List<Payment> execute() {
        return gateway.findAll();
    }
}
