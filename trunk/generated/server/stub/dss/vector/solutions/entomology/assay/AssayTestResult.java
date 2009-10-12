package dss.vector.solutions.entomology.assay;

import dss.vector.solutions.ontology.Term;

public abstract class AssayTestResult extends AssayTestResultBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235751817145L;
  
  public AssayTestResult()
  {
    super();
  }  
    
  @Override
  protected String buildKey()
  {
    if(this.getMosquito() != null)
    {
      return this.getType() + "." + this.getMosquito().getKey();
    }
    
    return super.getId();
  }
  
  /**
   * @return The test result of the AssayTestResult
   */
  public abstract Object getTestResult();
  
  public Term getTestMethod()
  {
    return null;
  }
  
  public Boolean hasTestMethod()
  {
    return false;
  }
}
