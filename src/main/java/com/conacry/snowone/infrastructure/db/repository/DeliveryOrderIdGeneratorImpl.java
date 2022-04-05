package com.conacry.snowone.infrastructure.db.repository;

import com.conacry.snowone.domain.entity.identifier.DeliveryOrderIdGenerator;
import com.conacry.snowone.domain.entity.identifier.DeliveryOrderIdentifier;
import com.conacry.snowone.infrastructure.db.dao.DeliveryOrderDao;
import org.springframework.stereotype.Repository;

@Repository
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
