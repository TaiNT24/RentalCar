/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import taint.model.feedback.FeedbackDAO;
import taint.model.feedback.FeedbackDTO;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Results({
    @Result(name = "success", type = "redirectAction",
            params = {
                "actionName", "historyshopping"
            })
    
//    @Result(name = "fail", location = "AdminHome.jsp")
})
public class SubmitfeedbackAction {
    
    private final String SUCCESS = "success";
    private String feedback;
    private int rentID;
    private int Rating;

    public SubmitfeedbackAction() {
    }
    
    public String execute() throws Exception {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        
        FeedbackDTO dto = new FeedbackDTO(Rating, feedback, rentID);
        
        feedbackDAO.insertFeedback(dto);
        
        return SUCCESS;
    }

    public int getRentID() {
        return rentID;
    }

    public void setRentID(int rentID) {
        this.rentID = rentID;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int Rating) {
        this.Rating = Rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    
}
