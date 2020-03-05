/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Result(name = "success", type = "redirectAction",
        params = {
            "actionName",""
        })
public class LogoutAction {

    private final String SUCCESS = "success";

    public LogoutAction() {
    }

    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();

        HttpSession session = request.getSession();

        session.invalidate();
        
        return SUCCESS;
    }

}
