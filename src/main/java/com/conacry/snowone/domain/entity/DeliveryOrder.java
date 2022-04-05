package com.conacry.snowone.domain.entity;

import com.conacry.snowone.domain.entity.identifier.DeliveryOrderIdGenerator;
import com.conacry.snowone.domain.entity.identifier.DeliveryOrderIdentifier;
import com.conacry.snowone.domain.event.OrderAssignedEvent;
import com.conacry.snowone.domain.event.OrderCompletedEvent;
import com.conacry.snowone.domain.event.OrderCreatedEvent;
import com.conacry.snowone.domain.event.OrderDeliveringEvent;
import com.conacry.snowone.domain.value.Address;
import com.conacry.snowone.domain.value.Elf;
import com.conacry.snowone.shared.ResultWithEvent;
import lombok.Builder;
import lombok.Getter;

import javax.annotation.Nonnull;
import java.util.Objects;

@Getter
@Builder
public class DeliveryOrder {

    private final DeliveryOrderIdentifier identifier;
    private final Pack pack;
    private final Gift gift;
    private final Address address;
    private DeliveryState state;
    private Elf elf;

    public static ResultWithEvent<DeliveryOrder, OrderCreatedEvent> create(
            @Nonnull DeliveryOrderIdGenerator idGenerator,
            @Nonnull Pack pack,
            @Nonnull Gift gift
    ) {
        Objects.requireNonNull(idGenerator);
        Objects.requireNonNull(pack);
        Objects.requireNonNull(gift);

        var address = gift.getChild().getAddress();
        var deliveryOrder = new DeliveryOrder(idGenerator.generate(), pack, gift, address, DeliveryState.CREATED);

        return ResultWithEvent.of(deliveryOrder, new OrderCreatedEvent(deliveryOrder.identifier));
    }

    private DeliveryOrder(
            DeliveryOrderIdentifier identifier,
            Pack pack,
            Gift gift,
            Address address,
            DeliveryState state
    ) {
        this.identifier = identifier;
        this.pack = pack;
        this.gift = gift;
        this.address = address;
        this.state = state;
    }

    private DeliveryOrder(
            DeliveryOrderIdentifier identifier,
            Pack pack,
            Gift gift,
            Address address,
            DeliveryState state,
            Elf elf
    ) {
        this.identifier = identifier;
        this.pack = pack;
        this.gift = gift;
        this.address = address;
        this.state = state;
        this.elf = elf;
    }

    public ResultWithEvent<DeliveryOrder, OrderAssignedEvent> assign(@Nonnull Elf elf) {
        Objects.requireNonNull(elf);

        this.elf = elf;
        this.state = DeliveryState.ASSIGNED;

        return ResultWithEvent.of(this, new OrderAssignedEvent());
    }

    public ResultWithEvent<DeliveryOrder, OrderDeliveringEvent> makeDelivery() {
        this.state = DeliveryState.DELIVERING;

        return ResultWithEvent.of(this, new OrderDeliveringEvent());
    }

    public ResultWithEvent<DeliveryOrder, OrderCompletedEvent> complete() {
        this.state = DeliveryState.COMPLETED;

        return ResultWithEvent.of(this, new OrderCompletedEvent());
    }
}
