package dss.vector.solutions.entomology.assay;

import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayBase;
import dss.vector.solutions.Property;
import dss.vector.solutions.entomology.assay.AdultAgeRangeValidator;
import dss.vector.solutions.entomology.assay.FedValidator;
import dss.vector.solutions.entomology.assay.GravidValidator;

public class AdultDiscriminatingDoseAssay extends AdultDiscriminatingDoseAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234543769104L;

  public AdultDiscriminatingDoseAssay()
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

  protected boolean isResistant()
  {
    Integer resistant = Property.getInt(Property.RESISTANCE_PACKAGE, Property.ADULT_DDA_RESISTANCE);

    return ( this.getMortality() < resistant );
  }

  protected boolean isPotentiallyResistant()
  {
    Integer susceptible = Property.getInt(Property.RESISTANCE_PACKAGE, Property.ADULT_DDA_SUSCEPTIBILE);
    Integer resistant = Property.getInt(Property.RESISTANCE_PACKAGE, Property.ADULT_DDA_RESISTANCE);

    return ( resistant < this.getMortality() && this.getMortality() <= susceptible );
  }

  protected boolean isSusceptible()
  {
    Integer susceptible = Property.getInt(Property.RESISTANCE_PACKAGE, Property.ADULT_DDA_SUSCEPTIBILE);

    return ( this.getMortality() > susceptible );
  }
}