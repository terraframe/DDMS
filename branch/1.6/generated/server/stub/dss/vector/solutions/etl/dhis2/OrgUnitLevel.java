package dss.vector.solutions.etl.dhis2;

public class OrgUnitLevel extends OrgUnitLevelBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 787015857;
  
  public OrgUnitLevel()
  {
    super();
  }
  
  @Override
  public String buildKey()
  {
    return this.getDhis2Id();
  }
}
