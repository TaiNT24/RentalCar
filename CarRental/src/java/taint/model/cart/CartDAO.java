/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.cart;

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
public class CartDAO implements Serializable{
    
    public CartDTO getCartOfUsername(String username) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        CartDTO cart = null;

        String sqlQuery = "SELECT IDCart "
                + "FROM Cart "
                + "WHERE Email = ? and DateRent is NULL";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setString(1, username);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    cart = new CartDTO(rs.getInt("IDCart"), username);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return cart;
    }
    
    public boolean createCartForUsername(String username) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;

        String sqlQuery = "INSERT INTO Cart(Email) values(?)";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setString(1, username);
                
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
