package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxService {
    @Autowired
    PropService propService;

    @Autowired
    TmpDao tmpDao;

    @Autowired
    DummyDao dummyDao;

    public void insertWithoutTx() throws Exception {
        tmpDao.insert(8, 800);
        tmpDao.insert(8, 800);
    }

    @Transactional(rollbackFor = Exception.class)   // default : RuntimeException 혹은 Error의 자손만 롤백 실행
    public void insertWithTx() throws Exception {
        tmpDao.insert(9, 900);
        tmpDao.insert(9, 900);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertBoundaryMainOuter(String propagation) {
        try {
            tmpDao.insert(9, 900);
            if ("req".equals(propagation)) {
                propService.insertBoundaryRequired("success");
            } else if ("new".equals(propagation)) {
                propService.insertBoundaryRequiresNew("success");
            }
            tmpDao.insert(9, 900);  // fail
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertBoundaryMainInner(String propagation) {
        try {
            tmpDao.insert(9, 900);
            if ("req".equals(propagation)) {
                propService.insertBoundaryRequired("fail");
            } else if ("new".equals(propagation)) {
                propService.insertBoundaryRequiresNew("fail");
            }
            tmpDao.insert(10, 1000);    // success
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}