package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.QueryUtil;

public class PlannedAreaTarget extends PlannedTargetUnion implements Reloadable
{
  private String geoTable;
  private String diseaseCol;
  private String spraySeason;
  
  public PlannedAreaTarget()
  {
    super();
    
    geoTable = MdEntityDAO.getMdEntityDAO(GeoTarget.CLASS).getTableName();
    diseaseCol = QueryUtil.getColumnName(GeoTarget.getDiseaseMd());
    spraySeason = QueryUtil.getColumnName(GeoTarget.getSeasonMd());
  }
  
  @Override
  public String setSpraySeason(Alias alias)
  {
    return set(geoTable, spraySeason, alias);
  }
  
  @Override
  public final String setId(Alias alias)
  {
    return set(IRSQuery.GEO_TARGET_VIEW, idCol, alias);
  }
  
  @Override
  public final String setPlannedDate(Alias alias)
  {
    return set(IRSQuery.GEO_TARGET_VIEW, Alias.PLANNED_DATE.getAlias(), alias);
  }

  @Override
  public String setDisease(Alias alias)
  {
    return set(geoTable, diseaseCol, alias);
  }

  @Override
  public String setGeoEntity(Alias alias)
  {
    return set(IRSQuery.GEO_TARGET_VIEW, q.getGeoEntity(), alias);
  }
  
  @Override
  public String setAreaPlannedTarget(Alias alias)
  {
    return set(IRSQuery.GEO_TARGET_VIEW, IRSQuery.WEEKLY_TARGET, alias);
  }


  @Override
  public String from()
  {
    String sql = "--Planned Area Target\n";
    sql += IRSQuery.GEO_TARGET_VIEW + " " + IRSQuery.GEO_TARGET_VIEW + " INNER JOIN " + geoTable + " "
        + geoTable + " ON " + IRSQuery.GEO_TARGET_VIEW + "." + idCol + " = " + geoTable + "." + idCol
        + " \n";

    return sql;
  }
}
