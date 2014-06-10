/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Category;
import entity.Customer;
import entity.Product;
import exception.ItemNotFound;
import exception.ItemsNotRetrieved;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author savita
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {
    @PersistenceContext(unitName = "WebApplication4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /* Add a new product to the database (this function is used by the admin) */
    public String addProduct (String name, String  catID, String brand, String description, float price, byte[] productPicture) {
        Product pro = new Product ();
        
        String id = "pro" + generateString();
        
        Category cat = ( (Category) em.createNamedQuery("Category.findByName")
                .setParameter("name", catID).getSingleResult());
                
        try {
            pro.setProductId(id);
            pro.setProductName(name);
            pro.setBrand(brand);
            pro.setPrice(price);
            pro.setDescription(description);
            pro.setCategoryId(cat);
            pro.setProductPicture(productPicture);

            em.persist(pro);
            return "success";
        }
        catch (Exception e) {
            System.out.println ("Could not add product " + e.getMessage() + "\n");
            return "fail";
        }
    }
    
    /* Return the name of the product of a particular productId from database. */
    public String productName (String productId) throws ItemNotFound {
        try {
            return ((Product) em.createNamedQuery ("Product.findByProductId")
                    .setParameter("productId", productId)
                    .getSingleResult()).getProductName();
        }
        catch (Exception e) {
            throw new exception.ItemNotFound("Could not retrieve item from database " + e.getMessage() + "\n");
        } 
    }
    
    /* Return the brand of the product of a particular productId from database. */
    public String productBrand (String productid) throws ItemNotFound {
        try {
            return ((Product) em.createNamedQuery ("Product.findByProductId")
                    .setParameter("productId", productid)
                    .getSingleResult()).getBrand();
        }
        catch (Exception e) {
            throw new exception.ItemNotFound("Could not retrieve item from database " + e.getMessage() + "\n");
        } 
    }
    
    /* Return the price of the product of a particular productId from database. */
    public float productPrice (String productid) throws ItemNotFound {
        try {
            return ((Product) em.createNamedQuery ("Product.findByProductId")
                    .setParameter("productId", productid)
                    .getSingleResult()).getPrice();
        }
        catch (Exception e) {
            throw new exception.ItemNotFound("Could not retrieve item from database " + e.getMessage() + "\n");
        } 
    }
    
    /* Return the description of the product of a particular productId from database. */
    public String productDescription (String productid) throws ItemNotFound {
        try {
            return ((Product) em.createNamedQuery ("Product.findByProductId")
                    .setParameter("productId", productid)
                    .getSingleResult()).getDescription();
        }
        catch (Exception e) {
            throw new exception.ItemNotFound("Could not retrieve item from database " + e.getMessage() + "\n");
        } 
    }

    /* Return the picture of the product of a particular productId from database. */
    public byte[] productPicture (String productid) throws ItemNotFound {
        try {
            return ((Product) em.createNamedQuery ("Product.findByProductId")
                    .setParameter("productId", productid)
                    .getSingleResult()).getProductPicture();
        }
        catch (Exception e) {
            throw new exception.ItemNotFound("Could not retrieve item from database " + e.getMessage() + "\n");
        } 
    }
    
    /* Prints all the products. */
    public List<Product> printProducts() throws ItemsNotRetrieved {
        try {
            return (List<Product>) em.createNamedQuery("Product.findAll").getResultList();
        }
        catch (Exception e) {
            throw new ItemsNotRetrieved("Could not retrieve categories : " + e.getMessage() + "\n");
        }
    }
    
    public static String generateString() {
        int length = 10;
        Random rng = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";

        char[] text = new char[length];
        for (int i = 0; i < length; i++) 
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        return new String(text);
    }

            
    /* Prints all products belonging to a particualar category. */
    public List <Product> printProductsofCategory (String categoryid) throws ItemsNotRetrieved {
        
        int[] range = new int[10];
        Category cat = new Category(categoryid);
            
        try {
            return (List<Product>) em.createNamedQuery("Product.findByCategoryId")
                    .setParameter("categoryId", categoryid)
                    .getResultList();
        }
        catch (Exception e) {
            throw new ItemsNotRetrieved("Could not retrieve categories: " + e.getMessage() + "\n");
        }
    }


    public ProductFacade() {
        super(Product.class);
    }
    
}
