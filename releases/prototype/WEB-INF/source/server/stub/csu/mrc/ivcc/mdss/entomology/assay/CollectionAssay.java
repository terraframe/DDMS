package csu.mrc.ivcc.mdss.entomology.assay;

import java.util.LinkedList;
import java.util.List;

import csu.mrc.ivcc.mdss.mo.Generation;

public abstract class CollectionAssay extends CollectionAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236893774938L;
  
  public CollectionAssay()
  {
    super();
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
  public void validateIntervalTime()
  {
    if (this.getIntervalTime() > this.getExposureTime())
    {
      String msg = "It is impossible to have an interval time larger than the exposure time";

      InvalidIntervalTimeProblem p = new InvalidIntervalTimeProblem(msg);
      p.setIntervalTime(this.getIntervalTime());
      p.setExposureTime(this.getExposureTime());
      p.setAssayId(this.getId());
      p.throwIt();
    }
  }


  @Override
  public void apply()
  {
    validateIsofemale();
    validateIntervalTime();

    super.apply();
  }
  
  public Integer calculatePeriod()
  {
    double exposureTime = (double) this.getExposureTime();
    Integer intervalTime = this.getIntervalTime();

    return (int) Math.ceil(exposureTime / intervalTime) + 1;
  }
}