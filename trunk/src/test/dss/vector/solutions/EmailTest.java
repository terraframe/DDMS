package dss.vector.solutions;

import java.util.HashMap;

import com.terraframe.mojo.session.StartSession;

import dss.vector.solutions.general.Email;
import dss.vector.solutions.general.EmailProtocol;
import dss.vector.solutions.general.SystemAlert;

import junit.framework.TestCase;

public class EmailTest extends TestCase {
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
/*
	@StartSession
	public void testSendTeravation() {
		Email email = new Email("mdsstest@teravation.com", "chris@reigrut.net", "Test Subject " + System.currentTimeMillis(), "This is a test of sending a " + "plain text e-mail through Teravation from Java.\n" + "Here is line 2.");
		email.setCcAddresses("chris@teravation.com");
		email.apply();
		if (!email.send("mail.teravation.com", EmailProtocol.SMTP, null, null)) { 
			fail();
		}
	}

	@StartSession
	public void testSendGmail() {
		Email email = new Email("mdsstest@gmail.com", "chris@reigrut.net, chris@teravation.com", "Test Subject " + System.currentTimeMillis(), "This is a test of sending a " + "plain text e-mail through Gmail from Java.\n" + "Here is line 2.");
		email.apply();
		if (!email.send("smtp.gmail.com", EmailProtocol.SMTPS, "mdsstest@gmail.com", "BobbyTables")) {
			fail();
		}
	}

	@StartSession
	public void testSendYahoo() {
		Email email = new Email("mdsstest@yahoo.com", "chris@reigrut.net", "Test Subject " + System.currentTimeMillis(), "This is a test of sending a " + "plain text e-mail through Yahoo from Java.\n" + "Here is line 2.");
		email.setBccAddresses("chris@teravation.com");
		email.apply();
		if (!email.send("smtp.mail.yahoo.com", EmailProtocol.SMTP_TLS, "mdsstest@yahoo.com", "BobbyTables")) {
			fail();
		}
	}

	@StartSession
	public void testSendHotmail() {
		Email email = new Email("mdsstest@hotmail.com", "chris@reigrut.net", "Test Subject " + System.currentTimeMillis(), "This is a test of sending a " + "plain text e-mail through Hotmail from Java.\n" + "Here is line 2.");
		email.apply();
		if (!email.send("smtp.live.com", EmailProtocol.SMTP_TLS, "mdsstest@hotmail.com", "BobbyTables")) {
			fail();
		}
	}

	@StartSession
	public void testSendFromConfig() {
		Email email = new Email("mdsstest@gmail.com", "chris@reigrut.net", "Test Subject " + System.currentTimeMillis(), "This is a test of sending a " + "plain text e-mail through the configured server from Java.\n" + "Here is line 2.");
		email.apply();
		if (!email.send()) {
			fail();
		}
	}
*/	
	@StartSession
	public void testSendNotification() {
		HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("alertType", "AlertType");
        data.put("thresholdType", "ThresholdType");
        data.put("entityLabel", "SecretEntity");
        data.put("threshold", 99);
        data.put("totalCases", 99);
		SystemAlert alert = SystemAlert.getByKey("PoliticalOutbreakNotification");
		if (!alert.sendEmail(data)) {
			fail();
		}
	}

	@StartSession
	public void testSendIdentification() {
		HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("alertType", "AlertType");
        data.put("thresholdType", "ThresholdType");
        data.put("entityLabel", "SecretEntity");
        data.put("threshold", 99);
        data.put("totalCases", 99);
		SystemAlert alert = SystemAlert.getByKey("PoliticalOutbreakIdentification");
		if (!alert.sendEmail(data)) {
			fail();
		}
	}
	
	@StartSession
	public void testSendAll() {
		Email email = new Email("mdsstest@gmail.com", "chris@reigrut.net", "Test Subject " + System.currentTimeMillis(), "This is a test of resending a " + "plain text e-mail through the configured server from Java.\n" + "Here is line 2.");
		email.apply(); // Save the email
		Email.sendAll();
	}
	
}
