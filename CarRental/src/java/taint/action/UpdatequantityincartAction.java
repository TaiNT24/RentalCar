/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Result(name = "success", location = "ShoppingCart.jsp")
public class UpdatequantityincartAction {
    private String btnAction;
    private String quantityItem;
    private String idRentChange;
    private String oldQuantity;
    
    private final String SUCCESS = "success";
    
    
    public UpdatequantityincartAction() {
    }
    
    public String execute() throws Exception {
        //if equal => click button
        if(quantityItem.equals(oldQuantity)){
            if(btnAction.equals("")){
                
            }
        }else{
            
        }
        
        return SUCCESS;
    }
    
}
