package mdss.ivcc.mrc.csu.entomology.assay;

import mdss.ivcc.mrc.csu.entomology.assay.InvalidIntervalTimeProblemDTOBase;

public class InvalidIntervalTimeProblemDTO extends InvalidIntervalTimeProblemDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "mdss.ivcc.mrc.csu.entomology.assay.InvalidIntervalTimeProblem";
  private static final long serialVersionUID = 1235164946276L;
  
  public InvalidIntervalTimeProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidIntervalTimeProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
}
