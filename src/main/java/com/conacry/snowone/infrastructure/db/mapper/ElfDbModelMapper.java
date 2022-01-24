package com.conacry.snowone.infrastructure.db.mapper;

import com.conacry.snowone.domain.value.Address;
import com.conacry.snowone.domain.value.Elf;
import com.conacry.snowone.infrastructure.db.model.ElfDbModel;
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
