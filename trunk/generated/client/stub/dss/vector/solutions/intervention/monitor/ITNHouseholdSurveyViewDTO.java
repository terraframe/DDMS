package dss.vector.solutions.intervention.monitor;

public class ITNHouseholdSurveyViewDTO extends ITNHouseholdSurveyViewDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1252970233283L;
  
  public ITNHouseholdSurveyViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
  
  /**
   * Bean form of isDisplayNonUseReasons used in EL for jsps
   * 
   * @return this.isDisplayTargetGroupsReadable()
   */
  public boolean getIsDisplayNonUseReasonsReadable()
  {
    return this.isDisplayNonUseReasonsReadable();
  }

 
}
