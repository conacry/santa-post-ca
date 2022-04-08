package com.conacry.snowone.domain.entity;

import com.conacry.snowone.domain.entity.identifier.DeliveryOrderIdGenerator;
import com.conacry.snowone.domain.entity.identifier.GiftIdentifier;
import com.conacry.snowone.domain.entity.identifier.PackCode;
import com.conacry.snowone.domain.value.Address;
import com.conacry.snowone.domain.value.Behavior;
import com.conacry.snowone.domain.value.Child;
import com.conacry.snowone.domain.value.Elf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DeliveryOrderTest {

    @Test
    void create_IdGeneratorIsNull_NPE() {
        var child = Child.of("name", Address.of("address"), Behavior.GOOD);
        var gift = Gift.of(GiftIdentifier.of(100), child, GiftSize.BIG);
        var pack = Pack.of(PackCode.of(110), PackSize.BIG, PackType.BOX);

        assertThrows(NullPointerException.class, () -> DeliveryOrder.create(null, pack, gift));
    }

    @Test
    void create_PackIsNull_NPE() {
        var child = Child.of("name", Address.of("address"), Behavior.GOOD);
        var gift = Gift.of(GiftIdentifier.of(100), child, GiftSize.BIG);
        var idGenerator = mock(DeliveryOrderIdGenerator.class);

        assertThrows(NullPointerException.class, () -> DeliveryOrder.create(idGenerator, null, gift));
    }

    @Test
    void create_GiftIsNull_NPE() {
        var pack = Pack.of(PackCode.of(110), PackSize.BIG, PackType.BOX);
        var idGenerator = mock(DeliveryOrderIdGenerator.class);

        assertThrows(NullPointerException.class, () -> DeliveryOrder.create(idGenerator, pack, null));
    }

    @Test
    void create_ValidParam_ReturnDeliveryOrder() {
        var child = Child.of("name", Address.of("address"), Behavior.GOOD);
        var gift = Gift.of(GiftIdentifier.of(100), child, GiftSize.BIG);
        var pack = Pack.of(PackCode.of(110), PackSize.BIG, PackType.BOX);
        var idGenerator = mock(DeliveryOrderIdGenerator.class);

        var actualResult = DeliveryOrder.create(idGenerator, pack, gift);
        assertNotNull(actualResult.getEvent());
        assertNotNull(actualResult.getValue());
        assertEquals(DeliveryState.CREATED, actualResult.getValue().getState());
    }

    @Test
    void assign_ElfIsNull_NPE() {
        var child = Child.of("name", Address.of("address"), Behavior.GOOD);
        var gift = Gift.of(GiftIdentifier.of(100), child, GiftSize.BIG);
        var pack = Pack.of(PackCode.of(110), PackSize.BIG, PackType.BOX);
        var idGenerator = mock(DeliveryOrderIdGenerator.class);
        var deliveryOrder = DeliveryOrder.create(idGenerator, pack, gift).getValue();

        assertThrows(NullPointerException.class, () -> deliveryOrder.assign(null));
    }

    @Test
    void assign_ValidParam_ChangeStateToAssigned() {
        var child = Child.of("name", Address.of("address"), Behavior.GOOD);
        var gift = Gift.of(GiftIdentifier.of(100), child, GiftSize.BIG);
        var pack = Pack.of(PackCode.of(110), PackSize.BIG, PackType.BOX);
        var idGenerator = mock(DeliveryOrderIdGenerator.class);
        var deliveryOrder = DeliveryOrder.create(idGenerator, pack, gift).getValue();

        var elf = Elf.of("Name", Address.of("address"));

        deliveryOrder.assign(elf);
        assertEquals(DeliveryState.ASSIGNED, deliveryOrder.getState());
    }

    @Test
    void makeDelivery_ValidParam_ChangeStateToDelivering() {
        var child = Child.of("name", Address.of("address"), Behavior.GOOD);
        var gift = Gift.of(GiftIdentifier.of(100), child, GiftSize.BIG);
        var pack = Pack.of(PackCode.of(110), PackSize.BIG, PackType.BOX);
        var idGenerator = mock(DeliveryOrderIdGenerator.class);
        var deliveryOrder = DeliveryOrder.create(idGenerator, pack, gift).getValue();

        deliveryOrder.makeDelivery();
        assertEquals(DeliveryState.DELIVERING, deliveryOrder.getState());
    }

    @Test
    void complete_ValidParam_ChangeStateToCompleted() {
        var child = Child.of("name", Address.of("address"), Behavior.GOOD);
        var gift = Gift.of(GiftIdentifier.of(100), child, GiftSize.BIG);
        var pack = Pack.of(PackCode.of(110), PackSize.BIG, PackType.BOX);
        var idGenerator = mock(DeliveryOrderIdGenerator.class);
        var deliveryOrder = DeliveryOrder.create(idGenerator, pack, gift).getValue();

        deliveryOrder.complete();
        assertEquals(DeliveryState.COMPLETED, deliveryOrder.getState());
    }
}