package com.conacry.post.domain.event;

import com.conacry.post.domain.entity.identifier.DeliveryOrderIdentifier;
import lombok.Getter;

@Getter
public final class OrderCreatedEvent implements DomainEvent {

    private final DeliveryOrderIdentifier orderIdentifier;

    public OrderCreatedEvent(DeliveryOrderIdentifier orderIdentifier) {
        this.orderIdentifier = orderIdentifier;
    }
}
