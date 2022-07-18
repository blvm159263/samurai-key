/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Context.DBUtil;
import Context.Hasher;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.User;

/**
 *
 * @author Lenovo
 */
public class UserDAO {

    // Check user servlet
    public static User check(String userName, String password) throws Exception {
        User user = null;
        //Connecting to a database
        DBUtil db = new DBUtil();
        Connection con = db.getConnection();
        //Creating and executing sql statements            
        String sql = "select * from Users where UserName=? and Passwords=?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, userName);
        stm.setString(2, Hasher.hash(password));
        ResultSet rs = stm.executeQuery();
        //if userId and password are correct
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt(1));
            user.setUserName(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setRole(rs.getString(4));
        }
        //Closing the connection
        con.close();
        return user;
    }

    // Check user cookies
    public static User check_web(String userName, String password) throws Exception {
        User user = null;
        //Connecting to a database
        DBUtil db = new DBUtil();
        Connection con = db.getConnection();
        //Creating and executing sql statements            
        String sql = "select * from Users where UserName=? and Passwords=?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, userName);
        stm.setString(2, password);
        ResultSet rs = stm.executeQuery();
        //if userId and password are correct
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt(1));
            user.setUserName(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setRole(rs.getString(4));
        }
        //Closing the connection
        con.close();
        return user;
    }

    // Get user by userName to check duplication
    public static int check_duplication(String userName) throws Exception {
        int noOfUser = 0;
        //Connecting to a database
        DBUtil db = new DBUtil();
        Connection con = db.getConnection();
        //Creating and executing sql statements            
        String sql = "select count(UserID) as noOfUser from Users where UserName=?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, userName);
        ResultSet rs = stm.executeQuery();
        //if userId and password are correct
        if (rs.next()) {
            noOfUser = rs.getInt("noOfUser");
        }
        //Closing the connection
        con.close();
        return noOfUser;
    }

//    public static boolean register(User user) throws Exception {
//        //Connecting to a database
//        DBUtil db = new DBUtil();
//        Connection con = db.getConnection();
//        //Creating and executing sql statements            
//        String sql = "insert Users values(?, ?, ?)";
//        PreparedStatement stm = con.prepareStatement(sql);
//        stm.setString(1, user.getUserName());
//        stm.setString(2, Hasher.hash(user.getPassword()));
//        stm.setString(3, user.getRole());
//        int count = stm.executeUpdate();
//        //Closing the connection
//        con.close();
//        return count == 1;
//    }
    public static boolean register(User user) throws Exception {
        //Connecting to a database
        DBUtil db = new DBUtil();
        Connection con = db.getConnection();
        boolean done = true;
        //Check_duplication
        if (check_duplication(user.getUserName()) != 0) {
            done = false;
        } else {
            //Creating and executing sql statements            
            String sql = "insert Users values(?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, user.getUserName());
            stm.setString(2, Hasher.hash(user.getPassword()));
            stm.setString(3, "USER");
            stm.executeUpdate();
            //Closing the connection
            con.close();
        }

        return done;
    }

    public static User find(String userName) throws Exception {
        User user = null;
        //Connecting to a database
        DBUtil db = new DBUtil();
        Connection con = db.getConnection();
        //Creating and executing sql statements            
        String sql = "select * from Users where UserName=?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, userName);
        ResultSet rs = stm.executeQuery();
        //if userId and password are correct
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt(1));
            user.setUserName(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setRole(rs.getString(4));
        }
        //Closing the connection
        con.close();
        return user;
    }

    public void update(User user) throws Exception {
        //Connecting to a database
        DBUtil db = new DBUtil();
        Connection con = db.getConnection();
        PreparedStatement stm = con.prepareStatement("update users set Passwords=? where UserID=?");
        stm.setString(1, Hasher.hash(user.getPassword()));
        stm.setInt(2, user.getId());
        stm.executeUpdate();
    }
}
