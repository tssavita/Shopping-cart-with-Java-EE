/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import cart.CartItem;
import entity.Customer;
import entity.Orders;
import entity.ProductsOrdered;
import entity.ProductsOrderedPK;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author savita
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PlaceOrder {
    @PersistenceContext(unitName = "WebApplication4PU")
    private EntityManager em;
    
    @EJB
    CustomerFacade customerfacade;
    
    public boolean customerDetailsEntered (Customer customer) {
        if (customer.getFirstName()!= null &&
                customer.getLastName() != null &&
                customer.getCustomerId() != null &&
                customer.getStreetNo() != null &&
                customer.getTown() != null &&
                customer.getCountry() != null) 
            return true;
        else 
            return false;
    }
    
    /* Create an order for the customer */
    public Orders addOrder (Customer customer, CartContents cart) {
        
        Orders orders = new Orders ();
        
        orders.setCustomerId(customer);
        orders.setAmount(cart.totalCost());
            

        em.persist(orders);
        return orders;
    }
    
    /* Link the products purchased with the order */
    public void addItemsToOrder (Orders orders, CartContents cart) {
        em.flush();
        List <CartItem> list = cart.getCartList();
        
        for (CartItem item : list) {

            ProductsOrderedPK popk = new ProductsOrderedPK();
            
            popk.setCustOrdId(orders.getOrderId());
            popk.setProductId(item.getProduct().getProductId());
            
            ProductsOrdered po = new ProductsOrdered (popk);
            
            po.setProductQty(item.getQuantity());
            
            em.persist(po);
        }
    }
    
    /* This functions is where three steps take place :
    
        1. customer details are entered into the database
        2. an order is created for the customer 
        3. The order is linked with the products that customer checked out from the cart.
    
    It is necessary that these steps SHOULD occur together.
    
    */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int completeOrder (String firstName, String lastName, String emailId, 
            String street, String town, String country, CartContents cart) {

        try {
            Customer customer = customerfacade.addCustomer(firstName, lastName, emailId, street, town, country);
            Orders orders = addOrder (customer, cart);
            addItemsToOrder (orders, cart);
            //cart.emptyCart();

            return orders.getOrderId();
        }
        catch (Exception e) {
            return 0;
        }
    }
}
