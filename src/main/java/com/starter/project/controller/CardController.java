package com.starter.project.controller;

import com.starter.project.dto.CardDto;
import com.starter.project.mapper.CardMapper;
import com.starter.project.model.Card;
import com.starter.project.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/cards")
public class CardController {
    @Autowired
    CardService cardService;

    @Autowired
    CardMapper mapper;


    @GetMapping
    public ResponseEntity<List<CardDto>> find(@Param("searchText") String searchText, @Param("size") int size) {
        List<Card> cards = cardService.find(searchText, size);

        return ResponseEntity.ok(mapper.toDto(cards));
    }


}