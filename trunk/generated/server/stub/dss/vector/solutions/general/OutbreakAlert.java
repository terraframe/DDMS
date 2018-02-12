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

import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;

public class OutbreakAlert extends OutbreakAlertBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID = 1256583480857L;

  public static final String VALUE_FORMAT     = "%1$.2f";

  public OutbreakAlert()
  {
    super();
  }

  @Override
  public java.lang.String localize(java.util.Locale locale)
  {
    // message = super.localize(locale, message);
    String emailWarning = " ";
    if (this.getEmailFailure())
    {
      emailWarning = ( (MdAttributeBooleanDAOIF) getEmailFailureMd() ).getPositiveDisplayLabel(locale);
    }
 
    String message = this.getLocalizedTemplate(locale);
    message = replace(message, "{emailWarning}", emailWarning);
    message = replace(message, "{messageText}", this.getMessageText());    
    message = replace(message, "{emailFailure}", this.getEmailFailure());

    return message;
  }

}
