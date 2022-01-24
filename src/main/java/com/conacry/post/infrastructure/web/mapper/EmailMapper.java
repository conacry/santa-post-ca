package com.conacry.post.infrastructure.web.mapper;

import com.conacry.post.application.model.GiftRequestData;
import com.conacry.post.infrastructure.web.dto.EmailToSanta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    GiftRequestData fromEmailToRequest(EmailToSanta email);
}
