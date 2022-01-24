package com.conacry.post.domain.value;

import lombok.Getter;

@Getter
public final class Elf {

    private final String name;
    private final Address address;

    public static Elf of(String name, Address address) {
        return new Elf(name, address);
    }

    private Elf(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
