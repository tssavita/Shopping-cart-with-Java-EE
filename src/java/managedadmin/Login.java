/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedadmin;

import ejb.CustomerFacade;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author savita
 */
@ManagedBean
@SessionScoped
@Named(value = "login")
public class Login implements Serializable {
    
    /* This managed bean is used to manage Admin UI. */
    
    /* This is to access to the functions inside CustomerFacade EJB. */
    @EJB
    CustomerFacade customerfacade;
       
    private String username;
    private String pass;
            
    public String getUserName () {
        return this.username;
    }
    
    public void setUserName (String userName) {
        this.username = userName;
    }
        
    public String getPass () {
        return this.pass;
    }
    
    public void setPass (String pass) {
        this.pass = pass;
    }
    
    public String welcome () {
        return "Welcome " + this.getUserName();
    }

    /* This function is used to check if the client accessing the 
    Admin UI is the admin himself. When the Administrator logs in, 
    he is added to the SessionMap using FacesContext functions. 
    FacesMessage has been used to print the result of this function 
    on the next page that occurs in the navigation.*/
    
    public String isAdmin () {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage;
        
        String uname = this.username;
        String passw = this.pass;
        
        if (uname.equals("admin") && passw.equals("XXXXXX")) {
            facesContext.getExternalContext().getSessionMap().put("username", "admin");
            facesMessage = new FacesMessage ("You logged in successfully");
            facesContext.addMessage("loginform", facesMessage);            
            return "success";
        }
        else {
            facesMessage = new FacesMessage ("You do not have access to this page");
            facesContext.addMessage("loginform", facesMessage);            
            return "fail";
        }
    }

    /* This is for the admin to logout. */
    public String logout () {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "loggedout";
    }
    
    /* This function could be uncommented if the states are to 
    be maintained using web sessions. It provides as the function 
    to check correct login of the user. */
    
/*    public String login () {
        System.out.println ("Username : " + this.getUserName() +" Password : " + this.getPass() );
        String result = customerfacade.login_check(getUserName(), getPass());
        //Customer current = this.customerfacade.
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage;
        if (result == "success") {
            facesContext.getExternalContext().getSessionMap().put("user", this);
            facesMessage = new FacesMessage ("You logged in successfully");
            facesContext.addMessage("loginform", facesMessage);
        }
        else {
            facesMessage = new FacesMessage ("You have entered an invalid username/password.");
            facesContext.addMessage("loginform", facesMessage);
        }
        return result;
    }*/

}
