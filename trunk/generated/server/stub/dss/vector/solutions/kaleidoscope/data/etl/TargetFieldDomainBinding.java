package dss.vector.solutions.kaleidoscope.data.etl;

public class TargetFieldDomainBinding extends TargetFieldDomainBindingBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -148401381;
  
  public TargetFieldDomainBinding()
  {
    super();
  }

  @Override
  public TargetFieldIF getTargetField()
  {
    TargetFieldDomain field = new TargetFieldDomain();

    populate(field);

    return field;
  }
  
  
}
