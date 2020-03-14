/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.rentCar;

/**
 *
 * @author nguye
 */
public class DetailsRentCarDTO {

    private int idRent;
    private int idCar;
    private int idCart;
    private int price;
    private int quantity;
    private int totalPrice;
    private String status = "Insert";

    private String dateRentCart;
    private int totalPriceInCart;

    private String carName;
    private String category;

    private String dateRent;
    private String dateReturn;

    public DetailsRentCarDTO() {
    }

    public DetailsRentCarDTO(int idRent, int idCar, int idCart, int price, int quantity, int totalPrice, String carName, String category, String dateRent, String dateReturn) {
        this.idRent = idRent;
        this.idCar = idCar;
        this.idCart = idCart;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.carName = carName;
        this.category = category;
        this.dateRent = dateRent;
        this.dateReturn = dateReturn;
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

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDateRent() {
        return dateRent;
    }

    public void setDateRent(String dateRent) {
        this.dateRent = dateRent;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public String getDateRentCart() {
        return dateRentCart;
    }

    public void setDateRentCart(String dateRentCart) {
        this.dateRentCart = dateRentCart;
    }

    public int getTotalPriceInCart() {
        return totalPriceInCart;
    }

    public void setTotalPriceInCart(int totalPriceInCart) {
        this.totalPriceInCart = totalPriceInCart;
    }

}
