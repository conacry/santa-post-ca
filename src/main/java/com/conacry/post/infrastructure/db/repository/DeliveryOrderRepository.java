package com.conacry.post.infrastructure.db.repository;

import com.conacry.post.application.port.DeliveryOrderGateway;
import com.conacry.post.domain.entity.DeliveryOrder;
import com.conacry.post.domain.entity.DeliveryState;
import com.conacry.post.domain.entity.identifier.DeliveryOrderIdentifier;
import com.conacry.post.domain.value.Elf;
import com.conacry.post.infrastructure.db.dao.DeliveryOrderDao;
import com.conacry.post.infrastructure.db.mapper.DeliveryOrderDbModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DeliveryOrderRepository implements DeliveryOrderGateway {

    private final DeliveryOrderDao deliveryOrderDao;
    private final DeliveryOrderDbModelMapper dbModelMapper;

    public DeliveryOrderRepository(DeliveryOrderDao deliveryOrderDao,
            DeliveryOrderDbModelMapper dbModelMapper) {
        this.deliveryOrderDao = deliveryOrderDao;
        this.dbModelMapper = dbModelMapper;
    }

    @Override
    public void save(@Nonnull DeliveryOrder entity) {
        Objects.requireNonNull(entity);
        var dbModel = dbModelMapper.toModel(entity);
        deliveryOrderDao.save(dbModel);
    }

    @Override
    public Optional<DeliveryOrder> findByIdentifier(@Nullable DeliveryOrderIdentifier identifier) {
        if (identifier == null) {
            return Optional.empty();
        }

        var dbModel = deliveryOrderDao.findById(identifier.getValue());

        return dbModel.map(dbModelMapper::toEntity).or(Optional::empty);
    }

    @Override
    public List<DeliveryOrder> findByElfAndState(@Nullable Elf elf, DeliveryState state, int limit) {
        if (elf == null) {
            return Collections.emptyList();
        }

        var models = deliveryOrderDao.findByElfNameAndState(elf.getName(), state.name(), Pageable.ofSize(limit));

        return models.stream()
                .map(dbModelMapper::toEntity)
                .collect(Collectors.toList());
    }
}
