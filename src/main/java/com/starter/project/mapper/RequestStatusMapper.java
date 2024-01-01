package com.starter.project.mapper;

import com.starter.project.dto.RequestStatusDto;
import com.starter.project.model.RequestStatus;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestStatusMapper {
    RequestStatusDto toDto(RequestStatus request);
    List<RequestStatusDto> toDto(List<RequestStatus> requests);
    RequestStatus toModel(RequestStatusDto requestDto);


}
