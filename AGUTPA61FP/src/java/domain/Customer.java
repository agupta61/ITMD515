/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.security.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Rashim
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Customer.findAll", query = "select a from Customer a"),
@NamedQuery(name = "Customer.findByUsername", query = "select a from Customer a where a.user.userName = :userName")
})

@XmlRootElement
public class Customer extends CommonEntity implements  Serializable{
    

    private String name;
    @Temporal(javax.persistence.TemporalType.DATE)
    
    private Date dob;
    
    private String phoneno;
     
   
    private String email;
        @OneToOne
        private Address address;

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

    @OneToOne
    @JoinColumn(name="USERNAME")
    private User user;

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

    public Customer() {
    }
     
    @OneToMany(mappedBy ="customer")
    private List<Policy> policy=new ArrayList<Policy>();
    @OneToMany(mappedBy ="customer")
    private List<Account> accounts=new ArrayList<Account>();    

    /*
    * adding policies to a customer as a customer can have mutiple policies.
    */
    
     public void addPolicy(Policy p) {
        if( ! this.policy.contains(p) ) {
            this.policy.add(p);
        }

        if( p.getCustomer() != this ){
            p.setCustomer(this);
        }
    }
    
    /**
     * Get the value of policy
     *
     * @return the value of policy
     */
    
    @XmlTransient
    public List<Policy> getPolicy() {
        return policy;
    }

    /**
     * Set the value of policy
     *
     * @param policy new value of policy
     */
    public void setPolicy(List<Policy> policy) {
        this.policy = policy;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Get the value of phoneno
     *
     * @return the value of phoneno
     */
    public String getPhoneno() {
        return phoneno;
    }

    /**
     * Set the value of phoneno
     *
     * @param phoneno new value of phoneno
     */
    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }


    /**
     * Get the value of dob
     *
     * @return the value of dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Set the value of dob
     *
     * @param dob new value of dob
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }


    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }


    

    public Customer(Integer id, String name, Date dob, String phoneno, String email) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phoneno = phoneno;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", dob=" + dob + ", phoneno=" + phoneno + ", email=" + email + '}';
    }

  
    
}
