package com.conacry.snowone.domain.entity;

import com.conacry.snowone.domain.entity.identifier.GiftIdentifier;
import com.conacry.snowone.domain.entity.identifier.PackCode;
import com.conacry.snowone.domain.entity.identifier.PackCodeGenerator;
import com.conacry.snowone.domain.value.Address;
import com.conacry.snowone.domain.value.Behavior;
import com.conacry.snowone.domain.value.Child;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PackTest {

    private static final PackCode PACK_CODE = PackCode.of(100500);

    @Test
    void of_PackCodeIsNull_NPE() {
        assertThrows(NullPointerException.class, () -> Pack.of(null, PackSize.BIG, PackType.BOX));
    }

    @Test
    void of_PackSizeIsNull_NPE() {
        assertThrows(NullPointerException.class, () -> Pack.of(PACK_CODE, null, PackType.BOX));
    }

    @Test
    void of_PackTypeIsNull_NPE() {
        assertThrows(NullPointerException.class, () -> Pack.of(PACK_CODE, PackSize.BIG, null));
    }

    @Test
    void of_ValidParam_ReturnPack() {
        var actualPack = Pack.of(PACK_CODE, PackSize.BIG, PackType.BOX);
        assertNotNull(actualPack);
    }

    @Test
    void pack_CodeGeneratorIsNull_NPE() {
        var child = Child.of("name", Address.of("address"), Behavior.GOOD);
        var gift = Gift.of(GiftIdentifier.of(100), child, GiftSize.BIG);
        assertThrows(NullPointerException.class, () -> Pack.pack(null, gift));
    }

    @Test
    void pack_GiftIsNull_NPE() {
        var codeGenerator = mock(PackCodeGenerator.class);
        assertThrows(NullPointerException.class, () -> Pack.pack(codeGenerator, null));
    }

    @Test
    void pack_BigGift_ReturnBigPackWithBoxType() {
        var child = Child.of("name", Address.of("address"), Behavior.GOOD);
        var gift = Gift.of(GiftIdentifier.of(100), child, GiftSize.BIG);
        var codeGenerator = mock(PackCodeGenerator.class);

        var actualPack = Pack.pack(codeGenerator, gift);
        assertNotNull(actualPack);
        assertEquals(PackSize.BIG, actualPack.getSize());
        assertEquals(PackType.BOX, actualPack.getType());
    }

    @Test
    void pack_MediumGift_ReturnMediumPackWithBoxType() {
        var child = Child.of("name", Address.of("address"), Behavior.GOOD);
        var gift = Gift.of(GiftIdentifier.of(100), child, GiftSize.MEDIUM);
        var codeGenerator = mock(PackCodeGenerator.class);

        var actualPack = Pack.pack(codeGenerator, gift);
        assertNotNull(actualPack);
        assertEquals(PackSize.MEDIUM, actualPack.getSize());
        assertEquals(PackType.BOX, actualPack.getType());
    }

    @Test
    void pack_SmallGift_ReturnSmallPackWithBagType() {
        var child = Child.of("name", Address.of("address"), Behavior.GOOD);
        var gift = Gift.of(GiftIdentifier.of(100), child, GiftSize.SMALL);
        var codeGenerator = mock(PackCodeGenerator.class);

        var actualPack = Pack.pack(codeGenerator, gift);
        assertNotNull(actualPack);
        assertEquals(PackSize.SMALL, actualPack.getSize());
        assertEquals(PackType.BAG, actualPack.getType());
    }
}