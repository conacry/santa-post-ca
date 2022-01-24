package com.conacry.post.infrastructure.db.repository;

import com.conacry.post.domain.entity.identifier.GiftIdGenerator;
import com.conacry.post.domain.entity.identifier.GiftIdentifier;
import com.conacry.post.infrastructure.db.dao.GiftDao;
import org.springframework.stereotype.Component;

@Component
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
