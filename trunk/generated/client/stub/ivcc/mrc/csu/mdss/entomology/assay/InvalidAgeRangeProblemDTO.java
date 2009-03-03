package ivcc.mrc.csu.mdss.entomology.assay;

import ivcc.mrc.csu.mdss.entomology.assay.InvalidAgeRangeProblemDTOBase;

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
