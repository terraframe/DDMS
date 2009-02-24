package mdss.ivcc.mrc.csu.entomology.assay;

import mdss.ivcc.mrc.csu.entomology.assay.InvalidFedQuantityProblemDTOBase;

public class InvalidFedQuantityProblemDTO extends InvalidFedQuantityProblemDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "mdss.ivcc.mrc.csu.entomology.assay.InvalidFedQuantityProblem";
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
