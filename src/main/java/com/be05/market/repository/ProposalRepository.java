package com.be05.market.repository;

import com.be05.market.entity.ProposalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<ProposalEntity, Long> {
    Page<ProposalEntity> findAllByItemIdAndWriter(Long itemId, String writer, Pageable pageable);
}
