package dss.vector.solutions.query;


public class LayerDTO extends LayerDTOBase
 implements com.runwaysdk.generation.loader.Reloadable, LayerIF {
  private static final long serialVersionUID = 1240900979001L;
  
  public LayerDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected LayerDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  public boolean hasThematicVariable()
  {
    String alias = this.getThematicUserAlias();
    return alias != null && alias.length() != 0;
  }
  
  /**
   * This accessor lowercases the column alias to match actual column alias
   * which is lowercased by the Query API prior to building the SQL.
   */
  public String getThematicColumnAlias()
  {
    return super.getThematicColumnAlias().toLowerCase();
  }
    
}
