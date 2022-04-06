package com.conacry.snowone.domain.entity.identifier;

import lombok.Getter;

import javax.annotation.Nonnull;
import java.util.Objects;

@Getter
public final class PackCode {

    private final Integer value;

    public static PackCode of(@Nonnull Integer value) {
        Objects.requireNonNull(value);
        return new PackCode(value);
    }

    private PackCode(Integer value) {
        this.value = value;
    }
}
