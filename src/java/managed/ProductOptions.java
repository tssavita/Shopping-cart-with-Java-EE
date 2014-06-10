/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed;

import ejb.ProductFacade;
import entity.Product;
import exception.ItemNotFound;
import exception.ItemsNotRetrieved;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author savita
 */
@Named(value = "productOptions")
@Dependent
public class ProductOptions {

    @EJB
    private ProductFacade productfacade;
    
    FacesContext context;
    ExternalContext eContext;
    
    public ProductOptions () {
    }
    
    @PostConstruct
    public void getCurrentValues () {
        context = FacesContext.getCurrentInstance();
        eContext = FacesContext.getCurrentInstance().getExternalContext();
    }

    public String getId () throws ItemNotFound {
        String productId = this.eContext.getRequestParameterMap().get("id");
        return productId;
    }

    
    public String getName () throws ItemNotFound {
        String productId = this.eContext.getRequestParameterMap().get("id");
        return productfacade.productName(productId);
    }
    
    public float getPrice () throws ItemNotFound {
        String productId = this.eContext.getRequestParameterMap().get("id");
        return productfacade.productPrice(productId);
    }
    
    public String getBrand () throws ItemNotFound {
        String productId = this.eContext.getRequestParameterMap().get("id");
        return productfacade.productBrand(productId);
    }
    
    public String getDescription () throws ItemNotFound {
        String productId = this.eContext.getRequestParameterMap().get("id");
        return productfacade.productDescription(productId);
    }
        
    /* This function is to get the picture that has been stored in the
    database in the form of 'bytes []' and to print it on the view. */
    public StreamedContent getPicture () throws ItemNotFound {
        
        if (this.context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }
        else {
            String id = this.eContext.getRequestParameterMap().get("id");
            byte[] image = productfacade.productPicture(id);
            InputStream is = new ByteArrayInputStream (image);
            StreamedContent newimage = new DefaultStreamedContent(is, "image/jpeg");
            return newimage;
        }
    }
    
    /* Returns all products that belong to a specified category. */
    public List <Product> printProductsofCategory (String categoryid) throws ItemsNotRetrieved {
        return productfacade.printProductsofCategory(categoryid);
    }
    
}
