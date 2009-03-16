package dss.vector.solutions.entomology.assay;

import java.util.List;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.entomology.AssaySex;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.EfficacyAssay;
import dss.vector.solutions.entomology.assay.InvalidFedQuantityProblem;
import dss.vector.solutions.entomology.assay.InvalidFedSexProblem;

public class FedValidator implements Reloadable
{
  private List<AssaySex> sex;
  
  private Integer fed;
  
  private Integer quantityTested;
  
  private String assayId;
    
  public FedValidator(AdultDiscriminatingDoseAssay assay)
  {
    this(assay.getSex(), assay.getFed(), assay.getQuantityTested(), assay.getId());
  }
  
  public FedValidator(EfficacyAssay assay)
  {
    this(assay.getSex(), assay.getFed(), assay.getQuantityTested(), assay.getId());    
  }
  
  public FedValidator(List<AssaySex> sex, Integer fed, Integer quantityTested, String assayId)
  {
    super();
    this.sex = sex;
    this.fed = fed;
    this.quantityTested = quantityTested;
    this.assayId = assayId;
  }

  public void validate()
  {
    
    if (fed != 0 && sex.size() > 0 && ( sex.get(0).equals(AssaySex.MALE) || sex.get(0).equals(AssaySex.UNKNOWN) ))
    {
      String msg = "It is impossible to have fed values on male or unknown sex assays";

      InvalidFedSexProblem p = new InvalidFedSexProblem(msg);
      p.setAssayId(assayId);
      p.throwIt();
    }

    if (fed > quantityTested)
    {
      String msg = "It is impossible to have red values larger than the quantity of mosquitos tested";

      InvalidFedQuantityProblem p = new InvalidFedQuantityProblem(msg);
      p.setAssayId(assayId);
      p.setFed(fed);
      p.throwIt();
    } 
  }
}
