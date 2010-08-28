package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.LeftJoinEq;

import dss.vector.solutions.util.QueryUtil;

public class IRSSpoofJoin extends LeftJoinEq implements Reloadable
{
  private String imposterTable;
  private String spoofedAlias;
  
  public IRSSpoofJoin(String attribute1, String tableName1, String tableAlias1, String attribute2, String tableName2, String tableAlias2)
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
    String idCol = QueryUtil.getIdColumn();
    
    return " LEFT JOIN "+IRSQuery.INSECTICIDE_VIEW+" "+IRSQuery.INSECTICIDE_VIEW+" ON "+
      IRSQuery.INSECTICIDE_VIEW+"."+idCol+" = "+this.spoofedAlias+"."+Alias.BRAND + " \n";
  }
}
