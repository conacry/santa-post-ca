package com.conacry.post.application.port;

import com.conacry.post.domain.entity.DeliveryState;
import com.conacry.post.domain.entity.identifier.DeliveryOrderIdentifier;
import com.conacry.post.domain.entity.DeliveryOrder;
import com.conacry.post.domain.value.Elf;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public interface DeliveryOrderGateway {

    void save(@Nonnull DeliveryOrder value);

    Optional<DeliveryOrder> findByIdentifier(@Nullable DeliveryOrderIdentifier identifier);

    List<DeliveryOrder> findByElfAndState(@Nullable Elf elf, DeliveryState state, int limit);
}
