/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



/**
 *
 * @author Rashim
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Policy.findAll", query = "select pol from Policy pol"),
@NamedQuery(name ="Policy.findAllPolicies",query="select pol from Policy pol,Customer c where pol.customer.id=c.id and c.user.userName=:userName"),
@NamedQuery(name ="Policy.findByName",query="select pol from Policy pol,Customer c where pol.customer.id=c.id and c.name=:custId")
})
@XmlRootElement
public class Policy extends CommonEntity implements  Serializable {
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date policyStartDate;

    public Policy() {
    }
        @ManyToOne
        private Customer customer;
           @OneToOne
            private Account account;

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


    /**
     * Get the value of customer
     *
     * @return the value of customer
     */
    @XmlTransient
    public Customer getCustomer() {
        return customer;
    }
    @OneToMany(mappedBy ="policy")
    private List<Claim> claim=new ArrayList<Claim>();
    
    /*
    * adding Claims to a policy as a policy can have multiple claims.
    */
    
     public void addClaims(Claim c) {
        if( ! this.getClaim().contains(c) ) {
            this.getClaim().add(c);
        }

        if( c.getPolicy() != this ){
            c.setPolicy(this);
        }
    }

    /**
     * Get the value of claim
     *
     * @return the value of claim
     */
     
    @XmlTransient
    public List<Claim> getClaim() {
        return claim;
    }

    /**
     * Set the value of claim
     *
     * @param claim new value of claim
     */
    public void setClaim(List<Claim> claim) {
        this.claim = claim;
    }


    /**
     * Set the value of customer
     *
     * @param customer new value of customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
        // if the customer policy list does not have this policy,then add it to collectoin.
        if (!customer.getPolicy().contains(this)) {
            customer.getPolicy().add(this);
        }
    }

    
    
    /**
     * Get the value of policyStartDate
     *
     * @return the value of policyStartDate
     */
    
    public Date getPolicyStartDate() {
        return policyStartDate;
    }

    /**
     * Set the value of policyStartDate
     *
     * @param policyStartDate new value of policyStartDate
     */
    public void setPolicyStartDate(Date policyStartDate) {
        this.policyStartDate = policyStartDate;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date policyEndDate;

    /**
     * Get the value of policyEndDate
     *
     * @return the value of policyEndDate
     */
    @XmlTransient
    public Date getPolicyEndDate() {
        return policyEndDate;
    }

    /**
     * Set the value of policyEndDate
     *
     * @param policyEndDate new value of policyEndDate
     */
    public void setPolicyEndDate(Date policyEndDate) {
        this.policyEndDate = policyEndDate;
    }


    private Long productId;

    /**
     * Get the value of productId
     *
     * @return the value of productId
     */
    @XmlTransient
    public Long getProductId() {
        return productId;
    }

    /**
     * Set the value of productId
     *
     * @param productId new value of productId
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }




    private Long providerId;

    /**
     * Get the value of providerId
     *
     * @return the value of providerId
     */
    @XmlTransient
    public Long getProviderId() {
        return providerId;
    }

   

    /**
     * Set the value of providerId
     *
     * @param providerId new value of providerId
     */
    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }


    @Override
    public String toString() {
        return "Policy{" + "policyStartDate=" + policyStartDate + ", policyEndDate=" + policyEndDate + ", productId=" + productId + ", providerId=" + providerId + ", policyNo=" + id + '}';
    }

    

    

   

    
    
}
