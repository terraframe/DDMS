package dss.vector.solutions.synchronization;

public class SynchronizedTypeViewDTO extends SynchronizedTypeViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = -1956681657;
  
  public SynchronizedTypeViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  @Override
  public boolean isNewInstance()
  {
    return true;
  }
}
