package dss.vector.solutions.general;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

public class SystemAlert extends SystemAlertBase implements com.terraframe.mojo.generation.loader.Reloadable {
	private static final long serialVersionUID = 1254672710;

	public SystemAlert() {
		super();
	}

	@Transaction
	public boolean sendEmail(Map<String,Object> data) {
		boolean sent = false;
		
		if (this.getIsEmailActive()) {
			Email email = this.generateEmail(data);
			email.apply();
			sent = email.send();
		}
		
		return sent;
	}

	@Transaction
	public Email generateEmail() {
		return this.generateEmail(new HashMap<String,Object>());
	}

	@Transaction
	public Email generateEmail(Map<String,Object> data) {
		Email email = new Email();

		email.setToAddresses(this.getEmailToAddresses());
		email.setCcAddresses(this.getEmailCcAddresses());
		email.setBccAddresses(this.getEmailBccAddresses());
		email.setFromAddress(this.getEmailFromAddress());
		email.setSubject(this.processTemplate(this.getEmailSubject(), data));
		email.setBody(this.processTemplate(this.getEmailBody(), data));
		email.apply();

		return email;
	}

	private String processTemplate(String template, Map<String,Object> data) {
		Writer out = new StringWriter();

		Velocity.setProperty("directive.foreach.counter.initial.value", "0");
	    Velocity.setProperty( RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute" );
	    Velocity.setProperty("runtime.log.logsystem.log4j.logger", "velocity");

		try {
			Velocity.init();
			VelocityContext context = new VelocityContext(data);
			Velocity.evaluate(context, out, "Alert", template);
		} catch (Exception e) {
			return "Error processing template (" + e.getLocalizedMessage()  + ")";
		}

		return out.toString();
	}
}
