package mdss.ivcc.mrc.csu.entomology;

import mdss.ivcc.mrc.csu.entomology.ResistantCutOffBase;
import mdss.ivcc.mrc.csu.entomology.ResistantCutOffQuery;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class ResistantCutOff extends ResistantCutOffBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234981178501L;
  
  public ResistantCutOff()
  {
    super();
  }
  
  @Override
  public void validateADDAS()
  {
    super.validateADDAS();
    
    if(this.getADDAS() < this.getADDAR())
    {
      String msg = "The susceptible range for % mortality in adult diagonstic dose assay must be greater than the resistant range";
      throw new RuntimeException(msg);
    }
  }
  
  @Override
  public void validateLDDAS()
  {
    super.validateLDDAS();
    
    if(this.getLDDAS() < this.getLDDAR())
    {
      String msg = "The susceptible range for % mortality in larvae diagonstic dose assay must be greater than the resistant range";
      throw new RuntimeException(msg);
    }
  }
  
  @Override
  public void validateAAKnockDownPR()
  {
    super.validateAAKnockDownPR();
    
    if(this.getAAKnockDownPR() < this.getAAKnockDownR())
    {
      String msg = "The potentially resistant range for knock down in adult assays must be greater than the resistant range";
      throw new RuntimeException(msg);
    }
  }
  
  @Override
  public void validateLAKnockDownPR()
  {
    super.validateLAKnockDownPR();
    
    if(this.getLAKnockDownPR() < this.getLAKnockDownR())
    {
      String msg = "The potentially resistant range for knock down in larvae assays must be greater than the resistant range";
      throw new RuntimeException(msg);
    }
  }
  
  @Override
  public void apply()
  {
    if(this.isNew())
    {
      String msg = "Singleton instance: Unable to add new instances to the system";
      throw new RuntimeException(msg);
    }
    
    validateADDAS();
    validateLDDAS();
    validateAAKnockDownPR();
    validateLAKnockDownPR();
    
    super.apply();
  }
  
  public static mdss.ivcc.mrc.csu.entomology.ResistantCutOff instance()
  {
    ResistantCutOffQuery query = new ResistantCutOffQuery(new QueryFactory());
    OIterator<? extends ResistantCutOff> it = query.getIterator();
    
    ResistantCutOff instance = it.next();
    
    it.close();
    
    return instance;
  }
}
