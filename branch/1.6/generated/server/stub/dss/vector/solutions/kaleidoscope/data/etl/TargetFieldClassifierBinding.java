package dss.vector.solutions.kaleidoscope.data.etl;


public class TargetFieldClassifierBinding extends TargetFieldClassifierBindingBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 30417876;

  public TargetFieldClassifierBinding()
  {
    super();
  }

  @Override
  public TargetFieldIF getTargetField()
  {
    TargetFieldClassifier field = new TargetFieldClassifier();

    populate(field);

    return field;
  }
}
