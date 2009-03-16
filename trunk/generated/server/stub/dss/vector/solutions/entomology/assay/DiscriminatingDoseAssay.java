package dss.vector.solutions.entomology.assay;


public abstract class DiscriminatingDoseAssay extends DiscriminatingDoseAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236893766032L;
  
  public DiscriminatingDoseAssay()
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

    if (this.getQuantityDead() <= this.getQuantityTested())
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
  
  protected abstract boolean isResistant();
  
  protected abstract boolean isPotentiallyResistant();
  
  protected abstract boolean isSusceptible();  
}
