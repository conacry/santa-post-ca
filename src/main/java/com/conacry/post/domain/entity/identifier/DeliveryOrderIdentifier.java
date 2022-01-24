package com.conacry.post.domain.entity.identifier;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class DeliveryOrderIdentifier {

    private final Integer value;

    public DeliveryOrderIdentifier(Integer value) {
        this.value = value;
    }
}
