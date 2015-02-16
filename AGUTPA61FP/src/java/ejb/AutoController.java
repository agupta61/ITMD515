/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import domain.security.User;
import javax.ejb.Stateful;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Rashim
 */
@Stateful
@Named(value="controller")
@SessionScoped
public class AutoController {
    
    private User current;

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";}
    
   public String toPolicyPage(){
       
       return "policy";
   }
}
