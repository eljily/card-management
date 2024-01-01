package com.starter.project.service;

import com.starter.project.dao.CustomerDao;
import com.starter.project.dao.RequestDao;
import com.starter.project.dao.RequestHistoryDao;
import com.starter.project.dao.UserDao;
import com.starter.project.dto.RequestDto;
import com.starter.project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static java.util.Optional.ofNullable;


@Service
@Transactional
public class RequestService {
    @Autowired
    private RequestDao requestDao;
    @Autowired
    private RequestHistoryService requestHistoryService;

    @Autowired
    private UserDao userDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private RequestHistoryDao requestHistoryDao;

    public Page<Request> find(int page, int size) {
        return requestDao.findAll(PageRequest.of(page, size, Sort.by("id")));
    }
    public Request findById(long id) {
        return requestDao.findById(id).orElse(null);
    }
    public Request save(Request request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.findByUsername(authentication.getName()).orElse(null);
        request.setCreationDate(new Date());
        request.setUser(user);
        request.setAgency(ofNullable(user).map(User::getAgency).orElse(null));
        Customer customer = request.getAccount().getCustomer();
        customerDao.save(customer);
        if(request.getId()==null) {
            RequestHistory requestHistory = requestHistoryService.addHistoryDao(RequestStatusEnum.IN_PROGRESS, request);
            request.setHistories(Set.of(requestHistory));
        }
        Request savedRequest = requestDao.save(request);
        requestHistoryDao.saveAll(request.getHistories());
        return savedRequest;
    }
    public Request update(Request request) {
        return requestDao.save(request);
    }

    public Page<Request> findAcceptedRequests(int page,int size){

        return requestDao.findAccepted(PageRequest.of(page,size,Sort.by("id")));
    }
}
