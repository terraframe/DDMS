package csu.mrc.ivcc.mdss.entomology.assay;

import csu.mrc.ivcc.mdss.entomology.AssaySex;

public class AssayValidator
{
  private synchronized static boolean isAgeRangeModified(AdultAssayIF assay)
  {
    return assay.getAgeRange().isModified(AdultAgeRange.STARTPOINT)
        || assay.getAgeRange().isModified(AdultAgeRange.ENDPOINT);
  }

  public synchronized static void validateAdultAgeRange(AdultAssayIF assay)
  {
    if (isAgeRangeModified(assay))
    {
      assay.getAgeRange().validate();
    }
  }
  
  public synchronized static void validateGravid(AdultAssayIF assay)
  {
    AssaySex sex = assay.getSex().get(0);

    if (assay.getGravid() != 0 && ( sex.equals(AssaySex.MALE) || sex.equals(AssaySex.UNKNOWN) ))
    {
      String msg = "It is impossible to have gravid values on male or unknown sex assays";

      InvalidGravidSexProblem p = new InvalidGravidSexProblem(msg);
      p.setAssayId(assay.getId());
      p.throwIt();
    }

    if (assay.getGravid() > assay.getQuantityTested())
    {
      String msg = "It is impossible to have gravid values larger than the quantity of mosquitos tested";

      InvalidGravidQuantityProblem p = new InvalidGravidQuantityProblem(msg);
      p.setAssayId(assay.getId());
      p.setGravid(assay.getGravid());
      p.throwIt();
    }
  }
  
  public synchronized static void validateFed(AdultAssayIF assay)
  {
    AssaySex sex = assay.getSex().get(0);

    if (assay.getFed() != 0 && ( sex.equals(AssaySex.MALE) || sex.equals(AssaySex.UNKNOWN) ))
    {
      String msg = "It is impossible to have fed values on male or unknown sex assays";

      InvalidFedSexProblem p = new InvalidFedSexProblem(msg);
      p.setAssayId(assay.getId());
      p.throwIt();
    }

    if (assay.getFed() > assay.getQuantityTested())
    {
      String msg = "It is impossible to have red values larger than the quantity of mosquitos tested";

      InvalidFedQuantityProblem p = new InvalidFedQuantityProblem(msg);
      p.setAssayId(assay.getId());
      p.setFed(assay.getFed());
      p.throwIt();
    }
  }
  
  public static synchronized void validateQuantityDead(DiscriminatingDoseAssayIF assay)
  {
    if (assay.getQuantityDead() > assay.getQuantityTested())
    {
      String msg = "It is impossible to have a dead quantity larger than the total number of mosquitos tested";

      InvalidDeadQuantityProblem p = new InvalidDeadQuantityProblem(msg);
      p.setAssayId(assay.getId());
      p.setQuantityDead(assay.getQuantityDead());
      p.setQuantityTested(assay.getQuantityTested());
      p.throwIt();
    }
  }
}
