/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author savita
 */
@Entity
@Table(name = "shopping_cart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductsOrdered.findAll", query = "SELECT s FROM ProductsOrdered s"),
    @NamedQuery(name = "ProductsOrdered.findByCustOrdId", query = "SELECT s FROM ProductsOrdered s WHERE s.shoppingCartPK.custOrdId = :custOrdId"),
    @NamedQuery(name = "ProductsOrdered.findByProductId", query = "SELECT s FROM ProductsOrdered s WHERE s.shoppingCartPK.productId = :productId"),
    @NamedQuery(name = "ProductsOrdered.findByProductQty", query = "SELECT s FROM ProductsOrdered s WHERE s.productQty = :productQty")})
public class ProductsOrdered implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductsOrderedPK shoppingCartPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_qty")
    private int productQty;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;
    @JoinColumn(name = "cust_ord_id", referencedColumnName = "order_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orders orders;

    public ProductsOrdered() {
    }

    public ProductsOrdered(ProductsOrderedPK shoppingCartPK) {
        this.shoppingCartPK = shoppingCartPK;
    }

    public ProductsOrdered(ProductsOrderedPK shoppingCartPK, int productQty) {
        this.shoppingCartPK = shoppingCartPK;
        this.productQty = productQty;
    }

    public ProductsOrdered(int custOrdId, String productId) {
        this.shoppingCartPK = new ProductsOrderedPK(custOrdId, productId);
    }

    /*public ProductsOrdered(String productId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    public ProductsOrderedPK getShoppingCartPK() {
        return shoppingCartPK;
    }

    public void setShoppingCartPK(ProductsOrderedPK shoppingCartPK) {
        this.shoppingCartPK = shoppingCartPK;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shoppingCartPK != null ? shoppingCartPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductsOrdered)) {
            return false;
        }
        ProductsOrdered other = (ProductsOrdered) object;
        if ((this.shoppingCartPK == null && other.shoppingCartPK != null) || (this.shoppingCartPK != null && !this.shoppingCartPK.equals(other.shoppingCartPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ShoppingCart[ shoppingCartPK=" + shoppingCartPK + " ]";
    }
    
}
