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
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import taint.model.car.CarDAO;
import taint.model.cart.CartDAO;
import taint.model.dateTimeRentCar.DateTimeRentCarDAO;
import taint.model.dateTimeRentCar.DateTimeRentCarDTO;
import taint.model.feedback.FeedbackDAO;
import taint.model.rentCar.DetailsRentCarDTO;
import taint.model.rentCar.RentCarDAO;
import taint.model.rentCar.RentCarDTO;
import taint.utils.Utils;

/**
 *
 * @author nguye
 */
@ResultPath("/")
@Results({
    @Result(name = "success", location = "HistoryShopping.jsp"),
    @Result(name = "fail", type = "redirectAction",
                            params = {"actionName","''"}
            )
})

public class HistoryshoppingAction {

    private List<Integer> listIDCart;
    private Hashtable<Integer, List<DetailsRentCarDTO>> listHistory;

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public HistoryshoppingAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        
        CartDAO cartDAO = new CartDAO();
        Map session = ActionContext.getContext().getSession();
        String email = (String) session.get("USER_EMAIL");

        if (email != null) {
            listIDCart = cartDAO.getAllCartOfUser(email);
            listHistory = new Hashtable();

            if (!listIDCart.isEmpty()) {
                CarDAO carDAO = new CarDAO();
                RentCarDAO rentCarDAO = new RentCarDAO();
                DateTimeRentCarDAO dtrcDAO = new DateTimeRentCarDAO();
                FeedbackDAO feedbackDAO = new FeedbackDAO();

                List<RentCarDTO> listRentCar;
                List<DateTimeRentCarDTO> listDTRC;

                for (Integer id : listIDCart) {
                    listRentCar = rentCarDAO.getListRentCarOfUser(id);

                    String dateRentCart = cartDAO.getDateRentCart(id);
                    int totalPriceInCart = cartDAO.getTotalPriceCart(id);

                    listDTRC = dtrcDAO.getListDateRentCarOfUser(listRentCar);

                    List<DetailsRentCarDTO> listRentCarDetails = new ArrayList<>();
                    for (int i = 0; i < listRentCar.size(); i++) {
                        int idRent = listRentCar.get(i).getIdRent();
                        int idCar = listRentCar.get(i).getIdCar();
                        int idCart = listRentCar.get(i).getIdCart();
                        int price = listRentCar.get(i).getPrice();
                        int quantity = listRentCar.get(i).getQuantity();
                        int totalPrice = listRentCar.get(i).getTotalPrice();
                        String status = listRentCar.get(i).getStatus();

                        int idRating = feedbackDAO.getFeedback(idRent);
                        
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
                        if(idRating>0){
                            dto.setIdFeedback(idRating);
                        }
                        
                        
                        listRentCarDetails.add(dto);

                    }

                    listHistory.put(id, listRentCarDetails);

                }
                session.put("LIST_ID_CART_HISTORY", listIDCart);
                session.put("LIST_CAR_RENT_HISTORY", listHistory);
            }
            url = SUCCESS;
        }

        return url;
    }

    public List<Integer> getListIDCart() {
        return listIDCart;
    }

    public void setListIDCart(ArrayList<Integer> listIDCart) {
        this.listIDCart = listIDCart;
    }

    public Hashtable<Integer, List<DetailsRentCarDTO>> getListHistory() {
        return listHistory;
    }

    public void setListHistory(Hashtable<Integer, List<DetailsRentCarDTO>> listHistory) {
        this.listHistory = listHistory;
    }

}
