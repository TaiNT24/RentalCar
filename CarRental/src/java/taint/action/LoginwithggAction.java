/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import taint.model.account.AccountDAO;
import taint.model.account.AccountDTO;

/**
 *
 * @author nguye
 */
public class LoginwithggAction {

    private String txtEmail;
    private String txtPassword;
    private String name;

    private final String USER = "user";

    public LoginwithggAction() {
    }

    public String execute() throws Exception {

        AccountDAO dao = new AccountDAO();

        int role = dao.checkLogin(txtEmail, txtPassword);

        if (role == -1) {
            AccountDTO dto = new AccountDTO(txtEmail, txtPassword, name);
            dao.insertNewAccount(dto);
        }
        
        String username = dao.getUserName(txtEmail);
        Map session = ActionContext.getContext().getSession();

        session.put("ROLE", 0);
        session.put("USER_EMAIL", txtEmail);
        session.put("USER_NAME", username);


        return USER;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
