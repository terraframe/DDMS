package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

public abstract class PlannedTargetUnion extends AbstractTargetUnion implements Reloadable
{
  public abstract String setId(Alias alias);
  
  public abstract String setPlannedDate(Alias alias);
  
  public abstract String setSpraySeason(Alias alias);
  
  public String setSprayOperator(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setGeoEntity(Alias alias)
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

    return setNULL(alias);
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
