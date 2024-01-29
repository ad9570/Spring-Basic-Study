package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:**/resources/test-context.xml"})
public class TxServiceTest {
    @Autowired
    TxService txService;

    @Test
    public void insertWithoutTx() {
        try {
            txService.insertWithoutTx();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void insertWithTx() throws Exception {
        txService.insertWithTx();
    }
}