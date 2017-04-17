package dss.vector.solutions.entomology;

public class ImmatureCollectionViewDTO extends ImmatureCollectionViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = -1119197359;
  
  public ImmatureCollectionViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  
  public boolean getIsContainerGridReadable()
  {
    return this.isContainerGridReadable();
  }
}
