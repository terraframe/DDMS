package dss.vector.solutions.ontology;

public class BrowserRootViewDTO extends BrowserRootViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1252959712957L;
  
  public BrowserRootViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  public String getDisplayLabel()
  {
    return this.getTermName();
  }
}
