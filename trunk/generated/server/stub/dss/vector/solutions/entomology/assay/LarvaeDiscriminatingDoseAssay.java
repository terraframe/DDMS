package dss.vector.solutions.entomology.assay;

import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayBase;
import dss.vector.solutions.Property;
import dss.vector.solutions.mo.LarvaeAge;

public class LarvaeDiscriminatingDoseAssay extends LarvaeDiscriminatingDoseAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236962664268L;
  
  public LarvaeDiscriminatingDoseAssay()
  {
    super();
  }
  
  @Override
  public void delete()
  {
    LarvaeAgeRange range = this.getAgeRange();
        
    super.delete();
    
    if (range != null)
    {
      range.delete();
    }
  }
  
  @Override
  public void setAgeRange(LarvaeAge startAge, LarvaeAge endAge)
  {
    if(this.getAgeRange() != null)
    {
      LarvaeAgeRange ageRange = this.getAgeRange();      
      ageRange.setStartPoint(startAge);
      ageRange.setEndPoint(endAge);
      ageRange.apply();      
    }
    else
    {
      LarvaeAgeRange ageRange = new LarvaeAgeRange();
      ageRange.setStartPoint(startAge);
      ageRange.setEndPoint(endAge);
      ageRange.apply();      

      this.setAgeRange(ageRange);
    }        
  }
  
  protected boolean isResistant()
  {
    Integer resistant = Property.getInt(Property.RESISTANCE_PACKAGE, Property.LARVAE_DDA_RESISTANCE);

    return ( this.getMortality() < resistant );
  }

  protected boolean isPotentiallyResistant()
  {
    Integer susceptible = Property.getInt(Property.RESISTANCE_PACKAGE, Property.LARVAE_DDA_SUSCEPTIBILE);
    Integer resistant = Property.getInt(Property.RESISTANCE_PACKAGE, Property.LARVAE_DDA_RESISTANCE);

    return ( resistant < this.getMortality() && this.getMortality() <= susceptible );
  }

  protected boolean isSusceptible()
  {
    Integer susceptible = Property.getInt(Property.RESISTANCE_PACKAGE, Property.LARVAE_DDA_SUSCEPTIBILE);

    return ( this.getMortality() > susceptible );
  }
}
