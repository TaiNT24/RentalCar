/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import taint.model.car.CarDAO;
import taint.model.cart.CartDAO;
import taint.model.cart.CartDTO;
import taint.model.rentCar.RentCarDAO;
import taint.model.rentCar.RentCarDTO;

/**
 *
 * @author nguye
 */
@ResultPath("/")
@Results({
    @Result(name = "success", location = "Home.jsp")
    ,
    @Result(name = "fail", location = "Login.jsp")
})
public class AddtocartAction {

    private int idCar;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public AddtocartAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;

        Map session = ActionContext.getContext().getSession();
        String username = (String) session.get("USERNAME");
        if (username != null) {
            CartDAO cartDAO = new CartDAO();
            RentCarDAO rentCarDAO = new RentCarDAO();
            CarDAO carDAO = new CarDAO();

            CartDTO cart = cartDAO.getCartOfUsername(username);
            if (cart == null) {
                cartDAO.createCartForUsername(username);
                cart = cartDAO.getCartOfUsername(username);
            }
            int price = carDAO.getPrice(idCar);

            RentCarDTO dto = new RentCarDTO(idCar, cart.getIdCart(), price, 1, price);

            boolean isInsert = rentCarDAO.insertARentCar(dto);
            if (isInsert) {
                url = SUCCESS;
            }

        }
        return url;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

}
