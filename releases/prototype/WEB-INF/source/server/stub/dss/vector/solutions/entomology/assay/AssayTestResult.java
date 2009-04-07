package dss.vector.solutions.entomology.assay;

import dss.vector.solutions.entomology.assay.AssayTestResultBase;
import dss.vector.solutions.mo.AbstractTerm;

public abstract class AssayTestResult extends AssayTestResultBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235751817145L;
  
  public AssayTestResult()
  {
    super();
  }  
  
  /**
   * @return The test result of the AssayTestResult
   */
  public abstract Object getTestResult();
  
  public AbstractTerm getTestMethod()
  {
    return null;
  }
  
  public Boolean hasTestMethod()
  {
    return false;
  }
}
