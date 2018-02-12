/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions;

import java.util.HashMap;

import com.runwaysdk.session.Request;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.Email;
import dss.vector.solutions.general.EmailProtocol;
import dss.vector.solutions.general.SystemAlert;
import dss.vector.solutions.general.SystemAlertType;
import junit.framework.TestCase;

public class EmailTest extends TestCase
{
  protected void setUp() throws Exception
  {
    super.setUp();
  }

  protected void tearDown() throws Exception
  {
    super.tearDown();
  }

  @Request
  public void testSendGmailSSL()
  {
    Email email = new Email(Disease.getMalaria(), "mdsstest@gmail.com", "mdsstest@gmail.com", "Test Subject " + System.currentTimeMillis(), "This is a test of sending a " + "plain text e-mail through Gmail from Java.\n" + "Here is line 2.");
    email.apply();
    if (!email.send("smtp.gmail.com", EmailProtocol.SMTPS, "mdsstest@gmail.com", "BobbyTables"))
    {
      fail();
    }
  }

  @Request
  public void testSendGmailTSL()
  {
    Email email = new Email(Disease.getMalaria(), "mdsstest@gmail.com", "mdsstest@gmail.com", "Test Subject " + System.currentTimeMillis(), "This is a test of sending a " + "plain text e-mail through Gmail from Java.\n" + "Here is line 2.");
    email.apply();
    if (!email.send("smtp.gmail.com", EmailProtocol.SMTP_TLS, "mdsstest@gmail.com", "BobbyTables"))
    {
      fail();
    }
  }
  
  @Request
  public void testSendGmailSMTP()
  {
    Email email = new Email(Disease.getMalaria(), "mdsstest@gmail.com", "mdsstest@gmail.com", "Test Subject " + System.currentTimeMillis(), "This is a test of sending a " + "plain text e-mail through Gmail from Java.\n" + "Here is line 2.");
    email.apply();
    if (!email.send("smtp.gmail.com", EmailProtocol.SMTP, "mdsstest@gmail.com", "BobbyTables"))
    {
      fail();
    }
  }
  
  @Request
  public void testSendYahoo()
  {
    Email email = new Email(Disease.getMalaria(), "mdsstest@yahoo.com", "mdsstest@gmail.com", "Test Subject " + System.currentTimeMillis(), "This is a test of sending a " + "plain text e-mail through Yahoo from Java.\n" + "Here is line 2.");
    email.setBccAddresses("mdsstest@gmail.com");
    email.apply();
    if (!email.send("smtp.mail.yahoo.com", EmailProtocol.SMTP_TLS, "mdsstest@yahoo.com", "BobbyTables"))
    {
      fail();
    }
  }

  @Request
  public void testSendHotmail()
  {
    Email email = new Email(Disease.getMalaria(), "mdsstest@hotmail.com", "mdsstest@gmail.com", "Test Subject " + System.currentTimeMillis(), "This is a test of sending a " + "plain text e-mail through Hotmail from Java.\n" + "Here is line 2.");
    email.apply();
    if (!email.send("smtp.live.com", EmailProtocol.SMTP_TLS, "mdsstest@hotmail.com", "BobbyTables"))
    {
      fail();
    }
  }

  @Request
  public void testSendFromConfig()
  {
    Email email = new Email(Disease.getMalaria(), "mdsstest@gmail.com", "mdsstest@gmail.com", "Test Subject " + System.currentTimeMillis(), "This is a test of sending a " + "plain text e-mail through the configured server from Java.\n" + "Here is line 2.");
    email.apply();
    if (!email.send())
    {
      fail();
    }
  }

  @Request
  public void testSendNotification()
  {
    HashMap<String, Object> data = new HashMap<String, Object>();
    data.put("alertType", "AlertType");
    data.put("thresholdType", "ThresholdType");
    data.put("entityLabel", "SecretEntity");
    data.put("threshold", 99);
    data.put("totalCases", 99);
    SystemAlert alert = SystemAlert.get(Disease.getMalaria(), SystemAlertType.SOURCE_OUTBREAK_NOTIFICATION);
    if (!alert.sendEmail(data))
    {
      fail();
    }
  }

  @Request
  public void testSendIdentification()
  {
    HashMap<String, Object> data = new HashMap<String, Object>();
    data.put("alertType", "AlertType");
    data.put("thresholdType", "ThresholdType");
    data.put("entityLabel", "SecretEntity");
    data.put("threshold", 99);
    data.put("totalCases", 99);
    SystemAlert alert = SystemAlert.get(Disease.getMalaria(), SystemAlertType.SOURCE_OUTBREAK_IDENTIFICATION);
    if (!alert.sendEmail(data))
    {
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
