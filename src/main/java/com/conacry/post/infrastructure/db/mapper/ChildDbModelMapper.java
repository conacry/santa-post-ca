package com.conacry.post.infrastructure.db.mapper;

import com.conacry.post.domain.value.Address;
import com.conacry.post.domain.value.Behavior;
import com.conacry.post.domain.value.Child;
import com.conacry.post.infrastructure.db.model.ChildDbModel;
import org.springframework.stereotype.Component;

@Component
public final class ChildDbModelMapper {

    public Child toEntity(ChildDbModel model) {
        var address = Address.of(model.getAddress());
        var behavior = Behavior.valueOf(model.getBehavior());
        return Child.of(model.getName(), address, behavior);
    }

    public ChildDbModel toModel(Child child) {
        var model = new ChildDbModel();

        model.setName(child.getName());
        model.setBehavior(child.getName());
        model.setAddress(child.getAddress().getValue());

        return model;
    }
}
