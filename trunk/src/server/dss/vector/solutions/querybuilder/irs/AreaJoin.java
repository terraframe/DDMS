package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

/**
 * Generates the code responsible for creating the SprayView containing Area target data.
 * 
 * @author Justin Naifeh, Richard Rowlands
 */
public class AreaJoin extends TargetJoin implements Reloadable
{

  // /** OBSOLETE as of 9.1, which support Hash Conditions in RIGHT/OUTER joins
  // * Marks this area join as either the left or right join to simulate a full
  // outer join.
  // * This is done due to limitations in postgres when doing full outer joins
  // */
  // private boolean isLeftJoin;
  public static final String JOIN_SUFFIX = "_join";

  public AreaJoin(IRSQB irsQB)
  {
    super(irsQB);
  }

  @Override
  public void loadDependencies()
  {
    // HEY! THIS CODE NEVER GETS EXECUTED.
    // The reason is because the AreaJoin is used in the SprayView and this only gets new instanced when the SQL gets generated,
    // which is far too late for the dependencies to be loaded. If you want to modify the dependencies do it in the SprayView.
    
    super.loadDependencies();

    // Load aliases that will be in the JOIN clause
    Alias[] joinAliases = new Alias[] { Alias.TARGET, Alias.TARGET_WEEK, Alias.SPRAY_SEASON,
        Alias.GEO_ENTITY, Alias.DISEASE };
    this.irsQB.addRequiredAlias(View.ALL_ACTUALS, joinAliases);
    this.irsQB.addRequiredAlias(View.PLANNED_AREA, joinAliases);
  }

  public final String FROM()
  {
    String a = IRSQB.View.ALL_ACTUALS + " " + TargetJoin.ACTUAL_ALIAS;
    String p = IRSQB.View.PLANNED_AREA + " " + TargetJoin.PLANNED_ALIAS;

    // Area targets work as follows: limit the planned targets by the universal
    // columns,
    // but sum the actual targets for all entities beneath the planned target
    // geo entity.
    /*
    if (this.hasActual && this.hasPlanned)
    {
      String sql = "";

      // PostgreSQL 9.1+ supports RIGHT and OUTER joins with Hash Conditions,
      // so there's no need to UNION a LEFT and RIGHT join to simulate an OUTER.
      sql += a +  this.dateGroupJoin(TargetJoin.ACTUAL_ALIAS, Alias.SPRAY_DATE.getAlias()) + " FULL OUTER JOIN " + p + " \n";

//      sql += a + " "+(this.isLeftJoin ? "LEFT" : "RIGHT")+" OUTER JOIN " + p + " \n";
      
      
      // NOTE: This is the old code for reference 
//      sql += "ON extract(YEAR FROM "+TargetJoin.ACTUAL_ALIAS+"."+Alias.SPRAY_DATE.getAlias()+") " +
//              "= extract(YEAR FROM "+TargetJoin.PLANNED_ALIAS+"."+Alias.PLANNED_DATE.getAlias()+") \n";
      sql += "ON " + TargetJoin.PLANNED_ALIAS + "." + Alias.TARGET_WEEK + " = "
          + View.DATE_GROUPS.getView()+"."+Alias.DATEGROUP_EPIWEEK+"::"+Alias.TARGET_WEEK.getType() + " \n";
      
      // FIXED: joined based on spray season instead of year + week
      sql += "AND "+TargetJoin.PLANNED_ALIAS + "." + Alias.SPRAY_SEASON + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + Alias.SPRAY_SEASON + " \n";
      
      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.DISEASE + " = " + TargetJoin.ACTUAL_ALIAS
          + "." + Alias.DISEASE + " \n";

      String pathsTable = MdEntityDAO.getMdEntityDAO(AllPaths.CLASS).getTableName();
      String childGeo = QueryUtil.getColumnName(AllPaths.getChildGeoEntityMd());
      String parentGeo = QueryUtil.getColumnName(AllPaths.getParentGeoEntityMd());
      
      sql += "AND EXISTS (SELECT 1 FROM " + pathsTable + " WHERE" + " " + parentGeo + " = " + TargetJoin.PLANNED_ALIAS + "."
          + Alias.GEO_ENTITY + " AND " + childGeo + " = " + TargetJoin.ACTUAL_ALIAS + "."
          + Alias.GEO_ENTITY + ")";

      sql += new DateGroups(irsQB, this, View.PLANNED_AREA, TargetJoin.PLANNED_ALIAS, Alias.PLANNED_DATE).getOverrideSQL();
      
      return sql;
    }
    */
    if(this.hasPlanned)
    {
      return p + new DateGroups(irsQB, this, View.PLANNED_AREA, TargetJoin.PLANNED_ALIAS, Alias.PLANNED_DATE).getOverrideSQL();
    }
    else
    {
      return a + dateGroupJoin(TargetJoin.ACTUAL_ALIAS, Alias.SPRAY_DATE.getAlias()) + this.GROUP_BY();
    }
  }
  
