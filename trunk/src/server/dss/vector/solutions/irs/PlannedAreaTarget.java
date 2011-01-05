package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdEntity;

import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

public class PlannedAreaTarget extends PlannedTargetUnion implements Reloadable
{
  private static final String GTV_ALIAS = "gt";
  
  private String childGeoEntity;
  private String parentGeoEntity;
  
  public PlannedAreaTarget()
  {
    super();
    
    childGeoEntity = QueryUtil.getColumnName(AllPaths.getChildGeoEntityMd());
    parentGeoEntity = QueryUtil.getColumnName(AllPaths.getParentGeoEntityMd());
  }
  
  @Override
  public String setSpraySeason(Alias alias)
  {
    return set(IRSQuery.MALARIA_SEASON, alias);
  }
  
  @Override
  public final String setId(Alias alias)
  {
    return setNULL(alias);
  }
  
  @Override
  public final String setPlannedDate(Alias alias)
  {
    return set(Alias.PLANNED_DATE.getAlias(), alias);
  }

  @Override
  public String setGeoEntity(Alias alias)
  {
    return set(parentGeoEntity, alias);
  }
  
  @Override
  public String setAreaPlannedTarget(Alias alias)
  {
    String sum = QueryConstants.SUM_AREA_TARGETS+"("+parentGeoEntity+", to_char("+IRSQuery.TARGET_WEEK+"-1, 'FM99'), "+Alias.DISEASE.getAlias()+", "+IRSQuery.MALARIA_SEASON+")";
    return set(sum, alias);
  }


  @Override
  public String from()
  {
    String sql = "--Planned Area Target\n";

    String geoTable = MdEntityDAO.getMdEntityDAO(AllPaths.CLASS).getTableName();
    
    sql += IRSQuery.GEO_TARGET_VIEW +" "+GTV_ALIAS+" INNER JOIN "+geoTable+" g ON g."+childGeoEntity+" = "+GTV_ALIAS+"."+this.q.getGeoEntity()+" \n";
    
    return sql;
  }
  
  @Override
  public String where()
  {
    String sql = "";
    
    // Attempt to restrict by the universals. We join based on the ontological structure of the universal
    // tree and not by exact matches on the universal type. For example, if Settlement is located within District
    // and District is chosen as universal criteria, then we gather all Settlements within Districts and Districts as well
    // (as opposed to gathering only Districts).
    String universal = this.q.getSmallestUniversal();
    
    if (universal != null)
    {
      String parentMd = QueryUtil.getColumnName(AllPaths.getParentUniversalMd());
      
      sql += parentMd + " = '" + MdEntity.getMdEntity(universal).getId() + "'";
    }
    
    sql += "GROUP BY "+parentGeoEntity+", "+Alias.PLANNED_DATE.getAlias()+", "
    +Alias.TARGET_WEEK.getAlias()+", "+IRSQuery.MALARIA_SEASON+", "+IRSQuery.PLANNED_TARGET_DISEASE+"\n";
    
    return sql;
  }
}
