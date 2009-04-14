package dss.vector.solutions.entomology.assay.infectivity;


public abstract class InfectivityAssayTestResult extends InfectivityAssayTestResultBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235751242226L;
  
  public InfectivityAssayTestResult()
  {
    super();
  }

  public Boolean hasTestMethod()
  {
    return true;
  }
}
