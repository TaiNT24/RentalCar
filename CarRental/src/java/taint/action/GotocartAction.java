/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import taint.model.car.CarDAO;
import taint.model.cart.CartDAO;
import taint.model.cart.CartDTO;
import taint.model.dateTimeRentCar.DateTimeRentCarDAO;
import taint.model.dateTimeRentCar.DateTimeRentCarDTO;
import taint.model.rentCar.DetailsRentCarDTO;
import taint.model.rentCar.RentCarDAO;
import taint.model.rentCar.RentCarDTO;
import taint.utils.Utils;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Result(name = "success", location = "ShoppingCart.jsp")
public class GotocartAction {

    private int totalCart;
    private List<DetailsRentCarDTO> listRentCarDetails;

    private final String SUCCESS = "success";

    public GotocartAction() {
    }

    public String execute() throws Exception {
        List<RentCarDTO> listRentCar;
        List<DateTimeRentCarDTO> listDTRC;

        Map session = ActionContext.getContext().getSession();

        String email = (String) session.get("USER_EMAIL");

        CartDAO cartDAO = new CartDAO();
        CarDAO carDAO = new CarDAO();
        RentCarDAO rentCarDAO = new RentCarDAO();
        DateTimeRentCarDAO dtrcDAO = new DateTimeRentCarDAO();

        CartDTO cart = cartDAO.getCurrentCartOfUser(email);
        if (cart == null) {
            cartDAO.createCartForUsername(email);
            cart = cartDAO.getCurrentCartOfUser(email);
        }

        totalCart = cart.getTotalPrice();
        listRentCar
                = rentCarDAO.getListRentCarOfUser(cart.getIdCart());

        listDTRC = dtrcDAO.getListDateRentCarOfUser(listRentCar);

        listRentCarDetails = new ArrayList<>();
        for (int i = 0; i < listRentCar.size(); i++) {
            int idRent = listRentCar.get(i).getIdRent();
            int idCar = listRentCar.get(i).getIdCar();
            int idCart = listRentCar.get(i).getIdCart();
            int price = listRentCar.get(i).getPrice();
            int quantity = listRentCar.get(i).getQuantity();
            int totalPrice = listRentCar.get(i).getTotalPrice();
            
            StringTokenizer stk1 = new StringTokenizer(listDTRC.get(i).getDateRent()," ");
            StringTokenizer stk2 = new StringTokenizer(listDTRC.get(i).getDateReturn()," ");
            
            String dateRent = Utils.formatStringDate2(stk1.nextToken());
            String dateReturn = Utils.formatStringDate2(stk2.nextToken());
            
            String carName = carDAO.getName(idCar);
            String category = carDAO.getCategory(idCar);
            
            DetailsRentCarDTO dto = new DetailsRentCarDTO(idRent, idCar, idCart
                    , price, quantity, totalPrice, carName, category, dateRent, dateReturn);
            
            listRentCarDetails.add(dto);
        }

        session.put("LIST_RENT_CAR_IN_CART", listRentCarDetails);
        session.put("TOTAL_IN_CART", totalCart);
        
        return SUCCESS;
    }

    public List<DetailsRentCarDTO> getListRentCarDetails() {
        return listRentCarDetails;
    }

    public void setListRentCarDetails(List<DetailsRentCarDTO> listRentCarDetails) {
        this.listRentCarDetails = listRentCarDetails;
    }

    public int getTotalCart() {
        return totalCart;
    }

    public void setTotalCart(int totalCart) {
        this.totalCart = totalCart;
    }

    
}
