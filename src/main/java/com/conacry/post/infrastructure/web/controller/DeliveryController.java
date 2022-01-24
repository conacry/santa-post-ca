package com.conacry.post.infrastructure.web.controller;

import com.conacry.post.application.usecase.CompleteDelivery;
import com.conacry.post.application.usecase.MakeDelivery;
import com.conacry.post.domain.entity.identifier.DeliveryOrderIdentifier;
import com.conacry.post.infrastructure.web.dto.DeliveryOrderDto;
import com.conacry.post.infrastructure.web.dto.ElfDataDto;
import com.conacry.post.infrastructure.web.mapper.DeliveryOrderMapper;
import com.conacry.post.infrastructure.web.mapper.ElfDataDtoMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/web/v1/")
public class DeliveryController {

    private final MakeDelivery makeDelivery;
    private final CompleteDelivery completeDelivery;
    private final DeliveryOrderMapper deliveryOrderMapper;
    private final ElfDataDtoMapper elfDataDtoMapper;

    public DeliveryController(MakeDelivery makeDelivery,
            CompleteDelivery completeDelivery,
            DeliveryOrderMapper deliveryOrderMapper,
            ElfDataDtoMapper elfDataDtoMapper
    ) {
        this.makeDelivery = makeDelivery;
        this.completeDelivery = completeDelivery;
        this.deliveryOrderMapper = deliveryOrderMapper;
        this.elfDataDtoMapper = elfDataDtoMapper;
    }

    @PostMapping(path = "getOrdersToDelivery")
    public List<DeliveryOrderDto> getOrdersToDelivery(@RequestBody ElfDataDto elfDataDto) {
        var elfInfo = elfDataDtoMapper.fromDto(elfDataDto);
        var orders = makeDelivery.execute(elfInfo);

        return orders.stream()
                .map(deliveryOrderMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "completeDelivery")
    public void completeDelivery(@RequestParam(name = "deliveryId") Integer deliveryIdentifier) {
        completeDelivery.execute(new DeliveryOrderIdentifier(deliveryIdentifier));
    }
}
