/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import domain.Account;
import domain.Customer;
import domain.Policy;
import domain.Product;
import domain.Provider;
import domain.security.User;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Rashim
 */
@Stateless
@Path("domain.security.user")
public class UserFacadeREST extends AbstractFacade<User> {
    @PersistenceContext(unitName = "AGUTPA61_FP_PU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(User entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, User entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }
    @Inject
    ProductBean productBean;
    
    private Product product;

    /**
     * Get the value of product
     *
     * @return the value of product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Set the value of product
     *
     * @param product new value of product
     */
    public void setProduct(Product product) {
        this.product = product;
    }
    @Inject
    ProviderBean providerBean;
    
    private Provider provider;
    @Inject
    PolicyBean policyBean;
    /**
     * Get the value of provider
     *
     * @return the value of provider
     */
    public Provider getProvider() {
        return provider;
    }

    /**
     * Set the value of provider
     *
     * @param provider new value of provider
     */
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Inject
    CustomerBean customerBean;
    @Inject 
    TeslaEmail teslaEmail;
  @Inject
  AccountBean accountBean;
    @GET
    @Path("{name}/{email}")
    @Produces({"application/xml", "application/json"})
    public Policy find(@PathParam("name") String id,@PathParam("email") String email) {
         
        Date date = new Date();
        Policy policy =new Policy();
        policy.setPolicyStartDate(date);
        //get the policy End date 
        Calendar c = Calendar.getInstance(); 
        c.setTime(date); 
        c.add(Calendar.DATE, 1);//next day
        date = c.getTime();
        policy.setPolicyEndDate(date);
        
        product=productBean.findByProductName("Travel Insurance");
        provider=providerBean.findByUserName("Tesla");
        //setting product id or name
        policy.setProductId(product.getId().longValue());
        policy.setProviderId(provider.getId().longValue());
            Account acc=new Account();
            Customer cust1=new Customer();
             acc.setAccountNo(999999999);
              cust1.setName(id);
              
             policy.setCustomer(cust1);
             //adding the customer to account
        acc.setCustomer(cust1);
        //adding policy to account
        acc.setPolicy(policy);
       // policy.setAccount(acc);
        //persist the accout 
        accountBean.create(acc);
        customerBean.create(cust1);
        teslaEmail.sendEmail(email, "Policy Confirmation Email", "Policy Number:" + policy.getId());
        teslaEmail.sendEmail("agupta61@hawk.iit.edu", "Policy Sold", "Policy Number:" + policy.getId());
        
        return (policyBean.findByCustName(id));
             
        
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<User> findAll() {
        return super.findAll();
    }

    

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
