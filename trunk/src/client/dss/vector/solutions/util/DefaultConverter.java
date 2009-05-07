package dss.vector.solutions.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.controller.DateParseProblemDTO;
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
    return super.parse(value, locale);
//    if (c.equals(Date.class))
//    {
//      DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, locale);
//
//      try
//      {
//        return format.parse(value);
//      }
//      catch (Exception e)
//      {
//        //Continue on to the Standard Parsing
//      }
//    }
//    
//    return super.parse(value, locale);
  }
}
