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

                    CarDTO dto = new CarDTO(idCar, carName, color, yearProduce,
                            category, price, quantity);

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
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sqlQuery = "SELECT Price from Car where IDCar = ?";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setInt(1, idCar);

                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("Price");
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

    public String getName(int idCar)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sqlQuery = "SELECT CarName from Car where IDCar = ?";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setInt(1, idCar);

                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getString("CarName");
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
    
    public String getCategory(int idCar)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sqlQuery = "SELECT Category from Car where IDCar = ?";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setInt(1, idCar);

                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getString("Category");
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
    
    public List<CarDTO> searchByName(String carName, String dateWantRent, String dateWantReturn)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        List<CarDTO> listCart = new ArrayList<>();

        String sqlQuery = "SELECT IDCar, CarName, Color, YearProduce, "
                + "Category, Price, Quantity "
                + "FROM Car "
                + "WHERE CarName like ? "
                    + "AND IDCar in (SELECT IDCar FROM RentCar "
                                    + "WHERE IDRent IN ( SELECT IDRent "
                                                        + "FROM DateTimeRentCar "
                                                        + "WHERE DateRent > ? "
                                                        + "OR DateReturn < ? ) "
                                        + "AND Status='Paymented' "
                                    + "UNION "
                                    + "SELECT IDCar FROM Car WHERE AvailableQuantity > 0 )";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setString(1, "%" + carName + "%");
                stm.setString(2, dateWantReturn);
                stm.setString(3, dateWantRent);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int idCar = rs.getInt("IDCar");
                    String name = rs.getString("CarName");
                    String color = rs.getString("Color");
                    int yearProduce = rs.getInt("YearProduce");
                    String category = rs.getString("Category");
                    int price = rs.getInt("Price");
                    int quantity = rs.getInt("Quantity");

                    CarDTO dto = new CarDTO(idCar, name, color, yearProduce,
                            category, price, quantity);
                    
                    int availableQuantity = getAvailableQuantity(idCar, dateWantRent, dateWantReturn);
                    dto.setAvailableQuantity(availableQuantity);
                    
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

    public List<CarDTO> searchByCategory(String carName, String category,
            String dateWantRent, String dateWantReturn)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        List<CarDTO> listCart = new ArrayList<>();

        String sqlQuery = "SELECT IDCar, CarName, Color, YearProduce,"
                + " Category, Price, Quantity "
                + "FROM Car "
                + "WHERE CarName like ? and Category = ? "
                + "AND IDCar in (SELECT IDCar FROM RentCar "
                                + "WHERE IDRent IN ( SELECT IDRent "
                                                + "FROM DateTimeRentCar "
                                                + "WHERE DateRent > ? "
                                                    + "OR DateReturn < ? ) "
                                    + "AND Status='Paymented' "
                                + "UNION "
                                + "SELECT IDCar FROM Car WHERE AvailableQuantity > 0 )";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setString(1, "%" + carName + "%");
                stm.setString(2, category);
                stm.setString(3, dateWantReturn);
                stm.setString(4, dateWantRent);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int idCar = rs.getInt("IDCar");
                    String name = rs.getString("CarName");
                    String color = rs.getString("Color");
                    int yearProduce = rs.getInt("YearProduce");
                    int price = rs.getInt("Price");
                    int quantity = rs.getInt("Quantity");

                    CarDTO dto = new CarDTO(idCar, name, color, yearProduce,
                            category, price, quantity);

                    int availableQuantity = getAvailableQuantity(idCar, dateWantRent, dateWantReturn);
                    dto.setAvailableQuantity(availableQuantity);
                    
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


    public int getAvailableQuantity(int idCar, String dateWantRent, String dateWantReturn)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        String sqlQuery = "EXEC sp_GetAvailableQuantity ?, ?, ?";
                            // idCar, dateWantRent, dateWantReturn
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setInt(1, idCar);
                stm.setString(2, dateWantRent);
                stm.setString(3, dateWantReturn);

                rs = stm.executeQuery();

                if (rs.next()) {
                    int available = rs.getInt("AvailableQuantity");
                    return available;
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
    
    
    
}
