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
    
    public CartDTO getCurrentCartOfUser(String email) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        CartDTO cart = null;

        String sqlQuery = "SELECT IDCart, TotalPrice "
                + "FROM Cart "
                + "WHERE Email = ? and DateRent is NULL";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setString(1, email);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    cart = new CartDTO(rs.getInt("IDCart"), email, rs.getInt("TotalPrice"));
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
    
    public boolean createCartForUsername(String email) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;

        String sqlQuery = "INSERT INTO Cart(Email) values(?)";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setString(1, email);
                
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
    
    public boolean updateDateCart(int idCart, String dateRent) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;

        String sqlQuery = "UPDATE Cart SET DateRent = ? "
                + "WHERE IDCart = ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setString(1, dateRent);
                stm.setInt(2, idCart);
                
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
