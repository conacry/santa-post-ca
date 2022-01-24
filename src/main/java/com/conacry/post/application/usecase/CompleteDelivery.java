package com.conacry.post.application.usecase;

import com.conacry.post.domain.entity.identifier.DeliveryOrderIdentifier;

public interface CompleteDelivery {

    void execute(DeliveryOrderIdentifier identifier);
}
