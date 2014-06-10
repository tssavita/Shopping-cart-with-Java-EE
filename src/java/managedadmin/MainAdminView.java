/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedadmin;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author savita
 */
@Named(value = "mainAdminView")
@ViewScoped
@ManagedBean
public class MainAdminView implements Serializable {
    
    /* This managed bean is used to manage Admin UI. */

    /**
     * Creates a new instance of MainAdminView
     */
    public MainAdminView() {
    }
    
    /* For navigation purposes */
    public String goToCategory () {
        return "viewcategory";
    }
    
    public String goToUsers () {
        return "viewuser";
    }
    
}
