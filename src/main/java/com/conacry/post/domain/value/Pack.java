package com.conacry.post.domain.value;

import com.conacry.post.domain.entity.Gift;
import com.conacry.post.domain.entity.GiftSize;
import lombok.Getter;

import java.util.AbstractMap;
import java.util.Map;

@Getter
public class Pack {

    private PackCode code;
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

    public static Pack of(PackCode code, PackSize size, PackType type) {
       return new Pack(code, size, type);
    }

    public static Pack pack(PackCodeGenerator codeGenerator, Gift gift) {
        var size = SIZE_MATCHING.get(gift.getSize());
        var type = TYPE_MATCHING.get(size);

        return new Pack(codeGenerator.generate(), size, type);
    }

    private Pack(PackCode code, PackSize size, PackType type) {
        this.code = code;
        this.size = size;
        this.type = type;
    }
}
