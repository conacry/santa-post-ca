package com.conacry.post.infrastructure.web.mapper;

import com.conacry.post.application.model.ElfInfo;
import com.conacry.post.infrastructure.web.dto.ElfDataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ElfDataDtoMapper {

    ElfInfo fromDto(ElfDataDto elfDataDto);
}
