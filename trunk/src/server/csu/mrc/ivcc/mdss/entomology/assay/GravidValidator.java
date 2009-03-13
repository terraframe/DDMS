package csu.mrc.ivcc.mdss.entomology.assay;

import java.util.List;

import com.terraframe.mojo.generation.loader.Reloadable;

import csu.mrc.ivcc.mdss.entomology.AssaySex;

public class GravidValidator implements Reloadable
{
  private List<AssaySex> sex;
  
  private Integer gravid;
  
  private Integer quantityTested;
  
  private String assayId;
  
  public GravidValidator(AdultDiscriminatingDoseAssay assay)
  {
    this(assay.getSex(), assay.getGravid(), assay.getQuantityTested(), assay.getId());
  }
  
  public GravidValidator(EfficacyAssay assay)
  {
    this(assay.getSex(), assay.getGravid(), assay.getQuantityTested(), assay.getId());    
  }
  
  public GravidValidator(List<AssaySex> sex, Integer gravid, Integer quantityTested, String assayId)
  {
    this.sex = sex;
    this.gravid = gravid;
    this.quantityTested = quantityTested;
    this.assayId = assayId;
  }

  public void validate()
  {
    if (gravid != 0 && sex.size() > 0 && ( sex.get(0).equals(AssaySex.MALE) || sex.get(0).equals(AssaySex.UNKNOWN)))
    {
      String msg = "It is impossible to have gravid values on male or unknown sex assays";

      InvalidGravidSexProblem p = new InvalidGravidSexProblem(msg);
      p.setAssayId(assayId);
      p.throwIt();
    }

    if (gravid > quantityTested)
    {
      String msg = "It is impossible to have gravid values larger than the quantity of mosquitos tested";

      InvalidGravidQuantityProblem p = new InvalidGravidQuantityProblem(msg);
      p.setAssayId(assayId);
      p.setGravid(gravid);
      p.throwIt();
    }
  }
}
