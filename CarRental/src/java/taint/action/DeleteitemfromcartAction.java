/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import taint.model.dateTimeRentCar.DateTimeRentCarDAO;
import taint.model.rentCar.DetailsRentCarDTO;
import taint.model.rentCar.RentCarDAO;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Result(name = "success", location = "ShoppingCart.jsp")
public class DeleteitemfromcartAction {

    private int idRentInRentCar;
    private int totalCart;
    private List<DetailsRentCarDTO> listRentCarDetails;

    private final String SUCCESS = "success";

    public DeleteitemfromcartAction() {
    }

    public String execute() throws Exception {

        RentCarDAO dao = new RentCarDAO();
        DateTimeRentCarDAO dtrcDAO = new DateTimeRentCarDAO();

        dtrcDAO.deleteADateRentCar(idRentInRentCar);

        boolean isDelete = dao.deleteRentCarFromCart(idRentInRentCar);

        if (isDelete) {
            Map session = ActionContext.getContext().getSession();
            listRentCarDetails = (List<DetailsRentCarDTO>) session.get("LIST_RENT_CAR_IN_CART");
            totalCart = (int) session.get("TOTAL_IN_CART");

            for (DetailsRentCarDTO rentCar : listRentCarDetails) {
                if (rentCar.getIdRent() == idRentInRentCar) {

                    System.out.println(rentCar.getTotalPrice());

                    totalCart -= rentCar.getTotalPrice();
                    session.put("TOTAL_IN_CART", totalCart);

                    listRentCarDetails.remove(rentCar);
                    session.put("LIST_RENT_CAR_IN_CART", listRentCarDetails);

                    break;
                }
            }
        }

        return SUCCESS;
    }

    public int getIdRentInRentCar() {
        return idRentInRentCar;
    }

    public void setIdRentInRentCar(int idRentInRentCar) {
        this.idRentInRentCar = idRentInRentCar;
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
