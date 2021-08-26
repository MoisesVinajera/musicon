package mx.uady.musicon.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.scheduling.annotation.Async;

public class MailService {

    
    
    public MailService() {
    }

    @Async
    public void sendEmail(String usuario, String userPassword, String subject, String text) throws InterruptedException{


        String to =  usuario;
        String from = usuario; 
        // or IP address
        String host = "localhost";
        final String username = usuario;   
        final String password = userPassword;


        // Get system properties
        Properties properties = new Properties();                      
        // enable authentication
        properties.put("mail.smtp.auth", "true");                       
        // enable STARTTLS
        properties.put("mail.smtp.starttls.enable", "true");              
        // Setup mail server
        properties.put("mail.smtp.host", "smtp.gmail.com");              
        // TLS Port
        properties.put("mail.smtp.port", "587");   
    
        Session session = Session.getInstance(properties,
          new javax.mail.Authenticator() {
             
            protected PasswordAuthentication 
                           getPasswordAuthentication() {
                                         
                return new PasswordAuthentication(username, 
                                                 password);
            }
          });
    
          createMessage(session, from, to, subject, text);

    }

    public void createMessage(Session session, String from, String to, String subject, String text){

        // compose the message
        try {      
            // compose the message
            // javax.mail.internet.MimeMessage class is 
            // mostly used for abstraction.
            Message message = new MimeMessage(session);    
                
            // header field of the header.
            message.setFrom(new InternetAddress(from)); 
                
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);
    
            Transport.send(message);         //send Message
    
            System.out.println("Done");
    
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
