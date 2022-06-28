/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Context.DBUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Consoles;

/**
 *
 * @author buile
 */
public class ConsolesDAO {
    public List<Consoles> list() {
        List<Consoles> list = null;
        DBUtil db = new DBUtil();
        try {
            list = new ArrayList<>();
            Connection con = db.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM dbo.Consoles");
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Consoles console = new Consoles(id, name);
                list.add(console);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
