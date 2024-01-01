package com.starter.project.service;

import com.starter.project.dao.CardDao;
import com.starter.project.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CardService {
    @Autowired
    private CardDao cardDao;

    public Card save(Card account) {
        return cardDao.save(account);
    }
    public List<Card> findAll() {
        return cardDao.findAll();
    }
    public List<Card> find(String searchText,int size) {
        return cardDao.find(searchText,size);
    }

}
