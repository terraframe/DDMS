package mdss.entomology.assay;

public class InvalidGenerationProblemDTO extends InvalidGenerationProblemDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "mdss.entomology.assay.InvalidGenerationProblem";
  private static final long serialVersionUID = 1235164936136L;
  
  public InvalidGenerationProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidGenerationProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
}
