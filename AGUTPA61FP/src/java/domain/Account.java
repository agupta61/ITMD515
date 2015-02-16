/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Length;


/**
 *
 * @author Rashim
 */
@Entity
@NamedQuery(name = "Account.findAll", query = "select a from Account a")

@XmlRootElement
public class Account extends CommonEntity implements  Serializable { 

        
    //private Integer customerId;
    
    @ManyToOne
    private Customer customer;
    
    
    @Size(min = 2,max = 20, message = "Bank Name must be at least 2 Characters.")
    private String bankName;
    @NotNull(message = "Account no can not be blank!")
    private Integer accountNo;

    /**
     * Get the value of accountNo
     *
     * @return the value of accountNo
     */
    public Integer getAccountNo() {
        return accountNo;
    }

    /**
     * Set the value of accountNo
     *
     * @param accountNo new value of accountNo
     */
    public void setAccountNo(Integer accountNo) {
        this.accountNo = accountNo;
    }


    /**
     * Get the value of bankName
     *
     * @return the value of bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Set the value of bankName
     *
     * @param bankName new value of bankName
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * Get the value of customer
     *
     * @return the value of customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Set the value of customer
     *
     * @param customer new value of customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToOne(mappedBy = "account",cascade = CascadeType.PERSIST)
    private Policy policy;

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


    /**
     * Get the value of customerId
     *
     * @return the value of customerId
     
    public Integer getCustomerId() {
        return customerId;
    }
*/
    /**
     * Set the value of customerId
     *
     * @param customerId new value of customerId
     
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    */
   

    
}
