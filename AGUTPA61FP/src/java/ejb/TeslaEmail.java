/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Rashim
 */

@Singleton
public class TeslaEmail {
    
    
    @Resource(lookup = "mail/teslaMail")
    private Session mailSession;
    private static final Logger LOG = Logger.getLogger(TeslaEmail.class.getName());
    
    public void sendEmail(String to, String subject, String body){
        try {
            MimeMessage msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));
            msg.setSentDate(new Date());
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(body);
            
            // Send it off
            Transport.send(msg);
        } catch (AddressException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

}
