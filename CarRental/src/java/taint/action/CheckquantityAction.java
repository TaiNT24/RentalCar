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
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import taint.model.car.CarDAO;
import taint.model.discount.DiscountDAO;
import taint.model.discount.DiscountDTO;
import taint.model.rentCar.DetailsRentCarDTO;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Results({
    @Result(name = "success", type = "redirectAction",
            params = {
                "actionName", "paymentcart",
                "idCart", "${idCartNeedCheck}",
                "totalPriceAfterUseVoucher", "${totalPriceAfterUseVoucher}",
                "CodeDiscountValue", "${CodeDiscountValue}"
            }
    )
    ,
    @Result(name = "fail", location = "ShoppingCart.jsp")
})

public class CheckquantityAction {

    private int idCartNeedCheck;
    private List<DetailsRentCarDTO> listRentCarDetails;
    private int totalCart;
    private int totalPriceAfterUseVoucher;
    private String CodeDiscountValue;
    private String errorVoucher;

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public CheckquantityAction() {
    }

    public String execute() throws Exception {
        String url = SUCCESS;
        Map session = ActionContext.getContext().getSession();
        listRentCarDetails
                = (List<DetailsRentCarDTO>) session.get("LIST_RENT_CAR_IN_CART");
        totalCart = (int) session.get("TOTAL_IN_CART");

        if (!CodeDiscountValue.equals("")) {
            DiscountDAO discountDAO = new DiscountDAO();
            DiscountDTO dto = discountDAO.checkDiscount(CodeDiscountValue);
            if (dto.getStatus().equals("Used")) {
                errorVoucher = "This voucher had been used";
                url = FAIL;
            }
        } else {
            Hashtable<Integer, Integer> listRentCarOutOfStock = new Hashtable<>();

            CarDAO dao = new CarDAO();

            for (DetailsRentCarDTO dto : listRentCarDetails) {
                String dateRent = dto.getDateRent();
                String dateReturn = dto.getDateReturn();
                int idCar = dto.getIdCar();

                int quantityWantRent = dto.getQuantity();

                int quantityInStock = dao.getAvailableQuantity(idCar, dateRent, dateReturn);
                if (quantityWantRent > quantityInStock) {
                    listRentCarOutOfStock.put(dto.getIdRent(), quantityInStock);
                }

            }
            if (!listRentCarOutOfStock.isEmpty()) {
                url = FAIL;
                HttpServletRequest request = ServletActionContext.getRequest();
                request.removeAttribute("ListOut");
                request.setAttribute("ListOut", listRentCarOutOfStock);
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

    public int getTotalCart() {
        return totalCart;
    }

    public void setTotalCart(int totalCart) {
        this.totalCart = totalCart;
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

    public String getErrorVoucher() {
        return errorVoucher;
    }

    public void setErrorVoucher(String errorVoucher) {
        this.errorVoucher = errorVoucher;
    }

}
