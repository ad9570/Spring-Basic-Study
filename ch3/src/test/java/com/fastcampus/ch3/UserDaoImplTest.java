package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {
    @Autowired
    UserDao userDao;

    @Test
    public void deleteUser() {
        int rowCnt = userDao.deleteUser("pppp");

        assertEquals(1, rowCnt);
    }

    @Test
    public void selectUser() {
        User selUser = userDao.selectUser("pppp");

        System.out.println("selUser = " + selUser);
        assertEquals("pppp", selUser.getId());
    }

    @Test
    public void insertUser() {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(1997, 0, 1);
        User user = new User("pppp", "9090", "who", "nnnn@naver.com", cal.getTime(), "instagram", new Date());

        int rowCnt = userDao.insertUser(user);

        assertEquals(1, rowCnt);
    }

    @Test
    public void updateUser() {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(1995, 4, 25);
        User user = new User("zxcv", "9090", "who", "ad9570@daum.net", cal.getTime(), "facebook", new Date());

        int rowCnt = userDao.updateUser(user);

        assertEquals(1, rowCnt);
    }
}