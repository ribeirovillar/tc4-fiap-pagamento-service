package com.fiap.pagamento.gateway.database.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentEntity {

    @Id
    UUID id;
    UUID orderId;
    String customerName;
    String customerCpf;
    String cardNumber;
    BigDecimal paymentAmount;

}
