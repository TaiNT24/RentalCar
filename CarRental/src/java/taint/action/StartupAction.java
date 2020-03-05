/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.List;
import java.util.Map;
import taint.model.car.CarDAO;
import taint.model.car.CarDTO;
import taint.model.category.CategoryDAO;
import taint.model.category.CategoryDTO;

/**
 *
 * @author nguye
 */

public class StartupAction {
    private final String SUCCESS = "success";
    
    public StartupAction() {
    }
    
    public String execute() throws Exception {
        Map application = ActionContext.getContext().getApplication();
        
        List<CarDTO> listCars = (List<CarDTO>) application.get("LISTCAR");
        
        if(listCars == null){
            CarDAO dao = new CarDAO();
            CategoryDAO categoryDAO  = new CategoryDAO();

            listCars = dao.getListCar();
            
            List<CategoryDTO> listCategory = categoryDAO.loadCategory();
            
            application.put("LISTCAR", listCars);
            application.put("CATEGORY", listCategory);
            
        }
        
        return SUCCESS;
    }

}
