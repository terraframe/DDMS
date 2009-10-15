package dss.vector.solutions.entomology.assay.biochemical;

import dss.vector.solutions.ontology.Term;

public abstract class MetabolicAssayTestResult extends MetabolicAssayTestResultBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237829484960L;
  
  public MetabolicAssayTestResult()
  {
    super();
  }
  
  @Override
  public void setTestMethod(Term testMethod)
  {
    //Balk: do nothing this type does not have a test method
  }
}
