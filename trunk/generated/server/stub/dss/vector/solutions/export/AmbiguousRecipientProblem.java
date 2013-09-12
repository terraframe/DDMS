package dss.vector.solutions.export;

import java.text.DateFormat;
import java.util.Date;

public class AmbiguousRecipientProblem extends AmbiguousRecipientProblemBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256860224517L;

  public AmbiguousRecipientProblem()
  {
    super();
  }

  public AmbiguousRecipientProblem(java.lang.String developerMessage)
  {
    super(developerMessage);
  }

  protected String replace(String template, String replaceMe, Object newValue)
  {
    if (newValue == null)
    {
      return template;
    }

    if (newValue instanceof Date)
    {
      DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, locale);

      String formattedValue = format.format((Date) newValue);
      return template.replace(replaceMe, formattedValue);
    }

    return template.replace(replaceMe, newValue.toString());
  }
}
