package com.conacry.snowone.domain.entity.identifier;

import lombok.Getter;

@Getter
public final class GiftIdentifier {

    private final Integer value;

    public static GiftIdentifier of(Integer value) {
        return new GiftIdentifier(value);
    }

    private GiftIdentifier(Integer value) {
        this.value = value;
    }
}