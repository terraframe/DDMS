package dss.vector.solutions.general;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;

import dss.vector.solutions.MdssLog;

public class Email extends EmailBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1506997120;

  public Email()
  {
    super();
  }

  public Email(Disease disease, String fromAddress, String toAddresses, String subject, String body)
  {
    this();
    this.setDisease(disease);
    this.setFromAddress(fromAddress);
    this.setToAddresses(toAddresses);
    this.setSubject(subject);
    this.setBody(body);
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else
    {
      return this.getClassDisplayLabel();
    }
  }

  public static void sendAll()
  {
    sendAll(EmailConfiguration.getDefault());
  }

  @Request
  public static void sendAll(EmailConfiguration config)
  {
    Calendar cutoff = Calendar.getInstance();
    cutoff.add(Calendar.DAY_OF_MONTH, -1 * config.getTimeout());

    EmailQuery query = new EmailQuery(new QueryFactory());
    query.WHERE(query.getSentDate().EQ((Date) null));
    query.WHERE(query.getCreateDate().GE(cutoff.getTime()));
    query.ORDER_BY_DESC(query.getCreateDate());

    OIterator<? extends Email> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        Email email = iterator.next();
        email.send(config);
      }
    }
    finally
    {
      iterator.close();
    }
  }

  @Transaction
  public boolean send()
  {
    return this.send(EmailConfiguration.getDefault());
  }

  @Transaction
  public boolean send(EmailConfiguration emailConfig)
  {
    return this.send(emailConfig.getEmailServer(), emailConfig.getProtocol().get(0), emailConfig.getEmailUserid(), emailConfig.getEmailPassword());
  }

  @Transaction
  public synchronized boolean send(String smtp, EmailProtocol protocol, String userid, String password)
  {
    System.out.println(System.getProperty("java.home"));

//    String store = "/etc/java-6-sun/security/cacerts";
    String store = "C:/MDSS/Java/jdk1.6.0_16/jre/lib/security/cacerts";
    
    System.setProperty("javax.net.ssl.trustStore", store);

    org.apache.commons.mail.Email email = new SimpleEmail();
    email.setHostName(smtp);

    switch (protocol)
    {
      case SMTP_TLS:
        email.setSmtpPort(587);
        email.setTLS(true);
        break;
      case SMTP:
        email.setSmtpPort(25);
        break;
      case SMTPS:
        email.setSmtpPort(465);
        email.setSSL(true);
        email.setTLS(true);
        break;
    }

    try
    {
      email.setAuthenticator(new DefaultAuthenticator(userid, password));
      email.setFrom(this.getFromAddress());
      
      for(String address : this.getAddresses(this.getToAddresses()))
      {
        email.addTo(address);
      }
      
      for(String address : this.getAddresses(this.getCcAddresses()))
      {
        email.addCc(address);
      }
      
      for(String address : this.getAddresses(this.getBccAddresses()))
      {
        email.addBcc(address);
      }
      email.setSubject(this.getSubject());
      email.setMsg(this.getBody());
      email.send();

      // Update this email object
      this.lock();
      this.setSentDate(new Date());
      this.apply();

      return true;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      
      MdssLog.error("Exception when sending email", e);

      this.lock();
      this.setError(new Date() + ": " + e.getLocalizedMessage());
      this.apply();
    }

    return false;
  }

  private List<String> getAddresses(String addressList)
  {
    List<String> list = new LinkedList<String>();
    if (addressList != null && addressList.length() > 0)
    {
      String[] addresses = addressList.split(",");
      for (String address : addresses)
      {
        list.add(address.trim());
      }
    }
    
    return list;
  }
}
