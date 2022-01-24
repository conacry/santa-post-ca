package com.conacry.post.infrastructure.web.mapper;

import com.conacry.post.application.model.DeliveryOrderInfo;
import com.conacry.post.infrastructure.web.dto.DeliveryOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeliveryOrderMapper {

    @Mapping(target = "address", source = "address.value")
    DeliveryOrderDto toDto(DeliveryOrderInfo deliveryOrderInfo);
}
