/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.category;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class CategoryDTO implements Serializable{
    
    private String category;
    private String categoryDesc;

    public CategoryDTO() {
    }

    public CategoryDTO(String category, String categoryDesc) {
        this.category = category;
        this.categoryDesc = categoryDesc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }
    
    
    
}
