/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import taint.model.car.CarDAO;
import taint.model.car.CarDTO;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Results({
    @Result(name = "success", location = "ResultSearch.jsp")
    ,
    @Result(name = "fail", location = "ResultSearch.jsp")

})
public class SearchAction {

    private String searchVal;
    private List<CarDTO> listCars;

    private String moreFilter;
    private String categoryApply;
    private int amount;
    private String dateRent;
    private String dateReturn;

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public SearchAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        
        listCars = new ArrayList<>();

        CarDAO dao = new CarDAO();
        List<CarDTO> tempListCars = new ArrayList<>();

        if (moreFilter == null || moreFilter.equals("")) {
            tempListCars = dao.searchByName(searchVal, dateRent, dateReturn);

        } else {
            if (moreFilter.equals("category")) {
                tempListCars = dao.searchByCategory(searchVal, categoryApply, dateRent, dateReturn);

            }
        }

        if (!tempListCars.isEmpty()) {
            for (CarDTO car : tempListCars) {
                int availableQuan = car.getAvailableQuantity();
                int fromAmout = 1;
                int toAmout = amount + 5;

                if (availableQuan >= fromAmout && availableQuan <= toAmout) {
                    listCars.add(car);
                }
            }
            url = SUCCESS;
        }
//
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("searchVal", searchVal);
        request.setAttribute("moreFilter", moreFilter);
        request.setAttribute("categoryApply", categoryApply);
        request.setAttribute("amount", amount);
        request.setAttribute("dateRent", dateRent);
        request.setAttribute("dateReturn", dateReturn);
        //
        

        return url;
    }

    public String getSearchVal() {
        return searchVal;
    }

    public void setSearchVal(String searchVal) {
        this.searchVal = searchVal;
    }

    public List<CarDTO> getListCars() {
        return listCars;
    }

    public void setListCars(List<CarDTO> listCars) {
        this.listCars = listCars;
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

}
