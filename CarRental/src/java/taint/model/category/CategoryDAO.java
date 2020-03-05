/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.category;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import taint.model.car.CarDTO;
import taint.utils.DBUtils;

/**
 *
 * @author nguye
 */
public class CategoryDAO implements Serializable{
    
    public List<CategoryDTO> loadCategory() throws NamingException, SQLException{
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;

        List<CategoryDTO> listCategory = new ArrayList<>();

        String sqlQuery = "SELECT Category, CategoryDesc "
                + "FROM Category";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                stm = con.createStatement();

                rs = stm.executeQuery(sqlQuery);
                while (rs.next()) {
                    String category = rs.getString("Category");
                    String categoryDesc = rs.getString("CategoryDesc");
                    
                    CategoryDTO dto = new CategoryDTO(category, categoryDesc);
                    
                    listCategory.add(dto);
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
        return listCategory;
    }
}
