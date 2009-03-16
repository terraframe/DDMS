package dss.vector.solutions.entomology.assay;

import dss.vector.solutions.entomology.assay.InvalidAgeRangeProblemDTOBase;

public class InvalidAgeRangeProblemDTO extends InvalidAgeRangeProblemDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1234731976925L;
  
  public InvalidAgeRangeProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidAgeRangeProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
}