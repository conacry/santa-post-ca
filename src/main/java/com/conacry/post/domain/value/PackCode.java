package com.conacry.post.domain.value;

import lombok.Getter;

@Getter
public final class PackCode {

    private final Integer value;

    public PackCode(Integer value) {
        this.value = value;
    }
}
