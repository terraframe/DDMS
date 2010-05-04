package dss.vector.solutions.general;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;

public class SystemAlert extends SystemAlertBase implements com.runwaysdk.generation.loader.Reloadable {
	private static final long serialVersionUID = 1254672710;

	public SystemAlert() {
		super();
	}
	
	
	@Transaction
	public static SystemAlert get(SystemAlertType type) {
		return get(DiseaseWrapper.getDisease(), type);
	}
	
	@Transaction
	public static SystemAlert get(Disease disease, SystemAlertType type) {
		SystemAlert alert = null;
		SystemAlertQuery q = new SystemAlertQuery(new QueryFactory());
		q.WHERE(q.getAlertType().containsExactly(type));
		q.WHERE(OR.get(q.getDisease().containsExactly(disease), q.getDisease().EQ(null)));
		q.ORDER_BY_DESC(q.getDisease().getEnumName());
		OIterator<? extends SystemAlert> it = q.getIterator();

		try {
			if (it.hasNext()) {
				alert =  (SystemAlert) it.next();
			}
		} finally {
			it.close();
		}
		
		return alert;
	}
	
	@Transaction
	public boolean sendEmail(Map<String,Object> data) {
		boolean sent = false;
		
		if (this.getIsEmailActive()) {
			Email email = this.generateEmail(data);
			
			if(!email.isAppliedToDB())
			{
			  email.apply();
			}
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
