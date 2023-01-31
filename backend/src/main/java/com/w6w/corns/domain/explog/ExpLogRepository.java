package com.w6w.corns.domain.explog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ExpLogRepository extends JpaRepository<ExpLog, Integer> {

    Page<ExpLog> findByUserId(int userId, Pageable pageable, LocalDateTime dt);
}

