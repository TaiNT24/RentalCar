/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import java.util.Date;
import java.util.StringTokenizer;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import taint.model.discount.DiscountDAO;
import taint.model.discount.DiscountDTO;
import taint.utils.Utils;

/**
 *
 * @author nguye
 */
@ResultPath(value = "/")
@Result(name = "success", location = "ShoppingCart.jsp")
public class DiscountvoucherAction {

    private String voucher;
    private final String SUCCESS = "success";

    public DiscountvoucherAction() {
    }

    public String execute() throws Exception {
        Date now = new Date();

        DiscountDAO dao = new DiscountDAO();
        System.out.println("Voucher code: "+voucher);
        DiscountDTO dto = dao.checkDiscount(voucher);

        String msg = "";
        boolean isValid = true;
        if (dto != null) {
            if (!dto.getStatus().equalsIgnoreCase("Active")) {
                msg = "This code had been used,";
                isValid = false;
            }
            //process date Expired
            String dateEx = dto.getDayExpired();
            StringTokenizer stk = new StringTokenizer(dateEx, " ");
            dateEx = stk.nextToken();

            String d = Utils.formatStringDate2(dateEx);
            Date dateExpired = new Date(d);

            if (dateExpired.compareTo(now) < 0) {
                msg = "This code is expired,";
                isValid = false;
            }

            if (isValid) {
                msg = "Apply voucher successful !," + dto.getPercentDiscount();
            }

        } else {
            msg = "This code is not valid,";
        }

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/plain;charset=UTF-8");
        ServletOutputStream sout = response.getOutputStream();
        sout.print(msg);
        sout.close();

        return SUCCESS;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String Voucher) {
        this.voucher = Voucher;
    }

}
