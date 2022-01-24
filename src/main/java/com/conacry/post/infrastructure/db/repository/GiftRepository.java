package com.conacry.post.infrastructure.db.repository;

import com.conacry.post.application.port.GiftGateway;
import com.conacry.post.domain.entity.Gift;
import com.conacry.post.domain.entity.identifier.GiftIdentifier;
import com.conacry.post.infrastructure.db.dao.GiftDao;
import com.conacry.post.infrastructure.db.mapper.GiftDbModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

@Component
public class GiftRepository implements GiftGateway {

    private final GiftDbModelMapper giftDbModelMapper;
    private final GiftDao giftDao;

    public GiftRepository(GiftDbModelMapper giftDbModelMapper, GiftDao giftDao) {
        this.giftDbModelMapper = giftDbModelMapper;
        this.giftDao = giftDao;
    }

    @Override
    public void save(@Nonnull Gift entity) {
        Objects.requireNonNull(entity);

        var model = giftDbModelMapper.toModel(entity);
        giftDao.save(model);
    }

    @Override
    public Optional<Gift> findByIdentifier(@Nullable GiftIdentifier identifier) {
        if (identifier == null) {
            return Optional.empty();
        }

        var model = giftDao.findById(identifier.getValue());
        return model.map(giftDbModelMapper::toEntity).or(Optional::empty);
    }
}
