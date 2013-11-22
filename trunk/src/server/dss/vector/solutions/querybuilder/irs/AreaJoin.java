package dss.vector.solutions.querybuilder.irs;

import java.util.Map;

import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;
import dss.vector.solutions.util.QueryUtil;

public class AreaJoin extends TargetJoin implements Reloadable
{

  // /** OBSOLETE as of 9.1, which support Hash Conditions in RIGHT/OUTER joins
  // * Marks this area join as either the left or right join to simulate a full
  // outer join.
  // * This is done due to limitations in postgres when doing full outer joins
  // */
  // private boolean isLeftJoin;
  public static final String JOIN_SUFFIX = "_join";
  
  public AreaJoin(IRSQB irsQB, boolean hasActual, boolean hasPlanned)
  {
    super(irsQB, hasActual, hasPlanned);
  }

  @Override
  public void loadDependencies()
  {
    super.loadDependencies();

    // Load aliases that will be in the JOIN clause
    Alias[] joinAliases = new Alias[] { Alias.TARGET, Alias.TARGET_WEEK, Alias.SPRAY_SEASON,
        Alias.GEO_ENTITY, Alias.DISEASE };
    this.irsQB.addRequiredAlias(View.ALL_ACTUALS, joinAliases);
    this.irsQB.addRequiredAlias(View.PLANNED_AREA, joinAliases);
  }

  public final String FROM()
  {
    return IRSQB.View.ALL_ACTUALS + " " + TargetJoin.ACTUAL_ALIAS;
  }

  public final String CUSTOM_FROM(Map<String, IRSQB.Universal> universals)
  {
    // String a = IRSQB.View.ALL_ACTUALS + " " + TargetJoin.ACTUAL_ALIAS;
    String a = TargetJoin.ACTUAL_ALIAS;
    String p = IRSQB.View.PLANNED_AREA + " " + TargetJoin.PLANNED_ALIAS;

    // Area targets work as follows: limit the planned targets by the universal
    // columns,
    // but sum the actual targets for all entities beneath the planned target
    // geo entity.

    String sql = "";

    // PostgreSQL 9.1+ supports RIGHT and OUTER joins with Hash Conditions,
    // so there's no need to UNION a LEFT and RIGHT join to simulate an OUTER.
    sql += a + " FULL OUTER JOIN " + p + " \n";
    // sql += a + " "+(this.isLeftJoin ? "LEFT" : "RIGHT")+" OUTER JOIN " + p +
    // " \n";

    // NOTE: This is the old code for reference
    // sql +=
    // "ON extract(YEAR FROM "+TargetJoin.ACTUAL_ALIAS+"."+Alias.SPRAY_DATE.getAlias()+") "
    // +
    // "= extract(YEAR FROM "+TargetJoin.PLANNED_ALIAS+"."+Alias.PLANNED_DATE.getAlias()+") \n";
    sql += "ON " + TargetJoin.PLANNED_ALIAS + "." + Alias.TARGET_WEEK + " = " + TargetJoin.ACTUAL_ALIAS
        + "." + IRSQB.AREA_PREFIX + Alias.TARGET_WEEK + " \n";

    // FIXED: joined based on spray season instead of year + week
    sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.SPRAY_SEASON + " = "
        + TargetJoin.ACTUAL_ALIAS + "." + IRSQB.AREA_PREFIX + Alias.SPRAY_SEASON + " \n";

    sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.DISEASE + " = " + TargetJoin.ACTUAL_ALIAS
        + "." + IRSQB.AREA_PREFIX + Alias.DISEASE + " \n";

    String pathsTable = MdEntityDAO.getMdEntityDAO(AllPaths.CLASS).getTableName();
    String childGeo = QueryUtil.getColumnName(AllPaths.getChildGeoEntityMd());
    String parentGeo = QueryUtil.getColumnName(AllPaths.getParentGeoEntityMd());
    String parentUniversal = QueryUtil.getColumnName(AllPaths.getParentUniversalMd());

    sql += "AND EXISTS (SELECT 1 FROM " + pathsTable + " WHERE" + " " + parentGeo + " = "
        + TargetJoin.PLANNED_ALIAS + "." + Alias.GEO_ENTITY + " AND " + childGeo + " = "
        + TargetJoin.ACTUAL_ALIAS + "." + IRSQB.AREA_PREFIX + Alias.GEO_ENTITY + ")";

    String geoIdCol = QueryUtil.getColumnName(GeoEntity.getGeoIdMd());
    
    for(IRSQB.Universal universal : universals.values())
    {
      String alias = universal.getName().toLowerCase()+JOIN_SUFFIX;
      
      sql += "\n";
      sql += "LEFT JOIN (\n";
      sql += "    SELECT \n";
      sql += "      gdl."+geoIdCol+" AS "+universal.getGeoIdPlanned()+", \n";
      sql += "      gdl."+QueryUtil.LABEL_COLUMN+" AS "+universal.getEntityNamePlanned()+", \n";
      sql += "      apg."+childGeo+" \n";
      sql += "    FROM \n";
      sql += "      "+QueryUtil.GEO_DISPLAY_LABEL+" gdl INNER JOIN "+pathsTable+" apg ON apg."+parentGeo+" = gdl."+this.irsQB.getIdCol()+" \n";
      sql += "      AND "+parentUniversal+" = '"+universal.getId()+"' \n";
      sql += ") "+alias+" ON "+alias+"."+childGeo+" = "+PLANNED_ALIAS+"."+Alias.GEO_ENTITY+" \n";
    }
    
    return sql;
  }

}
