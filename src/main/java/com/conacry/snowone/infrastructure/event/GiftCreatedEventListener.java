package com.conacry.snowone.infrastructure.event;

import com.conacry.snowone.application.usecase.CreateDeliveryOrder;
import com.conacry.snowone.domain.event.GiftCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GiftCreatedEventListener {

    private final CreateDeliveryOrder createDeliveryOrder;

    public GiftCreatedEventListener(CreateDeliveryOrder createDeliveryOrder) {
        this.createDeliveryOrder = createDeliveryOrder;
    }

    @EventListener(classes = { GiftCreatedEvent.class })
    public void handleEvent(GiftCreatedEvent giftCreatedEvent) {
        createDeliveryOrder.execute(giftCreatedEvent.getGiftIdentifier());
    }
}
