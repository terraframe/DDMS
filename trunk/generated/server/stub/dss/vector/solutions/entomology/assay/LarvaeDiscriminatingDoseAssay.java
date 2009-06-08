package dss.vector.solutions.entomology.assay;

import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.entomology.ControlMortalityException;

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
  public void validateControlTestMortality()
  {
    if(this.getControlTestMortality() != null && this.getControlTestMortality() > 20)
    {
      String msg = "The mortality rate of the control collection exceeds 20% invalidating this test";
      
      ControlMortalityException e = new ControlMortalityException(msg);
      e.apply();
      
      throw e;
    }
  }

  @Override
  public void apply()
  {
    validateControlTestMortality();
    validateQuantityDead();
    
    float mortality = 0.0F;
    int live = 0;
    
    if (this.getQuantityDead() != null && this.getQuantityTested() != null && this.getQuantityDead() <= this.getQuantityTested())
    {
      mortality = (float) ( this.getQuantityDead() ) * 100 / this.getQuantityTested();
      live = this.getQuantityTested() - this.getQuantityDead();
    }

    this.setQuantityLive(live);
    this.setMortality(mortality);

    if(this.getControlTestMortality() != null && this.getControlTestMortality() > 5)
    {
      //Use abbots formula to correct the mortality rate
      //Corrected % = 100 * (T - C) / (100 - C) (WHO/CDC/NTD/WHOPES/GCDPP/2006.3)
      //T = % mortality of the treated population
      //C = % mortality of the control population
      
      float corrected = 100.0F * (mortality - this.getControlTestMortality()) / (100.0F - this.getControlTestMortality());
      this.setMortality(corrected);
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
