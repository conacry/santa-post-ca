package com.conacry.snowone.infrastructure.db.dao;

import com.conacry.snowone.infrastructure.db.model.ChildDbModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildInfoDao extends JpaRepository<ChildDbModel, String> {

}
