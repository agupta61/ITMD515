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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rashim
 */
@Entity
@Table(name="sec_group")
@NamedQueries({
@NamedQuery(name = "Group.findAll", query = "select g from Group g")
//@NamedQuery(name = "Group.find",    query = "select a from Group a where a.group.groupName = :groupName")
})
@XmlRootElement
public class Group implements Serializable {

    public Group() {
    }

    public Group(String groupName, String groupDesc) {
        this.groupName = groupName;
        this.groupDesc = groupDesc;
    }
    
    @Id
    private String groupName;
   public void addUser(User u){
       if(!this.users.contains(u)){
           this.users.add(u);
       }
       
       if (!u.getGroups().contains(this)){
           u.getGroups().add(this);
           
       }
   }
    /**
     * Get the value of groupName
     *
     * @return the value of groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Set the value of groupName
     *
     * @param groupName new value of groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    private String groupDesc;

    /**
     * Get the value of groupDesc
     *
     * @return the value of groupDesc
     */
    public String getGroupDesc() {
        return groupDesc;
    }

    /**
     * Set the value of groupDesc
     *
     * @param groupDesc new value of groupDesc
     */
    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }
     
   @ManyToMany (mappedBy = "groups")
    private List<User> users=new ArrayList<>();

    /**
     * Get the value of users
     *
     * @return the value of users
     */
    @XmlTransient
    public List<User> getUsers() {
        return users;
    }

    /**
     * Set the value of users
     *
     * @param users new value of users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

}
