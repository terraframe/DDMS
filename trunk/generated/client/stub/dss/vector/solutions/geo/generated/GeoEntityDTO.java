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
    //${item.geoEntity.geoId} <br/> ${item.geoEntity.entityName} (${item.geoEntity.typeDisplayLabel})
    return this.getEntityName() + "(" + this.getTypeDisplayLabel() + ") - " +this.getGeoId();
  }
  
}
