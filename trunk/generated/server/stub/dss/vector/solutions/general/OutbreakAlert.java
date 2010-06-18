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
    String emailFailure = "";
    if (this.getEmailFailure())
    {
      emailFailure = ( (MdAttributeBooleanDAOIF) getEmailFailureMd() ).getPositiveDisplayLabel(locale);
    }

    String message = super.localize(locale);
    message = replace(message, "{id}", this.getId());

    message = replace(message, "{alertType}", this.getAlertType());
    message = replace(message, "{thresholdType}", this.getThresholdType());
    message = replace(message, "{thresholdValue}", this.getThresholdValue());
    message = replace(message, "{actualValue}", this.getActualValue());
    message = replace(message, "{geoEntity}", this.getGeoEntity());
    message = replace(message, "{emailFailure}", emailFailure);

    return message;
  }

}
