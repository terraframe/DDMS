package csu.mrc.ivcc.mdss.entomology.assay;

import csu.mrc.ivcc.mdss.Property;

public class LarvaeDiscriminatingDoseAssay extends LarvaeDiscriminatingDoseAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236962664268L;
  
  public LarvaeDiscriminatingDoseAssay()
  {
    super();
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