package dss.vector.solutions.general;

public class SystemURL extends SystemURLBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1698680258;
  
  public SystemURL()
  {
    super();
  }

@Override
protected String buildKey() {
	return this.getDisplayLabel().getValue("defaultLocale");
}
  
  
}
