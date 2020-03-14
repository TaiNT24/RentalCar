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
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.naming.NamingException;
import taint.utils.DBUtils;
import taint.utils.Utils;

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
    
    public boolean updateDateRentCart(int idCart, String dateRent) 
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
    
    public boolean updateTotalPriceRentCartAfterUseVoucher(int idCart, int total, String code) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;

        String sqlQuery = "UPDATE Cart SET TotalPrice = ?, IDDiscount = ? "
                + "WHERE IDCart = ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                int id = getIDCodeDiscount(code);
                
                stm = con.prepareStatement(sqlQuery);
                
                stm.setInt(1, total);
                stm.setInt(2, id);
                stm.setInt(3, idCart);
                
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
    
    public int getIDCodeDiscount(String code) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sqlQuery = "Select IDDiscount from Discount "
                + "Where CodeDiscount = ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                
                stm = con.prepareStatement(sqlQuery);

                stm.setString(1, code);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("IDDiscount");
                    updateStatusCodeDiscount(id);
                    return id;
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
        return 0;
    }
    
    public boolean updateStatusCodeDiscount(int idCode) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sqlQuery = "Update Discount set Status = 'Used' "
                + "Where IDDiscount = ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                
                stm = con.prepareStatement(sqlQuery);

                stm.setInt(1, idCode);
                
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
    
    public ArrayList<Integer> getAllCartOfUser(String email) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        ArrayList<Integer> listID = new ArrayList<>();

        String sqlQuery = "SELECT IDCart "
                + "FROM Cart "
                + "WHERE Email = ? and DateRent is not NULL "
                + "ORDER BY DateRent DESC";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setString(1, email);
                
                rs = stm.executeQuery();
                while (rs.next()) {
                    int idCart = rs.getInt("IDCart");
                    listID.add(idCart);
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
        return listID;
    }
    
    public String getDateRentCart(int idCart) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        ArrayList<Integer> listID = new ArrayList<>();

        String sqlQuery = "SELECT DateRent "
                + "FROM Cart "
                + "WHERE IDCart = ? ";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setInt(1, idCart);
                
                rs = stm.executeQuery();
                while (rs.next()) {
                    String dateRent = rs.getString("DateRent");
                    StringTokenizer stk = new StringTokenizer(dateRent, " ");
                    dateRent = stk.nextToken();
                    String dateRentCart = Utils.formatStringDate2(dateRent);
                    return dateRentCart;
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
        return null;
    }
    
    public int getTotalPriceCart(int idCart) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        ArrayList<Integer> listID = new ArrayList<>();

        String sqlQuery = "SELECT TotalPrice "
                + "FROM Cart "
                + "WHERE IDCart = ? ";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setInt(1, idCart);
                
                rs = stm.executeQuery();
                while (rs.next()) {
                    int totalPrice = rs.getInt("TotalPrice");
                    return totalPrice;
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
        return 0;
    }
    
    
    
}
