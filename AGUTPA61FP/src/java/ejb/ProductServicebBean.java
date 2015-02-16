/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import domain.Product;
import domain.ProductService;
import domain.Provider;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rashim
 */
@Stateful
@Named
@RequestScoped
public class ProductServicebBean {
@PersistenceContext(unitName = "AGUTPA61_FP_PU")
    private EntityManager em;
    public ProductServicebBean() {
    }
    public void create(ProductService a) {
        em.persist(a);
        
    }

    public void update(ProductService a) {
        em.merge(a);
    }

    public void remove(ProductService service ) {
        
        service = em.getReference(ProductService.class, service.getId());

        //Detach product from this provider        
        service.getProvider().getProducts().remove(service.getProduct()); 
        // Detach Provider from Product
        service.getProduct().getProviders().remove(service.getProvider());
        //setting the service's product and provider to null
        service.setProduct(null);
        service.setProvider(null);
        //finally delete the service.
        em.remove(service);
    }
       public List<ProductService> findAll() {
        return em.createNamedQuery("ProductService.findAll", ProductService.class).getResultList();
    }
    public List<ProductService> find(String userName) {
        
        return em.createNamedQuery("ProductService.findAllByUserName", ProductService.class).setParameter("userName", userName).getResultList();
        
    }       
    private static final Logger LOG = Logger.getLogger(ProductServicebBean.class.getName());
    
     public ProductService findByID(Integer productId,Integer providerId) {
         LOG.info("prd id="+productId+" provider id="+providerId);
        return em.createNamedQuery("ProductService.findByID", ProductService.class).setParameter("productId", productId).setParameter("providerId", providerId).getSingleResult();
        }
}
