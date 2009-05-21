package dss.vector.solutions.entomology.assay;

import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;

public class LarvaeDiscriminatingDoseAssay extends LarvaeDiscriminatingDoseAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236962664268L;
  
  public LarvaeDiscriminatingDoseAssay()
  {
    super();
  }
  
  @Override
  public void validateQuantityDead()
  {
    super.validateQuantityDead();

    new QuantityDeadValidator(this).validate();
  }

  @Override
  public void apply()
  {
    validateQuantityDead();
    
    if (this.getQuantityDead() != null && this.getQuantityTested() != null && this.getQuantityDead() <= this.getQuantityTested())
    {
      this.setQuantityLive(this.getQuantityTested() - this.getQuantityDead());
      this.setMortality( ( (float) ( this.getQuantityDead() ) * 100 / this.getQuantityTested() ));
    }
    else
    {
      this.setQuantityLive(0);
      this.setMortality(new Float(0));
    }
    
    super.apply();
    
    if (this.isSusceptible())
    {
      SusceptibleCollection info = new SusceptibleCollection();
      info.throwIt();
    }
    else if (this.isPotentiallyResistant())
    {
      PotentiallyResistantCollection info = new PotentiallyResistantCollection();
      info.throwIt();
    }
    else if (this.isResistant())
    {
      ResistantCollection info = new ResistantCollection();
      info.throwIt();
    }
  }
    
  protected boolean isResistant()
  {
    Integer resistant = Property.getInt(PropertyInfo.RESISTANCE_PACKAGE, PropertyInfo.LARVAE_DDA_RESISTANCE);

    return ( this.getMortality() < resistant );
  }

  protected boolean isPotentiallyResistant()
  {
    Integer susceptible = Property.getInt(PropertyInfo.RESISTANCE_PACKAGE, PropertyInfo.LARVAE_DDA_SUSCEPTIBILE);
    Integer resistant = Property.getInt(PropertyInfo.RESISTANCE_PACKAGE, PropertyInfo.LARVAE_DDA_RESISTANCE);

    return ( resistant < this.getMortality() && this.getMortality() <= susceptible );
  }

  protected boolean isSusceptible()
  {
    Integer susceptible = Property.getInt(PropertyInfo.RESISTANCE_PACKAGE, PropertyInfo.LARVAE_DDA_SUSCEPTIBILE);

    return ( this.getMortality() > susceptible );
  }
}
