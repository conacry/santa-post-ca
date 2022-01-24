package com.conacry.post.application.usecase;

import com.conacry.post.domain.entity.identifier.GiftIdentifier;

public interface CreateDeliveryOrder {

    void execute(GiftIdentifier giftIdentifier);
}
