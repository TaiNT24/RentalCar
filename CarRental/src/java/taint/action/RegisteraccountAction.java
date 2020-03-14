/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import taint.model.account.AccountDAO;
import taint.model.account.AccountDTO;
import taint.utils.Utils;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Results({
    @Result(name = "success", location = "VerifyNewAccount.jsp")
    ,
    @Result(name = "fail", location = "RegisterNewAccount.jsp")
})
public class RegisteraccountAction {

    private String txtEmail;
    private String txtName;
    private String txtPassword;
    private String txtConfPassword;
    private String txtAddress;
    private String txtPhone;

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public RegisteraccountAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;

        Date now = new Date();
        String dateCreate = Utils.formatDateToStringDateSQL(now);
        boolean isError = false;

        AccountDAO dao = new AccountDAO();

        HttpServletRequest request = ServletActionContext.getRequest();

        String pattern = "[A-Za-z0-9+_.-]+@(.+)";
        if (!txtEmail.matches(pattern)) {
            isError = true;
            request.setAttribute("ERROR_EMAIL",
                    "Email is incorrect form. EX:abc@gmail.com");
        }
        if (dao.checkDupEmail(txtEmail)) {
            isError = true;
            request.setAttribute("ERROR_EMAIL", "This email has been existed !");
        }

        if (!txtConfPassword.equals(txtPassword)) {
            isError = true;
            request.setAttribute("ERROR_PASSWORD", "Confirm password not match !");
        }

        String patternPhone = "[0-9]{10,11}";
        if (!txtPhone.matches(patternPhone)) {
            isError = true;
            request.setAttribute("ERROR_PHONE", "Phone has 10-11 digits !");
        }

        if (!isError) {
            String codeVerify = Utils.sendEmail(txtEmail);
            Map session = ActionContext.getContext().getSession();

            AccountDTO dto = new AccountDTO(txtEmail, txtPassword, txtName,
                    txtAddress, txtPhone, dateCreate);
            
            
            session.put("CODE_VERIFY", codeVerify);
            session.put("Account_DTO", dto);
            url = SUCCESS;
        }

        return url;
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(String txtPassword) {
        this.txtPassword = txtPassword;
    }

    public String getTxtConfPassword() {
        return txtConfPassword;
    }

    public void setTxtConfPassword(String txtConfPassword) {
        this.txtConfPassword = txtConfPassword;
    }

    public String getTxtAddress() {
        return txtAddress;
    }

    public void setTxtAddress(String txtAddress) {
        this.txtAddress = txtAddress;
    }

    public String getTxtPhone() {
        return txtPhone;
    }

    public void setTxtPhone(String txtPhone) {
        this.txtPhone = txtPhone;
    }

}
