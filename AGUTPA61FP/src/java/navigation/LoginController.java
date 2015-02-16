/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.logging.Logger;
import javax.ejb.EJB;

/**
 *
 * @author Rashim
 */
@Named
@RequestScoped
public class LoginController extends mainController{
    
     
    @NotNull(message = "No User Name ?")
    private String username;

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }
    
    @NotNull(message = "Password is not provided!")
    @Size(min = 1,message = "Password must be atleast 1 character long!") 
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


    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public LoginController() {
    }
    
    @PostConstruct
    private void postConstruct(){
        
    }
    
    public String goToHome(){
        
        return getPortalPathByRole()+"welcome.xhtml";
        
    }
    // registration ends here
    public boolean isCustomer(){
        return facecontext.getExternalContext().isUserInRole("cust");
    }

    public boolean isSupervisor(){
        return facecontext.getExternalContext().isUserInRole("super");
    }
    public boolean isProvider(){
        return facecontext.getExternalContext().isUserInRole("prov");
    }  

     public String getRemoteUser(){
         
         return facecontext.getExternalContext().getRemoteUser();
         
         
     }    
     public String getPortalPathByRole() {
        if (isCustomer())
        { return  "/customerPortal/";
     }else if (isProvider()){
         return "/providerPortal/";
     }
     else if(isSupervisor()){
        return "/superUserPortal/";
                }
     else {
         return "/";
                }

    }
    public String doLogin(){
        Logger logger = Logger.getLogger(getClass().getName());
        HttpServletRequest req=(HttpServletRequest) facecontext.getExternalContext().getRequest();
        try {
            
            req.login(username, password);
           
            
            
        } catch (ServletException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            facecontext.addMessage(null, new FacesMessage("Bad Login: ", "You have entered a bad username or bad password!"));
            return "/login.xhtml";
        }
       
         //return getPortalPathByRole()+"welcome.xhtml";
         return getPortalPathByRole()+"welcome.xhtml";
    }
    
    public String doLogout(){
        
        HttpServletRequest req=(HttpServletRequest) facecontext.getExternalContext().getRequest();
        try {
            req.logout();
            
            //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        } catch (ServletException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            facecontext.addMessage(null, new FacesMessage("Bad Logout: ", "Something wrong happend while logging out!"));
            return "/error.xhtml";
        }
        return "/login.xhtml";
        
    }

   
    
}
