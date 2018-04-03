package fr.adaming.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoyerMail {
	public static void envoyerMessageAjout(String mailRecup) {
	final String username = "sophie.marcerou.georges@gmail.com";
	final String password = "353cv2vj";

	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");

	
	// Get Session object.
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
	try {

		// Create a default MimeMessage object.
		Message message = new MimeMessage(session);

		// Set From: header field of the header.
		message.setFrom(new InternetAddress("sophie.marcerou.georges@gmail.com"));

		// Set To: header field of the header.
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailRecup));

		// Set Subject: header field
		message.setSubject("Ajout client");
		
		

         // Send the actual HTML message, as big as you like
         message.setContent("Bonjour, <br/> Nous vous annonçons que votre inscription a été réalisée avec succès. <br/> Bien cordialement <br> Le service Client", "text/html");

         // Send message
         Transport.send(message, message.getAllRecipients());
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
}

}
