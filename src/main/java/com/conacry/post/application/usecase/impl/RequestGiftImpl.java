package com.conacry.post.application.usecase.impl;


import com.conacry.post.application.port.ChildInfoGateway;
import com.conacry.post.application.port.EventPublisher;
import com.conacry.post.application.port.GiftGateway;
import com.conacry.post.application.model.GiftRequestData;
import com.conacry.post.application.usecase.RequestGift;
import com.conacry.post.domain.entity.Gift;
import com.conacry.post.domain.entity.identifier.GiftIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;

@Slf4j
@Service
public class RequestGiftImpl implements RequestGift {

    private final ChildInfoGateway childInfoGateway;
    private final GiftIdGenerator idGenerator;
    private final GiftGateway giftGateway;
    private final EventPublisher eventPublisher;

    public RequestGiftImpl(
            ChildInfoGateway childInfoGateway,
            GiftIdGenerator idGenerator,
            GiftGateway giftGateway,
            EventPublisher eventPublisher
    ) {
        this.childInfoGateway = childInfoGateway;
        this.idGenerator = idGenerator;
        this.giftGateway = giftGateway;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void execute(@Nullable GiftRequestData requestData) {

        if (requestData == null) {
            throw new IllegalArgumentException();
        }

        var childOpt = childInfoGateway.findByName(requestData.getChildName());

        if (childOpt.isEmpty()) {
            log.warn("It isn't a child!!!. Name: {}", requestData.getChildName());
            return;
        }

        var result = Gift
                .create(childOpt.get(), requestData.getGiftSize(), idGenerator);

        giftGateway.save(result.getValue());
        eventPublisher.publish(result.getEvent());
    }
}
