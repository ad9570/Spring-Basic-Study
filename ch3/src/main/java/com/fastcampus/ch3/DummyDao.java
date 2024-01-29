package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class DummyDao {
    @Autowired
    DataSource ds;

    public void insert(int a, int b) throws SQLException {
        Connection conn = DataSourceUtils.getConnection(ds);
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO dummy_tx VALUES (?, ?)");
        pstmt.setInt(1, a);
        pstmt.setInt(2, b);

        pstmt.executeUpdate();

        close(pstmt);
        DataSourceUtils.releaseConnection(conn, ds);
    }

    private void close(AutoCloseable... acs) {
        for (AutoCloseable ac : acs)
            try {
                if (ac != null) {
                    ac.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
}
