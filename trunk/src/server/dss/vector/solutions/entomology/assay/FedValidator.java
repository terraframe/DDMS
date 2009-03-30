package dss.vector.solutions.entomology.assay;

import java.util.List;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.entomology.AssaySex;

public class FedValidator implements Reloadable
{
  private List<AssaySex> sex;
  
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
  
  public FedValidator(List<AssaySex> sex, Integer fed, Integer quantityTested, AbstractAssay assay)
  {
    super();
    this.sex = sex;
    this.fed = fed;
    this.quantityTested = quantityTested;
    this.assay = assay;
  }

  public void validate()
  {
    
    if (fed != 0 && sex.size() > 0 && ( sex.get(0).equals(AssaySex.MALE) || sex.get(0).equals(AssaySex.UNKNOWN) ))
    {
      String msg = "It is impossible to have fed values on male or unknown sex assays";

      InvalidFedSexProblem p = new InvalidFedSexProblem(msg);
      p.setNotification(assay, AdultDiscriminatingDoseAssay.FED);
      p.apply();
      p.throwIt();
    }

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
