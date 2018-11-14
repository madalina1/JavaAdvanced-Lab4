package com.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbInit {
    private PreparedStatement pst = null;
    private Connection con = null;
    private ResultSet rs = null;

    public DbInit() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "cami");
    }

    public ResultSet ExecuteSqlGet(String sql) throws SQLException {
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        return rs;
    }

    public boolean ExecuteSqlPost(String sql) throws SQLException {
        pst = con.prepareStatement(sql);
        return pst.executeUpdate() > 0;
    }

    public int countRows(String dataTable) throws SQLException {
        PreparedStatement pst = con.prepareStatement("select count(*) from " + dataTable);
        ResultSet rs = pst.executeQuery();
        int numberRows = 0;
        while (rs.next()) {
            numberRows = rs.getInt(1);
        }
        return numberRows;
    }
}
