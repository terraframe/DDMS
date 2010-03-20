package dss.vector.solutions.entomology.assay;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;

public class FedValidator implements Reloadable
{
  private Term sex;

  private Integer fed;

  private Integer quantityTested;

  private AbstractAssay assay;

  public FedValidator(AdultAssay assay)
  {
    this(assay.getSex(), assay.getFed(), assay.getQuantityTested(), assay);
  }

  public FedValidator(EfficacyAssay assay)
  {
    this(assay.getSex(), assay.getFed(), assay.getQuantityTested(), assay);
  }

  public FedValidator(Term sex, Integer fed, Integer quantityTested, AbstractAssay assay)
  {
    super();
    this.sex = sex;
    this.fed = fed;
    this.quantityTested = quantityTested;
    this.assay = assay;
  }

  public void validate()
  {
    //null is allowed
    if(fed == null)return;
    
    //TODO get the real id for the Male/unknown MO ontology
    String maleId = "";
    String unknownId = "";

    if (fed != 0 && sex != null && ( sex.getTermId().equals(maleId) || sex.getTermId().equals(unknownId) ))
    {
      String msg = "It is impossible to have fed values on male or unknown sex assays";

      InvalidFedSexProblem p = new InvalidFedSexProblem(msg);
      p.setNotification(assay, AdultDiscriminatingDoseAssay.FED);
      p.apply();
      p.throwIt();
    }

    if(quantityTested == null)return;

    if (fed > quantityTested)
    {
      String msg = "It is impossible to have red values larger than the quantity of mosquitos tested";

      InvalidFedQuantityProblem p = new InvalidFedQuantityProblem(msg);
      p.setFed(fed);
      p.setNotification(assay, AdultDiscriminatingDoseAssay.FED);
      p.apply();
      p.throwIt();
    }
  }
}
