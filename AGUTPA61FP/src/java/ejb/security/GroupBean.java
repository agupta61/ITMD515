/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.security;


import domain.security.Group;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rashim
 */
@Stateless
public class GroupBean {
    
    @PersistenceContext(unitName = "AGUTPA61_FP_PU")
  private  EntityManager em;
    
    
        public GroupBean() {
    }

    public void create(Group a) {
        //System.out.println("Customer Object"+a.toString());
        em.persist(a);
        
    }

 public void update(Group a) {
        em.merge(a);
    }

    public void remove(Group a) {
        em.remove(a);
    }
       public List<Group> findAll() {
        return em.createNamedQuery("Group.findAll", Group.class).getResultList();
    }
    public Group find(String groupName) {
        return em.find(Group.class, groupName);
        
    }
}
