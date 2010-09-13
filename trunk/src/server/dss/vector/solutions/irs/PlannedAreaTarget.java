package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

public class PlannedAreaTarget extends PlannedTargetUnion implements Reloadable
{
  @Override
  public final String setId(Alias alias)
  {
    return set(IRSQuery.GEO_TARGET_VIEW, idCol, alias);
  }

  @Override
  public String setDisease(Alias alias)
  {
    return set("disease", alias);
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
    String geoTable = MdEntityDAO.getMdEntityDAO(GeoTarget.CLASS).getTableName();

    String sql = "--Planned Area Target\n";
    sql += IRSQuery.GEO_TARGET_VIEW + " " + IRSQuery.GEO_TARGET_VIEW + " INNER JOIN " + geoTable + " "
        + geoTable + " ON " + IRSQuery.GEO_TARGET_VIEW + "." + idCol + " = " + geoTable + "." + idCol
        + " \n";

    return sql;
  }
}
