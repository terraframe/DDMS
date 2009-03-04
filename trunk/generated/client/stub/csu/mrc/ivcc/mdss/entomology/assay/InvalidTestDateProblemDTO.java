package csu.mrc.ivcc.mdss.entomology.assay;

import csu.mrc.ivcc.mdss.entomology.assay.InvalidTestDateProblemDTOBase;

public class InvalidTestDateProblemDTO extends InvalidTestDateProblemDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1235164941124L;
  
  public InvalidTestDateProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidTestDateProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
}
