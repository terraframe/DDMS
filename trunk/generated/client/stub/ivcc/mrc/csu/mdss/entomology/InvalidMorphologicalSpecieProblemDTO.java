package ivcc.mrc.csu.mdss.entomology;

import ivcc.mrc.csu.mdss.entomology.InvalidMorphologicalSpecieProblemDTOBase;

public class InvalidMorphologicalSpecieProblemDTO extends InvalidMorphologicalSpecieProblemDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1234374981651L;
  
  public InvalidMorphologicalSpecieProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidMorphologicalSpecieProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
}
