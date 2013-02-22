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
