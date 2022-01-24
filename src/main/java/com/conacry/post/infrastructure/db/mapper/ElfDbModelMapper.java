package com.conacry.post.infrastructure.db.mapper;

import com.conacry.post.domain.value.Address;
import com.conacry.post.domain.value.Elf;
import com.conacry.post.infrastructure.db.model.ElfDbModel;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

@Component
public class ElfDbModelMapper {

    public Elf toEntity(@Nullable ElfDbModel model) {

        if (model == null) {
            return null;
        }

        var address = Address.of(model.getAddress());
        return Elf.of(model.getName(), address);
    }

    public ElfDbModel toModel(Elf elf) {
        var model = new ElfDbModel();

        model.setName(elf.getName());
        model.setAddress(elf.getAddress().getValue());

        return model;
    }
}
