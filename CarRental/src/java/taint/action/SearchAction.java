/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import java.util.List;
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
    @Result(name = "fail", type = "redirect", location = "Home.jsp")

})
public class SearchAction {

    private String searchVal;
    private List<CarDTO> listCars;

    private String moreFilter;
    private String categoryApply;
    private String amount;
    private String dateRent;
    private String dateReturn;

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public SearchAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;

        CarDAO dao = new CarDAO();

        if (moreFilter == null) {
            listCars = dao.searchByName(searchVal);

        } else {
            if (moreFilter.equals("category")) {
                listCars = dao.searchByCategory(searchVal, categoryApply);
            }
        }

        if (amount.equals("")) {
            amount = "1";
        }
        url = SUCCESS;

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
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
