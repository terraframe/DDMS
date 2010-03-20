package dss.vector.solutions.entomology;


public class ResistanceProperty extends ResistancePropertyBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 637641852;

  public ResistanceProperty()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    if (this.getPropertyName() != null)
    {
      return this.getPropertyName();
    }
    return super.buildKey();
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

  public static Integer getPropertyValue(String propertyName)
  {
    ResistanceProperty property = (ResistanceProperty) ResistanceProperty.get(ResistanceProperty.CLASS, propertyName);
    
    return property.getPropertyValue();
  }

}
