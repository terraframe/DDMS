package dss.vector.solutions.entomology.assay;

public class KnockDownAssay extends KnockDownAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237230639050L;
  
  public KnockDownAssay()
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
    super.validateGravid();

    new GravidValidator(this).validate();
  }

  @Override
  public void validateFed()
  {
    super.validateFed();

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