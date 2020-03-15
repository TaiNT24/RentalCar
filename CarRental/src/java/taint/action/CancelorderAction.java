/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import taint.model.cart.CartDAO;
import taint.model.rentCar.DetailsRentCarDTO;
import taint.model.rentCar.RentCarDAO;
import taint.model.rentCar.RentCarDTO;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Results({
    @Result(name = "success", location = "HistoryShopping.jsp"), //    @Result(name = "fail", location = "123")
})
public class CancelorderAction {

    private int CartID;
    private List<Integer> listIDCart;
    private Hashtable<Integer, List<DetailsRentCarDTO>> listHistory;

    private final String SUCCESS = "success";
//    private final String FAIL = "fail";

    public CancelorderAction() {
    }

    public String execute() throws Exception {
        String url = SUCCESS;

        RentCarDAO rentCarDAO = new RentCarDAO();

        Map session = ActionContext.getContext().getSession();
        listIDCart = (List<Integer>) session.get("LIST_ID_CART_HISTORY");
        listHistory = (Hashtable<Integer, List<DetailsRentCarDTO>>) session.get("LIST_CAR_RENT_HISTORY");

        
        List<RentCarDTO> listRent = rentCarDAO.getListRentCarOfUser(CartID);
        if (listRent.get(0).getStatus().equals("Paymented")) {
            rentCarDAO.setStatusCarRent(listRent, "Canceled");

            if (listHistory.containsKey(CartID)) {
                List<DetailsRentCarDTO> listDetail = listHistory.get(CartID);
                for (DetailsRentCarDTO dto : listDetail) {
                    dto.setStatus("Canceled");
                }
            }
        }

        return url;
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int CartID) {
        this.CartID = CartID;
    }

    public List<Integer> getListIDCart() {
        return listIDCart;
    }

    public void setListIDCart(List<Integer> listIDCart) {
        this.listIDCart = listIDCart;
    }

    public Hashtable<Integer, List<DetailsRentCarDTO>> getListHistory() {
        return listHistory;
    }

    public void setListHistory(Hashtable<Integer, List<DetailsRentCarDTO>> listHistory) {
        this.listHistory = listHistory;
    }

}
