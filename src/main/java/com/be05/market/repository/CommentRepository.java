package com.be05.market.repository;

import com.be05.market.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<ItemEntity, Long> {
}
