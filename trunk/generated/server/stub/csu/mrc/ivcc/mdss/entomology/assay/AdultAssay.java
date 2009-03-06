package csu.mrc.ivcc.mdss.entomology.assay;


import java.util.LinkedList;
import java.util.List;

import csu.mrc.ivcc.mdss.entomology.AssaySex;
import csu.mrc.ivcc.mdss.entomology.assay.AdultAssayBase;
import csu.mrc.ivcc.mdss.mo.Generation;


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

    if (this.getGravid() != 0 && ( sex.equals(AssaySex.MALE) || sex.equals(AssaySex.UNKNOWN) ))
    {
      String msg = "It is impossible to have gravid values on male or unknown sex assays";

      InvalidGravidSexProblem p = new InvalidGravidSexProblem(msg);
      p.setAssayId(this.getId());
      p.throwIt();
    }

    if (this.getGravid() > this.getQuantityTested())
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

    if (this.getFed() != 0 && ( sex.equals(AssaySex.MALE) || sex.equals(AssaySex.UNKNOWN) ))
    {
      String msg = "It is impossible to have fed values on male or unknown sex assays";

      InvalidFedSexProblem p = new InvalidFedSexProblem(msg);
      p.setAssayId(this.getId());
      p.throwIt();
    }

    if (this.getFed() > this.getQuantityTested())
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

    if (this.getIsofemale() && this.isGenerationF0())
    {
      String msg = "Isofemale line cannot be selected if the generation is F0.";

      InvalidGenerationProblem p = new InvalidGenerationProblem(msg);
      p.setAssayId(this.getId());
      p.throwIt();
    }
  }
  
  private boolean isGenerationF0()
  {
    Generation gen = this.getGeneration();
    List<String> ids = new LinkedList<String>();
    ids.add("MIRO_343458349");
        
    return ids.contains(gen.getTermId());
  }

  @Override
  public void apply()
  {
    validateAgeRange();
    validateGravid();
    validateFed();

    validateIsofemale();

    super.apply();
  }

}
