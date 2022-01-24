package com.conacry.snowone.application.usecase.impl;

import com.conacry.snowone.application.port.DeliveryOrderGateway;
import com.conacry.snowone.application.port.ElfInfoGateway;
import com.conacry.snowone.application.port.EventPublisher;
import com.conacry.snowone.application.usecase.AssignDeliveryOrder;
import com.conacry.snowone.domain.entity.identifier.DeliveryOrderIdentifier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AssignDeliveryOrderImpl implements AssignDeliveryOrder {

    private final DeliveryOrderGateway deliveryOrderGateway;
    private final ElfInfoGateway elfInfoGateway;
    private final EventPublisher eventPublisher;

    public AssignDeliveryOrderImpl(
            DeliveryOrderGateway deliveryOrderGateway,
            ElfInfoGateway elfInfoGateway,
            EventPublisher eventPublisher
    ) {
        this.deliveryOrderGateway = deliveryOrderGateway;
        this.elfInfoGateway = elfInfoGateway;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void execute(DeliveryOrderIdentifier identifier) {
        var deliveryOrderOpt = deliveryOrderGateway.findByIdentifier(identifier);

        if (deliveryOrderOpt.isEmpty()) {
            log.warn("Delivery order not found with identifier: {}", identifier);
            return;
        }

        var deliveryOrder = deliveryOrderOpt.get();

        var elfOpt = elfInfoGateway.findByAddress(deliveryOrder.getAddress());

        if (elfOpt.isEmpty()){
            log.warn("Cannot be assign a courier to this address: {}", deliveryOrder.getAddress());
            return;
        }

        var result = deliveryOrder.assign(elfOpt.get());

        deliveryOrderGateway.save(result.getValue());
        eventPublisher.publish(result.getEvent());
    }
}
