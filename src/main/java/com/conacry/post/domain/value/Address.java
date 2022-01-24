package com.conacry.post.domain.value;

import lombok.Getter;

@Getter
public final class Address {

    private final String value;

    public static Address of(String value) {
        return new Address(value);
    }

    private Address(String value) {
        this.value = value;
    }
}
