package com.fiap.pagamento.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {

    private UUID id;
    private UUID orderId;
    private String customerName;
    private String customerCpf;
    private String cardNumber;
    private BigDecimal paymentAmount;

}
