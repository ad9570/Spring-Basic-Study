package com.fastcampus.ch3;

import java.sql.*;

public class DBConnectionTest {
    // JDBC(Java DataBase Connectivity) API
    public static void main(String[] args) throws Exception {
        // DB종류, host주소, port번호, schema이름에 따라 알맞게 변경
        final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

        // DB의 userid와 pwd를 알맞게 변경해야 함
        final String DB_USER = "postgres";
        final String DB_PASSWORD = "1";

        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);    // 데이터베이스의 연결을 얻는다.
        Statement stmt = conn.createStatement();    // Statement를 생성한다.

        String query = "SELECT now()";              // 시스템의 현재 날짜시간을 출력하는 쿼리(query)
        ResultSet rs = stmt.executeQuery(query);    // query를 실행한 결과를 ResultSet(2차원 테이블 형태의 자료구조)에 담는다.

        // 실행결과가 담긴 rs에서 한 줄씩 읽어서 출력
        while (rs.next()) {
            String curDate = rs.getString(1);   // 읽어온 행의 첫번째 컬럼의 값을 String으로 읽어서 curDate에 저장
            System.out.println(curDate);                // 2022-01-11 13:53:00.0
        }
    }
}