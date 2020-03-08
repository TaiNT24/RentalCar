/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.car;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class CarDTO implements Serializable{
    private int idCar;
    private String carName;
    private String color;
    private int yearProduce;
    private String category;
    private int price;
    private int quantity;
    private int availableQuantity;

    public CarDTO() {
    }

    public CarDTO(int idCar, String carName, String color, int yearProduce, String category, int price, int quantity) {
        this.idCar = idCar;
        this.carName = carName;
        this.color = color;
        this.yearProduce = yearProduce;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYearProduce() {
        return yearProduce;
    }

    public void setYearProduce(int yearProduce) {
        this.yearProduce = yearProduce;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
    
    
}
