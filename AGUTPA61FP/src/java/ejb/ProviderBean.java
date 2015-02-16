/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;


import domain.Provider;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
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
public class ProviderBean {

@PersistenceContext(unitName = "AGUTPA61_FP_PU")
    private EntityManager em;
                    
    
        public ProviderBean() {
    }

    public void create(Provider a) {
       System.out.println("Provider Object-"+a.toString());
        em.persist(a);
       
    }

    public void update(Provider a) {
        em.merge(a);
    }

    public void remove(Provider a) {
        em.remove(a);
    }
       public List<Provider> findAll() {
        return em.createNamedQuery("Provider.findAll", Provider.class).getResultList();
    }
public Provider findByUserName(String userName){
           return em.createNamedQuery("Provider.findByUsername", Provider.class).setParameter("userName", userName).getSingleResult();
       }       
    public  List<Provider> findProvidersByProductId(Integer prodID){
           return em.createNamedQuery("Provider.findProvidersByProductId", Provider.class).setParameter("id", prodID).getResultList();
       }
}
