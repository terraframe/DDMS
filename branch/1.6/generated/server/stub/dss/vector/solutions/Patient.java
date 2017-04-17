package dss.vector.solutions;

public class Patient extends PatientBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240342486486L;
  
  public Patient()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getId();
  }
}
