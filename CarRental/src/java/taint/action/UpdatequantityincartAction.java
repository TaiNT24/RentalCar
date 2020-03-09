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
import taint.model.rentCar.DetailsRentCarDTO;
import taint.model.rentCar.RentCarDAO;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Result(name = "success", location = "ShoppingCart.jsp")
public class UpdatequantityincartAction {

    private String btnAction;
    private int quantityItem;
    private int idCartChange;
    private int idCarChange;
    private int oldQuantity;
    
    private int totalCart;
    private List<DetailsRentCarDTO> listRentCarDetails;

    private final String SUCCESS = "success";

    public UpdatequantityincartAction() {
    }

    public String execute() throws Exception {
        RentCarDAO rentCarDAO = new RentCarDAO();

        Map session = ActionContext.getContext().getSession();
        listRentCarDetails = (List<DetailsRentCarDTO>) session.get("LIST_RENT_CAR_IN_CART");
        totalCart = (int) session.get("TOTAL_IN_CART");

        //if equal => click button
        if (quantityItem == oldQuantity) {
            boolean isIncre = false;
            if (btnAction.equals("increItem")) {
                rentCarDAO.increQuantity(idCartChange, idCarChange, oldQuantity);
                isIncre = true;
            } else {
                rentCarDAO.decreQuantity(idCartChange, idCarChange, oldQuantity);
            }
            
            for (DetailsRentCarDTO dto : listRentCarDetails) {
                if(dto.getIdCar()==idCarChange && dto.getIdCart() == idCartChange){
                    int price = dto.getPrice();
                    if(isIncre){
                        totalCart += price;
                        dto.setQuantity(oldQuantity+1);
                        dto.setTotalPrice(dto.getTotalPrice()+price);
                    }else{
                        totalCart -= price;
                        dto.setQuantity(oldQuantity-1);
                        dto.setTotalPrice(dto.getTotalPrice()-price);
                    }
                    
                    break;
                }
            }
        } else {

        }

        session.put("TOTAL_IN_CART", totalCart);
        session.put("LIST_RENT_CAR_IN_CART", listRentCarDetails);
        
        return SUCCESS;
    }

    public String getBtnAction() {
        return btnAction;
    }

    public void setBtnAction(String btnAction) {
        this.btnAction = btnAction;
    }

    public int getQuantityItem() {
        return quantityItem;
    }

    public void setQuantityItem(int quantityItem) {
        this.quantityItem = quantityItem;
    }

    public int getIdCartChange() {
        return idCartChange;
    }

    public void setIdCartChange(int idCartChange) {
        this.idCartChange = idCartChange;
    }

    public int getIdCarChange() {
        return idCarChange;
    }

    public void setIdCarChange(int idCarChange) {
        this.idCarChange = idCarChange;
    }

    public int getOldQuantity() {
        return oldQuantity;
    }

    public void setOldQuantity(int oldQuantity) {
        this.oldQuantity = oldQuantity;
    }

    public int getTotalCart() {
        return totalCart;
    }

    public void setTotalCart(int totalCart) {
        this.totalCart = totalCart;
    }

    public List<DetailsRentCarDTO> getListRentCarDetails() {
        return listRentCarDetails;
    }

    public void setListRentCarDetails(List<DetailsRentCarDTO> listRentCarDetails) {
        this.listRentCarDetails = listRentCarDetails;
    }

    
}
