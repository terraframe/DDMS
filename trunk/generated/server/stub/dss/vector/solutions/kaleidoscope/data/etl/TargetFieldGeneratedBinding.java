package dss.vector.solutions.kaleidoscope.data.etl;

public class TargetFieldGeneratedBinding extends TargetFieldGeneratedBindingBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1426159365;

  public TargetFieldGeneratedBinding()
  {
    super();
  }

  @Override
  public TargetFieldIF getTargetField()
  {
    TargetFieldGenerated field = new TargetFieldGenerated();

    populate(field);

    return field;
  }
}
