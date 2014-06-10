package managed;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.CartContents;
import ejb.PlaceOrder;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author savita
 */
@ManagedBean
@Named(value = "checkout")
@SessionScoped
public class Checkout implements Serializable {
    
    /* This is to access functions written in PlaceOrder EJB. */
    @EJB
    private PlaceOrder placeorder;
        
    private String firstName;
    private String lastName;
    private String emailId;
    private String street;
    private String town;
    private String country;
    
    int orderid;

    public Checkout() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public int getOrderId () {
        return orderid;
    }
    
    public void setOrderId (int orderid) {
        this.orderid = orderid;
    }
       
    public String completeOrder (CartContents cartcontents) {

        orderid = placeorder.completeOrder(this.getFirstName(), this.getLastName(), 
                this.getEmailId(), this.getStreet(), this.getTown(), this.getCountry(), cartcontents);
        
        if (orderid > 0)
            return "success";
        else 
            return "failure";
    
    }
    
}
