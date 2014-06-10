/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedadmin;

import ejb.CategoryFacade;
import exception.ItemsNotRetrieved;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author savita
 */
@Named(value = "categoryAdminView")
@ViewScoped
@ManagedBean
public class CategoryAdminView implements Serializable {
    
    /* This managed bean is used to manage Admin UI. */
    
    /* This is to access to the functions inside CategoryFacade EJB. */
    @EJB
    private CategoryFacade categoryfacade;
    
    private String name;

    
    public CategoryAdminView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addCategory () {
        categoryfacade.addCategory(this.getName());
        this.setName("");
    }
    
    /* Returns a list of call cateogories of products. */
    public List<entity.Category> printCategory() throws ItemsNotRetrieved {
        return categoryfacade.printCategory();
    }
    
}
