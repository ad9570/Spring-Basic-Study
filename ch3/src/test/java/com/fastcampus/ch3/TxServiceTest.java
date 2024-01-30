package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    public void insertWithTx() {
        try {
            txService.insertWithTx();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void insertRequiredOuter() {
        txService.insertBoundaryMainOuter("req");
    }

    @Test
    public void insertRequiresNewOuter() {
        txService.insertBoundaryMainOuter("new");
    }

    @Test
    public void insertRequiredInner() {
        txService.insertBoundaryMainInner("req");
    }

    @Test
    public void insertRequiresNewInner() {
        txService.insertBoundaryMainInner("new");
    }
}