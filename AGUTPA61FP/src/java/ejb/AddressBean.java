/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import domain.Address;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rashim
 */
@Stateless
public class AddressBean {
        @PersistenceContext(unitName = "AGUTPA61_FP_PU")
    private EntityManager em;

    public AddressBean() {
    }
public void create(Address a) {
        em.persist(a);
        
    }

    public void update(Address a) {
        em.merge(a);
    }

    public void remove(Address a) {
        em.remove(a);
    }
       public List<Address> findAll() {
        return em.createNamedQuery("Address.findAll", Address.class).getResultList();
    }
    public Address find(Integer addressId) {
        return em.find(Address.class, addressId);
        
    }        
}
