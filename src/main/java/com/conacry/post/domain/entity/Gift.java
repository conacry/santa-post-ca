package com.conacry.post.domain.entity;

import com.conacry.post.domain.entity.identifier.GiftIdGenerator;
import com.conacry.post.domain.entity.identifier.GiftIdentifier;
import com.conacry.post.domain.value.Behavior;
import com.conacry.post.domain.value.Child;
import com.conacry.post.domain.exception.GiftCreationException;
import com.conacry.post.domain.event.GiftCreatedEvent;
import com.conacry.post.shared.ResultWithEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Gift {

    private final GiftIdentifier identifier;
    private final Child child;
    private final GiftSize size;

    public static Gift of(GiftIdentifier identifier, Child child, GiftSize giftSize) {
        return new Gift(identifier, child, giftSize);
    }

    public static ResultWithEvent<Gift, GiftCreatedEvent> create(
            Child child,
            String requestedSize,
            GiftIdGenerator idGenerator
    ) {

        GiftSize size;

        try {
            size = GiftSize.valueOf(requestedSize);
        } catch (IllegalArgumentException ex) {
            log.error("There is no gift of this size: {}", requestedSize);
            throw new GiftCreationException();
        }

        var behavior = child.getBehavior();

        if ((behavior == Behavior.BAD) && (size != GiftSize.SMALL)) {
            log.warn("The child behaved badly, so he will get a small gift");
            size = GiftSize.SMALL;
        }

        if ((behavior == Behavior.GOOD) && (size == GiftSize.BIG)) {
            log.warn("The child did not behave well enough for a big gift");
            size = GiftSize.MEDIUM;
        }

        var gift = new Gift(idGenerator.generate(), child, size);

        return ResultWithEvent.of(gift, new GiftCreatedEvent(gift.identifier));
    }

    private Gift(GiftIdentifier identifier, Child child, GiftSize size) {
        this.identifier = identifier;
        this.child = child;
        this.size = size;
    }
}
