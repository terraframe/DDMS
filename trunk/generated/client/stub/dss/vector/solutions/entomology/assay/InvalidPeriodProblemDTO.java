package dss.vector.solutions.entomology.assay;

import dss.vector.solutions.entomology.assay.InvalidPeriodProblemDTOBase;

public class InvalidPeriodProblemDTO extends InvalidPeriodProblemDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1235164936174L;
  
  public InvalidPeriodProblemDTO(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidPeriodProblemDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
}
