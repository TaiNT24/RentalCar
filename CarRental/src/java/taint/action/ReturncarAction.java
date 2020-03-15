/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import java.util.List;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import taint.model.rentCar.RentCarDAO;
import taint.model.rentCar.RentCarDTO;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Results({
    @Result(name = "success", type = "redirectAction",
            params = {
                "actionName", "admin"
            })
    ,
//    @Result(name = "fail", location = "AdminHome.jsp")
})
public class ReturncarAction {

    private int CartID;
    
    private final String SUCCESS = "success";
//    private final String FAIL = "fail";

    public ReturncarAction() {
    }

    public String execute() throws Exception {
        String url = SUCCESS;

        RentCarDAO rentCarDAO = new RentCarDAO();

        List<RentCarDTO> listRent = rentCarDAO.getListRentCarOfUser(CartID);
        if (listRent.get(0).getStatus().equals("Paymented")) {
            rentCarDAO.setStatusCarRent(listRent, "Returned");

            url = SUCCESS;
        }

        return url;
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int CartID) {
        this.CartID = CartID;
    }
    
}
