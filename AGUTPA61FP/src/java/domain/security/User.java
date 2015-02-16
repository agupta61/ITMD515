/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.codec.digest.DigestUtils;


/**
 *
 * @author Rashim
 */
@Entity
@Table(name = "sec_user")
@NamedQuery(name = "User.findAll", query = "select u from User u")
@XmlRootElement
public class User implements Serializable {

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
    @Id
    private String userName;
  
    @ManyToMany
    @JoinTable(
               name = "sec_user_group",
               joinColumns=@JoinColumn(name="USERNAME"),
               inverseJoinColumns =@JoinColumn(name = "GROUPNAME") )
    private List<Group> groups=new ArrayList<>();
    public void addGroup(Group g){
        if(!this.groups.contains(g)){
            this.groups.add(g);
        }
        
        if (!g.getUsers().contains(this)){
            g.getUsers().add(this);
        }
    }

    /**
     * Get the value of groups
     *
     * @return the value of groups
     */
    @XmlTransient
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Set the value of groups
     *
     * @param groups new value of groups
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    /**
     * Get the value of userName
     *
     * @return the value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the value of userName
     *
     * @param userName new value of userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String password;

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    @PrePersist
    @PreUpdate
  public void hasdPassword(){
      
      String digestPassword=DigestUtils.md5Hex(this.password);
      this.password=digestPassword;
      
  }
}
