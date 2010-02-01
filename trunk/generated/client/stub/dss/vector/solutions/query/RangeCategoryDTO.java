package dss.vector.solutions.query;

public class RangeCategoryDTO extends RangeCategoryDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable
 {
  private static final long serialVersionUID = 1241158118899L;
  
  public RangeCategoryDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected RangeCategoryDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
