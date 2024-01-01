package com.starter.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class RequestStatus {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private RequestStatusEnum statusCode;
    private String frLabel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RequestStatusEnum getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(RequestStatusEnum statusCode) {
        this.statusCode = statusCode;
    }

    public String getFrLabel() {
        return frLabel;
    }

    public void setFrLabel(String frLabel) {
        this.frLabel = frLabel;
    }
}
