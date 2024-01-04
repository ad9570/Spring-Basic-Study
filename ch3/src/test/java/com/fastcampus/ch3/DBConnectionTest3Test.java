package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})  // ApplicationContext를 자동으로 생성
public class DBConnectionTest3Test {
    @Autowired
    DataSource ds;

    @Test
    public void insertUserTest() throws SQLException {
        Calendar cal = Calendar.getInstance();
        cal.set(1995, 4, 25);
        User user = new User("zxcv2", "9090", "who", "ad9570@daum.net", cal.getTime(), "twitter");

        int result = insertUser(user);

        assertTrue(result > 0);
    }

    @Test
    public void selectUserTest() throws SQLException {
        User user = selectUser("zxcv2");

        assertEquals("zxcv2", user.getId());
    }

    @Test
    public void updateUserTest() throws SQLException {
        int result = updateUser("who");

        assertTrue(result > 0);
    }

    @Test
    public void deleteUserTest() throws SQLException {
        int result = deleteUser("zxcv2");

        assertTrue(result > 0);
    }

    // 사용자 정보를 user_info 테이블에 저장하는 메소드
    public int insertUser(User user) throws SQLException {
        Connection conn = ds.getConnection();
        String sql = "INSERT INTO user_info VALUES (?, ?, ?, ?, ?, ?, now())";

        // PreparedStatement : 기존에 사용하던 Statement에 비해 SQL Injection 공격에 강함, 가독성 향상('"+ 제거), 성능 향상(쿼리문 재사용으로 캐싱 효과)
        // SQL Injection : 파라미터에 SQL문을 넣어 쿼리의 정상작동을 방해하는 해커들의 공격 방법
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // pstmt.set타입(N번째 ?, 값) 의 형태
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getPwd());
        pstmt.setString(3, user.getNm());
        pstmt.setString(4, user.getEmail());
        /*
        // PreparedStatement의 setDate()는 java.sql.Date를 사용하므로, User 객체에서 사용하던 java.util.Date를 형변환 해줘야 함.
        java.util.Date utilDate = user.getBirth();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        pstmt.setDate(5, sqlDate);
        */
        pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(6, user.getSns());

        // PreparedStatement의 executeUpdate()는 insert, update, delete문에만 사용 가능하며, 쿼리 실행 결과로 영향을 받은 행의 수를 반환한다.
        int result = pstmt.executeUpdate();
        System.out.println("inserted row(s) count : " + result);

        return result;
    }

    public User selectUser(String id) throws SQLException {
        Connection conn = ds.getConnection();
        String sql = "SELECT * FROM user_info WHERE id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        // PreparedStatement의 executeQuery()는 select문에만 사용 가능하며, 2차원 테이블 형태의 자료형인 ResultSet을 반환한다.
        ResultSet rs = pstmt.executeQuery();

        User user = null;
        if (rs.next()) {
            user = new User(
                    // pstmt.get타입(rs의 N번째 컬럼) 의 형태
                    rs.getString(1),                                // id
                    rs.getString(2),                                // pwd
                    rs.getString(3),                                // nm
                    rs.getString(4),                                // email
                    new java.util.Date(rs.getDate(5).getTime()),    // birth
                    rs.getString(6),                                // sns
                    new java.util.Date(rs.getDate(7).getTime())     // reg_date
            );
            System.out.println("result set : " + user);
        }

        return user;
    }

    public int updateUser(String nm) throws SQLException {
        Connection conn = ds.getConnection();
        String sql = "UPDATE user_info SET sns = 'facebook' WHERE nm = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, nm);

        int result = pstmt.executeUpdate();
        System.out.println("updated row(s) count : " + result);

        return result;
    }

    public int deleteUser(String id) throws SQLException {
        Connection conn = ds.getConnection();
        String sql = "DELETE FROM user_info WHERE id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        int result = pstmt.executeUpdate();
        System.out.println("deleted row(s) count : " + result);

        return result;
    }

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