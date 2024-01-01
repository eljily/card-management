package com.starter.project.mapper;

import com.starter.project.dto.CardDto;
import com.starter.project.model.Card;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {AccountMapper.class,CardTypeMapper.class})
public interface CardMapper {
    CardDto toDto(Card card);
    List<CardDto> toDto(List<Card> cards);
    Card toModel(CardDto cardDto);


}
