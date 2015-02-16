/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;


import domain.Product;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rashim
 */

@Stateful
@Named
@RequestScoped
public class ProductBean {
   
    @PersistenceContext(unitName = "AGUTPA61_FP_PU")
   private EntityManager em;
        
    private Product user;

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public Product getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(Product user) {
        this.user = user;
    }
    
        public ProductBean() {
    }

    public void create(Product a) {
            System.out.println("Product Object-"+a.toString());
        em.persist(a);
        
    }

    public void update(Product a) {
        em.merge(a);
    }

    public void remove(Product a) {
        em.remove(a);
    }
       public List<Product> findAll() {
        return em.createNamedQuery("Product.findAll", Product.class).getResultList();
    }
        
    public List<Product> findAllProducts(String userName){
           return em.createNamedQuery("Product.findByUserName", Product.class).setParameter("userName", userName).getResultList();
       }   
    
        public Product findByProductName(String prdname){
           return em.createNamedQuery("Product.findByProductName", Product.class).setParameter("prdname", prdname).getSingleResult();
       } 
        
        public Product find(Integer productId) {
        return em.find(Product.class, productId);
        }
}
