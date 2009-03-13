package csu.mrc.ivcc.mdss.entomology.assay;

import java.util.Date;

public abstract class DiscriminatingDoseAssay extends DiscriminatingDoseAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236893766032L;
  
  public DiscriminatingDoseAssay()
  {
    super();
  }
  
  @Override
  public void validateTestDate()
  {
    super.validateTestDate();

    if (this.getTestDate() != null)
    {
      Date collectionDate = this.getCollection().getDateCollected();

      if (this.getTestDate().before(collectionDate))
      {
        String msg = "It is impossible to have a test date before the mosquito collection date";

        InvalidTestDateProblem p = new InvalidTestDateProblem(msg);
        p.setTestDate(this.getTestDate());
        p.setCollectionDate(collectionDate);
        p.setAssayId(this.getId());
        p.throwIt();
      }
    }
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
    validateTestDate();

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

    boolean firstApply = this.isNew() && !this.isAppliedToDB();
    
    super.apply();
    
    // CREATE Test Intervals
    if (firstApply)
    {
      int periods = this.calculatePeriod();

      for (int i = 0; i < periods; i++)
      {
        ADDATestInterval interval = new ADDATestInterval();
        interval.setAssay(this);
        interval.setPeriod(i);
        interval.setKnockedDown(0);
        interval.apply();
      }
    }
    
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