/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Le Nguyen Nhat Minh
 */
public class Cart {
    private List<Product> list = null;

    public Cart() {
        list = new ArrayList<>();
    }
    
    public void add(Product product){
        list.add(product);
    }

    public List<Product> getList() {
        return list;
    }
    
    public void empty(){
        list.clear();
    }
}