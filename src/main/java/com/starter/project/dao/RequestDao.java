package com.starter.project.dao;

import com.starter.project.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestDao extends JpaRepository<Request, Long> {
    @Query("SELECT r FROM Request r " +
            "JOIN r.histories h " +
            "WHERE h.status.statusCode = 'ACCEPTED'")
    Page<Request> findAccepted(Pageable pageable);
}
