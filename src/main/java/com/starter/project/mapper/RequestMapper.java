package com.starter.project.mapper;

import com.starter.project.dto.RequestDto;
import com.starter.project.model.Request;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {CardMapper.class, RequestHistoryMapper.class})
public interface RequestMapper {
    RequestDto toDto(Request request);
    List<RequestDto> toDto(List<Request> requests);
    Request toModel(RequestDto requestDto);


}
