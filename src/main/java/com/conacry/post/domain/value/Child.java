package com.conacry.post.domain.value;

import lombok.Getter;

@Getter
public class Child {

    private final String name;
    private final Address address;
    private final Behavior behavior;

    public static Child of(String name, Address address, Behavior behavior) {
        return new Child(name, address, behavior);
    }

    private Child(String name, Address address, Behavior behavior) {
        this.name = name;
        this.address = address;
        this.behavior = behavior;
    }

}
