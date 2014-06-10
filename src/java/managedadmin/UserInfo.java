/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedadmin;

import ejb.CustomerFacade;
import entity.Customer;
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
@Named(value = "userInfo")
@ViewScoped
@ManagedBean
public class UserInfo implements Serializable {

    /* This managed bean is used to manage Admin UI. */
    
    /* This is to access to the functions inside CustomerFacade EJB. */    
    @EJB
    CustomerFacade customerfacade;
    
    public UserInfo() {

    }
    
    /* Prints all information of all the customers for the admin to view. */
    public List<Customer> printCustomers() throws ItemsNotRetrieved {
        return customerfacade.printCustomer();
    }
    
}
