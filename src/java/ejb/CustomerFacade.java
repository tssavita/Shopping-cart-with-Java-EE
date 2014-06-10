/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Customer;
import exception.ItemsNotRetrieved;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author savita
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {
    @PersistenceContext(unitName = "WebApplication4PU")
    private EntityManager em;
    
    /* Function - Queries the database and returns the customer's firstname */
    public String customerFirstName(String customerId) {
        return ((Customer) em.createNamedQuery("Customer.findByCustomerId")
                .setParameter ("customerId", customerId)
                .getSingleResult()).getFirstName();
    }
    
    /* Function - Queries the database and returns the customer's lastname */
    public String customerLastName(String customerId) {
        return ((Customer) em.createNamedQuery("Customer.findByCustomerId")
                .setParameter ("customer_id", customerId)
                .getSingleResult()).getLastName();
    }

/* Incase the shopping cart were to implement a login and associate the cartcontents
    with a user so that he can retrieve it later the following functions :
    
    1. login_check ()
    2. is_present () 
    
    could be used.*/
    
/*    public String login_check (String customer_id, String pass) {
        Customer result;
        try {
            result = (Customer) em.createNamedQuery("Customer.findByCustomerId")
                .setParameter("customerId", customer_id)
                .getSingleResult();
        }
        catch (Exception e) {  
            result = null;
        }
        if (result == null)
            return "fail";
        else if (pass.equals(result.getPass()))
            return "success";
        else 
            return "fail";
    }*/
    
    public boolean is_Present(String customerId) {
        Customer customer;
        try {
            customer = (Customer) em.createNamedQuery("Customer.findByCustomerId")
                .setParameter("customerId", customerId)
                .getSingleResult();
        }
        catch (Exception e) {
            customer = null;
        }
        if (customer == null)
            return true;
        else 
            return false;
    }
    
    /* function - returns the list of all customers.
    invoked from - admin UI view userinfo.xhtml*/
    public List<Customer> printCustomer() throws ItemsNotRetrieved {
        try {
            return (List<Customer>) em.createNamedQuery("Customer.findAll").getResultList();
        }
        catch (Exception e) {
            throw new ItemsNotRetrieved("Could not retrieve categories : " + e.getMessage() + "\n");
        }        
    }
    
    /* function - adds a new customer to the database through persistence.
    invoked from - customer UI view - checkout.xhtml */
    public Customer addCustomer (String fname, String lname, String emailId,
        String street, String town, String country) {
      
        Customer c = new Customer ();
        
        String customerId = "cust" + generateString();
        
        try {

            c.setFirstName(fname);
            c.setLastName(lname);
            c.setCustomerId(customerId);
            c.setEmailId(emailId);
            c.setStreetNo(street);
            c.setTown(town);
            c.setCountry(country);

            em.persist(c);

        }
        catch (Exception e) {
            System.out.println ("User already registered");
        }
        return c;
    };

    public static String generateString() {
        int length = 10;
        Random rng = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";

        char[] text = new char[length];
        for (int i = 0; i < length; i++) 
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        return new String(text);
    }
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }
    
}
