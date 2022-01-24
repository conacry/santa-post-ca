package com.conacry.post.infrastructure.db.mapper;

import com.conacry.post.domain.value.Pack;
import com.conacry.post.domain.value.PackCode;
import com.conacry.post.domain.value.PackSize;
import com.conacry.post.domain.value.PackType;
import com.conacry.post.infrastructure.db.model.PackDbModel;
import org.springframework.stereotype.Component;

@Component
public class PackDbModelMapper {

    public PackDbModel toModel(Pack pack) {
        var model = new PackDbModel();

        model.setId(pack.getCode().getValue());
        model.setSize(pack.getSize().name());
        model.setType(pack.getType().name());

        return model;
    }

    public Pack toEntity(PackDbModel model) {
        var size = PackSize.valueOf(model.getSize());
        var type = PackType.valueOf(model.getType());
        var code = new PackCode(model.getId());

        return Pack.of(code, size, type);
    }
}
