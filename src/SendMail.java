import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	
	SendMail() {
	
	}

	public void send(String name, String secretName, String toEmail) {
		try {

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use
																// smtp.gmail.com
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

			Session mailSession = Session.getInstance(props,
					new javax.mail.Authenticator() {

						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"secretsantasarah22@gmail.com", "chocolate21,pie");
						}
					});

			mailSession.setDebug(true); // Enable the debug mode

			Message msg = new MimeMessage(mailSession);

			// --[ Set the FROM, TO, DATE and SUBJECT fields
			msg.setFrom(new InternetAddress("secretsantasarah22@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toEmail));
			msg.setSentDate(new Date());
			msg.setSubject("Merry Christmas!");

			// --[ Create the body of the mail
			msg.setText("Merry Christmas "+name+"!!!\n\nThis year you will play Secret Santa to "+secretName+" :)))\nHappy Shopping! Ho Ho Ho!\nFrom your Secret Santa automated name delivery system\n\nps. if anybody has any problems (like getting your own name) let me know! :P ");

			// --[ Ask the Transport class to send our mail message
			Transport.send(msg);

		} catch (Exception E) {
			System.out.println("Oops something has gone pearshaped!");
			System.out.println(E);
		}
	}

}
