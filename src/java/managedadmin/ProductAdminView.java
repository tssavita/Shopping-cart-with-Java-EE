/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedadmin;

import ejb.ProductFacade;
import entity.Product;
import exception.ItemNotFound;
import exception.ItemsNotRetrieved;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import utils.ImageConversion;

/**
 *
 * @author savita
 */
@Named(value = "productAdminView")
@SessionScoped
@ManagedBean
public class ProductAdminView implements Serializable {
    
    /* This managed bean is used to manage Admin UI. */
    
    /* This is to access to the functions inside ProductFacade EJB. */
    @EJB
    private ProductFacade productfacade;
    
    private String productName;
    private float productPrice;
    private String productBrand;
    private String productDescription;
    private String categoryName;
    private byte[] productPicture;
    private Part uploadimage;
    private FacesContext context;
    private ExternalContext eContext;

    public ProductAdminView() {
    }
    
    @PostConstruct
    public void getCurrentValues () {
        context = FacesContext.getCurrentInstance();
        eContext = FacesContext.getCurrentInstance().getExternalContext();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductDescription() {
        return productDescription;         
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Part getUploadimage() {
        return uploadimage;
    }

    public void setUploadimage(Part uploadimage) {
        System.out.println ("Entered setuploadimage func in mbean \n");
        System.out.println ("Name : " + uploadimage.getName() + "\n");
        this.uploadimage = uploadimage;
    }
    
    public void Uploadedtobyte () throws IOException {
        System.out.println ("Entered uploadedtobyte func in mbean\n");
        Part file = this.getUploadimage();
        
        try {
            InputStream ifs = file.getInputStream();
            this.productPicture = ImageConversion.InputStreamToByte(ifs);  
        }
        catch (IOException e) {
            System.out.println("Image could not be converted. " + e.getMessage() + "\n");
        }
    }
    
    public byte[] getProductPicture () {
        return this.productPicture;
    }

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
    
    public void uploadedtobyte () {
         
        System.out.println ("Entered uploadedtobyte func in mbean\n");
        Part file = this.getUploadimage();
        
        try {
            InputStream ifs = file.getInputStream();
            this.productPicture = ImageConversion.InputStreamToByte(ifs);  
        }
        catch (IOException e) {
            System.out.println("Image could not be converted. " + e.getMessage() + "\n");
        }
       
    }
    
    /* To add a new product to the database. This is called from the Admin UI. */
    public void addProduct () {

        String result = productfacade.addProduct (this.getProductName(), 
                this.getCategoryName(), this.getProductBrand(), this.getProductDescription(), 
                this.getProductPrice(), this.getProductPicture());
        if (result == "success") {
            FacesMessage facesMessage = new FacesMessage ("Added product successfully");
            FacesContext.getCurrentInstance().addMessage("addproduct", facesMessage);            
        }
        else if (result == "fail") {
            FacesMessage facesMessage = new FacesMessage ("Did not add product");
            FacesContext.getCurrentInstance().addMessage("addproduct", facesMessage);            
        }
        
        this.setCategoryName("");
        this.setProductBrand("");
        this.setProductName("");
        this.setProductPrice(0);
        this.setProductDescription("");
        
                
    }
    
    public String gotoAdminmain() {
        return "mainpage";
    }
    
    /* Lists all products for the admin to view. */
    public List <Product> printProducts () throws ItemsNotRetrieved {
        return productfacade.printProducts();
    }
    
    /* Lists all products that belong to a particular category, for the admin to view. */
    public List <Product> printProductsofCategory (String categoryid) throws ItemsNotRetrieved {
        return productfacade.printProductsofCategory(categoryid);
    }

}
