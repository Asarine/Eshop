package fr.adaming.service;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendMail {
	
	public static void sendMessage(String mail) {
	final String username = "phanuellesainteloi@gmail.com";
	final String password = "ARYA052230";
	
	//Création de la session
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
	properties.put("mail.smtp.starttls.enable", "true");
	properties.put("mail.smtp.host", "smtp.gmail.com");
	properties.put("mail.smtp.port", "587");
    Session session = Session.getInstance(properties);

    try {
        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress("phanuellesainteloi@gmail.com"));

        // Set To: header field of the header.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));

        // Set Subject: header field
        message.setSubject("Enregistrement produit");
        
        // Create the message part 
        MimeBodyPart messageBodyPart = new MimeBodyPart();

        // Fill the message
        messageBodyPart.setText("Les modification apportées à la liste de produits ont été enregistrées");

        // Create a multipart message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

     // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        String filename = "C:\\Users\\inti0426\\Desktop\\Formation Java\\Produit.pdf";
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
        
        // Send the complete message parts
        message.setContent(multipart );
        
        // Send message
        Transport.send(message);
        System.out.println("Sent message successfully....");
     } catch (MessagingException mex) {
        mex.printStackTrace();
     }

	}
	
	
  }
    

