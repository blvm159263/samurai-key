/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author buile
 */
public class Checkout {
    private int checkoutID;
    private String fullName;
    private String address;
    private double total;
    private String email;
    private String phone;
    private Date dateOder;
    private int userID;

    public Checkout() {
    }

    public Checkout(int checkoutID, String fullName, String address, double total, String email, String phone, Date dateOder, int userID) {
        this.checkoutID = checkoutID;
        this.fullName = fullName;
        this.address = address;
        this.total = total;
        this.email = email;
        this.phone = phone;
        this.dateOder = dateOder;
        this.userID = userID;
    }

    public int getCheckoutID() {
        return checkoutID;
    }

    public void setCheckoutID(int checkoutID) {
        this.checkoutID = checkoutID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOder() {
        return dateOder;
    }

    public void setDateOder(Date dateOder) {
        this.dateOder = dateOder;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    
}
