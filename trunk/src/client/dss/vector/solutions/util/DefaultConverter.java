package dss.vector.solutions.util;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import com.terraframe.mojo.controller.StandardConverter;

public class DefaultConverter extends StandardConverter
{

  public DefaultConverter(Class<?> c)
  {
    super(c);
  }

  @Override
  public String format(Object object, Locale locale)
  {
    if(object == null)
    {
      return null;
    }
    
    if (object instanceof Date)
    {
      DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, locale);

      return format.format((Date) object);
    }
    return super.format(object, locale);
  }

  @Override
  public Object parse(String value, Locale locale)
  {
    if(value == null || value.equals(""))
    {
      return null;
    }
    
    if (c.equals(Date.class))
    {
      DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, locale);

      try
      {
        return format.parse(value);
      }
      catch (Exception e)
      {
        //Continue on to the Standard Parsing
      }
    }

    return super.parse(value, locale);
  }
}
