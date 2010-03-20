package dss.vector.solutions.general;

public class EmailConfiguration extends EmailConfigurationBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1203605481;

  public EmailConfiguration()
  {
    super();
  }

  public static EmailConfiguration getDefault()
  {
    return EmailConfiguration.getByKey("default");
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else
    {
      return this.getClassDisplayLabel();
    }
  }
}
