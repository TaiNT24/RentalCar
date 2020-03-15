/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.feedback;

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
public class FeedbackDAO implements Serializable{
    
    public boolean insertFeedback(FeedbackDTO dto) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement preStm = null;
        
        String sqlQuery = "INSERT INTO Feedback values(?,?,?) ";
        try{
            con = DBUtils.connectDB();
            if(con!=null){
                preStm = con.prepareStatement(sqlQuery);
                
                preStm.setInt(1, dto.getScale());
                preStm.setString(2, dto.getFeedback());
                preStm.setInt(3, dto.getIdRent());
                
                int row = preStm.executeUpdate();
                if(row>0){
                    return true;
                }
            }
        }finally{
            if(preStm!=null){
                preStm.close();
            }
            if(con!=null){
                con.close();
            }
        }
        return false;
    }

    public int getFeedback(int idRent) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        String sqlQuery = "select IDRating from Feedback where IDRent = ? ";
        try{
            con = DBUtils.connectDB();
            if(con!=null){
                preStm = con.prepareStatement(sqlQuery);
                
                preStm.setInt(1, idRent);
                
                rs = preStm.executeQuery();
                if(rs.next()){
                    return rs.getInt("IDRating");
                }
            }
        }finally{
            if(rs!=null){
                rs.close();
            }
            if(preStm!=null){
                preStm.close();
            }
            if(con!=null){
                con.close();
            }
        }
        return -1;
    }
    
    
}
