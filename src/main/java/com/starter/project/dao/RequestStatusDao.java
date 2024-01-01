package com.starter.project.dao;

import com.starter.project.model.RequestStatus;
import com.starter.project.model.RequestStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestStatusDao extends JpaRepository<RequestStatus, Long> {
    RequestStatus findByStatusCode(RequestStatusEnum status);
}
