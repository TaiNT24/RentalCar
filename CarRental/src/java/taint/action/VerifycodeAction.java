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
import taint.model.account.AccountDTO;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Results({
    @Result(name = "success", location = "Login.jsp")
    ,
    @Result(name = "fail", location = "VerifyNewAccount.jsp")
})
public class VerifycodeAction {

    private String txtVerifyCode;
    private String txtEmail;

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public VerifycodeAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;

        Map session = ActionContext.getContext().getSession();
        String code = (String) session.get("CODE_VERIFY");

        HttpServletRequest request = ServletActionContext.getRequest();

        System.out.println(code);
        System.out.println(txtVerifyCode);
        if (code.equals(txtVerifyCode)) {
            
            AccountDTO dto = (AccountDTO) session.get("Account_DTO");
            dto.setStatus("Active");

            AccountDAO dao = new AccountDAO();
            boolean isInsert = dao.insertNewAccount(dto);
//            
            if (isInsert) {
                request.setAttribute("RegisterSuccess", "RegisterSuccess");
                url = SUCCESS;
            }
        } else {
            request.setAttribute("CodeNotMatch", "true");

        }

        return url;
    }

    public String getTxtVerifyCode() {
        return txtVerifyCode;
    }

    public void setTxtVerifyCode(String txtVerifyCode) {
        this.txtVerifyCode = txtVerifyCode;
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

}
