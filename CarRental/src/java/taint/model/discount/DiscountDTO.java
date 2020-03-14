/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.discount;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class DiscountDTO implements Serializable{
    private int idDiscount;
    private String codeDiscount;
    private int percentDiscount;
    private String dayExpired;
    private String status;

    public DiscountDTO() {
    }

    public DiscountDTO(int IDDiscount, String CodeDiscount, int PercentDiscount, String DayExpired, String Status) {
        this.idDiscount = IDDiscount;
        this.codeDiscount = CodeDiscount;
        this.percentDiscount = PercentDiscount;
        this.dayExpired = DayExpired;
        this.status = Status;
    }
    
    public int getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(int idDiscount) {
        this.idDiscount = idDiscount;
    }

    public String getCodeDiscount() {
        return codeDiscount;
    }

    public void setCodeDiscount(String CodeDiscount) {
        this.codeDiscount = CodeDiscount;
    }

    public int getPercentDiscount() {
        return percentDiscount;
    }

    public void setPercentDiscount(int PercentDiscount) {
        this.percentDiscount = PercentDiscount;
    }

    public String getDayExpired() {
        return dayExpired;
    }

    public void setDayExpired(String DayExpired) {
        this.dayExpired = DayExpired;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }
    
}
