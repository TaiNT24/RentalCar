/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.cart;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class CartDTO implements Serializable{
    private int idCart;
    private String email;
    private int totalPrice = 0;
    private int idDiscount = 0;
    private String dateRent = null;

    public CartDTO() {
    }

    public CartDTO(int idCart, String email, int totalPrice) {
        this.idCart = idCart;
        this.email = email;
        this.totalPrice = totalPrice;
    }

    public CartDTO(int idCart, String email, int totalPrice, String dateRent) {
        this.idCart = idCart;
        this.email = email;
        this.totalPrice = totalPrice;
        this.dateRent = dateRent;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDateRent() {
        return dateRent;
    }

    public void setDateRent(String dateRent) {
        this.dateRent = dateRent;
    }

    public int getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(int idDiscount) {
        this.idDiscount = idDiscount;
    }
    
    
}
