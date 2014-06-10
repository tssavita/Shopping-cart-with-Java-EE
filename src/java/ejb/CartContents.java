/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import cart.CartItem;
import entity.Product;
import exception.ItemNotInserted;
import exception.ItemsNotRetrieved;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author savita
 */
@Stateful
public class CartContents {
    
    @EJB
    private CartItem cartItem;

    @PersistenceContext(unitName = "WebApplication4PU")
    private EntityManager em;
    
    
    private List <CartItem> cartlist;

    public CartContents() {
    }
    
    @PostConstruct
    public void initialize () {
        cartlist = new ArrayList <> ();
    }
    
    public void setCartList (List <CartItem> contents) {
        cartlist = contents;
    }

    /* Add a new item to the cart */
    public String addToCart (String productId) throws ItemNotInserted {
        int flag = 0;
        int quantity = 0;
        try {
    
            // Check if the item is already present. If yes, just increment its quantity.
            for (CartItem item : cartlist) {
                System.out.println("pro name" + productId + "in for loop" + "productId" +item.getProduct().getProductId());
                if (productId.equals(item.getProduct().getProductId())) {
                    System.out.println("pro name" + productId + "in if brace");
                    quantity = item.getQuantity() + 1;
                    item.setQuantity(quantity);
                    flag = 1;
                    break;
                }   
            }
            
            /* An object of Product entity has been created with id = productId */
            if (flag == 0) {
                System.out.println("pro name" + productId + "creating new pro");
                Product pro = new Product ();
                
                pro = ((Product) em.createNamedQuery("Product.findByProductId")
                        .setParameter("productId", productId)
                        .getSingleResult());
            
                cartItem = new CartItem (pro);
                cartlist.add(cartItem);               
            }
            
            return "added to cart";
        }
        catch (Exception e) {
            throw new ItemNotInserted ("Could not insert item into cart using addToCart : " + e.getMessage() + "\n");
        } 
    }
    
    /* Set the quantity for a particular product */
    public void setQuantity (int qty, String productId) {
        int quantity = 0;      
        int index = 0;
        
        /* Check if the item is already present. If yes, just increment its quantity. */
        for (CartItem item : cartlist) {
            if (productId.equals(item.getProduct().getProductId())) {
                System.out.println("pro name" + productId + "in if brace");
                if (qty == 0) {
                    index = cartlist.indexOf(item);
                    cartlist.remove(index);
                    break;
                }
                
                quantity = qty;
                item.setQuantity(quantity);
                break;
            }   
        }
    }
    
    public CartContents thisCartcontents () {
        return this;
    }
    
    /* Remove the specified product from the cart. */
    public void removeItem (String productId) {
        System.out.println ("productID" + productId);
        int index = 0;
        for (CartItem item : cartlist) {
            if (productId.equals(item.getProduct().getProductId())) {
                index = cartlist.indexOf(item);
                cartlist.remove(index);
                break;
            }
        }
    }
    
    /* Returns all the items in the cart. */
    public List <CartItem> viewCart() throws ItemsNotRetrieved {
        try {
            return cartlist;
        }
        catch (Exception e) {
            throw new ItemsNotRetrieved ("Could not retrieve cart contents : " + e.getMessage() + "\n");
        }
    }

    /* Sums up the total cost of the items present in the cart. */
    public float totalCost() {
        float amount = 0;
        float individual_cost;
        
        for (CartItem item : cartlist) {
            individual_cost = ((Product) em.createNamedQuery("Product.findByProductId")
                    .setParameter("productId", item.getProduct().getProductId())
                    .getSingleResult()).getPrice();
            amount += individual_cost * item.getQuantity();
        }
        
        return amount;
    }

    public List <CartItem> getCartList () {
        return this.cartlist;
    }
    
    public int getCartListSize() {
        return this.cartlist.size();
    }
    
    /* Adds the delivery charge with the totalcost of the cart calculated. */
    public float totalIncludingDelivery () {
        return (float) (this.totalCost() + 3.5);
    }

    /* Remove the cart. */
    public void emptyCart () {
        this.cartlist.clear();
    }
    
}
