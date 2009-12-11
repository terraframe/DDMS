package dss.vector.solutions.general;

public class OutbreakAlert extends OutbreakAlertBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256583480857L;
  
  public OutbreakAlert()
  {
    super();
  }
  
  protected java.lang.String localize(java.util.Locale locale, java.lang.String message)
  {
    message = super.localize(locale, message);
	String emailFailure = "";
    if (this.getEmailFailure()) {
    	emailFailure = getEmailFailureMd().getDisplayLabel(locale);
    }
    message = replace(message, "{emailFailure}", emailFailure);
    return message;
  }
  
}
