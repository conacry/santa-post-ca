package com.conacry.snowone.application.model;

import com.conacry.snowone.domain.entity.PackSize;
import com.conacry.snowone.domain.value.Address;
import lombok.Data;

@Data
public final class DeliveryOrderInfo {

    private Address address;
    private PackSize size;
}
