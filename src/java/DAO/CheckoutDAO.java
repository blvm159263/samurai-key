/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Context.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Checkout;

/**
 *
 * @author buile
 */
public class CheckoutDAO {

    public boolean addOrder(String fullName, String address, double total, String email, String phone, String date, int userID) throws SQLException, Exception {
        Checkout order = null;
        DBUtil db = new DBUtil();
        Connection con = db.getConnection();
        String sql = "INSERT INTO dbo.Checkout values (?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, fullName);
        stm.setString(2, address);
        stm.setDouble(3, total);
        stm.setString(4, email);
        stm.setString(5, phone);
        stm.setString(6, date);
        stm.setInt(7, userID);
        int count = stm.executeUpdate();
        con.close();
        return count == 1;
    }
}
