/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import taint.model.account.AccountDAO;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Results({
    @Result(name = "user", type = "redirectAction",
            params = {
                "actionName", ""
            })
    ,
    @Result(name = "admin", type = "redirectAction",
            params = {
                "actionName", "admin"
            })
    ,
    @Result(name = "fail", location = "Login.jsp")
})
public class LoginAction {

    private String txtEmail;
    private String txtPassword;

    private final String USER = "user";
    private final String ADMIN = "admin";
    private final String FAIL = "fail";

    public LoginAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        //code here
        if (!txtEmail.equals("") && !txtPassword.equals("")) {
            AccountDAO dao = new AccountDAO();

            int role = dao.checkLogin(txtEmail, txtPassword);
            if (role != -1) {
                Map session = ActionContext.getContext().getSession();
                
                String name = dao.getUserName(txtEmail);
                if (role == 0) {
                    session.put("ROLE", 0);

                    url = USER;
                } else if (role == 1) {
                    session.put("ROLE", 1);

                    url = ADMIN;
                }
                session.put("USER_EMAIL", txtEmail);
                session.put("USER_NAME", name);
            } else {
                HttpServletRequest request = ServletActionContext.getRequest();

                request.setAttribute("ERROR_LOGIN", "true");
            }
        }

        return url;
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public String getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(String txtPassword) {
        this.txtPassword = txtPassword;
    }

}
