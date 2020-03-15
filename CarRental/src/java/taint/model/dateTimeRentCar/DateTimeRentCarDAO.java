/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.dateTimeRentCar;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import taint.model.rentCar.RentCarDTO;
import taint.utils.DBUtils;

/**
 *
 * @author nguye
 */
public class DateTimeRentCarDAO implements Serializable {

    public boolean insertADateRentCar(int idRent, String dateRent, String dateReturn)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        String sqlQuery = "insert into DateTimeRentCar values(?,?,?)";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                stm.setInt(1, idRent);

                stm.setString(2, dateRent);
                stm.setString(3, dateReturn);

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

    public List<DateTimeRentCarDTO> getListDateRentCarOfUser(List<RentCarDTO> list) 
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        List<DateTimeRentCarDTO> listDateRentCar = new ArrayList<>();

        String sqlQuery = "SELECT DateRent, DateReturn "
                + "FROM DateTimeRentCar "
                + "WHERE IDRent = ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);
                for (RentCarDTO dto : list) {
                    stm.setInt(1, dto.getIdRent());

                    rs = stm.executeQuery();
                    if (rs.next()) {
                        String dateRent = rs.getString("DateRent");
                        String dateReturn = rs.getString("DateReturn");

                        DateTimeRentCarDTO dtrcDTO = new DateTimeRentCarDTO(dto.getIdRent(), dateRent, dateReturn);

                        listDateRentCar.add(dtrcDTO);
                    }
                    stm.clearParameters();
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
        return listDateRentCar;
    }
    
    public boolean deleteADateRentCar(int idRent)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        String sqlQuery = "delete from DateTimeRentCar where IDRent = ?";
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

    public boolean deleteAllDateRentCarOfCart(List<RentCarDTO> listRent)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        String sqlQuery = "delete from DateTimeRentCar where IDRent = ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.prepareStatement(sqlQuery);

                for (RentCarDTO dto : listRent) {
                    stm.setInt(1, dto.getIdRent());

                    stm.executeUpdate();

                    stm.clearParameters();
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
        return true;
    }
    
    
}
