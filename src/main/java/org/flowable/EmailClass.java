package org.flowable;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
 
 
public class EmailClass implements TaskListener 
{
 
public void notify(DelegateTask delegateTask) {
	// TODO Auto-generated method stub
	 // email ID of Recipient.
	
	String taskname=delegateTask.getName();
    String recipient = "sujeet7surve@gmail.com";

    // email ID of  Sender.
    String sender = "28rkshirsagar@gmail.com";

    // using host as localhost
    String host = "smtp.gmail.com";

    // Getting system properties
    Properties properties = System.getProperties();

    
    
   
	 properties.setProperty("mail.smtp.host",host);
	 properties.setProperty("mail.smtp.ssl.enable", "true");
	 properties.setProperty("mail.smtp.starttls.enable","true" );
	 properties.setProperty("mail.smtp.user", "28rkshirsagar@gmail.com");
	 properties.setProperty("mail.smtp.password", "rohit1990");
	 properties.setProperty("mail.smtp.auth", "true"); 
	 Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(
	                "28rkshirsagar@gmail.com", "rohit1990");// Specify the Username and the PassWord
	        }
	 });
	     
    // Setting up mail server
    

    // creating session object to get properties
   // Session session = Session.getDefaultInstance(properties);

    try
    {
       // MimeMessage object.
       MimeMessage message = new MimeMessage(session);

       // Set From Field: adding senders email to from field.
       message.setFrom(new InternetAddress(sender));

       // Set To Field: adding recipient's email to from field.
       message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

       // Set Subject: subject of the email
       message.setSubject("Status Changed");

       // set body of the email.
       message.setText("Your "+taskname + " task is completed");

       // Send email.
       Transport.send(message);
       System.out.println("Mail successfully sent");
    }
    catch (MessagingException mex) 
    {
       mex.printStackTrace();
    }

}
}