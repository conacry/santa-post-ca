package com.conacry.snowone.domain.entity;

import com.conacry.snowone.domain.entity.identifier.PackCode;
import lombok.Getter;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;

@Getter(onMethod_ = { @Nonnull})
public class Pack {

    private final PackCode code;
    private final PackSize size;
    private final PackType type;

    private static final Map<GiftSize, PackSize> SIZE_MATCHING = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(GiftSize.BIG, PackSize.BIG),
            new AbstractMap.SimpleEntry<>(GiftSize.MEDIUM, PackSize.MEDIUM),
            new AbstractMap.SimpleEntry<>(GiftSize.SMALL, PackSize.SMALL)
    );

    private static final Map<PackSize, PackType> TYPE_MATCHING = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(PackSize.BIG, PackType.BOX),
            new AbstractMap.SimpleEntry<>(PackSize.MEDIUM, PackType.BOX),
            new AbstractMap.SimpleEntry<>(PackSize.SMALL, PackType.BAG)
    );

    public static Pack of(@Nonnegative PackCode code, @Nonnull PackSize size, @Nonnull PackType type) {
        Objects.requireNonNull(code);
        Objects.requireNonNull(size);
        Objects.requireNonNull(type);

        return new Pack(code, size, type);
    }

    private Pack(PackCode code, PackSize size, PackType type) {
        this.code = code;
        this.size = size;
        this.type = type;
    }
}
