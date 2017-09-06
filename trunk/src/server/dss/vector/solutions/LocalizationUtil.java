package dss.vector.solutions;

import java.text.SimpleDateFormat;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

public class LocalizationUtil implements Reloadable
{
  public static SimpleDateFormat getDateFormat()
  {
    SimpleDateFormat format = (SimpleDateFormat) SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, Session.getCurrentLocale());
    String pattern = format.toPattern();
    pattern = pattern.replace("yy", "yyyy");
    pattern = pattern.replace("yyyyyyyy", "yyyy");

    return new SimpleDateFormat(pattern);
  }

}
