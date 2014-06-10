/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed;

import cart.CartItem;
import ejb.CartContents;
import exception.ItemNotInserted;
import exception.ItemsNotRetrieved;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author savita
 */
@Named(value = "cartOperations")
@SessionScoped
@ManagedBean
public class CartOperations implements Serializable {

    /* This is used to invoke the functions inside CartContents EJB 
    that implement business logic. */
    @EJB
    private CartContents cartcontents;

    public CartOperations() {
    }
    
    /* Retrieves the parameter that contains the id of the item to be inserted
    in the cart using FacesContext. It passes it to addToCart() function in CartContents EJB.*/
    
    public String addToCart () throws ItemNotInserted {
        FacesContext context = FacesContext.getCurrentInstance();
        String productId = context.getExternalContext().getRequestParameterMap().get("id");
        return cartcontents.addToCart(productId);
    }
    
    public List <CartItem> retrieveList () throws ItemsNotRetrieved {
        return cartcontents.viewCart();
    }
        
    public CartContents getCartContents () {
        return this.cartcontents;
    }
    
    public void setProductQty (int qty, String productId) {
        cartcontents.setQuantity(qty, productId);
    }
    

    public void removeItem (String productId) {
        cartcontents.removeItem(productId);
    }
    
    public int getCartListSize () {
        if (cartcontents.getCartListSize() != 0)
            return cartcontents.getCartListSize();            
        else 
            return 0;
    }   
    
    public void emptyCart () {
        cartcontents.emptyCart();
    }
    
    public float totalCart () {
        return cartcontents.totalCost();
    }
    
    public float totalIncludingDelivery () {
        return cartcontents.totalIncludingDelivery();
    }
    
    /* Navigation Functions that are called from commandButtons in jsf pages.*/
    
    // function to navigate to Login page.
    public String goToCheckout () {
        if (cartcontents.getCartListSize() == 0) {
            FacesMessage message = new FacesMessage ("You have nothing to checkout");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "nothing to checkout";
        }
        return "checkout";
    }
    
    // function to navigate to Index page.
    public String goToIndex () {
        return "index";
    }

}
