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
    if (this.getIsValidate() == null || this.getIsValidate())
    {
      TargetFieldDomain field = new TargetFieldDomain();

      populate(field);

      return field;
    }
    else
    {
      TargetFieldClassifier field = new TargetFieldClassifier();

      populate(field);

      return field;
    }
  }
}
