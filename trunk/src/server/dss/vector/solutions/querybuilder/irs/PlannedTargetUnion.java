package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.Metadata;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.util.QueryUtil;

public abstract class PlannedTargetUnion extends AbstractTargetUnion implements Reloadable
{
  public abstract String setId(Alias alias);

  public abstract String setUniqueSprayId(Alias alias);
  
  public abstract String setUniquePlannedId(Alias alias);

  public abstract String setPlannedDate(Alias alias);
  
  public abstract String setSpraySeason(Alias alias);
  
  protected String keyName;
  
  public PlannedTargetUnion()
  {
    super();
    
    keyName = QueryUtil.getColumnName(Metadata.getKeyNameMd());
  }
  
  public String setSprayOperator(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setGeoEntity(Alias alias)
  {
    return setNULL(alias);
  }

  public String setParentGeoEntity(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setSprayOperatorDefaultLocale(Alias alias)
  {

    return setNULL(alias);
  }
  
  public String setSprayTeamDefaultLocale(Alias alias)
  {

    return setNULL(alias);
  }
  
  public String setSprayTeam(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setOperatorPlannedTarget(Alias alias)
  {

    return setNULL(alias);
  }
  
  public String setTeamPlannedTarget(Alias alias)
  {

    return setNULL(alias);
  }
  
  public String setDisease(Alias alias)
  {
    return set(IRSQB.PLANNED_TARGET_DISEASE, alias);
  }
  
  public String setAreaPlannedTarget(Alias alias)
  {
    return setNULL(alias);
  }
  
  public final String setTargetWeek(Alias alias)
  {
    return set(alias.getAlias(), alias);    
  }

}
