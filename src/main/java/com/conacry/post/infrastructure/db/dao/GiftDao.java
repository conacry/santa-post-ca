package com.conacry.post.infrastructure.db.dao;

import com.conacry.post.infrastructure.db.model.GiftDbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftDao extends JpaRepository<GiftDbModel, Integer> {

    @Query(nativeQuery = true, value = "SELECT NEXTVAL('public.gift_id_seq')")
    Integer nextId();
}
