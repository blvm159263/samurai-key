/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author buile
 */
public class Product {

    private int productID;
    private int price;
    private String productName;
    private byte quantity;
    private String desc;
    private byte rating;
    private String linkImg1;
    private String linkImg2;
    private String linkImg3;
    private String linkImg4;
    private String linkImg5;
    Genre genre = new Genre();
    Consoles console = new Consoles();

    public Product() {
    }

    public Product(int productID, int price, String productName, String linkImg1, Genre genre) {
        this.productID = productID;
        this.price = price;
        this.productName = productName;
        this.linkImg1 = linkImg1;
        this.genre = genre;
    }

    public Product(int id, int price, String productName, byte quantity, String desc, byte rating, String linkImg1, String linkImg2, String linkImg3, String linkImg4, String linkImg5) {
        this.productID = id;
        this.price = price;
        this.productName = productName;
        this.quantity = quantity;
        this.desc = desc;
        this.rating = rating;
        this.linkImg1 = linkImg1;
        this.linkImg2 = linkImg2;
        this.linkImg3 = linkImg3;
        this.linkImg4 = linkImg4;
        this.linkImg5 = linkImg5;
    }

    public Product(int id, int price, String productName, byte quantity, String desc, byte rating, String linkImg1, String linkImg2, String linkImg3, String linkImg4, String linkImg5, Genre genre, Consoles console) {
        this.productID = id;
        this.price = price;
        this.productName = productName;
        this.quantity = quantity;
        this.desc = desc;
        this.rating = rating;
        this.linkImg1 = linkImg1;
        this.linkImg2 = linkImg2;
        this.linkImg3 = linkImg3;
        this.linkImg4 = linkImg4;
        this.linkImg5 = linkImg5;
        this.genre = genre;
        this.console = console;
    }

    public Product(int productID, int price, String productName, String linkImg1) {
        this.productID = productID;
        this.price = price;
        this.productName = productName;
        this.linkImg1 = linkImg1;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public byte getQuantity() {
        return quantity;
    }

    public void setQuantity(byte quantity) {
        this.quantity = quantity;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public String getLinkImg1() {
        return linkImg1;
    }

    public void setLinkImg1(String linkImg1) {
        this.linkImg1 = linkImg1;
    }

    public String getLinkImg2() {
        return linkImg2;
    }

    public void setLinkImg2(String linkImg2) {
        this.linkImg2 = linkImg2;
    }

    public String getLinkImg3() {
        return linkImg3;
    }

    public void setLinkImg3(String linkImg3) {
        this.linkImg3 = linkImg3;
    }

    public String getLinkImg4() {
        return linkImg4;
    }

    public void setLinkImg4(String linkImg4) {
        this.linkImg4 = linkImg4;
    }

    public String getLinkImg5() {
        return linkImg5;
    }

    public void setLinkImg5(String linkImg5) {
        this.linkImg5 = linkImg5;
    }

}
