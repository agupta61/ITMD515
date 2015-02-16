/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Rashim
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Address.findAll", query = "select add from Address add"),
@NamedQuery(name ="Address.findByUserName",query="select add from Address add,Customer c where add.id=c.address.id and c.user.userName=:userName")
})

@XmlRootElement
public class Address  extends CommonEntity implements  Serializable {
  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
        @OneToOne(mappedBy = "address")
        private Customer customer;

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

    
    public Address() {
    }
    
    private Integer zip;

    /**
     * Get the value of zip
     *
     * @return the value of zip
     */
    public Integer getZip() {
        return zip;
    }

    /**
     * Set the value of zip
     *
     * @param zip new value of zip
     */
    public void setZip(Integer zip) {
        this.zip = zip;
    }

    private String country;

    /**
     * Get the value of country
     *
     * @return the value of country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the value of country
     *
     * @param country new value of country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    private String city;

    /**
     * Get the value of city
     *
     * @return the value of city
     */
    public String getCity() {
        return city;
    }

    
    
    /**
     * Set the value of city
     *
     * @param city new value of city
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    private String addFirstLine;

    /**
     * Get the value of addFirstLine
     *
     * @return the value of addFirstLine
     */
    public String getAddFirstLine() {
        return addFirstLine;
    }

    /**
     * Set the value of addFirstLine
     *
     * @param addFirstLine new value of addFirstLine
     */
    public void setAddFirstLine(String addFirstLine) {
        this.addFirstLine = addFirstLine;
    }

    private String street;

    /**
     * Get the value of street
     *
     * @return the value of street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Set the value of street
     *
     * @param street new value of street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    private String state;

    /**
     * Get the value of state
     *
     * @return the value of state
     */
    public String getState() {
        return state;
    }

    /**
     * Set the value of state
     *
     * @param state new value of state
     */
    public void setState(String state) {
        this.state = state;
    }

    public Address(Integer zip, String country, String city, String name, String addFirstLine, String street, String state) {
        this.zip = zip;
        this.country = country;
        this.city = city;
       
        this.addFirstLine = addFirstLine;
        this.street = street;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address{" + "zip=" + zip + ", country=" + country + ", city=" + city + ", addFirstLine=" + addFirstLine + ", street=" + street + ", state=" + state + '}';
    }
    
    
}
