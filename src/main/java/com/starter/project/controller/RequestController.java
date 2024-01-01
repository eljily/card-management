package com.starter.project.controller;

import com.starter.project.dto.PagebaleDataDto;
import com.starter.project.dto.RequestDto;
import com.starter.project.mapper.RequestMapper;
import com.starter.project.model.Request;
import com.starter.project.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/requests")
public class RequestController {
    @Autowired
    RequestService requestService;

    @Autowired
    RequestMapper mapper;

    @GetMapping
    public ResponseEntity<PagebaleDataDto<RequestDto>> find(@Param("page") int page, @Param("size") int size) {
        Page<Request> requests = requestService.find(page, size);
        PagebaleDataDto<RequestDto> pages = new PagebaleDataDto<>();
        pages.setTotal(requests.getTotalPages());
        pages.setData(mapper.toDto(requests.getContent()));
        return ResponseEntity.ok(pages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestDto> find(@PathVariable("id") Long id) {
        Request request = requestService.findById(id);
        return ResponseEntity.ok(mapper.toDto(request));
    }

    @GetMapping("/acceptedRequests")
    public ResponseEntity<Page<RequestDto>> findAccepted(@Param("page") int page, @Param("size") int size){
        Page<Request> requests = requestService.findAcceptedRequests(page,size);
        Page<RequestDto> requestDtos = requests.map(r-> mapper.toDto(r));
        return ResponseEntity.ok(requestDtos);
    }

    @PostMapping
    public ResponseEntity<RequestDto> save(@RequestBody  RequestDto requestDto) {
        Request requestSaved = requestService.save(this.mapper.toModel(requestDto));
        return ResponseEntity.ok(mapper.toDto(requestSaved));
    }

    @PutMapping
    public ResponseEntity<RequestDto> update(@RequestBody  RequestDto requestDto) {
        Request requestSaved = requestService.update(this.mapper.toModel(requestDto));
        return ResponseEntity.ok(mapper.toDto(requestSaved));
    }

}