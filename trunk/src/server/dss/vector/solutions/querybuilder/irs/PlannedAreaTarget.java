package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdEntity;

import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.querybuilder.IRSQB;
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
    return set(IRSQB.MALARIA_SEASON, alias);
  }
  
  @Override
  public final String setId(Alias alias)
  {
    return setNULL(alias);
  }
  
  @Override
  public String setTarget(Alias alias)
  {
    return setNULL(alias);
  }

  @Override
  public final String setUniqueSprayId(Alias alias)
  {
    return setNULL(alias);
  }
  
  @Override
  public final String setPlannedDate(Alias alias)
  {
    return set(Alias.PLANNED_DATE.getAlias(), alias);
  }
  
  @Override
  public final String setUniquePlannedId(Alias alias)
  {
    return set(GTV_ALIAS, keyName, alias);
  }

  @Override
  public String setGeoEntity(Alias alias)
  {
    return set(parentGeoEntity, alias);
  }
  
  @Override
  public String setAreaPlannedTarget(Alias alias)
  {
    // #2865 - don't sum anything here. Do it in the final select
    //String sum = QueryConstants.SUM_AREA_TARGETS+"("+parentGeoEntity+", to_char("+IRSQB.TARGET_WEEK+"-1, 'FM99'), "+Alias.DISEASE.getAlias()+", "+IRSQB.MALARIA_SEASON+")";
    //return set(sum, alias);
    return setNULL(alias);
  }


  @Override
  public String from()
  {
    String geoTable = MdEntityDAO.getMdEntityDAO(AllPaths.CLASS).getTableName();
    
    String sql = IRSQB.GEO_TARGET_VIEW +" "+GTV_ALIAS+" INNER JOIN "+geoTable+" g ON g."+childGeoEntity+" = "+GTV_ALIAS+"."+this.q.getGeoEntity()+" \n";
    
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
    sql += "\n";
    sql += "GROUP BY "+GTV_ALIAS+"."+this.keyName+", "+parentGeoEntity+", "+Alias.PLANNED_DATE.getAlias()+", "
    +Alias.TARGET_WEEK.getAlias()+", "+IRSQB.MALARIA_SEASON+", "+IRSQB.PLANNED_TARGET_DISEASE+"\n";
    
    return sql;
  }
}
