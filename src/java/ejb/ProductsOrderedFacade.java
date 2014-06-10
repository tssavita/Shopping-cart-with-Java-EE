/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.ProductsOrdered;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author savita
 */
@Stateless
public class ProductsOrderedFacade extends AbstractFacade<ProductsOrdered> {
    @PersistenceContext(unitName = "WebApplication4PU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsOrderedFacade() {
        super(ProductsOrdered.class);
    }
    
}
