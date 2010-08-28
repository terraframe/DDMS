package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.QueryUtil;

public class PlannedAreaTarget extends PlannedTargetUnion implements Reloadable
{
  @Override
  public String setDisease(Alias alias)
  {
    return set("disease", alias);
  }
  
  @Override
  public String setGeoEntity(Alias alias)
  {
    String geoEntityCol = QueryUtil.getColumnName(GeoTarget.getGeoEntityMd());
    return set(geoEntityCol, alias);
  }
  
  @Override
  public String setAreaPlannedTarget(Alias alias)
  {
    return set(IRSQuery.WEEKLY_TARGET, alias);
  }
  
  @Override
  public String from()
  {
    String sql = "";
    sql += IRSQuery.GEO_TARGET_VIEW + " " + IRSQuery.GEO_TARGET_VIEW + " \n";
    
    return sql;
  }
}
