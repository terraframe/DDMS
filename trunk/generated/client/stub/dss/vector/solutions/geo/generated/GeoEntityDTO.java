package dss.vector.solutions.geo.generated;


public abstract class GeoEntityDTO extends GeoEntityDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1236367038920L;
  
  public GeoEntityDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected GeoEntityDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  public String getDisplayString()
  {
    // To avoid a trip to the server, make a best attempt to render the display string here.
    String typeDisplay = this.getMd().getDisplayLabel();
    String termId = this.getValue(GeoEntityDTO.TERM);
    String display;
    if((typeDisplay == null || typeDisplay.trim().length() == 0)
        || (termId != null && termId.trim().length() > 0))
    {
      // We have to fetch the type display label or the MO term
      display = this.getTypeDisplayLabel();
    }
    else
    {
      display = typeDisplay;
    }
    
    return this.getEntityName() + " (" + display + ") - " + this.getGeoId();
  }
  
}
