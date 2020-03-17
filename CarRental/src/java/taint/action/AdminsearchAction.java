/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import taint.model.cart.CartDTO;
import taint.model.rentCar.DetailsRentCarDTO;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Results({
    @Result(name = "success", location = "AdminHome.jsp")
    ,
    @Result(name = "fail", location = "Login.jsp")
})
public class AdminsearchAction {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    private String searchEmail;
    private String status;
    private List<CartDTO> listCart;
    private Hashtable<Integer, List<DetailsRentCarDTO>> listDetailDTO;

    public AdminsearchAction() {
    }

    public String execute() throws Exception {
        String url;

        Map session = ActionContext.getContext().getSession();

        int role = (int) session.get("ROLE");
        if (role == 1) {
            listCart = new ArrayList<>();
            listDetailDTO = new Hashtable<>();

            List<CartDTO> listAllCart;
            Hashtable<Integer, List<DetailsRentCarDTO>> listAllDetailDTO;

            listAllCart = (List<CartDTO>) session.get("LIST_CART");
            listAllDetailDTO = (Hashtable<Integer, List<DetailsRentCarDTO>>) session.get("LIST_DETAIL_DTO");

            for (CartDTO listcart : listAllCart) {
                if (listcart.getEmail().contains(searchEmail)) {

                    int idCart = listcart.getIdCart();
                    List<DetailsRentCarDTO> detailDTO
                            = listAllDetailDTO.get(idCart);

                    if (!status.equals("Status")) {
                        //1 rentcar eq status -> 1 cart eq status
                        if (detailDTO.get(0).getStatus().equals(status)) {
                            listCart.add(listcart);
                            listDetailDTO.put(idCart, detailDTO);
                        }
                    } else {
                        listCart.add(listcart);
                        listDetailDTO.put(idCart, detailDTO);
                    }

                }
            }

            url = SUCCESS;
        } else {
            HttpServletRequest request = ServletActionContext.getRequest();

            request.setAttribute("ERROR_LOGIN", "true");
            url = FAIL;
        }

        return url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getSearchEmail() {
        return searchEmail;
    }

    public void setSearchEmail(String searchEmail) {
        this.searchEmail = searchEmail;
    }

    public List<CartDTO> getListCart() {
        return listCart;
    }

    public void setListCart(List<CartDTO> listCart) {
        this.listCart = listCart;
    }

    public Hashtable<Integer, List<DetailsRentCarDTO>> getListDetailDTO() {
        return listDetailDTO;
    }

    public void setListDetailDTO(Hashtable<Integer, List<DetailsRentCarDTO>> listDetailDTO) {
        this.listDetailDTO = listDetailDTO;
    }

}
