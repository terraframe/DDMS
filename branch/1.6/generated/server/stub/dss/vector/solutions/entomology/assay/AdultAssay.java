package dss.vector.solutions.entomology.assay;


public abstract class AdultAssay extends AdultAssayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237579415849L;

  public AdultAssay()
  {
    super();
  }

  @Override
  public void validateAgeRange()
  {
    super.validateAgeRange();

    new AdultAgeRangeValidator(this).validate();
  }

  @Override
  public void validateGravid()
  {
    //super.validateGravid();

    new GravidValidator(this).validate();
  }

  @Override
  public void validateFed()
  {
    new FedValidator(this).validate();
  }


  @Override
  public void apply()
  {
    validateAgeRange();
    validateFed();
    validateGravid();

    super.apply();
  }
}
