/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.dateTimeRentCar;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class DateTimeRentCarDTO implements Serializable{
    private int idDTRC;
    private int idRent;
    private String dateRent;
    private String dateReturn;

    public DateTimeRentCarDTO() {
    }

    public DateTimeRentCarDTO(int idRent, String dateRent, String dateReturn) {
        this.idRent = idRent;
        this.dateRent = dateRent;
        this.dateReturn = dateReturn;
    }

    public int getIdDTRC() {
        return idDTRC;
    }

    public void setIdDTRC(int idDTRC) {
        this.idDTRC = idDTRC;
    }

    public int getIdRent() {
        return idRent;
    }

    public void setIdRent(int idRent) {
        this.idRent = idRent;
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
