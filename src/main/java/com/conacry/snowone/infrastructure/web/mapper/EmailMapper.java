package com.conacry.snowone.infrastructure.web.mapper;

import com.conacry.snowone.application.model.GiftRequestData;
import com.conacry.snowone.infrastructure.web.dto.EmailToSanta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    GiftRequestData fromEmailToRequest(EmailToSanta email);
}
