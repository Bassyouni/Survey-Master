package mailapp;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;


public class SendMail 
{
    
    private static final String user = "surveymaster.fci@gmail.com";
    private static final String pass = "poiuytrewq0987654321";
    
    public static void sendAll(Address[] to, String sub,String msg)
    {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");	
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(props,new Authenticator() 
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(user, pass);
            }
        });

        try 
        {
            Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject(sub);
            message.setText(msg);

            Transport.send(message);
            
            System.out.println("sent");
            
        } catch (MessagingException e) 
        {
            System.out.println("not sent");
            
            throw new RuntimeException(e);
        }
    }

    public static void send(String to, String sub, String msg) 
    {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");	
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(props,new Authenticator() 
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(user, pass);
            }
        });

        try 
        {
            Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(msg);

            Transport.send(message);
            
            System.out.println("sent");
            
        } catch (MessagingException e) 
        {
            System.out.println("not sent");
            
            throw new RuntimeException(e);
        }
        
    }
}