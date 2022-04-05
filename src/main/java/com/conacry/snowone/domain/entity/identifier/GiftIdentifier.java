package com.conacry.snowone.domain.entity.identifier;

import lombok.Getter;

@Getter
public final class GiftIdentifier {

    private final Integer value;

    public GiftIdentifier(Integer value) {
        this.value = value;
    }
}