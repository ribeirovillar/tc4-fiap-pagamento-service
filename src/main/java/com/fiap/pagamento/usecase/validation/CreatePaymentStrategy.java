package com.fiap.pagamento.usecase.validation;

import com.fiap.pagamento.domain.Payment;

public interface CreatePaymentStrategy {

    void validate(Payment payment);

}
