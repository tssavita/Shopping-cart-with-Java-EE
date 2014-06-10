/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Category;
import exception.ItemsNotRetrieved;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utils.RandomString;

/**
 *
 * @author savita
 */

@Named (value = "categoryfacade")
@Stateless
public class CategoryFacade extends AbstractFacade<Category> {
    @PersistenceContext(unitName = "WebApplication4PU")
    private EntityManager em;
    
    /* Adds a new category to the shopping store. */
    public void addCategory (String categoryName) {
        
        String categoryId = "cat" + generateString();
        
        Category cat = new Category(categoryId, categoryName);
        try {
            em.persist(cat);
        }
        catch (Exception e) {
            System.out.println ("Could not insert category" + e.getMessage() + "\n");
        }
    }
    
    /* Print categories on the front page. */
    public List<Category> printCategory() throws ItemsNotRetrieved {
        try {
            return (List<Category>) em.createNamedQuery("Category.findAll").getResultList();
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
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
    }
    
}
