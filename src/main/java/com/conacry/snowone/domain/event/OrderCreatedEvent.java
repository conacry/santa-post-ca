package com.conacry.snowone.domain.event;

import com.conacry.snowone.domain.entity.identifier.DeliveryOrderIdentifier;
import com.conacry.snowone.shared.mark.DomainEvent;
import lombok.Getter;

import javax.annotation.Nonnull;

@Getter(onMethod_ = { @Nonnull })
public final class OrderCreatedEvent implements DomainEvent {

    private final DeliveryOrderIdentifier orderIdentifier;

    public OrderCreatedEvent(DeliveryOrderIdentifier orderIdentifier) {
        this.orderIdentifier = orderIdentifier;
    }
}
