package com.conacry.snowone.domain.value;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChildTest {

    private static final String NAME = "Name";
    private static final Address ADDRESS = Address.of("address");
    private static final Behavior BEHAVIOR = Behavior.EXCELLENT;

    @Test
    void of_NameIsNull_NPE() {
        assertThrows(NullPointerException.class, () -> Child.of(null, ADDRESS, BEHAVIOR));
    }

    @Test
    void of_AddressIsNull_NPE() {
        assertThrows(NullPointerException.class, () -> Child.of(NAME, null, BEHAVIOR));
    }

    @Test
    void of_BehaviorIsNull_NPE() {
        assertThrows(NullPointerException.class, () -> Child.of(NAME, ADDRESS, null));
    }

    @Test
    void of_ValidParam_ReturnChild() {
        var actualChild = Child.of(NAME, ADDRESS, BEHAVIOR);
        assertNotNull(actualChild);
    }
}