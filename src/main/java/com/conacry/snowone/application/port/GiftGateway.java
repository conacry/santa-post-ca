package com.conacry.snowone.application.port;

import com.conacry.snowone.domain.entity.Gift;
import com.conacry.snowone.domain.entity.identifier.GiftIdentifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public interface GiftGateway {

    void save(@Nonnull Gift entity);

    Optional<Gift> findByIdentifier(@Nullable GiftIdentifier giftIdentifier);
}
