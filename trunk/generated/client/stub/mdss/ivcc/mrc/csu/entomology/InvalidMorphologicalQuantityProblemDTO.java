package mdss.ivcc.mrc.csu.entomology;

import mdss.ivcc.mrc.csu.entomology.InvalidMorphologicalQuantityProblemDTOBase;

public class InvalidMorphologicalQuantityProblemDTO extends InvalidMorphologicalQuantityProblemDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "mdss.ivcc.mrc.csu.entomology.InvalidMorphologicalQuantity";
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
