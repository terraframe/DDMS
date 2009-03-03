package ivcc.mrc.csu.mdss.entomology.assay;

import ivcc.mrc.csu.mdss.entomology.assay.AssayTestResultBase;
import ivcc.mrc.csu.mdss.mo.AbstractTerm;

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
  
  public abstract AbstractTerm getTestMethod();
}
