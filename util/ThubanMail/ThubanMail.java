//Mail-1.4.7.Jar is required

package util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ThubanMail {

	public static boolean naverSend(String recipientUserEmail, String subject, String contents){
		
		String naverId = ""; //메일을 전송할 네이버 아이디
		String naverPass = ""; //비밀번호
		
		String host = "smtp.naver.com";
		String user = naverId+"@naver.com";
		String password = naverPass;
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.ssl.enable", "true");

		
    	Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { 
    		protected PasswordAuthentication getPasswordAuthentication() { 
    			return new PasswordAuthentication(user, password); 
			} 
		}); 
    	
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientUserEmail));
			message.setSubject(subject); //메일 제목
			message.setText(contents);// 메일 내용

			Transport.send(message); //메일 전송
			return true;
		} catch (MessagingException e) {
			return false;
		}

	}
	
}
