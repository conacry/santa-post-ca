package com.conacry.snowone.infrastructure.db.repository;

import com.conacry.snowone.domain.entity.identifier.GiftIdGenerator;
import com.conacry.snowone.domain.entity.identifier.GiftIdentifier;
import com.conacry.snowone.infrastructure.db.dao.GiftDao;
import org.springframework.stereotype.Repository;

@Repository
public class GiftIdGeneratorImpl implements GiftIdGenerator {

    private final GiftDao giftDao;

    public GiftIdGeneratorImpl(GiftDao giftDao) {
        this.giftDao = giftDao;
    }

    @Override
    public GiftIdentifier generate() {
        var next = giftDao.nextId();
        return new GiftIdentifier(next);
    }
}
