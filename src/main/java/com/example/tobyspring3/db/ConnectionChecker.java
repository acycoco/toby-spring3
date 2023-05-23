package com.example.tobyspring3.db;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class ConnectionChecker {
    private Map<String, String> env = getenv();
    private String dbHost = env.get("DB_HOST");
    private String dbUser = env.get("DB_USER");
    private String dbPassword = env.get("DB_PASSWORD");
    public void check() throws SQLException, ClassNotFoundException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(dbHost,
                dbUser, dbPassword);

        Statement st = con.createStatement();
        st.executeQuery("SHOW DATABASES");
        ResultSet rs = st.getResultSet();
        while (rs.next()) {
            String str = rs.getString(1);
            System.out.println(str);
        }
    }

    public void add() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(dbHost,
                dbUser, dbPassword);

        PreparedStatement pstmt = con.prepareStatement("insert into users(id,name,password) values (?,?,?)");   //query를 반복적으로 실행할 때 씀   ? -> 나중에 값을 받음 그리고 완성되지 않은 쿼리를 넣을 수 있음
        pstmt.setString(1,"1");
        pstmt.setString(2,"kyeongrok");
        pstmt.setString(3,"12345678");
        pstmt.executeUpdate();
    }

    public void select() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(dbHost,
                dbUser, dbPassword);


        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from users");


        while (rs.next()){
            String str = rs.getString(1); //column 가져오기
            String str2 = rs.getString(2);
            String str3 = rs.getString(3);
            System.out.println(str + str2 + str3);
        }

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionChecker cc = new ConnectionChecker();
        cc.select();


    }
}

