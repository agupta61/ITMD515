/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.security;


import domain.security.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rashim
 */
@Stateless
public class UserBean {
    
    @PersistenceContext(unitName = "AGUTPA61_FP_PU")
  private  EntityManager em;
    
    
        public UserBean() {
    }

    public void create(User a) {
        //System.out.println("Customer Object"+a.toString());
        em.persist(a);
        
    }

 public void update(User a) {
        em.merge(a);
    }

    public void remove(User a) {
        em.remove(a);
    }
       public List<User> findAll() {
        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }
    
    
    
}
