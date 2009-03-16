package dss.vector.solutions.entomology.assay;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.EfficacyAssay;

public class AdultAgeRangeValidator implements Reloadable
{
  private boolean modified;
  
  private AdultAgeRange ageRange;
  
  public AdultAgeRangeValidator(AdultDiscriminatingDoseAssay assay)
  {
    this(assay.getAgeRange());    
  }
  
  public AdultAgeRangeValidator(KnockDownAssay assay)
  {
    this(assay.getAgeRange());    
  }
  
  public AdultAgeRangeValidator(EfficacyAssay assay)
  {
    this(assay.getAgeRange());
  }
  
  public AdultAgeRangeValidator(AdultAgeRange ageRange)
  {
    super();
    this.ageRange = ageRange;
    this.modified = (ageRange.isModified(AdultAgeRange.STARTPOINT) ||ageRange.isModified(AdultAgeRange.ENDPOINT));
  }

  public void validate()
  {
    if (modified)
    {
      ageRange.validate();
    }
  }
}
