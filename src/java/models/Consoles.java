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
public class Consoles {
    private int consolesID;
    private String consolesName;

    public Consoles() {
    }

    public Consoles(int consolesID, String consolesName) {
        this.consolesID = consolesID;
        this.consolesName = consolesName;
    }

    public int getConsolesID() {
        return consolesID;
    }

    public void setConsolesID(int consolesID) {
        this.consolesID = consolesID;
    }

    public String getConsolesName() {
        return consolesName;
    }

    public void setConsolesName(String consolesName) {
        this.consolesName = consolesName;
    }
    
    
}
