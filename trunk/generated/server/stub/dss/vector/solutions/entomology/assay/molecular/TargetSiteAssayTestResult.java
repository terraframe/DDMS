package dss.vector.solutions.entomology.assay.molecular;

public abstract class TargetSiteAssayTestResult extends TargetSiteAssayTestResultBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237829491431L;

  public TargetSiteAssayTestResult()
  {
    super();
  }

  public Boolean hasTestMethod()
  {
    return true;
  }
}
