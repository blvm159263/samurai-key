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
import models.Genre;
import models.Product;

/**
 *
 * @author buile
 */
public class ProductDAO {

    public List<Product> listNew() {
        List<Product> list = null;
        DBUtil db = new DBUtil();
        try {
            list = new ArrayList<>();
            Connection con = db.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT TOP(6) ProductID,ProductPrice,ProductName,LinkIMG1,Product.GenreID, GenreName\n"
                    + "FROM dbo.Product  LEFT JOIN dbo.Genre \n"
                    + "ON Genre.GenreID = Product.GenreID\n"
                    + "ORDER BY ProductID DESC");
            while (rs.next()) {
                int productID = rs.getInt(1);
                int price = rs.getInt(2);
                String productName = rs.getString(3);
                String linkImg1 = rs.getString(4);
                int genreID = rs.getInt(5);
                String genreName = rs.getString(6);
                Genre genre = new Genre(genreID, genreName);
                Product pro = new Product(productID, price, productName, linkImg1, genre);
                list.add(pro);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> listHome() {
        List<Product> list = null;
        DBUtil db = new DBUtil();
        try {
            list = new ArrayList<>();
            Connection con = db.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT ProductID, ProductPrice,ProductName,LinkIMG1\n"
                    + "FROM dbo.Product \n"
                    + "ORDER BY ProductName ASC\n"
                    + "OFFSET 0 ROWS FETCH NEXT 12 ROWs ONLY");
            while (rs.next()) {
                int productID = rs.getInt(1);
                int price = rs.getInt(2);
                String productName = rs.getString(3);
                String linkImg1 = rs.getString(4);
                Product pro = new Product(productID, price, productName, linkImg1);
                list.add(pro);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Product> listAll() {
        List<Product> list = null;
        DBUtil db = new DBUtil();
        try {
            list = new ArrayList<>();
            Connection con = db.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT ProductID, ProductPrice,ProductName,LinkIMG1\n"
                    + "FROM dbo.Product \n"
                    + "ORDER BY ProductName ASC\n");
            while (rs.next()) {
                int productID = rs.getInt(1);
                int price = rs.getInt(2);
                String productName = rs.getString(3);
                String linkImg1 = rs.getString(4);
                Product pro = new Product(productID, price, productName, linkImg1);
                list.add(pro);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public int minPrice(){
        int price = 0;
        DBUtil db = new DBUtil();
        try {
            Connection con = db.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT MIN(ProductPrice) FROM dbo.Product");
            while (rs.next()) {
                price = rs.getInt(1);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }
    public int maxPrice(){
        int price = 0;
        DBUtil db = new DBUtil();
        try {
            Connection con = db.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT MAX( ProductPrice) FROM dbo.Product");
            while (rs.next()) {
                price = rs.getInt(1);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

//    public static void main(String[] args) {
//        ProductDAO pd = new ProductDAO();
//        int price = pd.maxPrice();
//        System.out.println(price);
//        
//    }
}
