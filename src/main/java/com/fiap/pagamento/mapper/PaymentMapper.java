package com.fiap.pagamento.mapper;

import com.fiap.pagamento.controller.json.PaymentDTO;
import com.fiap.pagamento.domain.Payment;
import com.fiap.pagamento.gateway.database.jpa.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentDTO mapToDto(Payment payment);

    @Mapping(target = "id", ignore = true)
    Payment map(PaymentDTO paymentDTO);

    Payment map(PaymentEntity paymentEntity);

    PaymentEntity mapToEntity(Payment payment);
}
