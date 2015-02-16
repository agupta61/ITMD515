/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import domain.Account;
import domain.Address;
import domain.Claim;
import domain.Customer;
import domain.Policy;
import domain.Product;
import domain.ProductService;
import domain.Provider;

import domain.security.Group;
import domain.security.User;
import ejb.security.GroupBean;
import ejb.security.UserBean;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Rashim
 */
@Singleton
@Startup
public class StartUpBean {
    @EJB
    private ProductServicebBean productServicebBean;
    @EJB
    private AddressBean addressBean;
    @EJB
    private GroupBean groupBean;
    @EJB
    private UserBean userBean;
    @EJB
    private ProductBean productBean;
   @EJB
    private ProviderBean providerBean;
    @EJB

    private PolicyBean policyBean;
    @EJB
    private CustomerBean customerBean;
    
    


    public StartUpBean() {
    }

        @EJB
    private ClaimBean claimBean;
        
    @EJB  
    private AccountBean accuountBean;
     
    
    @PostConstruct
    private void populateDatabase() {
 Logger logger = Logger.getLogger(getClass().getName());
 logger.info("Before Groups");
    // Groups
    Group ConsumerGroup=new Group("Customer", "Groups of all policy holders");
    Group InsuranceGroup=new Group("Insurance Companies", "Groups of Insurance Providers");
    groupBean.create(ConsumerGroup);
    groupBean.create(InsuranceGroup);

    Group SupervisorGroup=new Group("SuperUser", "Groups of Admin");
    User superUser1=new User("Rashim","Rashim");
    User superUser2=new User("Anuj","Anuj");
    superUser1.addGroup(SupervisorGroup);
    superUser2.addGroup(SupervisorGroup);
    // Users
    User u1=new User("Newton","Newton");
    User u2=new User("Galelio","Galelio");
    User u3=new User("Tesla","Tesla");
    User u4=new User("Ambuj","Ambuj");
    User u5=new User("Atul","Atul");
    u1.addGroup(ConsumerGroup);
    u2.addGroup(InsuranceGroup);
    u3.addGroup(InsuranceGroup);
    u4.addGroup(ConsumerGroup);
    u5.addGroup(ConsumerGroup);
     
    // Customer Data
    Customer cust1=new Customer();
    cust1.setDob(new GregorianCalendar(1987,1, 1).getTime());
    cust1.setEmail("Newton@gmail.com");
    cust1.setName("Newton");
    cust1.setPhoneno("312-493-4021"); 
    cust1.setUser(u1);
    Customer cust2=new Customer();
    cust2.setDob(new GregorianCalendar(1987,1, 1).getTime());
    cust2.setEmail("ambujhbti@gmail.com");
    cust2.setName("Sandeep Kumar");
    cust2.setPhoneno("312-493-4021"); 
    cust2.setUser(u4);
    Customer cust3=new Customer();
    cust3.setDob(new GregorianCalendar(1987,1, 1).getTime());
    cust3.setEmail("ambujhbti@gmail.com");
    cust3.setName("Vivek Kumar");
    cust3.setPhoneno("312-493-4021"); 
    cust3.setUser(u5);
        //Address 
    Address address =new Address();
    address.setAddFirstLine("649 W");
    address.setCity("chigao");
    address.setCountry("Us");
    address.setCustomer(cust1);
    
    address.setState("IL");
    address.setZip(60609);
    Address address2 =new Address();
    address2.setAddFirstLine("649 W");
    address2.setCity("chigao");
    address2.setCountry("Us");
    address2.setCustomer(cust1);
    
    address2.setState("IL");
    address2.setZip(60609);  
    Address address3 =new Address();
    address3.setAddFirstLine("649 W");
    address3.setCity("New York");
    address3.setCountry("US");
    address3.setCustomer(cust2);
    
    address3.setState("IL");
    address3.setZip(60609);     
    //adding address to customer
    cust1.setAddress(address);
    cust2.setAddress(address2);
    cust3.setAddress(address3);

   //  Product Data    
    Product pro1=new Product();
    Product pro2=new Product();
    Product pro3=new Product();
    Product pro4=new Product();
    Product pro5=new Product();
    pro1.setName("Life Insurance");
    pro2.setName("Vehicle Insurance");
    pro3.setName("Child Insurance");
    pro4.setName("Bussiness Insurance");   
    pro5.setName("Travel Insurance");   
    // Provider Data   
    Provider prv1=new Provider();
    Provider prv2=new Provider();    
    prv1.setProvidername("Galelio");
    prv2.setProvidername("Tesla");  
    prv1.setUser(u2);
    prv2.setUser(u3);
   // relate ManyTOMany providers and products.
   pro1.addProvider(prv1);   
   pro2.addProvider(prv2);
   pro3.addProvider(prv1);
   pro4.addProvider(prv2);    
   pro5.addProvider(prv2); 
 // Policies Data 
    Policy pol1=new Policy();
    pol1.setPolicyStartDate(new GregorianCalendar(2016, 5, 15).getTime());
    pol1.setPolicyEndDate(new GregorianCalendar(2017, 5, 15).getTime());
    pol1.setProductId(new Long(2));
    pol1.setProviderId(new Long(2)); 
    Policy pol2=new Policy();
    pol2.setPolicyStartDate(new GregorianCalendar(2016, 5, 15).getTime());
    pol2.setPolicyEndDate(new GregorianCalendar(2017, 5, 15).getTime());
    pol2.setProductId(new Long(2));
    pol2.setProviderId(new Long(2));  
   // relate customer and policies    
    pol1.setCustomer(cust1);
    pol2.setCustomer(cust1);
    //creating the Service
    ProductService service1=new ProductService();
    service1.setProduct(pro1);
    service1.setProvider(prv1);
    service1.setServiceName("Our Life Insurance");
    ProductService service2=new ProductService();
    service2.setProduct(pro2);
    service2.setProvider(prv1);
    service2.setServiceName("Car Insurance");
    ProductService service3=new ProductService();
    service3.setProduct(pro3);
    service3.setProvider(prv1);
    service3.setServiceName(" Child Future Plan");
    ProductService service4=new ProductService();
    service4.setProduct(pro4);
    service4.setProvider(prv2);
    service4.setServiceName("Business Security");
    ProductService service5=new ProductService();
    service5.setProduct(pro5);
    service5.setProvider(prv2);
    service5.setServiceName("Passenger Safety");
    // Claims related to pol1 and pol2
    Claim clm1=new Claim();
    clm1.setIncidentDate(new GregorianCalendar(2016, 5, 15).getTime());
    clm1.setReportDate(new GregorianCalendar(2016, 5, 15).getTime());
    Claim clm2=new Claim();
    clm2.setIncidentDate(new GregorianCalendar(2016, 5, 15).getTime());
    clm2.setReportDate(new GregorianCalendar(2016, 5, 15).getTime());    
    Claim clm3=new Claim();
    clm3.setIncidentDate(new GregorianCalendar(2016, 5, 15).getTime());
    clm3.setReportDate(new GregorianCalendar(2016, 5, 15).getTime());    
    Claim clm4=new Claim();
    clm4.setIncidentDate(new GregorianCalendar(2016, 5, 15).getTime());
    clm4.setReportDate(new GregorianCalendar(2016, 5, 15).getTime());  
   // relate claims and policies
    pol1.addClaims(clm1);
    pol1.addClaims(clm2);
    pol2.addClaims(clm3);
    pol2.addClaims(clm4);
    // Creating Account for policy 
    Account acc1=new Account();
    Account acc2=new Account();
    acc1.setBankName("PNC");
    acc1.setAccountNo(1001111111);
    acc2.setBankName("CHASE");
    acc2.setAccountNo(1011112111);
    acc1.setCustomer(cust1);
    acc2.setCustomer(cust1);
    //relate account to policy
    acc1.setPolicy(pol1);
    acc2.setPolicy(pol2);
    //relate policy to account
    pol1.setAccount(acc1);
    pol2.setAccount(acc2); 
    // Persist Groups
    groupBean.create(SupervisorGroup);
    groupBean.create(InsuranceGroup);
    groupBean.create(ConsumerGroup);
    // Persist Users
    userBean.create(superUser1);
    userBean.create(superUser2);
    userBean.create(u1);
    userBean.create(u2);
    userBean.create(u3);
    userBean.create(u4);
    userBean.create(u5);
    //persist address
    addressBean.create(address);
    addressBean.create(address2);
    addressBean.create(address3);
    //persist Customers
    customerBean.create(cust1);
    customerBean.create(cust2);
    customerBean.create(cust3);
    //persist Claims
    claimBean.create(clm1);
    claimBean.create(clm2);
    claimBean.create(clm3);
    claimBean.create(clm4);
    //persist products
    productBean.create(pro1);
    productBean.create(pro2);
    productBean.create(pro3);
    productBean.create(pro4);
    productBean.create(pro5);
    //persist Providers
    providerBean.create(prv1);
    providerBean.create(prv2);
    //persist Policies    
    policyBean.create(pol1);
    policyBean.create(pol2);
    //persist Accounts
    accuountBean.create(acc1);
    accuountBean.create(acc2); 
    ////persist Services
    productServicebBean.create(service1);
    productServicebBean.create(service2);
    productServicebBean.create(service3);
    productServicebBean.create(service4);
    productServicebBean.create(service5);
   
    }
    
}
