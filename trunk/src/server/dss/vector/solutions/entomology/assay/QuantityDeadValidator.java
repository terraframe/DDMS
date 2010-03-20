package dss.vector.solutions.entomology.assay;

import com.runwaysdk.generation.loader.Reloadable;

public class QuantityDeadValidator implements Reloadable
{
  private Integer quantityDead;

  private Integer quantityTested;

  private AbstractAssay assay;

  public QuantityDeadValidator(AdultDiscriminatingDoseAssay assay)
  {
    this(assay.getQuantityDead(), assay.getQuantityTested(), assay);
  }

  public QuantityDeadValidator(LarvaeDiscriminatingDoseAssay assay)
  {
    this(assay.getQuantityDead(), assay.getQuantityTested(), assay);
  }

  public QuantityDeadValidator(EfficacyAssay assay)
  {
    this(assay.getQuantityDead(), assay.getQuantityTested(), assay);
  }

  public QuantityDeadValidator(Integer quantityDead, Integer quantityTested, AbstractAssay assay)
  {
    this.quantityDead = quantityDead;
    this.quantityTested = quantityTested;
    this.assay = assay;
  }

  public void validate()
  {
    if(quantityDead == null || quantityTested == null)return;

    if (quantityDead > quantityTested)
    {
      String msg = "It is impossible to have a dead quantity larger than the total number of mosquitos tested";

      InvalidDeadQuantityProblem p = new InvalidDeadQuantityProblem(msg);
      p.setQuantityDead(quantityDead);
      p.setQuantityTested(quantityTested);
      p.setNotification(assay, AdultDiscriminatingDoseAssay.QUANTITYDEAD);
      p.apply();
      p.throwIt();
    }
  }
}
