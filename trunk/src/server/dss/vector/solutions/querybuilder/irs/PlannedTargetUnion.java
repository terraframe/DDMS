package dss.vector.solutions.querybuilder.irs;

import java.util.Set;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.Metadata;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.util.QueryUtil;

public abstract class PlannedTargetUnion extends AbstractTargetUnion implements Reloadable
{
  public abstract String setId(Alias alias);
  
  public abstract String setTarget(Alias alias);

  public abstract String setUniqueSprayId(Alias alias);
  
  public abstract String setUniquePlannedId(Alias alias);

  public abstract String setPlannedDate(Alias alias);
  
  public abstract String setSpraySeason(Alias alias);
  
  protected String keyName;
  
  public PlannedTargetUnion(IRSQB irsQB)
  {
    super(irsQB);
    
    keyName = QueryUtil.getColumnName(Metadata.getKeyNameMd());
  }
  
  @Override
  public void loadDependencies()
  {
    Set<Alias> selectAliases = this.irsQB.getSelectAliases();
    for(Alias select : selectAliases)
    {
      this.irsQB.addRequiredAlias(this.getView(), select);
    }
    
    this.irsQB.addRequiredAlias(this.getView(), Alias.ID, Alias.UNIQUE_PLANNED_ID, Alias.PLANNED_DATE);
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
  
  public String setSprayOperatorPersonId(Alias alias)
  {
    return setNULL(alias);
  }

  public String setSprayOperatorBirthdate(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setSprayOperatorSex(Alias alias)
  {
    return setNULL(alias);
  }

  public String setSprayOperatorPerson(Alias alias)
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
