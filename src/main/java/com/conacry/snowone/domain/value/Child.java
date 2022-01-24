package com.conacry.snowone.domain.value;

import com.conacry.snowone.shared.mark.ValueObject;
import lombok.Getter;

import javax.annotation.Nonnull;
import java.util.Objects;

@Getter(onMethod_ = { @Nonnull })
public final class Child implements ValueObject {

    private final String name;
    private final Address address;
    private final Behavior behavior;

    public static Child of(@Nonnull String name, @Nonnull Address address, @Nonnull Behavior behavior) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(address);
        Objects.requireNonNull(behavior);
        return new Child(name, address, behavior);
    }

    private Child(String name, Address address, Behavior behavior) {
        this.name = name;
        this.address = address;
        this.behavior = behavior;
    }
}
