package com.conacry.post.application.usecase.impl;

import com.conacry.post.application.port.DeliveryOrderGateway;
import com.conacry.post.application.port.EventPublisher;
import com.conacry.post.application.port.GiftGateway;
import com.conacry.post.application.usecase.CreateDeliveryOrder;
import com.conacry.post.domain.entity.DeliveryOrder;
import com.conacry.post.domain.entity.identifier.DeliveryOrderIdGenerator;
import com.conacry.post.domain.value.Pack;
import com.conacry.post.domain.entity.identifier.GiftIdentifier;
import com.conacry.post.domain.value.PackCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;

@Slf4j
@Service
public class CreateDeliveryOrderImpl implements CreateDeliveryOrder {

    private final GiftGateway giftGateway;
    private final DeliveryOrderGateway deliveryOrderGateway;
    private final DeliveryOrderIdGenerator idGenerator;
    private final PackCodeGenerator packCodeGenerator;
    private final EventPublisher eventPublisher;

    public CreateDeliveryOrderImpl(
            GiftGateway giftGateway,
            DeliveryOrderGateway deliveryOrderGateway,
            DeliveryOrderIdGenerator idGenerator,
            PackCodeGenerator packCodeGenerator,
            EventPublisher eventPublisher
    ) {
        this.giftGateway = giftGateway;
        this.deliveryOrderGateway = deliveryOrderGateway;
        this.idGenerator = idGenerator;
        this.packCodeGenerator = packCodeGenerator;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void execute(@Nullable GiftIdentifier giftIdentifier) {
        if (giftIdentifier == null) {
            log.warn("The identifier of gift is null");
            return;
        }

        var gift = giftGateway.findByIdentifier(giftIdentifier);

        if (gift.isEmpty()) {
            log.warn("The gift don't exist");
            return;
        }

        var pack = Pack.pack(packCodeGenerator, gift.get());

        var result = DeliveryOrder.create(idGenerator, pack, gift.get());

        deliveryOrderGateway.save(result.getValue());
        eventPublisher.publish(result.getEvent());
    }
}
