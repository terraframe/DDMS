package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.LeftJoinEq;

public class IRSSpoofJoin extends LeftJoinEq implements Reloadable
{
  public IRSSpoofJoin(String attribute1, String tableName1, String tableAlias1, String attribute2, String tableName2, String tableAlias2)
  {
    super(attribute1, tableName1, tableAlias1, attribute2, tableName2, tableAlias2);
  }
  
  
  
  @Override
  protected String leftSideSQL()
  {
    return "";
  }
  
  @Override
  protected String getOperator()
  {
    return "";
  }
  
  @Override
  public String getSQL()
  {
    return "(SELECT NULL) spoofTable";
  }
}
