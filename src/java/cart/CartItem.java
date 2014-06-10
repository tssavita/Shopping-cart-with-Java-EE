/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cart;

import entity.Product;
import javax.ejb.Stateless;

/**
 *
 * @author savita
 */


/* The type of item that is inserted into the cart. */
@Stateless
public class CartItem {
    
    private Product product;
    private int quantity;

    public CartItem() {
        quantity = 1;
    }
    
    public CartItem(Product product) {
        this.product = product;
        quantity = 1;
    }

    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQty() {
        quantity++;
    }

    public void decrementQty() {
        quantity--;
    }

    public double getTotal() {
        double amount = 0;
        amount = (this.getQuantity() * product.getPrice().doubleValue());
        return amount;
    }

}
