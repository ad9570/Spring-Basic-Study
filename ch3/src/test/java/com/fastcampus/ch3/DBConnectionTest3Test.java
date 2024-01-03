package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})  // ApplicationContext를 자동으로 생성
public class DBConnectionTest3Test {
    @Autowired
    DataSource ds;

    @Test
    public void mainTest() throws SQLException {
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        assertNotNull(conn);                                            // assertNotNull(조건식) : 조건식이 null이 아니면 통과
        assertTrue(conn.toString().contains("org.postgresql.jdbc"));    // assertTrue(조건식) : 조건식이 true면 통과
        assertTrue(conn.toString().contains("PgConnection"));
    }
}