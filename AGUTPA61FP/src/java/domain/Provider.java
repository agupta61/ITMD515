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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 *
 * @author Rashim
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Provider.findAll", query = "select a from Provider a"),
@NamedQuery(name = "Provider.findByUsername", query = "select a from Provider a where a.user.userName = :userName"),
@NamedQuery(name ="Provider.findProvidersByProductId",query="select pro from Product prod,Provider pro where prod.id=:prodID and prod member of pro.products")        
})
public class Provider extends CommonEntity implements  Serializable {

        @OneToOne
        @JoinColumn(name = "USERNAME")
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

    private String providername;

    public Provider() {
    }
        @ManyToMany
        private List<Product> products =new ArrayList<>();
        public void addProduct(Product t) {
        if (!this.products.contains(t)) {
            this.products.add(t);
        }
        if (!t.getProviders().contains(this)) {
            t.getProviders().add(this);
        }
    }
    /**
     * Get the value of products
     *
     * @return the value of products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Set the value of products
     *
     * @param products new value of products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Get the value of providername
     *
     * @return the value of providername
     */
    public String getProvidername() {
        return providername;
    }

    /**
     * Set the value of providername
     *
     * @param providername new value of providername
     */
    public void setProvidername(String providername) {
        this.providername = providername;
    }


    public Provider(Integer providerid, String providername) {
        this.id = id;
        this.providername = providername;
    }

    @Override
    public String toString() {
        return "Provider{" + "providerid=" + id + ", providername=" + providername + '}';
    }
        @OneToMany(mappedBy = "provider")// service to provider
        private List<ProductService> productServices;

    /**
     * Get the value of productServices
     *
     * @return the value of productServices
     */
    public List<ProductService> getProductServices() {
        return productServices;
    }

    /**
     * Set the value of productServices
     *
     * @param productServices new value of productServices
     */
    public void setProductServices(List<ProductService> productServices) {
        this.productServices = productServices;
    }
}
