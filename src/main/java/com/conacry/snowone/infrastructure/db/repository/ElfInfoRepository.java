package com.conacry.snowone.infrastructure.db.repository;

import com.conacry.snowone.application.port.ElfInfoGateway;
import com.conacry.snowone.domain.value.Address;
import com.conacry.snowone.domain.value.Elf;
import com.conacry.snowone.infrastructure.db.dao.ElfInfoDao;
import com.conacry.snowone.infrastructure.db.mapper.ElfDbModelMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.Optional;

@Repository
public class ElfInfoRepository implements ElfInfoGateway {

    private final ElfInfoDao elfInfoDao;
    private final ElfDbModelMapper dbModelMapper;

    public ElfInfoRepository(ElfInfoDao elfInfoDao, ElfDbModelMapper dbModelMapper) {
        this.elfInfoDao = elfInfoDao;
        this.dbModelMapper = dbModelMapper;
    }

    @Override
    public Optional<Elf> findByAddress(@Nullable Address address) {
        if (address == null) {
            return Optional.empty();
        }

        var dbModel = elfInfoDao.findByAddress(address.getValue());

        return dbModel.map(dbModelMapper::toEntity).or(Optional::empty);
    }

    @Override
    public Optional<Elf> findByName(@Nullable String name) {
        if (name == null || name.isBlank()) {
            return Optional.empty();
        }

        var dbModel = elfInfoDao.findById(name);

        return dbModel.map(dbModelMapper::toEntity).or(Optional::empty);
    }
}
