package com.conacry.snowone.infrastructure.db.dao;

import com.conacry.snowone.infrastructure.db.model.ElfDbModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElfInfoDao extends JpaRepository<ElfDbModel, String> {

    Optional<ElfDbModel> findByAddress(String value);
}
