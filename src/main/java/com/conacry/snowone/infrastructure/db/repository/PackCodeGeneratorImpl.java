package com.conacry.snowone.infrastructure.db.repository;

import com.conacry.snowone.domain.entity.identifier.PackCode;
import com.conacry.snowone.domain.entity.identifier.PackCodeGenerator;
import com.conacry.snowone.infrastructure.db.dao.PackDao;
import org.springframework.stereotype.Repository;

@Repository
public class PackCodeGeneratorImpl implements PackCodeGenerator {

    private final PackDao packDao;

    public PackCodeGeneratorImpl(PackDao packDao) {
        this.packDao = packDao;
    }

    @Override
    public PackCode generate() {
        var next = packDao.nextId();
        return PackCode.of(next);
    }
}
