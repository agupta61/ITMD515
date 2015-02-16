/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;

import domain.Product;
import javax.annotation.PostConstruct;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Rashim
 */
@Named
@RequestScoped
public class moduleController extends mainController{
    
    public moduleController() {
    }
    @Inject
    LoginController loginController;
    
    
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

    
    public String doSelectProduct(Product p){
        product=p;
        return (loginController.getPortalPathByRole())+"customerDetails.xhtml";
        
    }
    public String doSelectPolicyList(){
        
        return (loginController.getPortalPathByRole())+"policyList.xhtml";
        
    }
    @PostConstruct
    private void postConstruct(){
        
    }
    
}
