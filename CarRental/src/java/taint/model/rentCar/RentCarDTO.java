/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.rentCar;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class RentCarDTO implements Serializable{
    private int idRent;
    private int idCar;
    private int idCart;
    private int price;
    private int quantity;
    private int totalPrice;
    private String status = "Insert";

    public RentCarDTO() {
    }

    public RentCarDTO(int idCar, int idCart, int price, int quantity, int totalPrice) {
        this.idCar = idCar;
        this.idCart = idCart;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    

    public int getIdRent() {
        return idRent;
    }

    public void setIdRent(int idRent) {
        this.idRent = idRent;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
