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
package dss.vector.solutions.util;

import java.util.Date;
import java.util.Locale;

import com.runwaysdk.format.DateFormat;
import com.runwaysdk.format.Format;
import com.runwaysdk.format.FormatException;
import com.runwaysdk.format.FormatFactory;
import com.runwaysdk.format.ParseException;
import com.runwaysdk.generation.loader.Reloadable;

public class DefaultConverter implements FormatFactory, Reloadable
{

  private class DDMSDateFormat extends DateFormat
  {
    public DDMSDateFormat()
    {
      super();
    }
    
    @Override
    public Date parse(String value, Locale locale) throws ParseException
    {
      if(!this.isValid(value))
      {
        return null;
      }
      
      try
      {
        java.text.DateFormat format = java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT, locale);
        return format.parse(value);
      }
      catch (Throwable t)
      {
        throw this.createParseException(t, locale, value);
      }
    }
    
    @Override
    public String format(Object value, Locale locale) throws FormatException
    {
      if(!this.isValid(value))
      {
        return null;
      }
      
      try
      {
        java.text.DateFormat format = java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT, locale);
        return format.format(value);
      }
      catch (Throwable t)
      {
        throw this.createFormatException(t, locale, value);
      }
    }
  }
  
  public DefaultConverter()
  {
    super();
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public <T> Format<T> getFormat(Class<T> clazz)
  {
    if(Date.class.isAssignableFrom(clazz))
    {
      return (Format<T>) new DDMSDateFormat();
    }
    
    return null;
  }

}
