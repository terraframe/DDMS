package csu.mrc.ivcc.mdss.entomology.assay;

import com.terraframe.mojo.generation.loader.Reloadable;

public class AdultAgeRangeValidator implements Reloadable
{
  private boolean modified;
  
  private AdultAgeRange ageRange;
  
  public AdultAgeRangeValidator(AdultDiscriminatingDoseAssay assay)
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
