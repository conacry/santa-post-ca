package com.conacry.snowone.domain.entity;

import com.conacry.snowone.domain.entity.identifier.GiftIdGenerator;
import com.conacry.snowone.domain.entity.identifier.GiftIdentifier;
import com.conacry.snowone.domain.event.GiftCreatedEvent;
import com.conacry.snowone.domain.exception.GiftCreationException;
import com.conacry.snowone.domain.value.Behavior;
import com.conacry.snowone.domain.value.Child;
import com.conacry.snowone.shared.ResultWithEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.util.Objects;

@Slf4j
@Getter
public class Gift {

    private final GiftIdentifier identifier;
    private final Child child;
    private final GiftSize size;

    public static Gift of(@Nonnull GiftIdentifier identifier, @Nonnull Child child, @Nonnull GiftSize giftSize) {
        Objects.requireNonNull(identifier);
        Objects.requireNonNull(child);
        Objects.requireNonNull(giftSize);

        return new Gift(identifier, child, giftSize);
    }

    public static ResultWithEvent<Gift, GiftCreatedEvent> create(
            @Nonnull Child child,
            @Nonnull String requestedSize,
            @Nonnull GiftIdGenerator idGenerator
    ) {

        Objects.requireNonNull(child);
        Objects.requireNonNull(requestedSize);
        Objects.requireNonNull(idGenerator);

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
            log.warn("The child didn't behave well enough for a big gift");
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
