/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import taint.utils.DBUtils;

/**
 * @author nguye Check user login
 */
public class AccountDAO implements Serializable {

    public int checkLogin(String email, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        String sqlQuery = "select Email, Role "
                + "from Account "
                + "where Email = ? and Password = ?";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setString(1, email);
                preStm.setString(2, password);

                rs = preStm.executeQuery();
                if (rs.next()) {
                    boolean role = rs.getBoolean("Role");

                    if (role == true) {
                        return 1;
                    } else {
                        return 0;
                    }
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
        return -1;
    }

    public String getUserName(String email)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        String sqlQuery = "select Name "
                + "from Account "
                + "where Email = ?";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setString(1, email);

                rs = preStm.executeQuery();

                if (rs.next()) {
                    return rs.getString("Name");
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
        return null;
    }

    public boolean checkDupEmail(String email)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        String url = "select Name "
                + "from Account "
                + "where Email = ?";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                preStm = con.prepareStatement(url);

                preStm.setString(1, email);

                rs = preStm.executeQuery();

                if (rs.next()) {
                    return true;
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
        return false;
    }

    public boolean insertNewAccount(AccountDTO dto)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement preStm = null;

        String url = "insert into Account "
                + "values(?,?,?,?,?,?,?,?)";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                preStm = con.prepareStatement(url);

                preStm.setString(1, dto.getEmail());
                preStm.setString(2, dto.getPassword());
                preStm.setString(3, dto.getName());
                preStm.setString(4, dto.getAddress());
                preStm.setString(5, dto.getPhone());
                preStm.setString(6, dto.getCreateDate());
                preStm.setBoolean(7, dto.isRole());
                preStm.setString(8, dto.getStatus());

                int count = preStm.executeUpdate();
                if (count > 0) {
                    return true;
                }
            }
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean quickInsertNewAccount(AccountDTO dto)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement preStm = null;

        String url = "insert into Account "
                + "(Email, Password, Name, Role, Status) "
                + "VALUES( ?, ?, ?, ?, ?)";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                preStm = con.prepareStatement(url);

                preStm.setString(1, dto.getEmail());
                preStm.setString(2, dto.getPassword());
                preStm.setString(3, dto.getName());
                preStm.setBoolean(4, dto.isRole());
                preStm.setString(5, dto.getStatus());

                int count = preStm.executeUpdate();
                if (count > 0) {
                    return true;
                }
            }
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
