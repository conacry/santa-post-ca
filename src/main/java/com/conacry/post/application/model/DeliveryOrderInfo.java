package com.conacry.post.application.model;

import com.conacry.post.domain.value.Address;
import com.conacry.post.domain.value.PackSize;
import lombok.Data;

@Data
public final class DeliveryOrderInfo {

    private Address address;
    private PackSize size;
}
