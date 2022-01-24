package com.conacry.post.infrastructure.db.repository;

import com.conacry.post.domain.value.PackCode;
import com.conacry.post.domain.value.PackCodeGenerator;
import org.springframework.stereotype.Component;

@Component
public class PackCodeGeneratorImpl implements PackCodeGenerator {

    private final PackDao packDao;

    public PackCodeGeneratorImpl(PackDao packDao) {
        this.packDao = packDao;
    }

    @Override
    public PackCode generate() {
        var next = packDao.nextId();
        return new PackCode(next);
    }
}
