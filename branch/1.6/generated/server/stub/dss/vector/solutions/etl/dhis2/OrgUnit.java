package dss.vector.solutions.etl.dhis2;

public class OrgUnit extends OrgUnitBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 27697990;
  
  public OrgUnit()
  {
    super();
  }
  
  @Override
  public String buildKey()
  {
    return this.getDhis2Id();
  }
}
