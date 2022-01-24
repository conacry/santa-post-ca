package com.conacry.post.infrastructure.db.mapper;

import com.conacry.post.domain.entity.DeliveryOrder;
import com.conacry.post.domain.entity.DeliveryState;
import com.conacry.post.domain.entity.identifier.DeliveryOrderIdentifier;
import com.conacry.post.domain.value.Address;
import com.conacry.post.infrastructure.db.model.DeliveryOrderDbModel;
import org.springframework.stereotype.Component;

@Component
public class DeliveryOrderDbModelMapper {

    private final PackDbModelMapper packDbModelMapper;
    private final ElfDbModelMapper elfDbModelMapper;
    private final GiftDbModelMapper giftDbModelMapper;

    public DeliveryOrderDbModelMapper(PackDbModelMapper packDbModelMapper,
            ElfDbModelMapper elfDbModelMapper, GiftDbModelMapper giftDbModelMapper) {
        this.packDbModelMapper = packDbModelMapper;
        this.elfDbModelMapper = elfDbModelMapper;
        this.giftDbModelMapper = giftDbModelMapper;
    }

    public DeliveryOrderDbModel toModel(DeliveryOrder deliveryOrder) {
        var model = new DeliveryOrderDbModel();

        model.setId(deliveryOrder.getIdentifier().getValue());
        model.setAddress(deliveryOrder.getAddress().getValue());
        model.setState(deliveryOrder.getState().name());

        if (deliveryOrder.getPack() != null) {
            var packModel = packDbModelMapper.toModel(deliveryOrder.getPack());
            model.setPack(packModel);
        }

        var giftModel = giftDbModelMapper.toModel(deliveryOrder.getGift());
        model.setGift(giftModel);

        if (deliveryOrder.getElf() != null) {
            var elfModel = elfDbModelMapper.toModel(deliveryOrder.getElf());
            model.setElf(elfModel);
        }

        return model;
    }

    public DeliveryOrder toEntity(DeliveryOrderDbModel model) {
        var gift = giftDbModelMapper.toEntity(model.getGift());
        var pack = packDbModelMapper.toEntity(model.getPack());
        var elf = elfDbModelMapper.toEntity(model.getElf());
        var identifier = new DeliveryOrderIdentifier(model.getId());
        var address = Address.of(model.getAddress());
        var state = DeliveryState.valueOf(model.getState());

        return DeliveryOrder.builder()
                .identifier(identifier)
                .address(address)
                .state(state)
                .pack(pack)
                .gift(gift)
                .elf(elf)
                .build();
    }
}
