package com.conacry.snowone.infrastructure.db.mapper;

import com.conacry.snowone.domain.entity.Gift;
import com.conacry.snowone.domain.entity.GiftSize;
import com.conacry.snowone.domain.entity.identifier.GiftIdentifier;
import com.conacry.snowone.infrastructure.db.model.GiftDbModel;
import org.springframework.stereotype.Component;

@Component
public class GiftDbModelMapper {

    private final ChildDbModelMapper childDbModelMapper;

    public GiftDbModelMapper(ChildDbModelMapper childDbModelMapper) {
        this.childDbModelMapper = childDbModelMapper;
    }

    public GiftDbModel toModel(Gift gift) {
        var model = new GiftDbModel();

        model.setSize(gift.getSize().name());
        model.setId(gift.getIdentifier().getValue());

        var childDbModel = childDbModelMapper.toModel(gift.getChild());
        model.setChildDbModel(childDbModel);

        return model;
    }

    public Gift toEntity(GiftDbModel model) {
        var child = childDbModelMapper.toEntity(model.getChildDbModel());
        var identifier = GiftIdentifier.of(model.getId());
        var giftSize = GiftSize.valueOf(model.getSize());

        return Gift.of(identifier, child, giftSize);
    }
}
