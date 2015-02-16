/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;

import domain.Product;
import domain.ProductService;
import domain.Provider;
import ejb.ProductBean;
import ejb.ProductServicebBean;
import ejb.ProviderBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Rashim
 */
@Named
@SessionScoped
public class ProviderController extends mainController implements  Serializable {
     
    @Inject
    LoginController loginController;
    @Inject
    ProductBean productBean;
    @Inject
    ProductServicebBean productService;
    @Inject
    ProviderBean providerBean;
    private Product product;
    
    private ProductService service;
    
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


    /**
     * Get the value of service
     *
     * @return the value of service
     */
    public ProductService getService() {
        return service;
    }

    /**
     * Set the value of service
     *
     * @param service new value of service
     */
    public void setService(ProductService service) {
        this.service = service;
    }


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

    public ProviderController() {
    }
    private static final Logger LOG = Logger.getLogger(ProviderController.class.getName());
   
    public String doUpdateProduct(ProductService service){
        this.service=service;
        return loginController.getPortalPathByRole()+"updateProduct.xhtml";
    }
     public String doDeleteProduct(ProductService service){
        this.service=service;
        productService.remove(service);
        return loginController.getPortalPathByRole()+"welcome.xhtml";
    }
    public String executeUpdate(){
        
        productService.update(service);
        return loginController.getPortalPathByRole()+"welcome.xhtml";
    }
    
    public String doProvideNewProduct(){
        
        this.service=new ProductService();
        return loginController.getPortalPathByRole()+"addProduct.xhtml";
        
    }

    public String doSaveNewProduct(){
        //find product to add to service on basis of product id in service object.
        this.product=productBean.find(product.getId());
        //fetch provider
        provider=providerBean.findByUserName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        //relate product to provider and vice versa
        product.addProvider(provider);
        service.addProduct(product);
        //relate service to provider and vice versa
        service.addProvider(provider);        
        //persist service to product service table
        productService.create(service);
        return loginController.getPortalPathByRole()+"welcome.xhtml";
    }
     @PostConstruct
     private void postConstruct(){
         product=new Product();
         //product.setProviders(null);
     }
}
