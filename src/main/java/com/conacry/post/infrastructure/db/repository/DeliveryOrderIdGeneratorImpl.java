package com.conacry.post.infrastructure.db.repository;

import com.conacry.post.domain.entity.identifier.DeliveryOrderIdGenerator;
import com.conacry.post.domain.entity.identifier.DeliveryOrderIdentifier;
import com.conacry.post.infrastructure.db.dao.DeliveryOrderDao;
import org.springframework.stereotype.Component;

@Component
public class DeliveryOrderIdGeneratorImpl implements DeliveryOrderIdGenerator {

    public final DeliveryOrderDao deliveryOrderDao;

    public DeliveryOrderIdGeneratorImpl(DeliveryOrderDao deliveryOrderDao) {
        this.deliveryOrderDao = deliveryOrderDao;
    }

    @Override
    public DeliveryOrderIdentifier generate() {
        var next = deliveryOrderDao.next();
        return new DeliveryOrderIdentifier(next);
    }
}
