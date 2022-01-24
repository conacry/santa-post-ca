package com.conacry.post.infrastructure.db.dao;

import com.conacry.post.infrastructure.db.model.ChildDbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildInfoDao extends JpaRepository<ChildDbModel, String> {

}
