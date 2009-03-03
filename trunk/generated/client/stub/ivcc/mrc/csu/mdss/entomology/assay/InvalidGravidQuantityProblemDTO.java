package ivcc.mrc.csu.mdss.entomology.assay;

import ivcc.mrc.csu.mdss.entomology.assay.InvalidGravidQuantityProblemDTOBase;

public class InvalidGravidQuantityProblemDTO extends InvalidGravidQuantityProblemDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1235164939726L;
  
  public InvalidGravidQuantityProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidGravidQuantityProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
}
