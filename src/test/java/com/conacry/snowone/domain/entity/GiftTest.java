package com.conacry.snowone.domain.entity;

import com.conacry.snowone.domain.entity.identifier.GiftIdGenerator;
import com.conacry.snowone.domain.entity.identifier.GiftIdentifier;
import com.conacry.snowone.domain.exception.GiftCreationException;
import com.conacry.snowone.domain.value.Address;
import com.conacry.snowone.domain.value.Behavior;
import com.conacry.snowone.domain.value.Child;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GiftTest {

    private static final GiftIdentifier GIFT_IDENTIFIER = GiftIdentifier.of(100500);

    @Test
    void of_IdentifierIsNull_NPE() {
        var child = Child.of("name", Address.of("address"), Behavior.EXCELLENT);
        assertThrows(NullPointerException.class, () -> Gift.of(null, child, GiftSize.BIG));
    }

    @Test
    void of_ChildIsNull_NPE() {
        assertThrows(NullPointerException.class, () -> Gift.of(GIFT_IDENTIFIER, null, GiftSize.BIG));
    }

    @Test
    void of_GiftSizeIsNull_NPE() {
        var child = Child.of("name", Address.of("address"), Behavior.EXCELLENT);
        assertThrows(NullPointerException.class, () -> Gift.of(GIFT_IDENTIFIER, child, null));
    }

    @Test
    void of_ValidParam_ReturnGift() {
        var child = Child.of("name", Address.of("address"), Behavior.EXCELLENT);
        var actualGift = Gift.of(GIFT_IDENTIFIER, child, GiftSize.BIG);
        assertNotNull(actualGift);
    }

    @Test
    void create_IdGeneratorIsNull_NPE() {
        var child = Child.of("name", Address.of("address"), Behavior.EXCELLENT);
        assertThrows(NullPointerException.class, () -> Gift.create(child, "BIG", null));
    }

    @Test
    void create_ChildIsNull_NPE() {
        var idGenerator = mock(GiftIdGenerator.class);
        assertThrows(NullPointerException.class, () -> Gift.create(null, "BIG", idGenerator));
    }

    @Test
    void create_GiftSizeIsNull_NPE() {
        var child = Child.of("name", Address.of("address"), Behavior.EXCELLENT);
        var idGenerator = mock(GiftIdGenerator.class);
        assertThrows(NullPointerException.class, () -> Gift.create(child, null, idGenerator));
    }

    @Test
    void create_WrongGiftSize_GiftCreationEx() {
        var child = Child.of("name", Address.of("address"), Behavior.EXCELLENT);
        var idGenerator = mock(GiftIdGenerator.class);
        assertThrows(GiftCreationException.class, () -> Gift.create(child, "WRONG", idGenerator));
    }

    @Test
    void create_ValidParam_ReturnGift() {
        var child = Child.of("name", Address.of("address"), Behavior.EXCELLENT);
        var idGenerator = mock(GiftIdGenerator.class);
        when(idGenerator.generate()).thenReturn(GIFT_IDENTIFIER);
        var result = Gift.create(child, "BIG", idGenerator);
        assertNotNull(result.getValue());
        assertNotNull(result.getEvent());
        assertEquals(GIFT_IDENTIFIER, result.getValue().getIdentifier());
    }

    @Test
    void create_RequestedBigGift_BehaviorBad_ReturnGiftWithSmallGift() {
        var child = Child.of("name", Address.of("address"), Behavior.BAD);
        var idGenerator = mock(GiftIdGenerator.class);
        var result = Gift.create(child, "BIG", idGenerator);
        assertNotNull(result.getValue());
        assertEquals(GiftSize.SMALL, result.getValue().getSize());
    }

    @Test
    void create_RequestedMediumGift_BehaviorBad_ReturnGiftWithSmallGift() {
        var child = Child.of("name", Address.of("address"), Behavior.BAD);
        var idGenerator = mock(GiftIdGenerator.class);
        var result = Gift.create(child, "MEDIUM", idGenerator);
        assertNotNull(result.getValue());
        assertEquals(GiftSize.SMALL, result.getValue().getSize());
    }

    @Test
    void create_RequestedSmallGift_BehaviorBad_ReturnGiftWithSmallGift() {
        var child = Child.of("name", Address.of("address"), Behavior.BAD);
        var idGenerator = mock(GiftIdGenerator.class);
        var result = Gift.create(child, "SMALL", idGenerator);
        assertNotNull(result.getValue());
        assertEquals(GiftSize.SMALL, result.getValue().getSize());
    }

    @Test
    void create_RequestedBigGift_BehaviorGood_ReturnGiftWithMediumGift() {
        var child = Child.of("name", Address.of("address"), Behavior.GOOD);
        var idGenerator = mock(GiftIdGenerator.class);
        var result = Gift.create(child, "BIG", idGenerator);
        assertNotNull(result.getValue());
        assertEquals(GiftSize.MEDIUM, result.getValue().getSize());
    }

    @Test
    void create_RequestedMediumGift_BehaviorGood_ReturnGiftWithMediumGift() {
        var child = Child.of("name", Address.of("address"), Behavior.GOOD);
        var idGenerator = mock(GiftIdGenerator.class);
        var result = Gift.create(child, "MEDIUM", idGenerator);
        assertNotNull(result.getValue());
        assertEquals(GiftSize.MEDIUM, result.getValue().getSize());
    }

    @Test
    void create_RequestedSmallGift_BehaviorGood_ReturnGiftWithSmallGift() {
        var child = Child.of("name", Address.of("address"), Behavior.GOOD);
        var idGenerator = mock(GiftIdGenerator.class);
        var result = Gift.create(child, "SMALL", idGenerator);
        assertNotNull(result.getValue());
        assertEquals(GiftSize.SMALL, result.getValue().getSize());
    }
}