package dss.vector.solutions.general;

import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;

public class OutbreakAlert extends OutbreakAlertBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256583480857L;

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

    String message = super.localize(locale);
    message = replace(message, "{emailWarning}", emailWarning);

    return message;
  }

}
