/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


import javax.mail.Authenticator;
import java.util.Properties;
import java.util.logging.Level;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author LENOVO
 */
public class MailUtil {
    public static void sendMail(String recepient) throws Exception{
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail = "aminebenabdallah15@gmail.com";
        String password ="iuilikoss9949aA";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
       });
        Message message = prepareMessage(session,myAccountEmail,recepient); 
        Transport.send(message);
        System.out.println("Message send successfully");
           
            

    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
     
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Nouvelle offre Tak-Tak");
            message.setText("vous avez une nouvelle offre...");
            return message ;
            
            
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }
 }