package mdss.entomology.assay;

import mdss.entomology.AssaySex;

public abstract class AdultAssay extends AdultAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234543766408L;
  
  public AdultAssay()
  {
    super();
  }
  
  private boolean isAgeRangeModified()
  {
    return this.getAgeRange().isModified(AdultAgeRange.STARTPOINT)
        || this.getAgeRange().isModified(AdultAgeRange.ENDPOINT);
  }

  @Override
  public void validateAgeRange()
  {
    super.validateAgeRange();

    if (isAgeRangeModified())
    {
      this.getAgeRange().validate();
    }
  }
  
  @Override
  public void validateGravid()
  {    
    super.validateGravid();
    
    AssaySex sex = this.getSex().get(0);
    
    if(this.getGravid() != 0 && (sex.equals(AssaySex.MALE) || sex.equals(AssaySex.UNKNOWN)))
    {
      String msg = "It is impossible to have gravid values on male or unknown sex assays";
      throw new RuntimeException(msg);
    }
    
    if(this.getGravid() > this.getQuantityTested())
    {
      String msg = "It is impossible to have gravid values larger than the quantity of mosquitos tested";
      throw new RuntimeException(msg);      
    }
  }
      
  @Override
  public void validateFed()
  {
    super.validateFed();
    
    AssaySex sex = this.getSex().get(0);
    
    if(this.getFed() != 0 && (sex.equals(AssaySex.MALE) || sex.equals(AssaySex.UNKNOWN)))
    {
      String msg = "It is impossible to have fed values on male or unknown sex assays";
      throw new RuntimeException(msg);
    }
    
    if(this.getFed() > this.getQuantityTested())
    {
      String msg = "It is impossible to have red values larger than the quantity of mosquitos tested";
      throw new RuntimeException(msg);      
    }
  }
    
  @Override
  public void apply()
  {
    validateAgeRange();    
    validateGravid();
    validateFed();
    validateInsecticide();

    super.apply();
  }
  
}
