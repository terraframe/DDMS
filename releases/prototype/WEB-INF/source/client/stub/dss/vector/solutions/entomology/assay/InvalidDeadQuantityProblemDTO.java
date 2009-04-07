package dss.vector.solutions.entomology.assay;

import dss.vector.solutions.entomology.assay.InvalidDeadQuantityProblemDTOBase;

public class InvalidDeadQuantityProblemDTO extends InvalidDeadQuantityProblemDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1235164941935L;
  
  public InvalidDeadQuantityProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidDeadQuantityProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
}
