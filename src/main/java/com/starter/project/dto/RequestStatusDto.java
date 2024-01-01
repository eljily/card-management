package com.starter.project.dto;

import com.starter.project.model.RequestStatusEnum;


public class RequestStatusDto {
    private Long id;
    private RequestStatusEnum name;
    private String frLabel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RequestStatusEnum getName() {
        return name;
    }

    public void setName(RequestStatusEnum name) {
        this.name = name;
    }

    public String getFrLabel() {
        return frLabel;
    }

    public void setFrLabel(String frLabel) {
        this.frLabel = frLabel;
    }
}
