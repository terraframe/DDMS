package csu.mrc.ivcc.mdss.entomology.assay;

import csu.mrc.ivcc.mdss.entomology.AssaySex;

public class AssayValidator
{
  private AbstractAssay assay;

  public AssayValidator(AbstractAssay assay)
  {
    this.assay = assay;
  }

  public boolean isAgeRangeModified()
  {
    if (assay instanceof AdultAssayIF)
    {
      AdultAssayIF adult = (AdultAssayIF) assay;
      return adult.getAgeRange().isModified(AdultAgeRange.STARTPOINT)
          || adult.getAgeRange().isModified(AdultAgeRange.ENDPOINT);
    }

    return false;
  }

  public void validateAdultAgeRange()
  {
    if (assay instanceof AdultAssayIF)
    {
      AdultAssayIF adult = (AdultAssayIF) assay;

      if (isAgeRangeModified())
      {
        adult.getAgeRange().validate();
      }
    }
  }

  public void validateGravid()
  {
    if (assay instanceof AdultAssayIF)
    {
      AdultAssayIF adult = (AdultAssayIF) assay;

      AssaySex sex = adult.getSex().get(0);

      if (adult.getGravid() != 0 && ( sex.equals(AssaySex.MALE) || sex.equals(AssaySex.UNKNOWN) ))
      {
        String msg = "It is impossible to have gravid values on male or unknown sex assays";

        InvalidGravidSexProblem p = new InvalidGravidSexProblem(msg);
        p.setAssayId(adult.getId());
        p.throwIt();
      }

      if (adult.getGravid() > adult.getQuantityTested())
      {
        String msg = "It is impossible to have gravid values larger than the quantity of mosquitos tested";

        InvalidGravidQuantityProblem p = new InvalidGravidQuantityProblem(msg);
        p.setAssayId(adult.getId());
        p.setGravid(adult.getGravid());
        p.throwIt();
      }
    }
  }

  public void validateFed()
  {
    if (assay instanceof AdultAssayIF)
    {
      AdultAssayIF adult = (AdultAssayIF) assay;

      AssaySex sex = adult.getSex().get(0);

      if (adult.getFed() != 0 && ( sex.equals(AssaySex.MALE) || sex.equals(AssaySex.UNKNOWN) ))
      {
        String msg = "It is impossible to have fed values on male or unknown sex assays";

        InvalidFedSexProblem p = new InvalidFedSexProblem(msg);
        p.setAssayId(adult.getId());
        p.throwIt();
      }

      if (adult.getFed() > adult.getQuantityTested())
      {
        String msg = "It is impossible to have red values larger than the quantity of mosquitos tested";

        InvalidFedQuantityProblem p = new InvalidFedQuantityProblem(msg);
        p.setAssayId(adult.getId());
        p.setFed(adult.getFed());
        p.throwIt();
      }
    }
  }

  public void validateQuantityDead()
  {
    if (assay instanceof DiscriminatingDoseAssayIF)
    {
      DiscriminatingDoseAssayIF dda = (DiscriminatingDoseAssayIF) assay;
      
      if (dda.getQuantityDead() > dda.getQuantityTested())
      {
        String msg = "It is impossible to have a dead quantity larger than the total number of mosquitos tested";

        InvalidDeadQuantityProblem p = new InvalidDeadQuantityProblem(msg);
        p.setAssayId(dda.getId());
        p.setQuantityDead(dda.getQuantityDead());
        p.setQuantityTested(dda.getQuantityTested());
        p.throwIt();
      }
    }
  }
}
