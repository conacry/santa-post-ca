package com.conacry.post.domain.entity.identifier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class GiftIdentifier {

    private final Integer value;

    public GiftIdentifier(Integer value) {
        this.value = value;
    }
}
