/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import java.util.Date;
import java.util.List;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import taint.model.cart.CartDAO;
import taint.model.rentCar.RentCarDAO;
import taint.model.rentCar.RentCarDTO;
import taint.utils.Utils;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Result(name = "success", location = "ShoppingCart.jsp")
public class PaymentcartAction {

    private int idCart;
    private String Paymented;
    private int totalPriceAfterUseVoucher;
    private String CodeDiscountValue;
    
    private final String SUCCESS = "success";

    public PaymentcartAction() {
    }

    public String execute() throws Exception {
        Date now = new Date();
        String dateRent = Utils.formatDateToStringDateSQL(now);
        
        CartDAO cartDAO = new CartDAO();
        RentCarDAO rentCarDAO = new RentCarDAO();
        
        boolean isPayment = cartDAO.updateDateRentCart(idCart, dateRent);
        if(totalPriceAfterUseVoucher!=0){
            cartDAO.updateTotalPriceRentCartAfterUseVoucher(idCart, totalPriceAfterUseVoucher, CodeDiscountValue);
        }
        if(isPayment){
            List<RentCarDTO> listRent = rentCarDAO.getListRentCarOfUser(idCart);
            
            isPayment = rentCarDAO.setStatusCarRent(listRent, "Paymented");
            if(isPayment){
                Paymented = "Payment successful !!";
            }
        }
        
        return SUCCESS;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public String getPaymented() {
        return Paymented;
    }

    public void setPaymented(String Paymented) {
        this.Paymented = Paymented;
    }

    public int getTotalPriceAfterUseVoucher() {
        return totalPriceAfterUseVoucher;
    }

    public void setTotalPriceAfterUseVoucher(int totalPriceAfterUseVoucher) {
        this.totalPriceAfterUseVoucher = totalPriceAfterUseVoucher;
    }

    public String getCodeDiscountValue() {
        return CodeDiscountValue;
    }

    public void setCodeDiscountValue(String CodeDiscountValue) {
        this.CodeDiscountValue = CodeDiscountValue;
    }

    
}
