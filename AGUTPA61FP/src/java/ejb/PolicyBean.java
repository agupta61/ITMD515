/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;


import domain.Policy;
import java.io.Serializable;
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
//@Stateless
@Stateful
@Named
@RequestScoped
public class PolicyBean implements Serializable {

   @PersistenceContext(unitName = "AGUTPA61_FP_PU")
   private EntityManager em;
    
    
        public PolicyBean() {
    }

    public void create(Policy a) {
        System.out.println("Policy Object-"+a.toString());
        em.persist(a);
        
    }

    public void update(Policy a) {
       
        em.merge(a);
    }

    public void remove(Policy a) {
         
        em.remove(em.merge(a));
    }
       public List<Policy> findAll() {
        return em.createNamedQuery("Policy.findAll", Policy.class).getResultList();
    }      
       
       public Policy find(Integer addressId) {
        return em.find(Policy.class, addressId);
        
    }  
       
     
     
     public Policy findByCustName(String custId){
           return em.createNamedQuery("Policy.findByName", Policy.class).setParameter("custId", custId).getSingleResult();
       } 
       
}
