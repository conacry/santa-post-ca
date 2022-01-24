package com.conacry.post.application.usecase;

import com.conacry.post.domain.entity.identifier.DeliveryOrderIdentifier;

public interface AssignDeliveryOrder {

    void execute(DeliveryOrderIdentifier deliveryOrderIdentifier);
}
