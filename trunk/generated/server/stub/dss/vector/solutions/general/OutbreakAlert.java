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
    message = replace(message, "{message}", this.getMessage());    
    message = replace(message, "{emailFailure}", this.getEmailFailure());

    return message;
  }

}
