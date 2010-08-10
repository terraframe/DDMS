package dss.vector.solutions;

import java.util.HashMap;

import junit.framework.TestCase;

import com.runwaysdk.session.Request;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.Email;
import dss.vector.solutions.general.SystemAlert;
import dss.vector.solutions.general.SystemAlertType;

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
	@Request
	public void testSendNotification()
	{
		HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("alertType", "AlertType");
        data.put("thresholdType", "ThresholdType");
        data.put("entityLabel", "SecretEntity");
        data.put("threshold", 99);
        data.put("totalCases", 99);
		SystemAlert alert = SystemAlert.get(Disease.getMalaria(), SystemAlertType.SOURCE_OUTBREAK_NOTIFICATION);
		if (!alert.sendEmail(data)) {
			fail();
		}
	}

	@Request
	public void testSendIdentification()
	{
		HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("alertType", "AlertType");
        data.put("thresholdType", "ThresholdType");
        data.put("entityLabel", "SecretEntity");
        data.put("threshold", 99);
        data.put("totalCases", 99);
		SystemAlert alert = SystemAlert.get(Disease.getMalaria(), SystemAlertType.SOURCE_OUTBREAK_IDENTIFICATION);
		if (!alert.sendEmail(data)) {
			fail();
		}
	}

	@Request
	public void testSendAll()
	{
		Email email = new Email(null, "mdsstest@gmail.com", "mdsstest@gmail.com", "Test Subject " + System.currentTimeMillis(), "This is a test of resending a " + "plain text e-mail through the configured server from Java.\n" + "Here is line 2.");
		email.apply(); // Save the email
		Email.sendAll();
	}

}
