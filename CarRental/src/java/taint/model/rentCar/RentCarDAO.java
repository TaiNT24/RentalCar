/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.rentCar;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import taint.utils.DBUtils;

/**
 *
 * @author nguye
 */
public class RentCarDAO implements Serializable{
    
    public boolean insertARentCar(RentCarDTO dto) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;

        String sqlQuery = "INSERT INTO RentCar values(?,?,?,?,?)";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setInt(1, dto.getIdCar());
                stm.setInt(2, dto.getIdCart());
                stm.setInt(3, dto.getPrice());
                stm.setInt(4, dto.getQuantity());
                stm.setInt(5, dto.getTotalPrice());
                
                int row = stm.executeUpdate();
                if (row>0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
