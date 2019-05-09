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
package dss.vector.solutions.general;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import dss.vector.solutions.util.LocalizationFacade;

public class EmailConfiguration extends EmailConfigurationBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1203605481;

  public EmailConfiguration()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    this.validateFrom();
    this.validateTo();
    
    super.apply();
  }
  
  public static void sendTestEmail(dss.vector.solutions.general.EmailConfiguration config)
  {
    config.validateFrom();
    config.validateTo();
    
    Email email = new Email();
    email.setToAddresses(config.getTo());
    email.setDisease(Disease.getCurrent());
    email.setFromAddress(config.getFrom());
    email.setSubject(LocalizationFacade.getFromBundles("emailSettings.testEmailSubject"));
    email.setBody(LocalizationFacade.getFromBundles("emailSettings.testEmailBody"));
    email.apply();
    
    if (!email.send(config))
    {
      EmailConfigurationException emailEx = new EmailConfigurationException();
      
      String error = email.getError();
      error = error.substring(error.indexOf(": ")+1, error.length());
      emailEx.setExtra(error);
      
      throw emailEx;
    }
  }
  
  private void validateAddress(String address)
  {
    try
    {
      new InternetAddress(address).validate();
    }
    catch (AddressException e)
    {
      InvalidEmailAddressException emailEx = new InvalidEmailAddressException();
      emailEx.setAddress(address);
      throw emailEx;
    }
  }
  
  @Override
  public void validateTo()
  {
    if (this.getTo() == null || this.getTo().length() == 0)
    {
      return; // The attribute is allowed to be blank.
    }
    
    if (this.getTo().contains(","))
    {
      String[] addresses = this.getTo().split(",");
      
      for (String address : addresses)
      {
        validateAddress(address);
      }
    }
    else
    {
      validateAddress(this.getTo());
    }
    
    super.validateTo();
  }
  
  @Override
  public void validateFrom()
  {
    if (this.getFrom() == null || this.getFrom().length() == 0)
    {
      return; // The attribute is allowed to be blank.
    }
    
    validateAddress(this.getFrom());
    
    super.validateFrom();
  }

  public static EmailConfiguration getDefault()
  {
    return EmailConfiguration.getByKey("default");
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
}
