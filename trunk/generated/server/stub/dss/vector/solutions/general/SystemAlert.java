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

public class SystemAlert extends SystemAlertBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254672710;

  public SystemAlert()
  {
    super();
  }

  @Transaction
  public static SystemAlertQuery getAllInstancesForDisease(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    SystemAlertQuery query = new SystemAlertQuery(new com.runwaysdk.query.QueryFactory());
    query.WHERE(query.getDisease().EQ(Disease.getCurrent()));
    getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }

  @Transaction
  public static SystemAlert get(SystemAlertType type)
  {
    return get(Disease.getCurrent(), type);
  }

  @Transaction
  public static SystemAlert get(Disease disease, SystemAlertType type)
  {
    SystemAlert alert = null;
    SystemAlertQuery q = new SystemAlertQuery(new QueryFactory());
    q.WHERE(q.getAlertType().containsExactly(type));
    q.AND(OR.get(q.getDisease().EQ(disease), q.getDisease().EQ("NULL")));
    // q.ORDER_BY_DESC(q.getDisease().getKeyName());
    OIterator<? extends SystemAlert> it = q.getIterator();

    try
    {
      if (it.hasNext())
      {
        alert = it.next();
      }
    }
    finally
    {
      it.close();
    }

    return alert;
  }

  @Transaction
  public boolean sendEmail(Map<String, Object> data)
  {
    boolean sent = false;

    if (this.getIsEmailActive())
    {
      Email email = this.generateEmail(data);

      if (!email.isAppliedToDB())
      {
        email.apply();
      }
      sent = email.send();
    }

    return sent;
  }

  @Transaction
  public Email generateEmail()
  {
    return this.generateEmail(new HashMap<String, Object>());
  }

  @Transaction
  public Email generateEmail(Map<String, Object> data)
  {
    Email email = new Email();

    email.setDisease(this.getDisease());
    email.setToAddresses(this.getEmailToAddresses());
    email.setCcAddresses(this.getEmailCcAddresses());
    email.setBccAddresses(this.getEmailBccAddresses());
    email.setFromAddress(this.getEmailFromAddress());
    email.setSubject(this.processTemplate(this.getEmailSubject(), data));
    email.setBody(this.processTemplate(this.getEmailBody(), data));
    email.apply();

    return email;
  }

  public String getTemplate(Map<String, Object> data)
  {
    String template = this.processTemplate(this.getEmailBody(), data);

    // IMPORTANT: We must remove any new lines from the message. When displaying
    // the message it is assumed that the newlines represent new messages.
    return template.replace('\n', ' ');
  }

  private String processTemplate(String template, Map<String, Object> data)
  {
    Writer out = new StringWriter();

    Velocity.setProperty("directive.foreach.counter.initial.value", "0");
    Velocity.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
    Velocity.setProperty("runtime.log.logsystem.log4j.logger", "velocity");

    try
    {
      Velocity.init();
      VelocityContext context = new VelocityContext(data);
      Velocity.evaluate(context, out, "Alert", template);
    }
    catch (Exception e)
    {
      return "Error processing template (" + e.getLocalizedMessage() + ")";
    }

    return out.toString();
  }
}
