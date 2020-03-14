/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.feedback;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class FeedbackDTO implements Serializable{
    private int idRating;
    private int scale;
    private String feedback;
    private int idRent;

    public FeedbackDTO() {
    }

    public FeedbackDTO(int idRating, int scale, String feedback, int idRent) {
        this.idRating = idRating;
        this.scale = scale;
        this.feedback = feedback;
        this.idRent = idRent;
    }

    public int getIdRating() {
        return idRating;
    }

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getIdRent() {
        return idRent;
    }

    public void setIdRent(int idRent) {
        this.idRent = idRent;
    }
    
    
    
}
