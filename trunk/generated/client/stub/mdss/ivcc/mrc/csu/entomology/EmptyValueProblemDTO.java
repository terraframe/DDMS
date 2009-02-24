package mdss.ivcc.mrc.csu.entomology;

import mdss.ivcc.mrc.csu.entomology.EmptyValueProblemDTOBase;

public class EmptyValueProblemDTO extends EmptyValueProblemDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "mdss.ivcc.mrc.csu.entomology.EmptyValueProblem";
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
