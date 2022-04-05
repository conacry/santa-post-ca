package com.conacry.snowone.infrastructure.web.mapper;

import com.conacry.snowone.application.model.DeliveryOrderInfo;
import com.conacry.snowone.infrastructure.web.dto.DeliveryOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeliveryOrderMapper {

    @Mapping(target = "address", source = "address.value")
    DeliveryOrderDto toDto(DeliveryOrderInfo deliveryOrderInfo);
}
