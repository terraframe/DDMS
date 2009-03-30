package dss.vector.solutions.entomology.assay;

import com.terraframe.mojo.generation.loader.Reloadable;

public class InvalidAgeProblemDTO extends InvalidAgeProblemDTOBase implements Reloadable
{
  private static final long serialVersionUID = 1234731978550L;
  
  public InvalidAgeProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidAgeProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }  
}
