/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author Rashim
 */
@Singleton
public class NewTimerSessionBean {
    private static final Logger LOG = Logger.getLogger(NewTimerSessionBean.class.getName());

    
    
    @Schedule(dayOfWeek = "Mon-Fri", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "*/30", persistent = false)
    
    public void myTimer() {
        LOG.info("Timer event: " + new Date());
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
