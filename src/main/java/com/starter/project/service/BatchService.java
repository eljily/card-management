package com.starter.project.service;

import com.starter.project.dao.BatchDao;
import com.starter.project.model.Batch;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BatchService {

    @Autowired
    BatchDao batchDao;

    public  Batch save(Batch batch){
        return batchDao.save(batch);
    }

    public Batch getBatchById(long id){
        Batch batch =  batchDao.findById(id).orElse(null);
        return  batch;
    }

    public List<Batch> getAllBatch(){
        return batchDao.findAll();
    }
}
