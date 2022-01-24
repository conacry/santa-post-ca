package com.conacry.snowone.application.usecase;

import com.conacry.snowone.domain.entity.identifier.GiftIdentifier;

public interface CreateDeliveryOrder {

    void execute(GiftIdentifier giftIdentifier);
}
