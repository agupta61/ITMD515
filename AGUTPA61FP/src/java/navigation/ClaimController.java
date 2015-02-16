/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;

import domain.Claim;
import ejb.ClaimBean;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Rashim
 */
@Named
@SessionScoped
public class ClaimController extends mainController implements  Serializable{

    public ClaimController() {
    }
        @Inject
    ClaimBean claimBean;
    
    private Claim claim;

    /**
     * Get the value of claim
     *
     * @return the value of claim
     */
    public Claim getClaim() {
        return claim;
    }

    /**
     * Set the value of claim
     *
     * @param claim new value of claim
     */
    public void setClaim(Claim claim) {
        this.claim = claim;
    }

        @Inject
    LoginController loginController;
        
        public String doDeleteClaim(Claim c){
        this.claim=c;        
        claimBean.remove(claim);
        return loginController.getPortalPathByRole()+"allClaimList.xhtml";
    }
}
