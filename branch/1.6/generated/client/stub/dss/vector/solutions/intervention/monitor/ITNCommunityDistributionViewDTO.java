package dss.vector.solutions.intervention.monitor;

public class ITNCommunityDistributionViewDTO extends ITNCommunityDistributionViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1252612685889L;
  
  public ITNCommunityDistributionViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  
  /**
   * Bean form of isDisplayNetsReadable used in EL for jsps
   * 
   * @return this.isDisplayNetsReadable()
   */
  public boolean getIsDisplayNetsReadable()
  {
    return this.isDisplayNetsReadable();
  }

  /**
   * Bean form of isDisplayTargetGroupsReadable used in EL for jsps
   * 
   * @return this.isDisplayTargetGroupsReadable()
   */
  public boolean getIsDisplayTargetGroupsReadable()
  {
    return this.isDisplayTargetGroupsReadable();
  }
}
