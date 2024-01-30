package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PropService {
    @Autowired
    DummyDao dummyDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertBoundaryRequired(String success) throws Exception {
        if ("success".equals(success)) {
            dummyDao.insert(5, 500);
            dummyDao.insert(6, 600);
        } else if ("fail".equals(success)) {
            dummyDao.insert(5, 500);
            dummyDao.insert(5, 500);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertBoundaryRequiresNew(String success) throws Exception {
        if ("success".equals(success)) {
            dummyDao.insert(5, 500);
            dummyDao.insert(6, 600);
        } else if ("fail".equals(success)) {
            dummyDao.insert(5, 500);
            dummyDao.insert(5, 500);
        }
    }
}