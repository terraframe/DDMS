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
  protected java.lang.String localize(java.util.Locale locale, java.lang.String message)
  {
    //message = super.localize(locale, message);
	String emailFailure = "";
    if (this.getEmailFailure()) {
    	emailFailure = ( (MdAttributeBooleanDAOIF) getEmailFailureMd() ).getPositiveDisplayLabel(locale);
    }
    message = replace(message, "{emailFailure}", emailFailure);

    message = replace(message, "{alertType}", this.getAlertType());
    message = replace(message, "{entityLabel}", this.getEntityLabel());
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{threshold}", this.getThreshold());
    message = replace(message, "{thresholdType}", this.getThresholdType());
    message = replace(message, "{totalCases}", this.getTotalCases());
    
    return message;
  }
  
}
