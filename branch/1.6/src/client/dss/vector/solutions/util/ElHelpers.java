package dss.vector.solutions.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class ElHelpers implements com.runwaysdk.generation.loader.Reloadable
{

  public static String getDateFormatString(HttpServletRequest request)
  {
    Locale locale = request.getLocale();
    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
    SimpleDateFormat formatter = (SimpleDateFormat) df;
    return formatter.toPattern();
  }

  public static String getFormatedDate(HttpServletRequest request, Date date)
  {
    SimpleDateFormat formatter = new SimpleDateFormat(getDateFormatString(request));
    return formatter.format(date);
  }

  public static String dashNull(String str)
  {
    if (str == null)
    {
      return "-";
    }
    else
    {
      return str;
    }
  }
}
