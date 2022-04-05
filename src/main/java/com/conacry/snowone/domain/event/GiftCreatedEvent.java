package com.conacry.snowone.domain.event;

import com.conacry.snowone.domain.entity.identifier.GiftIdentifier;
import com.conacry.snowone.shared.mark.DomainEvent;
import lombok.Getter;

import javax.annotation.Nonnull;

@Getter(onMethod_ = { @Nonnull })
public class GiftCreatedEvent implements DomainEvent {

    private final GiftIdentifier giftIdentifier;

    public GiftCreatedEvent(GiftIdentifier giftIdentifier) {
        this.giftIdentifier = giftIdentifier;
    }
}
