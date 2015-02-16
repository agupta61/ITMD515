/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Rashim
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Claim.findAll", query = "select a from Claim a"),
@NamedQuery(name ="Claim.findByPolicyNo",query="select distinct clm from Claim clm,Policy pol where clm.policy.id=:polID")
})
@XmlRootElement
public class Claim extends CommonEntity implements  Serializable {

        @ManyToOne
         private Policy policy;

    /**
     * Get the value of policy
     *
     * @return the value of policy
     */
    public Policy getPolicy() {
        return policy;
    }

    /**
     * Set the value of policy
     *
     * @param policy new value of policy
     */
    public void setPolicy(Policy policy) {
            this.policy=policy;        
        // if the policy list does not have this claim then add it to list of claims 
      if (!policy.getClaim().contains(this)) {
            policy.getClaim().add(this);
        }
    }
  
    

    

        @Temporal(javax.persistence.TemporalType.DATE)
    private Date incidentDate;

    /**
     * Get the value of incidentDate
     *
     * @return the value of incidentDate
     */
    public Date getIncidentDate() {
        return incidentDate;
    }

    /**
     * Set the value of incidentDate
     *
     * @param incidentDate new value of incidentDate
     */
    public void setIncidentDate(Date incidentDate) {
        this.incidentDate = incidentDate;
    }

    public Claim() {
    }

        @Temporal(javax.persistence.TemporalType.DATE)
    private Date reportDate;

    /**
     * Get the value of reportDate
     *
     * @return the value of reportDate
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     * Set the value of reportDate
     *
     * @param reportDate new value of reportDate
     */
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    
   

    public Claim(Integer claimId, Policy policy, Date incidentDate, Date reportDate) {
        this.id = id;
        this.policy = policy;
        this.incidentDate = incidentDate;
        this.reportDate = reportDate;
    }

    @Override
    public String toString() {
        return "Claim{" + "claimId=" + id + ", policy=" + policy + ", incidentDate=" + incidentDate + ", reportDate=" + reportDate + '}';
    }

   
   
    
}
