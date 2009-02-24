package mdss.ivcc.mrc.csu.entomology.assay;

import mdss.ivcc.mrc.csu.entomology.assay.InvalidGravidQuantityProblemDTOBase;

public class InvalidGravidQuantityProblemDTO extends InvalidGravidQuantityProblemDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "mdss.ivcc.mrc.csu.entomology.assay.InvalidGravidQuantityProblem";
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
