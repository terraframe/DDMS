package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.util.QueryUtil;

public class AreaJoin extends TargetJoin implements Reloadable
{
  
//  /** OBSOLETE as of 9.1, which support Hash Conditions in RIGHT/OUTER joins
//   * Marks this area join as either the left or right join to simulate a full outer join.
//   * This is done due to limitations in postgres when doing full outer joins
//   */
//  private boolean isLeftJoin;
  
  public AreaJoin(boolean hasActual, boolean hasPlanned)
  {
    super(hasActual, hasPlanned);
  }

  public final String from()
  {
    String a = IRSQB.ALL_ACTUALS + " " + TargetJoin.ACTUAL_ALIAS;
    String p = IRSQB.PLANNED_AREA + " " + TargetJoin.PLANNED_ALIAS;

    // Area targets work as follows: limit the planned targets by the universal
    // columns,
    // but sum the actual targets for all entities beneath the planned target
    // geo entity.
    if (hasPlanned)
    {
      String sql = "";

      // PostgreSQL 9.1+ supports RIGHT and OUTER joins with Hash Conditions,
      // so there's no need to UNION a LEFT and RIGHT join to simulate an OUTER.
      sql += a + " FULL OUTER JOIN " + p + " \n";
//      sql += a + " "+(this.isLeftJoin ? "LEFT" : "RIGHT")+" OUTER JOIN " + p + " \n";
      
      
      // NOTE: This is the old code for reference 
//      sql += "ON extract(YEAR FROM "+TargetJoin.ACTUAL_ALIAS+"."+Alias.SPRAY_DATE.getAlias()+") " +
//      		"= extract(YEAR FROM "+TargetJoin.PLANNED_ALIAS+"."+Alias.PLANNED_DATE.getAlias()+") \n";
      sql += "ON " + TargetJoin.PLANNED_ALIAS + "." + Alias.TARGET_WEEK + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + Alias.TARGET_WEEK + " \n";
      
      // FIXED: joined based on spray season instead of year + week
      sql += "AND "+TargetJoin.PLANNED_ALIAS + "." + Alias.SPRAY_SEASON + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + Alias.SPRAY_SEASON + " \n";
      
      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.DISEASE + " = " + TargetJoin.ACTUAL_ALIAS
          + "." + Alias.DISEASE + " \n";

      String pathsTable = MdEntityDAO.getMdEntityDAO(AllPaths.CLASS).getTableName();
      String childGeo = QueryUtil.getColumnName(AllPaths.getChildGeoEntityMd());
      String parentGeo = QueryUtil.getColumnName(AllPaths.getParentGeoEntityMd());
      
      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.GEO_ENTITY + " = (SELECT " + parentGeo
          + " FROM " + pathsTable + " WHERE" + " " + parentGeo + " = " + TargetJoin.PLANNED_ALIAS + "."
          + Alias.GEO_ENTITY + " AND " + childGeo + " = " + TargetJoin.ACTUAL_ALIAS + "."
          + Alias.GEO_ENTITY + ")";

      return sql;
    }
    else
    {
      return a;
    }
  }

}
