package com.conacry.snowone.application.usecase.impl;

import com.conacry.snowone.application.port.DeliveryOrderGateway;
import com.conacry.snowone.application.port.EventPublisher;
import com.conacry.snowone.application.usecase.CompleteDelivery;
import com.conacry.snowone.domain.entity.identifier.DeliveryOrderIdentifier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompleteDeliveryImpl implements CompleteDelivery {

    private final DeliveryOrderGateway deliveryOrderGateway;
    private final EventPublisher eventPublisher;

    public CompleteDeliveryImpl(DeliveryOrderGateway deliveryOrderGateway,
            EventPublisher eventPublisher) {
        this.deliveryOrderGateway = deliveryOrderGateway;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void execute(DeliveryOrderIdentifier identifier) {
        var deliveryOrderOpt = deliveryOrderGateway.findByIdentifier(identifier);

        if (deliveryOrderOpt.isEmpty()) {
            log.warn("Delivery order not found with identifier: {}", identifier);
            return;
        }

        var result = deliveryOrderOpt.get().complete();

        deliveryOrderGateway.save(result.getValue());
        eventPublisher.publish(result.getEvent());
    }
}
