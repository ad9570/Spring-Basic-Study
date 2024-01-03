package com.fastcampus.ch3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionTest2 {
    public static void main(String[] args) throws SQLException {
//        // DB종류, host주소, port번호, schema이름에 따라 알맞게 변경
//        final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
//
//        // DB의 userid와 pwd를 알맞게 변경해야 함
//        final String DB_USER = "postgres";
//        final String DB_PASSWORD = "1";
//        final String DB_DRIVER = "org.postgresql.Driver";
//
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName(DB_DRIVER);
//        ds.setUrl(DB_URL);
//        ds.setUsername(DB_USER);
//        ds.setPassword(DB_PASSWORD);

        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
    }
}
