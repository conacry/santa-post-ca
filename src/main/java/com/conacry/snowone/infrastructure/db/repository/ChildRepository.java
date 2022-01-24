package com.conacry.snowone.infrastructure.db.repository;

import com.conacry.snowone.application.port.ChildInfoGateway;
import com.conacry.snowone.domain.value.Child;
import com.conacry.snowone.infrastructure.db.dao.ChildInfoDao;
import com.conacry.snowone.infrastructure.db.mapper.ChildDbModelMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.Optional;

@Repository
public class ChildRepository implements ChildInfoGateway {

    private final ChildInfoDao childInfoDao;
    private final ChildDbModelMapper childDbModelMapper;

    public ChildRepository(ChildInfoDao childInfoDao,
            ChildDbModelMapper childDbModelMapper) {
        this.childInfoDao = childInfoDao;
        this.childDbModelMapper = childDbModelMapper;
    }

    @Override
    public Optional<Child> findByName(@Nullable String childName) {
        if (childName == null || childName.isBlank()) {
            return Optional.empty();
        }

        var dbModel = childInfoDao.findById(childName);

        return dbModel.map(childDbModelMapper::toEntity).or(Optional::empty);
    }
}
