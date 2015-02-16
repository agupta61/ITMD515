/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Rashim
 */
@Entity
@NamedQueries({
@NamedQuery(name = "ProductService.findAll", query = "select a from ProductService a"),
@NamedQuery(name ="ProductService.findAllByUserName",query="select pservice from ProductService pservice,Provider pro where  pro.id=pservice.provider.id and pro.user.userName=:userName"),
@NamedQuery(name ="ProductService.findByID",query="select pservice from ProductService pservice where pservice.product.id=:productId and PSERVICE.provider.id=:providerId")
})    
public class ProductService extends CommonEntity implements  Serializable {

    public ProductService() {
    }
    @ManyToOne // service to provider
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
    @NotNull(message = "Please provide product Name")
    @Size(min = 5,max = 20, message = "Product name must be between 5 to 20 characters!")
    private String serviceName;

    /**
     * Get the value of serviceName
     *
     * @return the value of serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Set the value of serviceName
     *
     * @param serviceName new value of serviceName
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    @ManyToOne
    private Product product;

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

         //add a provider
            public void addProvider(Provider t) {
        if (!t.equals(this.getProvider())) {
            this.setProvider(t);
        }
        if (!t.getProductServices().contains(this)) {
            t.getProductServices().add(this);
        }
    }    
    
          // add a product
            public void addProduct(Product t) {
        if (!t.equals(this.getProduct())) {
            this.setProduct(t);
        }
        if (!t.getProductServices().contains(this)) {
            t.getProductServices().add(this);
        }
    }
    
}
