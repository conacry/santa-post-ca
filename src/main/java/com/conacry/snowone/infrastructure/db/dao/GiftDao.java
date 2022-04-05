package com.conacry.snowone.infrastructure.db.dao;

import com.conacry.snowone.infrastructure.db.model.GiftDbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GiftDao extends JpaRepository<GiftDbModel, Integer> {

    @Query(nativeQuery = true, value = "SELECT NEXTVAL('public.gift_id_seq')")
    Integer nextId();
}
