package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
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
    return set(spraySeason, alias);
  }
  
  @Override
  public final String setId(Alias alias)
  {
    return set(IRSQuery.ORIGINAL_ID, alias);
  }
  
  @Override
  public final String setPlannedDate(Alias alias)
  {
    return set(Alias.PLANNED_DATE.getAlias(), alias);
  }

  @Override
  public String setDisease(Alias alias)
  {
    return set(diseaseCol, alias);
  }

  @Override
  public String setGeoEntity(Alias alias)
  {
    return set(IRSQuery.ORIGINAL_ID, alias);
  }
  
  @Override
  public String setAreaPlannedTarget(Alias alias)
  {
    return set(IRSQuery.WEEKLY_TARGET, alias);
  }


  @Override
  public String from()
  {
    String sql = "--Planned Area Target\n";

    String geoTable = MdEntityDAO.getMdEntityDAO(GeoEntity.CLASS).getTableName();
    
    sql += IRSQuery.ROLLUP_RESULTS +" rr INNER JOIN "+geoTable+" g ON g."+idCol+" = rr."+IRSQuery.ORIGINAL_ID+" \n";
    
    // Attempt to restrict by the universals. We join based on the ontological structure of the universal
    // tree and not by exact matches on the universal type. For example, if Settlement is located within District
    // and District is chosen as universal criteria, then we gather all Settlements within Districts and Districts as well
    // (as opposed to gathering only Districts).
    String universals = this.q.getUniversalsInCriteria();
    
    if (universals != null)
    {
      String type = QueryUtil.getColumnName(GeoEntity.getTypeMd());
      String in = "IN(" + universals + ") \n";
      
      sql += "INNER JOIN "+GeoHierarchy.ALLPATHS_VIEW+" ap ON g."+type+" = ap."+GeoHierarchy.ALLPATHS_CHILD_TYPE+" \n";
      sql += " AND (ap."+GeoHierarchy.ALLPATHS_ROOT_TYPE +" "+in + " OR ap."+GeoHierarchy.ALLPATHS_CHILD_TYPE+" "+in+") \n";
    }
    
    return sql;
  }
}
