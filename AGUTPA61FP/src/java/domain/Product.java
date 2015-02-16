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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 *
 * @author Rashim
 */
@Entity

@NamedQueries({
@NamedQuery(name = "Product.findAll", query = "select a from Product a"),
@NamedQuery(name = "Product.findByProductName", query = "select a from Product a where a.name=:prdname"),    
@NamedQuery(name ="Product.findByUserName",query="select prod from Product prod,Provider pro where  pro member of prod.providers and pro.user.userName=:userName")
})
public class Product extends CommonEntity implements  Serializable{

    
    private String name;

    public Product() {
    }
         @ManyToMany(fetch = FetchType.EAGER,mappedBy = "products", cascade = CascadeType.MERGE)
         private List<Provider> providers=new ArrayList<>();

         public void addProvider(Provider p) {
        if (!this.providers.contains(p)) {
            this.providers.add(p);
        }
        if (!p.getProducts().contains(this)) {
            p.getProducts().add(this);
        }
    }
         
         
    /**
     * Get the value of providers
     *
     * @return the value of providers
     */
    
    public List<Provider> getProviders() {
        return providers;
    }

    /**
     * Set the value of providers
     *
     * @param providers new value of providers
     */
    public void setProviders(List<Provider> providers) {
        this.providers = providers;
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


   

    public Product(Integer productid, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" + "productid=" + id + ", name=" + name + '}';
    }
        @OneToMany(mappedBy ="product")
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
