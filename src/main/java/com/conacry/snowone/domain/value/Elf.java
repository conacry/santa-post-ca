package com.conacry.snowone.domain.value;

import com.conacry.snowone.shared.mark.ValueObject;
import lombok.Getter;

import javax.annotation.Nonnull;
import java.util.Objects;

@Getter(onMethod_ = { @Nonnull })
public final class Elf implements ValueObject {

    private final String name;
    private final Address address;

    public static Elf of(@Nonnull String name, @Nonnull Address address) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(address);
        return new Elf(name, address);
    }

    private Elf(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
