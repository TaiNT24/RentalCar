/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import taint.model.rentCar.DetailsRentCarDTO;


/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Result(name = "success", location = "HistoryShopping.jsp")
public class SearchhistoryAction {

    private String isSearch;

    private String searchHistoryVal;
    private String dateRent;//from Date search
    private String dateReturn;//to Date search

    private List<Integer> listIDCart;
    private Hashtable<Integer, List<DetailsRentCarDTO>> listHistory;

    private final String SUCCESS = "success";

    public SearchhistoryAction() {
    }

    public String execute() throws Exception {
        searchHistoryVal = searchHistoryVal.toLowerCase();
        isSearch = "yes";
        Map session = ActionContext.getContext().getSession();

        List<Integer> listIDCart1 = (List<Integer>) session.get("LIST_ID_CART_HISTORY");
        Hashtable<Integer, List<DetailsRentCarDTO>> listHistory1 = (Hashtable<Integer, List<DetailsRentCarDTO>>) session.get("LIST_CAR_RENT_HISTORY");

        listIDCart = new ArrayList<>();
        listHistory = new Hashtable<>();

        boolean isSearchByDate = !"".equals(dateRent) && !"".equals(dateReturn);
        System.out.println(isSearchByDate);
        for (int i = 0; i < listIDCart1.size(); i++) {
            int idCart = listIDCart1.get(i);
            List<DetailsRentCarDTO> listCarRentInCart = listHistory1.get(idCart);
            
            for (DetailsRentCarDTO dto : listCarRentInCart) {
                
                if (!isSearchByDate) {
                    if (dto.getCarName().toLowerCase().contains(searchHistoryVal)) {
                        listIDCart.add(idCart);
                        listHistory.put(idCart, listCarRentInCart);
                        break;
                    }
                } else {
                    String dateRentCart = dto.getDateRentCart();
                    Date da = new Date(dateRentCart);
//                    dateRent = dateRent.replaceAll("-", "/");
//                    dateReturn = dateReturn.replaceAll("-", "/");
                    
                    Date daTo = new Date(dateReturn);
                    Date daFrom = new Date(dateRent);
                    
                    boolean match = dto.getCarName().toLowerCase().contains(searchHistoryVal)
                            && da.compareTo(daFrom)>=0 && da.compareTo(daTo)<=0;
                    
                    if (match) {
                        listIDCart.add(idCart);
                        listHistory.put(idCart, listCarRentInCart);
                        break;
                    }
                    
                }
            }
        }

//        dateRent = dateRent.replaceAll("/", "-");
//        dateReturn = dateReturn.replaceAll("/", "-");
        return SUCCESS;
    }

    public String getSearchHistoryVal() {
        return searchHistoryVal;
    }

    public void setSearchHistoryVal(String searchHistoryVal) {
        this.searchHistoryVal = searchHistoryVal;
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

    public String getIsSearch() {
        return isSearch;
    }

    public void setIsSearch(String isSearch) {
        this.isSearch = isSearch;
    }

}