  public String getLevel() {
    return "3";
  }

  // JN change
  /*
  public final String CUSTOM_FROM(Map<String, IRSQB.Universal> universals)
  {
    String sql = "";
    String a = TargetJoin.ACTUAL_ALIAS;
    String p = IRSQB.View.PLANNED_AREA + " " + TargetJoin.PLANNED_ALIAS;
    
    String pathsTable = MdEntityDAO.getMdEntityDAO(AllPaths.CLASS).getTableName();
    String childGeo = QueryUtil.getColumnName(AllPaths.getChildGeoEntityMd());
    String parentGeo = QueryUtil.getColumnName(AllPaths.getParentGeoEntityMd());
    String parentUniversal = QueryUtil.getColumnName(AllPaths.getParentUniversalMd());
    String geoIdCol = QueryUtil.getColumnName(GeoEntity.getGeoIdMd());
    this.hasPlanned = true;
    if (this.hasActual && this.hasPlanned)
    {

      // String a = IRSQB.View.ALL_ACTUALS + " " + TargetJoin.ACTUAL_ALIAS;

      // Area targets work as follows: limit the planned targets by the
      // universal
      // columns,
      // but sum the actual targets for all entities beneath the planned target
      // geo entity.

      // PostgreSQL 9.1+ supports RIGHT and OUTER joins with Hash Conditions,
      // so there's no need to UNION a LEFT and RIGHT join to simulate an OUTER.
      sql += a + " FULL OUTER JOIN " + p + " \n";
      // sql += a + " "+(this.isLeftJoin ? "LEFT" : "RIGHT")+" OUTER JOIN " + p
      // +
      // " \n";

      // NOTE: This is the old code for reference
      // sql +=
      // "ON extract(YEAR FROM "+TargetJoin.ACTUAL_ALIAS+"."+Alias.SPRAY_DATE.getAlias()+") "
      // +
      // "= extract(YEAR FROM "+TargetJoin.PLANNED_ALIAS+"."+Alias.PLANNED_DATE.getAlias()+") \n";
      sql += "ON " + TargetJoin.PLANNED_ALIAS + "." + Alias.TARGET_WEEK + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + IRSQB.AREA_PREFIX + Alias.TARGET_WEEK + " \n";

      // FIXED: joined based on spray season instead of year + week
      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.SPRAY_SEASON + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + IRSQB.AREA_PREFIX + Alias.SPRAY_SEASON + " \n";

      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.DISEASE + " = " + TargetJoin.ACTUAL_ALIAS
          + "." + IRSQB.AREA_PREFIX + Alias.DISEASE + " \n";

      sql += "AND EXISTS (SELECT 1 FROM " + pathsTable + " WHERE" + " " + parentGeo + " = "
          + TargetJoin.PLANNED_ALIAS + "." + Alias.GEO_ENTITY + " AND " + childGeo + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + IRSQB.AREA_PREFIX + Alias.GEO_ENTITY + ")";

    }
    else if(this.hasPlanned)
    {
      sql += p;
    }
    


    for (IRSQB.Universal universal : universals.values())
    {
      String alias = universal.getName().toLowerCase() + JOIN_SUFFIX;

      sql += "\n";
      sql += "LEFT JOIN (\n";
      sql += "    SELECT \n";
      sql += "      gdl." + geoIdCol + " AS " + universal.getGeoIdPlanned() + ", \n";
      sql += "      gdl." + QueryUtil.LABEL_COLUMN + " AS " + universal.getEntityNamePlanned() + ", \n";
      sql += "      apg." + childGeo + " \n";
      sql += "    FROM \n";
      sql += "      " + QueryUtil.GEO_DISPLAY_LABEL + " gdl INNER JOIN " + pathsTable + " apg ON apg."
          + parentGeo + " = gdl." + this.irsQB.getIdCol() + " \n";
      sql += "      AND " + parentUniversal + " = '" + universal.getId() + "' \n";
      sql += ") " + alias + " ON " + alias + "." + childGeo + " = " + PLANNED_ALIAS + "."
          + Alias.GEO_ENTITY + " \n";
    }

    return sql;
  }
*/
}
