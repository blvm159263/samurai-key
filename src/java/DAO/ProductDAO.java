/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Context.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public int minPrice() {
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

    public int maxPrice() {
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

    public List<Product> filter(String genreID, String consolesID, int minPrice, int maxPrice, String rating) {
        List<Product> list = null;
        DBUtil db = new DBUtil();
        String genreCondition = " ";
        String consolesCondition = " ";
        String ratingCondition = " ";

        if (genreID != null) {
            genreCondition = " genreID = " + genreID + " and ";
        }
        if (consolesID != null) {
            consolesCondition = " consolesID = " + consolesID + " and ";
        }
        if (rating != null) {
            ratingCondition = " rating = " + rating + " and ";
        }
        try {
            list = new ArrayList<>();
            Connection con = db.getConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT ProductID, ProductPrice,ProductName,LinkIMG1\n"
                    + "FROM dbo.Product \n"
                    + "Where" + genreCondition + consolesCondition + ratingCondition + "ProductPrice >= ? AND ProductPrice <= ?");
            pstm.setInt(1, minPrice);
            pstm.setInt(2, maxPrice);
            ResultSet rs = pstm.executeQuery();
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

    public List<Product> findProductByName(String fname) {
        List<Product> list = null;
        DBUtil db = new DBUtil();
        if (fname == null) {
            fname = "";
        }
        try {
            list = new ArrayList<>();
            Connection con = db.getConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT ProductID, ProductPrice,ProductName,LinkIMG1\n"
                    + "FROM dbo.Product \n"
                    + "Where ProductName LIKE ?");
            pstm.setString(1, "%"+fname+"%");
            ResultSet rs = pstm.executeQuery();
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
        }
        return list;
    }

public Product getProductbyID(String pid) {
        DBUtil db = new DBUtil();
        String query = "SELECT ProductID,ProductPrice,ProductName,ProductQuantity, ProductDesc,Rating,"
                + "LinkIMG1,LinkIMG2,LinkIMG3,LinkIMG4,LinkIMG5 "
                + "FROM dbo.Product WHERE ProductID=?";
        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getByte(4), rs.getString(5),
                        rs.getByte(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        ProductDAO pd = new ProductDAO();
//        List<Product> list = null;
//        list = pd.findProductByName(null);
//        for (Product p : list) {
//            System.out.println(p.getProductID());
//        }
//    }
}
