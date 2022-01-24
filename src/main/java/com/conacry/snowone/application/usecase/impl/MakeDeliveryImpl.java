package com.conacry.snowone.application.usecase.impl;

import com.conacry.snowone.application.exception.NotFoundException;
import com.conacry.snowone.application.model.DeliveryOrderInfo;
import com.conacry.snowone.application.model.ElfInfo;
import com.conacry.snowone.application.port.DeliveryOrderGateway;
import com.conacry.snowone.application.port.ElfInfoGateway;
import com.conacry.snowone.application.port.EventPublisher;
import com.conacry.snowone.application.usecase.MakeDelivery;
import com.conacry.snowone.domain.entity.DeliveryOrder;
import com.conacry.snowone.domain.entity.DeliveryState;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class MakeDeliveryImpl implements MakeDelivery {

    private final DeliveryOrderGateway deliveryOrderGateway;
    private final ElfInfoGateway elfInfoGateway;
    private final EventPublisher eventPublisher;

    public MakeDeliveryImpl(
            DeliveryOrderGateway deliveryOrderGateway,
            ElfInfoGateway elfInfoGateway,
            EventPublisher eventPublisher) {
        this.deliveryOrderGateway = deliveryOrderGateway;
        this.elfInfoGateway = elfInfoGateway;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<DeliveryOrderInfo> execute(@Nullable ElfInfo elfInfo) {

        if (elfInfo == null) {
            return Collections.emptyList();
        }

        var elfOpt = elfInfoGateway.findByName(elfInfo.getName());

        if (elfOpt.isEmpty()){
            log.warn("Cannot be find a courier with name: {}", elfInfo.getName());
            throw new NotFoundException();
        }

        var orders = deliveryOrderGateway.findByElfAndState(elfOpt.get(), DeliveryState.ASSIGNED, elfInfo.getLimit());

        return orders.stream().map(this::doUseCase).collect(Collectors.toList());
    }

    private DeliveryOrderInfo doUseCase(DeliveryOrder deliveryOrder) {
        var result = deliveryOrder.makeDelivery();
        deliveryOrderGateway.save(result.getValue());
        eventPublisher.publish(result.getEvent());

        var orderInfo = new DeliveryOrderInfo();
        orderInfo.setAddress(deliveryOrder.getAddress());
        orderInfo.setSize(deliveryOrder.getPack().getSize());

        return orderInfo;
    }
}
