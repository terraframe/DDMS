package dss.vector.solutions.entomology.assay;

import dss.vector.solutions.entomology.assay.InvalidFedQuantityProblemDTOBase;

public class InvalidFedQuantityProblemDTO extends InvalidFedQuantityProblemDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1235164946525L;
  
  public InvalidFedQuantityProblemDTO(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidFedQuantityProblemDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
}
