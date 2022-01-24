package com.conacry.snowone.domain.value;

import com.conacry.snowone.shared.mark.ValueObject;
import lombok.Getter;

import javax.annotation.Nonnull;
import java.util.Objects;

@Getter(onMethod_ = { @Nonnull })
public final class Address implements ValueObject {

    private final String value;

    public static Address of(@Nonnull String value) {
        Objects.requireNonNull(value);
        return new Address(value);
    }

    private Address(String value) {
        this.value = value;
    }
}
