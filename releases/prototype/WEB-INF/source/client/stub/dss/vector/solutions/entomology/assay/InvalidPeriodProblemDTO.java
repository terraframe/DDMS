package dss.vector.solutions.entomology.assay;

import dss.vector.solutions.entomology.assay.InvalidPeriodProblemDTOBase;

public class InvalidPeriodProblemDTO extends InvalidPeriodProblemDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1235164936174L;
  
  public InvalidPeriodProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidPeriodProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
}