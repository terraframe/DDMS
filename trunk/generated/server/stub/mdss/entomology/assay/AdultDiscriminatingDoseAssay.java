package mdss.entomology.assay;

import java.util.Date;

public class AdultDiscriminatingDoseAssay extends AdultDiscriminatingDoseAssayBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234543769104L;

  public AdultDiscriminatingDoseAssay()
  {
    super();
  }

  @Override
  public void validateTestDate()
  {
    super.validateTestDate();

    Date collectionDate = this.getCollection().getDateCollected();

    if (this.getTestDate().before(collectionDate))
    {
      String msg = "It is impossible to have a test date before the mosquito collection date";
      throw new RuntimeException(msg);
    }
  }  
  
  @Override
  public void validateQuantityDead()
  {
    super.validateQuantityDead();
    
    if(this.getQuantityDead() > this.getQuantityTested())
    {
      String msg = "It is impossible to have a dead quantity larger than the total number of mosquitos tested";
      throw new RuntimeException(msg);
    }
  }

  @Override
  public void apply()
  {
    validateCollection();
    validateSex();
    
    validateTestDate();
    validateQuantityDead();

    super.apply();
  }

}
