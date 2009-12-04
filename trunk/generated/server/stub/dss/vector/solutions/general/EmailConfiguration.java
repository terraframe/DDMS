package dss.vector.solutions.general;

public class EmailConfiguration extends EmailConfigurationBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1203605481;
  
  public EmailConfiguration()
  {
    super();
  }
  
  public static EmailConfiguration getDefault() {
	  return EmailConfiguration.getByKey("default");
  }
}
