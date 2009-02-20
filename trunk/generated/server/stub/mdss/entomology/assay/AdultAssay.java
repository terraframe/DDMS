package mdss.entomology.assay;

import mdss.entomology.AssaySex;
import mdss.mo.Generation;
import mdss.mo.GenerationQuery;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.OrderBy.SortOrder;

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

      InvalidGravidSexProblem p = new InvalidGravidSexProblem(msg);
      p.setAssayId(this.getId());
      p.throwIt();
    }
    
    if(this.getGravid() > this.getQuantityTested())
    {
      String msg = "It is impossible to have gravid values larger than the quantity of mosquitos tested";

      InvalidGravidQuantityProblem p = new InvalidGravidQuantityProblem(msg);
      p.setAssayId(this.getId());
      p.setGravid(this.getGravid());
      p.throwIt();
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
      
      InvalidFedSexProblem p = new InvalidFedSexProblem(msg);
      p.setAssayId(this.getId());
      p.throwIt();
    }
    
    if(this.getFed() > this.getQuantityTested())
    {
      String msg = "It is impossible to have red values larger than the quantity of mosquitos tested";
      
      InvalidFedQuantityProblem p = new InvalidFedQuantityProblem(msg);
      p.setAssayId(this.getId());
      p.setFed(this.getFed());
      p.throwIt();
    }
  }
  
  @Override
  public void validateIsofemale()
  {
    super.validateIsofemale();
    
    if(this.getIsofemale())
    {
      GenerationQuery query = new GenerationQuery(new QueryFactory());
      query.ORDER_BY(query.getTermName(), SortOrder.ASC);
      
      OIterator<? extends Generation> it = query.getIterator();
      Generation F0 = it.next();
      it.close();
      
      if(this.getGeneration().getId().equals(F0.getId()))
      {
        String msg = "Isofemale line cannot be selected if the generation is F0.";
        
        InvalidGenerationProblem p = new InvalidGenerationProblem(msg);
        p.setAssayId(this.getId());
        p.throwIt();        
      }
    }
  }
    
  @Override
  public void apply()
  {
    validateAgeRange();    
    validateGravid();
    validateFed();
    validateInsecticide();
    
    validateIsofemale();

    super.apply();
  }
  
}
