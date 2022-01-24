package com.conacry.snowone.application.usecase;

import com.conacry.snowone.domain.entity.identifier.DeliveryOrderIdentifier;

public interface CompleteDelivery {

    void execute(DeliveryOrderIdentifier identifier);
}

