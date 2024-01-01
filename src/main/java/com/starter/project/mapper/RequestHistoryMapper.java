package com.starter.project.mapper;

import com.starter.project.dto.RequestHistoryDto;
import com.starter.project.model.RequestHistory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {RequestStatusMapper.class})
public interface RequestHistoryMapper {
    RequestHistoryDto toDto(RequestHistory requestHistory);
    List<RequestHistoryDto> toDto(List<RequestHistory> requestsHistories);
    RequestHistory toModel(RequestHistoryDto requestHistoryDto);
}
