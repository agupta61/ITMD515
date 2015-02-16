/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;


import domain.Customer;
import domain.Policy;
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
public class CustomerBean {


  @PersistenceContext(unitName = "AGUTPA61_FP_PU")
  private  EntityManager em;
  Customer c ;
    
    
        public CustomerBean() {
            
    }

    public void create(Customer a) {
        //System.out.println("Customer Object"+a.toString());
        em.persist(a);
        
    }

 public void update(Customer a) {
        em.merge(a);
    }

    public void remove(Customer a) {
        em.remove(a);
    }
       public List<Customer> findAll() {
        return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }
       public Customer findByUserName(String userName){
           return em.createNamedQuery("Customer.findByUsername", Customer.class).setParameter("userName", userName).getSingleResult();
       }
       public List<Policy> findAllPolicy(String userName){
           return em.createNamedQuery("Policy.findAllPolicies", Policy.class).setParameter("userName", userName).getResultList();
       }
    
       
       
       
       
}
