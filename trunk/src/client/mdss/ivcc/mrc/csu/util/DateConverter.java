package mdss.ivcc.mrc.csu.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.controller.DateParseException;
import com.terraframe.mojo.util.Converter;


public class DateConverter implements Converter
{
  public String name;
  
  public DateConverter(String name)
  {
    this.name = name;
  }

  public String format(Object object, Locale locale)
  {    
    DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, locale);
    
    return format.format((Date) object);
  }

  public Object parse(String value, Locale locale)
  {
    DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, locale);

    try
    {
      return format.parse(value);
    }
    catch (ParseException e)
    {      
      LinkedList<ProblemDTOIF> list = new LinkedList<ProblemDTOIF>();
      list.add(new DateParseException(name, locale, value, format.toString()));

      throw new ProblemExceptionDTO("Invalid date format", list);
    }
  }

}
