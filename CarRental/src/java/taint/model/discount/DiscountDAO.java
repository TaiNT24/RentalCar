/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.discount;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import taint.utils.DBUtils;

/**
 *
 * @author nguye
 */
public class DiscountDAO implements Serializable {

    public DiscountDTO checkDiscount(String codeDiscount) 
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        DiscountDTO dto = null;
        String sqlQuery = "Select IDDiscount, PercentDiscount, DayExpired, Status "
                + "From Discount "
                + "Where CodeDiscount = ?";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setString(1, codeDiscount);

                rs = preStm.executeQuery();
                if (rs.next()) {
                    int idDiscount = rs.getInt("IDDiscount");
                    int percentDiscount = rs.getInt("PercentDiscount");
                    String dayExpired = rs.getString("DayExpired");
                    String status = rs.getString("Status");
                    
                    dto = new DiscountDTO(idDiscount, codeDiscount
                            , percentDiscount, dayExpired, status);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }
}
