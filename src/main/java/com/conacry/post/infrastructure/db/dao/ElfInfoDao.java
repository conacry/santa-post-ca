package com.conacry.post.infrastructure.db.dao;

import com.conacry.post.infrastructure.db.model.ElfDbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ElfInfoDao extends JpaRepository<ElfDbModel, String> {

    Optional<ElfDbModel> findByAddress(String value);
}
