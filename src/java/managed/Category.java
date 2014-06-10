/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed;

import ejb.CategoryFacade;
import exception.ItemsNotRetrieved;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author savita
 */
@Dependent
@ManagedBean
@Named (value = "category")
public class Category {

    /* This is used to access functions inside CategoryFacade EJB.*/
    @EJB
    private CategoryFacade categoryfacade;
    
    public Category() {
    }
    
    /* prints all the categories on the page */
    public List<entity.Category> printCategory() throws ItemsNotRetrieved {
        return categoryfacade.printCategory();
    }
    
}
