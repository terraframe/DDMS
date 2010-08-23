package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.LeftJoinEq;

public class AliasSpoofJoin extends LeftJoinEq implements Reloadable
{
  private String imposterTable;
  private String spoofedAlias;
  
  public AliasSpoofJoin(String attribute1, String tableName1, String tableAlias1, String attribute2, String tableName2, String tableAlias2)
  {
    super(attribute1, tableName1, tableAlias1, attribute2, tableName2, tableAlias2);
    
    this.imposterTable = tableName1;
    this.spoofedAlias = tableAlias2;
  }
  
  @Override
  protected String leftSideSQL()
  {
    return imposterTable + " AS " + spoofedAlias;
  }
  
  @Override
  public String getSQL()
  {
    return "";
  }
}
