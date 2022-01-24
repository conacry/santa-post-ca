package com.conacry.post.domain.event;

import com.conacry.post.domain.entity.identifier.GiftIdentifier;
import lombok.Getter;

@Getter
public class GiftCreatedEvent implements DomainEvent {

    private final GiftIdentifier giftIdentifier;

    public GiftCreatedEvent(GiftIdentifier giftIdentifier) {
        this.giftIdentifier = giftIdentifier;
    }
}
