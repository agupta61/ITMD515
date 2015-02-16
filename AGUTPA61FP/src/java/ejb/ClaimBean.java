/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;


import domain.Claim;
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
public class ClaimBean {

   @PersistenceContext(unitName = "AGUTPA61_FP_PU")
    private EntityManager em;
            
    
        public ClaimBean() {
    }

    public void create(Claim a) {
        System.out.println("Claim Object-"+a.toString());
        em.persist(a);
        
    }

    public void update(Claim a) {
        em.merge(a);
    }

    public void remove(Claim a) {
        a = em.getReference(Claim.class, a.getId());
        a.getPolicy().getClaim().remove(a);
        em.remove(a);
        
    }
       public List<Claim> findAll() {
        return em.createNamedQuery("Claim.findAll", Claim.class).getResultList();
    }
    private static final Logger LOG = Logger.getLogger(ClaimBean.class.getName());
    
        public  List<Claim> findByPolicyNo(Integer polID){
            LOG.info("Size is "+em.createNamedQuery("Claim.findByPolicyNo", Claim.class).setParameter("polID", polID).getResultList().size());
           return em.createNamedQuery("Claim.findByPolicyNo", Claim.class).setParameter("polID", polID).getResultList();
       }
}
