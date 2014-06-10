/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Customer;
import entity.Feedback;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author savita
 */
@Stateless
public class FeedbackFacade extends AbstractFacade<Feedback> {
    @PersistenceContext(unitName = "WebApplication4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeedbackFacade() {
        super(Feedback.class);
    }
    
    public void addFeedback (String emailId, String feedback) {
        
        Feedback f = new Feedback ();
        Customer customer = (Customer) em.createNamedQuery("Customer.findByEmailId")
                .setParameter("emailId", emailId).getSingleResult();
        
        f.setCustomerId(customer);
        f.setFeedDesc(feedback);
        
        em.persist(f);
        
    }
    
}
