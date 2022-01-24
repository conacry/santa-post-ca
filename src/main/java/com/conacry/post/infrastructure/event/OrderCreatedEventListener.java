package com.conacry.post.infrastructure.event;

import com.conacry.post.application.usecase.AssignDeliveryOrder;
import com.conacry.post.domain.event.OrderCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedEventListener {

    private final AssignDeliveryOrder assignDeliveryOrder;

    public OrderCreatedEventListener(AssignDeliveryOrder assignDeliveryOrder) {
        this.assignDeliveryOrder = assignDeliveryOrder;
    }

    @EventListener(classes = { OrderCreatedEvent.class })
    public void handleEvent(OrderCreatedEvent orderCreatedEvent) {
        assignDeliveryOrder.execute(orderCreatedEvent.getOrderIdentifier());
    }
}
