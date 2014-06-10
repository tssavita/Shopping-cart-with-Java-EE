/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author savita
 */
@Named(value = "index")
@Dependent
public class Index {

    /**
     * Creates a new instance of Index
     */
    public Index() {
        
    }
    
    /* This is to navigate to login.xhtml. */    
    public String goToLogin () {
        return "login";
    }
    
}
