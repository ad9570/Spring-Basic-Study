package com.fastcampus.ch3;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBConnectionTest2Test {
    @Test
    public void mainTest() throws SQLException {
        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        assertNotNull(conn);                                            // assertNotNull(조건식) : 조건식이 null이 아니면 통과
        assertTrue(conn.toString().contains("org.postgresql.jdbc"));    // assertTrue(조건식) : 조건식이 true면 통과
        assertTrue(conn.toString().contains("PgConnection"));
    }
}