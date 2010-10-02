package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.util.QueryUtil;

public class AreaJoin extends TargetJoin implements Reloadable
{

  public AreaJoin(boolean hasActual, boolean hasPlanned)
  {
    super(hasActual, hasPlanned);
  }

  public final String from()
  {
    String a = IRSQuery.ALL_ACTUALS + " " + TargetJoin.ACTUAL_ALIAS;
    String p = IRSQuery.PLANNED_AREA + " " + TargetJoin.PLANNED_ALIAS;

    // Area targets work as follows: limit the planned targets by the universal columns,
    // but sum the actual targets for all entities beneath the planned target geo entity.
    if (hasPlanned)
    {
      String sql = "";

      sql += a + " FULL OUTER JOIN " + p + " \n";
      sql += "ON " + TargetJoin.PLANNED_ALIAS + "." + Alias.GEO_ENTITY + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + Alias.GEO_ENTITY + " \n";
      sql += "ON " + TargetJoin.PLANNED_ALIAS + "." + Alias.TARGET_WEEK + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + Alias.TARGET_WEEK + " \n";
      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.DISEASE + " = " + TargetJoin.ACTUAL_ALIAS
          + "." + Alias.DISEASE + " \n";
      
      String geoTable = MdEntityDAO.getMdEntityDAO(GeoEntity.CLASS).getTableName();
     
      sql += "INNER JOIN "+geoTable+" geo \n";
      sql += "ON "+TargetJoin.PLANNED_ALIAS+"."+Alias.GEO_ENTITY+" = geo."+this.idCol+" \n";
      
      String universals = this.q.getUniversalsInCriteria();
      if(universals != null)
      {
        String type = QueryUtil.getColumnName(GeoEntity.getTypeMd());
        sql += "AND geo."+type+" IN("+universals+") \n";
      }
      
      String allpathsTable = MdEntityDAO.getMdEntityDAO(AllPaths.CLASS).getTableName();
      String childGeo = QueryUtil.getColumnName(AllPaths.getChildGeoEntityMd());
      String parentGeo = QueryUtil.getColumnName(AllPaths.getParentGeoEntityMd());
      
      sql += "INNER JOIN "+allpathsTable+" g ON \n";
      sql += TargetJoin.PLANNED_ALIAS+"." + Alias.GEO_ENTITY + " = g."+parentGeo+" \n";
      sql += "AND "+TargetJoin.ACTUAL_ALIAS+"." + Alias.GEO_ENTITY + " = g."+childGeo+" \n";
    
      return sql;
    }
    else
    {
      return a;
    }
  }

}
