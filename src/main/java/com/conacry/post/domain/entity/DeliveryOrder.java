package com.conacry.post.domain.entity;

import com.conacry.post.domain.entity.identifier.DeliveryOrderIdGenerator;
import com.conacry.post.domain.entity.identifier.DeliveryOrderIdentifier;
import com.conacry.post.domain.event.OrderCompletedEvent;
import com.conacry.post.domain.event.OrderDelivering;
import com.conacry.post.domain.value.Address;
import com.conacry.post.domain.value.Elf;
import com.conacry.post.domain.value.Pack;
import com.conacry.post.domain.event.OrderAssignedEvent;
import com.conacry.post.domain.event.OrderCreatedEvent;
import com.conacry.post.shared.ResultWithEvent;
import lombok.Builder;
import lombok.Getter;

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
            DeliveryOrderIdGenerator idGenerator,
            Pack pack,
            Gift gift
    ) {
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

    public ResultWithEvent<DeliveryOrder, OrderAssignedEvent> assign(Elf elf) {
        this.elf = elf;
        this.state = DeliveryState.ASSIGNED;

        return ResultWithEvent.of(this, new OrderAssignedEvent());
    }

    public ResultWithEvent<DeliveryOrder, OrderDelivering> makeDelivery() {
        this.state = DeliveryState.DELIVERING;

        return ResultWithEvent.of(this, new OrderDelivering());
    }

    public ResultWithEvent<DeliveryOrder, OrderCompletedEvent> complete() {
        this.state = DeliveryState.COMPLETED;

        return ResultWithEvent.of(this, new OrderCompletedEvent());
    }
}
