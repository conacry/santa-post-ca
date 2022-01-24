package com.conacry.snowone.infrastructure.web.mapper;

import com.conacry.snowone.application.model.ElfInfo;
import com.conacry.snowone.infrastructure.web.dto.ElfDataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ElfDataDtoMapper {

    ElfInfo fromDto(ElfDataDto elfDataDto);
}
