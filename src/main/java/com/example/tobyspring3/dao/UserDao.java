package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.*;

import java.util.Map;

import static java.lang.System.getenv;

public class UserDao {

    SimpleConnectionMaker connectionMaker = new SimpleConnectionMaker();


    public void add(User user) throws ClassNotFoundException, SQLException {

        Connection con = connectionMaker.makeNewConnection();
        PreparedStatement ps = con.prepareStatement("insert into users(id,name,password) values (?,?,?)");
        ps.setString(1,user.getId());
        ps.setString(2,user.getName());
        ps.setString(3,user.getPassword());
        ps.executeUpdate();
        ps.close();
        con.close();

    }

    public User get(String id) throws ClassNotFoundException, SQLException {  //id를 받아서 그 유저를 리턴하는 메서드
        Connection con = connectionMaker.makeNewConnection();

        PreparedStatement ps = con.prepareStatement("select id, name, password from users where id = ?");
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery(); //select는 결과값이 있음
        rs.next();//ctrl + enter

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        con.close();//db랑 연결 끊기    -> 서버에서 안내려가는 것을 쓸때는 굉장히 중요함 connection pool


        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setId("4");
        user.setName("kyeongrok");
        user.setPassword("12345678");

        userDao.add(user);

        User selectedUser = userDao.get("4");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());

    }
}
