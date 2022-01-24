package com.conacry.post.infrastructure.db.repository;

import com.conacry.post.infrastructure.db.model.PackDbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PackDao extends JpaRepository<PackDbModel, Integer> {

    @Query(nativeQuery = true, value = "SELECT NEXTVAL('public.pack_id_seq')")
    Integer nextId();
}
