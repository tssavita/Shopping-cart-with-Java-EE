/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author savita
 */
@Embeddable
public class ProductsOrderedPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "cust_ord_id")
    private int custOrdId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "product_id")
    private String productId;

    public ProductsOrderedPK() {
    }

    public ProductsOrderedPK(int custOrdId, String productId) {
        this.custOrdId = custOrdId;
        this.productId = productId;
    }

    public int getCustOrdId() {
        return custOrdId;
    }

    public void setCustOrdId(int custOrdId) {
        this.custOrdId = custOrdId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) custOrdId;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductsOrderedPK)) {
            return false;
        }
        ProductsOrderedPK other = (ProductsOrderedPK) object;
        if (this.custOrdId != other.custOrdId) {
            return false;
        }
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ShoppingCartPK[ custOrdId=" + custOrdId + ", productId=" + productId + " ]";
    }
    
}
