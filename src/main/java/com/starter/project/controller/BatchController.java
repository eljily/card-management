package com.starter.project.controller;

import com.starter.project.dto.BatchDto;
import com.starter.project.mapper.BatchMapper;
import com.starter.project.model.Batch;
import com.starter.project.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batch")
public class BatchController {

    @Autowired
    BatchService batchService;
    @Autowired
    BatchMapper mapper;

    @GetMapping
    public ResponseEntity<List<BatchDto>>findAll(){
        List<Batch> batches = batchService.getAllBatch();
        return ResponseEntity.ok(mapper.toDto(batches));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BatchDto>findById(@PathVariable Long id){
        Batch batch = batchService.getBatchById(id);
        return ResponseEntity.ok(mapper.toDto(batch));
    }
    @PostMapping
    public ResponseEntity<BatchDto> save(@RequestBody BatchDto batchDto) {
        Batch batch = batchService.save(this.mapper.toModel(batchDto));
        return ResponseEntity.ok(mapper.toDto(batch));
    }
}