package csu.mrc.ivcc.mdss.entomology;

import csu.mrc.ivcc.mdss.entomology.InvalidMorphologicalQuantityProblemDTOBase;

public class InvalidMorphologicalQuantityProblemDTO extends InvalidMorphologicalQuantityProblemDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1234371937931L;
  
  public InvalidMorphologicalQuantityProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidMorphologicalQuantityProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
}