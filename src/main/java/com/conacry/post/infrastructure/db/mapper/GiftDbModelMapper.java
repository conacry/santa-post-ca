package com.conacry.post.infrastructure.db.mapper;

import com.conacry.post.domain.entity.Gift;
import com.conacry.post.domain.entity.GiftSize;
import com.conacry.post.domain.entity.identifier.GiftIdentifier;
import com.conacry.post.infrastructure.db.mapper.ChildDbModelMapper;
import com.conacry.post.infrastructure.db.model.GiftDbModel;
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
        var identifier = new GiftIdentifier(model.getId());
        var giftSize = GiftSize.valueOf(model.getSize());

        return Gift.of(identifier, child, giftSize);
    }
}
