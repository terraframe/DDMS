package csu.mrc.ivcc.mdss.entomology.assay;

import com.terraframe.mojo.generation.loader.Reloadable;

public class QuantityDeadValidator implements Reloadable
{
  private Integer quantityDead;
  
  private Integer quantityTested;
  
  private String assayId;
  
  public QuantityDeadValidator(DiscriminatingDoseAssay assay)
  {
    this(assay.getQuantityDead(), assay.getQuantityTested(), assay.getId());    
  }
  
  public QuantityDeadValidator(EfficacyAssay assay)
  {
    this(assay.getQuantityDead(), assay.getQuantityTested(), assay.getId());
  }

  public QuantityDeadValidator(Integer quantityDead, Integer quantityTested, String assayId)
  {
    this.quantityDead = quantityDead;
    this.quantityTested = quantityTested;
    this.assayId = assayId;
  }
  
  public void validate()
  {
    if (quantityDead > quantityTested)
    {
      String msg = "It is impossible to have a dead quantity larger than the total number of mosquitos tested";

      InvalidDeadQuantityProblem p = new InvalidDeadQuantityProblem(msg);
      p.setAssayId(assayId);
      p.setQuantityDead(quantityDead);
      p.setQuantityTested(quantityTested);
      p.throwIt();
    }
  }
}
