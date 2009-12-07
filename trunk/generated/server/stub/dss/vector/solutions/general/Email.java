package dss.vector.solutions.general;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class Email extends EmailBase implements com.terraframe.mojo.generation.loader.Reloadable {
	private static final long serialVersionUID = -1506997120;

	public Email() {
		super();
	}

	public Email(String fromAddress, String toAddresses, String subject, String body) {
		this();
		this.setFromAddress(fromAddress);
		this.setToAddresses(toAddresses);
		this.setSubject(subject);
		this.setBody(body);
	}

	public static void sendAll() {
		EmailQuery query = new EmailQuery(new QueryFactory());
		query.WHERE(query.getSentDate().EQ((Date) null));
		query.ORDER_BY_DESC(query.getCreateDate());

		OIterator<? extends Email> iterator = query.getIterator();

		try {
			while (iterator.hasNext()) {
				Email email = iterator.next();
				email.send();
			}
		} finally {
			iterator.close();
		}
	}

	@Transaction
	public boolean send() {
		EmailConfiguration emailConfig = EmailConfiguration.getDefault();
		return this.send(emailConfig.getEmailServer(), emailConfig.getProtocol().get(0), emailConfig.getEmailUserid(), emailConfig.getEmailPassword());
	}

	@Transaction
	public synchronized boolean send(String smtp, EmailProtocol protocol, String userid, String password) {
		boolean sent = false;
		Properties props = new Properties();
		int port = 25;

		switch (protocol) {
		case SMTP_TLS:
			port = 587;
			props.put("mail.smtp.starttls.enable", "true");
			// Also apply SMTP settings
		case SMTP:
			// use default port 25 for SMTP
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", smtp);
			break;
		case SMTPS:
			port = 465;
			props.put("mail.transport.protocol", "smtps");
			props.put("mail.smtps.host", smtp);
			props.put("mail.smtps.auth", "true");
			break;
		}

		try {
			// Get the current session
			Session session = Session.getInstance(props);
			// session.setDebug(true);

			// Create a new message
			Message msg = new MimeMessage(session);
			// Set the FROM and TO fields
			msg.setFrom(new InternetAddress(this.getFromAddress()));
			this.addRecipients(msg, Message.RecipientType.TO, this.getToAddresses());
			this.addRecipients(msg, Message.RecipientType.CC, this.getCcAddresses());
			this.addRecipients(msg, Message.RecipientType.BCC, this.getBccAddresses());

			// Set the subject and body text
			msg.setSubject(this.getSubject());
			msg.setText(this.getBody());
			/*
			 * Multipart mp = new MimeMultipart("alternative");
			 * 
			 * Iterator iterator = doc.getBodyParts().keySet().iterator(); while
			 * (iterator.hasNext()) { String mimeType = (String)iterator.next();
			 * String partContent = (String) doc.getBodyParts().get(mimeType);
			 * 
			 * BodyPart part = new MimeBodyPart(); part.setContent(partContent,
			 * mimeType); mp.addBodyPart(part); }
			 * 
			 * msg.setContent(mp);
			 */

			msg.saveChanges();

			// Send the message
			Transport transport = session.getTransport();
			transport.connect(smtp, port, userid, password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			this.setSentDate(new Date());
			this.apply();
			sent = true;
		} catch (Exception e) {
			this.setError(new Date() + ": " + e.getLocalizedMessage());
			this.apply();
		}
		
		return sent;
	}

	private void addRecipients(Message msg, RecipientType type, String addressList) throws AddressException, MessagingException {
		if (addressList != null && addressList.length() > 0) {
			String[] addresses = addressList.split(",");
			for (int i = 0; i < addresses.length; i++) {
				msg.addRecipient(type, new InternetAddress(addresses[i].trim()));
			}
		}
	}
}
