package dss.vector.solutions.query;

public class RangeCategoryDTO extends RangeCategoryDTOBase
 implements com.runwaysdk.generation.loader.Reloadable, RangeCategoryIF 
 {
  private static final long serialVersionUID = 1241158118899L;
  
  public RangeCategoryDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected RangeCategoryDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  public String toString()
  {
    String s = "";
    String lower = this.getLowerBoundStr();
    String upper = this.getUpperBoundStr();
    
    if(lower != null && lower.trim().length() > 0)
    {
      s += lower;
    }
    
    s += " < ";
    
    if(upper != null && upper.trim().length() > 0)
    {
      s += upper;
    }
    
    return s;
  }
  
}
