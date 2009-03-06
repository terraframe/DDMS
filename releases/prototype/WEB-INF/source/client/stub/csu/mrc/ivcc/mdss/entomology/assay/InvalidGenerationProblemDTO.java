package csu.mrc.ivcc.mdss.entomology.assay;

import csu.mrc.ivcc.mdss.entomology.assay.InvalidGenerationProblemDTOBase;

public class InvalidGenerationProblemDTO extends InvalidGenerationProblemDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1235164936136L;
  
  public InvalidGenerationProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidGenerationProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
}