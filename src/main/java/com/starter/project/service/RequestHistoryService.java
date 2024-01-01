package com.starter.project.service;

import com.starter.project.dao.RequestHistoryDao;
import com.starter.project.dao.RequestStatusDao;
import com.starter.project.model.Request;
import com.starter.project.model.RequestHistory;
import com.starter.project.model.RequestStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
public class RequestHistoryService {
    @Autowired
    RequestHistoryDao requestHistoryDao;
    @Autowired
    RequestStatusDao requestStatusDao;
    public RequestHistory addHistoryDao(RequestStatusEnum status, Request request){
        RequestHistory history = new RequestHistory();
        history.setStatus(requestStatusDao.findByStatusCode(status));
        history.setStartDate(new Date());
        history.setRequest(request);
        return history;
    }
}
