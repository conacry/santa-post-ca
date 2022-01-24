package com.conacry.snowone.infrastructure.event;

import com.conacry.snowone.application.usecase.AssignDeliveryOrder;
import com.conacry.snowone.domain.event.OrderCreatedEvent;
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
