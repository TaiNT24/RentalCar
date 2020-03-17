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
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import taint.model.car.CarDAO;
import taint.model.cart.CartDAO;
import taint.model.cart.CartDTO;
import taint.model.category.CategoryDAO;
import taint.model.category.CategoryDTO;
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
@Results({
    @Result(name = "success", location = "AdminHome.jsp")
    ,
    @Result(name = "fail", location = "Login.jsp")
})
public class AdminAction {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    private List<CartDTO> listCart;
    private Hashtable<Integer, List<DetailsRentCarDTO>> listDetailDTO;

    public AdminAction() {
    }

    public String execute() throws Exception {
        String url = SUCCESS;
        Map session = ActionContext.getContext().getSession();

        int role = (int) session.get("ROLE");

        if (role == 1) {
            Map application = ActionContext.getContext().getApplication();

            CarDAO carDAO = new CarDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            CartDAO cartDAO = new CartDAO();
            RentCarDAO rentCartDAO = new RentCarDAO();
            DateTimeRentCarDAO dtrcDAO = new DateTimeRentCarDAO();

            listCart = cartDAO.getAllCart(); // get all cart
            listDetailDTO = new Hashtable<>();

            List<RentCarDTO> listRentCar = new ArrayList<>();
            List<DateTimeRentCarDTO> listDTRC;
            
            for (CartDTO cartDTO : listCart) {
                
                
                //get list rent car in cart
                listRentCar = rentCartDAO.getListRentCarOfUser(cartDTO.getIdCart());

                String dateRentCart = cartDTO.getDateRent();
                int totalPriceInCart = cartDTO.getTotalPrice();
                int idDiscount = cartDTO.getIdDiscount();

                listDTRC = dtrcDAO.getListDateRentCarOfUser(listRentCar);

                // -> convert to detail rent car
                List<DetailsRentCarDTO> listRentCarDetails = new ArrayList<>();
                for (int i = 0; i < listRentCar.size(); i++) {
                    int idRent = listRentCar.get(i).getIdRent();
                    int idCar = listRentCar.get(i).getIdCar();
                    int idCart = listRentCar.get(i).getIdCart();
                    int price = listRentCar.get(i).getPrice();
                    int quantity = listRentCar.get(i).getQuantity();
                    int totalPrice = listRentCar.get(i).getTotalPrice();
                    String status = listRentCar.get(i).getStatus();

                    StringTokenizer stk1 = new StringTokenizer(listDTRC.get(i).getDateRent(), " ");
                    StringTokenizer stk2 = new StringTokenizer(listDTRC.get(i).getDateReturn(), " ");

                    String dateRent = Utils.formatStringDate2(stk1.nextToken());
                    String dateReturn = Utils.formatStringDate2(stk2.nextToken());

                    String carName = carDAO.getName(idCar);
                    String category = carDAO.getCategory(idCar);

                    DetailsRentCarDTO dto = new DetailsRentCarDTO(idRent, idCar, idCart,
                            price, quantity, totalPrice, carName, category, dateRent, dateReturn);
                    dto.setDateRentCart(dateRentCart);
                    dto.setTotalPriceInCart(totalPriceInCart);
                    dto.setStatus(status);
                    dto.setIdDiscount(idDiscount);

                    listRentCarDetails.add(dto);
                }
                
                // -> Add to hash table
                listDetailDTO.put(cartDTO.getIdCart(), listRentCarDetails);

            }
            
            session.put("LIST_CART", listCart);
            session.put("LIST_DETAIL_DTO", listDetailDTO);
            

            List<CategoryDTO> listCategory = categoryDAO.loadCategory();

            application.put("CATEGORY", listCategory);

        } else {
            HttpServletRequest request = ServletActionContext.getRequest();

            request.setAttribute("ERROR_LOGIN", "true");
            url = FAIL;
        }

        return url;
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
