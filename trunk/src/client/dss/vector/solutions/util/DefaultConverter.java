package dss.vector.solutions.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import com.runwaysdk.controller.StandardConverter;
import com.runwaysdk.generation.loader.Reloadable;

public class DefaultConverter extends StandardConverter implements Reloadable
{

  public DefaultConverter(Class<?> c)
  {
    super(c);
  }

  @Override
  public String format(Object object, Locale locale)
  {
    if (object == null)
    {
      return null;
    }

    if (object instanceof Date)
    {
      DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, locale);

      return format.format((Date) object);
    }
    else if (object instanceof Number)
    {
      NumberFormat format = NumberFormat.getNumberInstance(locale);

      try
      {
        return format.format(object);
      }
      catch (Exception e)
      {
        // Continue on to the Standard Parsing
      }
    }
    else if (object instanceof BigDecimal)
    {
      NumberFormat format = NumberFormat.getNumberInstance(locale);

      try
      {
        BigDecimal decimal = (BigDecimal) object;

        return format.format(decimal.doubleValue());
      }
      catch (Exception e)
      {
        // Continue on to the Standard Parsing
      }
    }

    return super.format(object, locale);
  }

  @Override
  public Object parse(String value, Locale locale)
  {
    if (value == null || value.equals(""))
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
        // Continue on to the Standard Parsing
      }
    }
    else if (c.equals(Float.class))
    {
      NumberFormat format = NumberFormat.getNumberInstance(locale);

      try
      {
        Number number = format.parse(value);
        float floatValue = number.floatValue();
        
        return new Float(floatValue);  
      }
      catch (Exception e)
      {
        // Continue on to the Standard Parsing
        System.out.println(e.getLocalizedMessage());
      }
    }
    else if (c.equals(Double.class))
    {
      NumberFormat format = NumberFormat.getNumberInstance(locale);

      try
      {
        return (Double) format.parse(value);
      }
      catch (Exception e)
      {
        // Continue on to the Standard Parsing
      }
    }
    else if (c.equals(BigDecimal.class))
    {
      NumberFormat format = NumberFormat.getNumberInstance(locale);

      try
      {
        return new BigDecimal((Double) format.parse(value));
      }
      catch (Exception e)
      {
        // Continue on to the Standard Parsing
      }
    }

    return super.parse(value, locale);
  }
}
