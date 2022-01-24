package com.conacry.snowone.domain.entity.identifier;

import lombok.Getter;

@Getter
public final class DeliveryOrderIdentifier {

    private final Integer value;

    public DeliveryOrderIdentifier(Integer value) {
        this.value = value;
    }
}
