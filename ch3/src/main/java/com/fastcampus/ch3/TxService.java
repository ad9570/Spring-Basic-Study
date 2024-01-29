package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxService {
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
}