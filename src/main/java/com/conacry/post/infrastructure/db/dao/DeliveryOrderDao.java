package com.conacry.post.infrastructure.db.dao;

import com.conacry.post.infrastructure.db.model.DeliveryOrderDbModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryOrderDao extends JpaRepository<DeliveryOrderDbModel, Integer> {

    List<DeliveryOrderDbModel> findByElfNameAndState(String elfName, String state, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT NEXTVAL('public.delivery_order_id_seq')")
    Integer next();
}
