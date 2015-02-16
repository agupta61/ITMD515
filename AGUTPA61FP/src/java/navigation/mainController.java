/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author Rashim
 */
public class mainController {

    protected FacesContext facecontext;
    protected Flash flash;
    
    
    
    public mainController() {
    }
    @PostConstruct
    private void postConstruct(){
       facecontext=FacesContext.getCurrentInstance();
       flash=facecontext.getExternalContext().getFlash();
    }
    
}
