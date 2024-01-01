package com.starter.project.dao;

import com.starter.project.model.RequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestHistoryDao extends JpaRepository<RequestHistory, Long> {
}
