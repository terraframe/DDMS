package dss.vector.solutions.intervention.monitor;

public class ITNDataViewDTO extends ITNDataViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1245774473137L;
  
  public ITNDataViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  public boolean hasConcreteId()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
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
   * Bean form of isDisplayServicesReadable used in EL for jsps
   * 
   * @return this.isDisplayServicesReadable()
   */
  public boolean getIsDisplayServicesReadable()
  {
    return this.isDisplayServicesReadable();
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
