package csu.mrc.ivcc.mdss.entomology;

import csu.mrc.ivcc.mdss.entomology.EmptyValueProblemDTOBase;

public class EmptyValueProblemDTO extends EmptyValueProblemDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1234371768073L;
  
  public EmptyValueProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public EmptyValueProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
}
