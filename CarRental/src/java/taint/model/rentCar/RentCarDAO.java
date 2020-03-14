/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.rentCar;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import taint.utils.DBUtils;

/**
 *
 * @author nguye
 */
public class RentCarDAO implements Serializable {

    public boolean insertARentCar(RentCarDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        String sqlQuery = "INSERT INTO RentCar values(?,?,?,?,?,?,?)";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setInt(1, dto.getIdCar());
                stm.setInt(2, dto.getIdCart());
                stm.setInt(3, dto.getPrice());
                stm.setInt(4, dto.getQuantity());
                stm.setString(5, dto.getStatus());
                stm.setInt(6, dto.getTotalPrice());
                stm.setInt(7, 0);

                int row = stm.executeUpdate();
                if (row > 0) {
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

    public List<RentCarDTO> getListRentCarOfUser(int idCart)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        List<RentCarDTO> listRentCar = new ArrayList<>();

        String sqlQuery = "SELECT IDRent, IDCar, Price, Quantity, TotalPrice, Status "
                + "FROM RentCar "
                + "WHERE IDCart = ?";
        try {

            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setInt(1, idCart);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int idRent = rs.getInt("IDRent");
                    int idCar = rs.getInt("IDCar");
                    int price = rs.getInt("Price");
                    int quantity = rs.getInt("Quantity");
                    int totalPrice = rs.getInt("TotalPrice");
                    String status = rs.getString("Status");

                    RentCarDTO dto = new RentCarDTO(idCar, idCart, price, quantity, totalPrice);
                    dto.setIdRent(idRent);
                    dto.setStatus(status);

                    listRentCar.add(dto);
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
        return listRentCar;
    }

    public int getLastRentCar() throws NamingException, SQLException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;

        String sqlQuery = "SELECT TOP(1) IDRent FROM RentCar ORDER BY IDRent DESC";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.createStatement();

                rs = stm.executeQuery(sqlQuery);
                if (rs.next()) {
                    return rs.getInt("IDRent");
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
        return -1;
    }

    public boolean deleteRentCarFromCart(int idRent)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;

        String sqlQuery = "delete from RentCar where IDRent = ? ";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setInt(1, idRent);

                int row = stm.executeUpdate();
                if (row > 0) {
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

    public int checkCarInCart(int idCart, int idCar) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sqlQuery = "select Quantity from RentCar "
                + "where IDCart = ? and IDCar = ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setInt(1, idCart);
                stm.setInt(2, idCar);

                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("Quantity");
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
        return -1;
    }

    public boolean increQuantity(int idCart, int idCar, int oldQuan)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sqlQuery = "update RentCar set Quantity = ? "
                + "where IDCart = ? and IDCar = ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setInt(1, oldQuan + 1);
                stm.setInt(2, idCart);
                stm.setInt(3, idCar);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
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
        return false;
    }

    public boolean decreQuantity(int idCart, int idCar, int oldQuan)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sqlQuery = "update RentCar set Quantity = ? "
                + "where IDCart = ? and IDCar = ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setInt(1, oldQuan - 1);
                stm.setInt(2, idCart);
                stm.setInt(3, idCar);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
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
        return false;
    }
    
    public boolean setStatusCarRent(List<RentCarDTO> listRent,String status) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;

        
        String sqlQuery = "UPDATE RentCar SET Status = ? "
                + " WHERE IDRent = ?";
        try{
            con = DBUtils.connectDB();
            if(con!=null){
                stm = con.prepareStatement(sqlQuery);
                for (RentCarDTO dto : listRent) {
                    stm.setString(1, status);
                    stm.setInt(2, dto.getIdRent());

                    stm.executeUpdate();
                    stm.clearParameters();
                }
                
            }
        }finally{
            if(stm!=null){
                stm.close();
            }
            if(con!=null){
                con.close();
            }
        }
        return true;
    }
    
    public boolean updateQuantity(int idCart, int idCar, int newQuan)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sqlQuery = "update RentCar set Quantity = ? "
                + "where IDCart = ? and IDCar = ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setInt(1, newQuan);
                stm.setInt(2, idCart);
                stm.setInt(3, idCar);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
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
        return false;
    }
    
    
    
}
