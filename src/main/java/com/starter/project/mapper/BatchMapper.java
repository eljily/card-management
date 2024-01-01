package com.starter.project.mapper;

import com.starter.project.dto.BatchDto;
import com.starter.project.model.Batch;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BatchMapper {
    BatchDto toDto(Batch batch);
    List<BatchDto> toDto(List<Batch>batches);

    Batch toModel(BatchDto batchDto);
}
