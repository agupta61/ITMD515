 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import domain.Account; 
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Rashim
 */

@Stateless
public class AccountBean {

    
    @PersistenceContext(unitName = "AGUTPA61_FP_PU")
    private EntityManager em;
       
    
        public AccountBean() {
    }

    public void create(Account a) {
        System.out.println("Account Object-"+a.toString());
        em.persist(a);
        
    }

    public void update(Account a) {
        em.merge(a);
    }

    public void remove(Account a) {
        em.remove(a);
    }
       public List<Account> findAll() {
        return em.createNamedQuery("Account.findAll", Account.class).getResultList();
    }
    public Account find(Integer addressId) {
        return em.find(Account.class, addressId);
        
    }  
}
