/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import taint.model.cart.CartDAO;
import taint.model.cart.CartDTO;
import taint.model.rentCar.RentCarDAO;
import taint.model.rentCar.RentCarDTO;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Result(name = "success", location = "ShoppingCart.jsp")
public class GotocartAction {
    private List<RentCarDTO> listRentCar;
    private final String SUCCESS = "success";
    
    public GotocartAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        
        String email = (String) session.get("USER_EMAIL");
        
        CartDAO cartDAO = new CartDAO();
        RentCarDAO rentCarDAO = new RentCarDAO();
        
        CartDTO cart = cartDAO.getCurrentCartOfUser(email);
        if(cart == null){
            cartDAO.createCartForUsername(email);
            cart = cartDAO.getCurrentCartOfUser(email);
        }
        
        listRentCar = 
                rentCarDAO.getListRentCarOfUser(cart.getIdCart());
        
        
        
        return SUCCESS;
    }

    public List<RentCarDTO> getListRentCar() {
        return listRentCar;
    }

    public void setListRentCar(List<RentCarDTO> listRentCar) {
        this.listRentCar = listRentCar;
    }

    
}
