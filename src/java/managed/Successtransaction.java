/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed;

import ejb.FeedbackFacade;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.renderkit.CoreRenderer;

/**
 *
 * @author savita
 */
@Named(value = "feedback")
@Dependent
@ManagedBean
public class Successtransaction extends CoreRenderer {
    
    @EJB
    FeedbackFacade feedbackfacade;
    
    CartOperations cartoperations;
    
    private String feedback;

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    public Successtransaction() {
    }
    
    public void addfeedback (String emailId) {
        System.out.println (this.feedback);
        feedbackfacade.addFeedback(emailId, this.getFeedback());
        FacesMessage successfeedback = new FacesMessage ("Thank you for your feedback !");
        FacesContext.getCurrentInstance().addMessage("addfeedbackform", successfeedback);

    }
    
    public String gotoHome(CartOperations cartoperations) {
        cartoperations.emptyCart();
        return "success";
        
    }
    
}
