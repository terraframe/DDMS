package dss.vector.solutions.entomology.assay;

import java.util.List;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.entomology.AssaySex;

public class GravidValidator implements Reloadable
{
  private List<AssaySex> sex;

  private Integer gravid;

  private Integer quantityTested;

  private AbstractAssay assay;

  public GravidValidator(AdultAssay assay)
  {
    this(assay.getSex(), assay.getGravid(), assay.getQuantityTested(), assay);
  }

  public GravidValidator(EfficacyAssay assay)
  {
    this(assay.getSex(), assay.getGravid(), assay.getQuantityTested(), assay);
  }

  public GravidValidator(List<AssaySex> sex, Integer gravid, Integer quantityTested, AbstractAssay assay)
  {
    this.sex = sex;
    this.gravid = gravid;
    this.quantityTested = quantityTested;
    this.assay = assay;
  }

  public void validate()
  {

    //null is allowed
    if(gravid == null)return;

    if (gravid != 0 && sex.size() > 0 && ( sex.get(0).equals(AssaySex.MALE) || sex.get(0).equals(AssaySex.UNKNOWN)))
    {
      String msg = "It is impossible to have gravid values on male or unknown sex assays";

      InvalidGravidSexProblem p = new InvalidGravidSexProblem(msg);
      p.setNotification(assay, AdultDiscriminatingDoseAssay.GRAVID);
      p.apply();
      p.throwIt();
    }

    if (gravid > quantityTested)
    {
      String msg = "It is impossible to have gravid values larger than the quantity of mosquitos tested";

      InvalidGravidQuantityProblem p = new InvalidGravidQuantityProblem(msg);
      p.setGravid(gravid);
      p.setNotification(assay, AdultDiscriminatingDoseAssay.GRAVID);
      p.apply();
      p.throwIt();
    }
  }
}
