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
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import taint.model.car.CarDAO;
import taint.model.rentCar.DetailsRentCarDTO;
import taint.utils.Utils;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Results({
    @Result(name = "success", type = "redirectAction", 
            params = {
                "actionName","paymentcart",
                "idCart","${idCartNeedCheck}"
            }
    )
    ,
    @Result(name = "fail", location = "ShoppingCart.jsp")
})

public class CheckquantityAction {

    private int idCartNeedCheck;
    private List<DetailsRentCarDTO> listRentCarDetails;
    private List<Integer> listRentCarOutOfStock;
    private int totalCart;

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public CheckquantityAction() {
    }

    public String execute() throws Exception {
        String url = SUCCESS;
        listRentCarOutOfStock = new ArrayList<>();

        Map session = ActionContext.getContext().getSession();
        listRentCarDetails
                = (List<DetailsRentCarDTO>) session.get("LIST_RENT_CAR_IN_CART");
        totalCart = (int) session.get("TOTAL_IN_CART");

        CarDAO dao = new CarDAO();

        for (DetailsRentCarDTO dto : listRentCarDetails) {
            String dateRent = dto.getDateRent();
            String dateReturn = dto.getDateReturn();
            int idCar = dto.getIdCar();

            int quantityWantRent = dto.getQuantity();

            int quantityInStock = dao.getAvailableQuantity(idCar, dateRent, dateReturn);
            if (quantityWantRent > quantityInStock) {
                listRentCarOutOfStock.add(idCar);
                url = FAIL;
            }
        }

        return url;
    }

    public int getIdCartNeedCheck() {
        return idCartNeedCheck;
    }

    public void setIdCartNeedCheck(int idCartNeedCheck) {
        this.idCartNeedCheck = idCartNeedCheck;
    }

    public List<DetailsRentCarDTO> getListRentCarDetails() {
        return listRentCarDetails;
    }

    public void setListRentCarDetails(List<DetailsRentCarDTO> listRentCarDetails) {
        this.listRentCarDetails = listRentCarDetails;
    }

    public List<Integer> getListRentCarOutOfStock() {
        return listRentCarOutOfStock;
    }

    public void setListRentCarOutOfStock(List<Integer> listRentCarOutOfStock) {
        this.listRentCarOutOfStock = listRentCarOutOfStock;
    }

    public int getTotalCart() {
        return totalCart;
    }

    public void setTotalCart(int totalCart) {
        this.totalCart = totalCart;
    }

    
}
