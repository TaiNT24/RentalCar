/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.car;

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
public class CarDAO implements Serializable {

    public List<CarDTO> getListCar() 
            throws NamingException, SQLException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;

        List<CarDTO> listCart = new ArrayList<>();

        String sqlQuery = "SELECT IDCar, CarName, Color, YearProduce,"
                + " Category, Price, Quantity "
                + "FROM Car";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.createStatement();

                rs = stm.executeQuery(sqlQuery);
                while (rs.next()) {
                    int idCar = rs.getInt("IDCar");
                    String carName = rs.getString("CarName");
                    String color = rs.getString("Color");
                    int yearProduce = rs.getInt("YearProduce");
                    String category = rs.getString("Category");
                    int price = rs.getInt("Price");
                    int quantity = rs.getInt("Quantity");
                    
                    CarDTO dto = new CarDTO(idCar, carName, color, yearProduce
                            , category, price, quantity);
                    
                    listCart.add(dto);
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
        return listCart;
    }
    
    public int getPrice(int idCar) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        String sqlQuery = "SELECT Price from Car where IDCar = ?";
        try{
            con = DBUtils.connectDB();
            
            if(con!=null){
                stm = con.prepareStatement(sqlQuery);
                
                stm.setInt(1, idCar);
                
                rs = stm.executeQuery();
                if(rs.next()){
                    return rs.getInt("Price");
                }
            }
        }finally{
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
    
    public List<CarDTO> searchByName(String carName) 
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        List<CarDTO> listCart = new ArrayList<>();

        String sqlQuery = "SELECT IDCar, CarName, Color, YearProduce,"
                + " Category, Price, Quantity "
                + "FROM Car "
                + "WHERE CarName like ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setString(1,"%" + carName + "%");
                
                rs = stm.executeQuery();
                while (rs.next()) {
                    int idCar = rs.getInt("IDCar");
                    String name = rs.getString("CarName");
                    String color = rs.getString("Color");
                    int yearProduce = rs.getInt("YearProduce");
                    String category = rs.getString("Category");
                    int price = rs.getInt("Price");
                    int quantity = rs.getInt("Quantity");
                    
                    CarDTO dto = new CarDTO(idCar, name, color, yearProduce
                            , category, price, quantity);
                    
                    listCart.add(dto);
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
        return listCart;
    }
    
    public List<CarDTO> searchByCategory(String carName, String category) 
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        List<CarDTO> listCart = new ArrayList<>();

        String sqlQuery = "SELECT IDCar, CarName, Color, YearProduce,"
                + " Category, Price, Quantity "
                + "FROM Car "
                + "WHERE CarName like ? and Category = ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setString(1,"%" + carName + "%");
                stm.setString(2, category);
                
                rs = stm.executeQuery();
                while (rs.next()) {
                    int idCar = rs.getInt("IDCar");
                    String name = rs.getString("CarName");
                    String color = rs.getString("Color");
                    int yearProduce = rs.getInt("YearProduce");
                    int price = rs.getInt("Price");
                    int quantity = rs.getInt("Quantity");
                    
                    CarDTO dto = new CarDTO(idCar, name, color, yearProduce
                            , category, price, quantity);
                    
                    listCart.add(dto);
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
        return listCart;
    }
    
    public List<CarDTO> searchByAmount(String carName, int amount) 
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        List<CarDTO> listCart = new ArrayList<>();

        String sqlQuery = "SELECT IDCar, CarName, Color, YearProduce,"
                + " Category, Price, Quantity "
                + "FROM Car "
                + "WHERE CarName like ? and Quantity between ? and ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setString(1,"%" + carName + "%");
                
                stm.setInt(2, amount-5);
                stm.setInt(3, amount+5);
                
                rs = stm.executeQuery();
                while (rs.next()) {
                    int idCar = rs.getInt("IDCar");
                    String name = rs.getString("CarName");
                    String color = rs.getString("Color");
                    int yearProduce = rs.getInt("YearProduce");
                    String category = rs.getString("Category");
                    int price = rs.getInt("Price");
                    int quantity = rs.getInt("Quantity");
                    
                    CarDTO dto = new CarDTO(idCar, name, color, yearProduce
                            , category, price, quantity);
                    
                    listCart.add(dto);
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
        return listCart;
    }
    
    
}
