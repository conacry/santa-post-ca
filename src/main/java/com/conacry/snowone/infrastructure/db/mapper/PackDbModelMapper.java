package com.conacry.snowone.infrastructure.db.mapper;

import com.conacry.snowone.domain.entity.Pack;
import com.conacry.snowone.domain.entity.PackSize;
import com.conacry.snowone.domain.entity.PackType;
import com.conacry.snowone.domain.entity.identifier.PackCode;
import com.conacry.snowone.infrastructure.db.model.PackDbModel;
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
        var code = PackCode.of(model.getId());

        return Pack.of(code, size, type);
    }
}
