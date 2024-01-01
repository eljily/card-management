package com.starter.project.dao;

import com.starter.project.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchDao extends JpaRepository<Batch, Long> {
}