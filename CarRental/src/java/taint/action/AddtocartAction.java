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
import taint.model.dateTimeRentCar.DateTimeRentCarDAO;
import taint.model.rentCar.RentCarDAO;
import taint.model.rentCar.RentCarDTO;

/**
 *
 * @author nguye
 */
@ResultPath("/")
@Results({
    @Result(name = "success", type = "redirectAction",
            params = {"actionName", "search",
                "searchVal", "${searchVal}",
                "moreFilter", "${moreFilter}",
                "categoryApply", "${categoryApply}",
                "amount", "${amount}",
                "dateRent", "${dateRent}",
                "dateReturn", "${dateReturn}"
            }
    )
    ,
    @Result(name = "fail", location = "Login.jsp")
})
public class AddtocartAction {

    private int idCar;
    private String searchVal;

    private String moreFilter;
    private String categoryApply;
    private int amount;
    private String dateRent;
    private String dateReturn;

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public AddtocartAction() {

    }

    public String execute() throws Exception {
        String url = FAIL;

        Map session = ActionContext.getContext().getSession();
        String email = (String) session.get("USER_EMAIL");
        if (email != null) {
            CartDAO cartDAO = new CartDAO();
            RentCarDAO rentCarDAO = new RentCarDAO();
            CarDAO carDAO = new CarDAO();

            CartDTO cart = cartDAO.getCurrentCartOfUser(email);
            if (cart == null) {
                cartDAO.createCartForUsername(email);
                cart = cartDAO.getCurrentCartOfUser(email);
            }
            

            int oldQuan = rentCarDAO.checkCarInCart(cart.getIdCart(), idCar);
            if (oldQuan>0) {
                rentCarDAO.increQuantity(cart.getIdCart(), idCar, oldQuan);
            } else {
                int price = carDAO.getPrice(idCar);
                RentCarDTO dto = new RentCarDTO(idCar, cart.getIdCart(), price, 1, price);

                boolean isInsert = rentCarDAO.insertARentCar(dto);
                if (isInsert) {
                    int lastIDRent = rentCarDAO.getLastRentCar();

                    DateTimeRentCarDAO dtrcDAO = new DateTimeRentCarDAO();

                    dtrcDAO.insertADateRentCar(lastIDRent, dateRent, dateReturn);

                    
                }
            }
            url = SUCCESS;
        }

        return url;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
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

    public String getSearchVal() {
        return searchVal;
    }

    public void setSearchVal(String searchVal) {
        this.searchVal = searchVal;
    }

    public String getMoreFilter() {
        return moreFilter;
    }

    public void setMoreFilter(String moreFilter) {
        this.moreFilter = moreFilter;
    }

    public String getCategoryApply() {
        return categoryApply;
    }

    public void setCategoryApply(String categoryApply) {
        this.categoryApply = categoryApply;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
