/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation; 

import domain.Account;
import domain.Address;
import domain.Claim;
import domain.Customer;
import domain.Policy;
import domain.Product;
import domain.Provider;
import domain.security.User;
import ejb.AccountBean;
import ejb.AddressBean;
import ejb.ClaimBean;
import ejb.CustomerBean;
import ejb.PolicyBean;
import ejb.ProductBean;
import ejb.TeslaEmail;
import ejb.security.GroupBean;
import ejb.security.UserBean;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Rashim
 */
@Named
@SessionScoped
public class CustomerDetails extends mainController implements  Serializable{
    @EJB
    private GroupBean groupBean;
    @EJB
    private UserBean userBean;
    @Inject
    TeslaEmail teslaEmail;
    private Claim claim;
    
    private Provider provider;

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

    /**
     * Get the value of claim
     *
     * @return the value of claim
     */
    public Claim getClaim() {
        return claim;
    }

    /**
     * Set the value of claim
     *
     * @param claim new value of claim
     */
    public void setClaim(Claim claim) {
        this.claim = claim;
    }
    @Inject
    ClaimBean claimBean;
   
     @Inject
     LoginController loginController;
     @EJB
     private CustomerBean customerBean; 
     @EJB
     private ProductBean productBean;
     @EJB
     private AccountBean accountBean;
     @EJB
     private PolicyBean policyBean; 
     @EJB
     private AddressBean addressBean;
     
     
     
    private User user=new User();
    Logger logger = Logger.getLogger(getClass().getName());
    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }

     
     
    public CustomerDetails() {
    }
        
    private Product product=new Product();

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

        private Account account=new Account();

    /**
     * Get the value of account
     *
     * @return the value of account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Set the value of account
     *
     * @param account new value of account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    private Policy policy=new Policy();

    /**
     * Get the value of policy
     *
     * @return the value of policy
     */
    public Policy getPolicy() {
        return policy;
    }

    /**
     * Set the value of policy
     *
     * @param policy new value of policy
     */
    public void setPolicy(Policy policy) {
        this.policy = policy;
    }
    
    private Address address=new Address();

    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

           private Customer customer=new Customer();
           

    /**
     * Get the value of customer
     *
     * @return the value of customer
     */
    public Customer getCustomer() {
        return customer;
    }
    @PostConstruct
    public void postConstruct(){
        
    }
    /**
     * Set the value of customer
     *
     * @param customer new value of customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
      public String doSelectProduct(Product p,Provider provider){
        this.product=p;
        this.provider=provider;
        //getting customer and address details.
        customer=customerBean.findByUserName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        address=addressBean.find(customer.getAddress().getId());
        return (loginController.getPortalPathByRole())+"customerDetails.xhtml";
        
    }
     public String saveCustomerDetails(){       
        Date date = new Date();
        policy.setPolicyStartDate(date);
        //get the policy End date 
        Calendar c = Calendar.getInstance(); 
        c.setTime(date); 
        c.add(Calendar.DATE, 365);
        date = c.getTime();
        policy.setPolicyEndDate(date);
        //setting product id or name
        policy.setProductId(product.getId().longValue());
        policy.setProviderId(provider.getId().longValue());
        //adding the customer to policy
        policy.setCustomer(customer);        
        //adding the account customer to policy
        policy.setAccount(account);
        //adding the customer to account
        account.setCustomer(customer);
        //adding policy to account
        account.setPolicy(policy);
        logger.info("Policy and Account Creation Starts.");
        //persist the accout 
        accountBean.create(account);
        //persist the policy
       // policyBean.create(policy);
        logger.info("Policy and Account Creation Ends.");
        
        teslaEmail.sendEmail(customer.getEmail(), "Policy Confirmation Email", "Ploicy Number:" + policy.getId());
        teslaEmail.sendEmail("agupta61@hawk.iit.edu", "Policy Sold", "Policy Number:" + policy.getId());
        return loginController.getPortalPathByRole()+"thankYoupage.xhtml";
    }
   //registration
    
    public String doRegisterUser(){
          
          //add user to group
          user.addGroup(groupBean.find("Customer"));
          //create User
          userBean.create(user);
          // set the username for customer
          customer.setUser(user);
          customer.setAddress(address);
          //create address
          addressBean.create(address);        
          //create customer
          customerBean.create(customer);        

          //redirect it to login page
          FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
          logger.info("customer created!!!");
        return "/login.xhtml";
    }
    
      
     public String doGetAllDetails(){        
        //getting customer and address details.
        customer=customerBean.findByUserName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        address=addressBean.find(customer.getAddress().getId());
        return loginController.getPortalPathByRole()+"editPersonalDetails.xhtml";
     } 
     public String executeUpdate(){
        customerBean.update(customer);
        addressBean.update(address);
        return loginController.getPortalPathByRole()+"welcome.xhtml";
    }
     public String doinitiateClaim(Policy p){
         this.policy=policyBean.find(p.getId());
         //get current date time with Calendar()
	 Calendar cal = Calendar.getInstance();
         claim=new Claim();
         policy.addClaims(claim);         
         claim.setIncidentDate(cal.getTime());
         claim.setReportDate(cal.getTime());
         claimBean.create(claim);
         return loginController.getPortalPathByRole()+"policyList.xhtml";
     }
     
    
}
