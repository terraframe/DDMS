package dss.vector.solutions.entomology.assay.molecular;

import dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResultBase;

public abstract class TargetSiteAssayTestResult extends TargetSiteAssayTestResultBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235751246349L;
  
  public TargetSiteAssayTestResult()
  {
    super();
  }

  public Boolean hasTestMethod()
  {
    return true;
  }
}
