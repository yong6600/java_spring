package com;

import java.sql.*;

/**
 * Created by yong on 2017-05-28.
 */
public class userDao {
    // add db table 한줄 추가 메소드
    public void add(User user) throws SQLException {
        Connection con = null;
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring_practice_db", "root","password");
        PreparedStatement ps = con.prepareStatement(
                "insert into users(id,password,name) values(?,?,?)"
        );

        ps.setString(1,user.getId());
        ps.setString(2,user.getPassword());
        ps.setString(3,user.getName());

        ps.executeUpdate();
        ps.close();
        con.close();
    }

    //get db talbe에서 한줄 읽어오는 메소드
    public User get(String id) throws SQLException {

    Connection con  = null;
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring_practice_db", "root","password");

    PreparedStatement ps = con.prepareStatement("select * from users where id = ?");
    ps.setString(1,id);

    ResultSet rs = ps.executeQuery();
    rs.next();


    User user = new User();

    user.setId(rs.getString("id"));
    user.setPassword(rs.getString("password"));
    user.setName(rs.getString("name"));

    rs.close();
    ps.close();
    con.close();

    return user;

}

    public static void main(String[] args) throws SQLException {
        userDao dao = new userDao();

        User user = new User();
        user.setId("abc7");
        user.setPassword("1123");
        user.setName("Yong");

        //dao.add(user);
        User selectedUser = dao.get("abc");

        System.out.println("id : " + selectedUser.getId());
        System.out.println("password : " + selectedUser.getPassword());
        System.out.println("name : " + selectedUser.getName());

    }
}

