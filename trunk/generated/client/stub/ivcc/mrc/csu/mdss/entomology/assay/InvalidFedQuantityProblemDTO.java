package ivcc.mrc.csu.mdss.entomology.assay;

import ivcc.mrc.csu.mdss.entomology.assay.InvalidFedQuantityProblemDTOBase;

public class InvalidFedQuantityProblemDTO extends InvalidFedQuantityProblemDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1235164946525L;
  
  public InvalidFedQuantityProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidFedQuantityProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
}
