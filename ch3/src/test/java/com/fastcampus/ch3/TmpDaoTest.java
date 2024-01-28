package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:**/resources/test-context.xml"})
public class TmpDaoTest {
    @Autowired
    DataSource ds;

    @Autowired
    TmpDao tmpDao;

    @Test
    public void insert() {
        // TxManager 생성 - 시작
        PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
        TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());

        try {
            tmpDao.insert(8, 800);
            tmpDao.insert(8, 800);
            // 성공 : 커밋 - Tx 종료
            tm.commit(status);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // 실패 : 롤백 - Tx 종료
            tm.rollback(status);
        }
    }
}