package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

public abstract class TargetJoin extends AbstractSprayProvider implements Reloadable
{
  public static final String ACTUAL_ALIAS = "a";
  public static final String PLANNED_ALIAS = "p";
  
  public TargetJoin(IRSQB irsQB)
  {
    super(irsQB);

  }
  
  @Override
  protected View getView()
  {
    return View.SPRAY_VIEW;
  }
  
  @Override
  public void loadDependencies()
  {
//    Set<Alias> selectAliases = this.irsQB.getSelectAliases();
    
    // need to do anything here?
  }
  
  
  protected String dateGroupJoin(String joinTable, String joinDate)
  {
    if(this.irsQB.getDategroups().size() > 0)
    {
      String v = View.DATE_GROUPS.getView();
      return " LEFT JOIN "+v+" ON "+v+"."+Alias.SPRAY_DATE+" = "+joinTable+"."+joinDate + " \n";
    }
    else
    {
      return "";
    }
  }
  
  public String getDateGroupAlias()
  {
    return this.getView().getView()+"_"+View.DATE_GROUPS.getView();
  }
  
  public String setDategroupEpiWeek(Alias alias)
  {
    if(this.hasActual && this.hasPlanned)
    {
      return this.rawSwap(View.DATE_GROUPS.getView()+"."+alias, this.getDateGroupAlias()+"."+alias);
    }
    else if(this.hasPlanned)
    {
      return this.set(this.getDateGroupAlias(), alias, alias);
    }
    else
    {
      return this.set(View.DATE_GROUPS.getView(), alias, alias);
    }
  }
  
  public String setDategroupSeason(Alias alias)
  {
    if(this.hasActual && this.hasPlanned)
    {
      return this.rawSwap(View.DATE_GROUPS.getView()+"."+alias, this.getDateGroupAlias()+"."+alias);
    }
    else if(this.hasPlanned)
    {
      return this.set(this.getDateGroupAlias(), alias, alias);
    }
    else
    {
      return this.set(View.DATE_GROUPS.getView(), alias, alias);
    }
  }
  
  public String setDategroupQuarter(Alias alias)
  {
    if(this.hasActual && this.hasPlanned)
    {
      return this.rawSwap(View.DATE_GROUPS.getView()+"."+alias, this.getDateGroupAlias()+"."+alias);
    }
    else if(this.hasPlanned)
    {
      return this.set(this.getDateGroupAlias(), alias, alias);
    }
    else
    {
      return this.set(View.DATE_GROUPS.getView(), alias, alias);
    }
  }
  
  public String setDategroupMonth(Alias alias)
  {
    if(this.hasActual && this.hasPlanned)
    {
      return this.rawSwap(View.DATE_GROUPS.getView()+"."+alias, this.getDateGroupAlias()+"."+alias);
    }
    else if(this.hasPlanned)
    {
      return this.set(this.getDateGroupAlias(), alias, alias);
    }
    else
    {
      return this.set(View.DATE_GROUPS.getView(), alias, alias);
    }
  }
  
  public String setDategroupEpiYear(Alias alias)
  {
    if(this.hasActual && this.hasPlanned)
    {
      return this.rawSwap(View.DATE_GROUPS.getView()+"."+alias, this.getDateGroupAlias()+"."+alias);
    }
    else if(this.hasPlanned)
    {
      return this.set(this.getDateGroupAlias(), alias, alias);
    }
    else
    {
      return this.set(View.DATE_GROUPS.getView(), alias, alias);
    }
  }
  
  public String setDategroupYear(Alias alias)
  {
    if(this.hasActual && this.hasPlanned)
    {
      return this.rawSwap(View.DATE_GROUPS.getView()+"."+alias, this.getDateGroupAlias()+"."+alias);
    }
    else if(this.hasPlanned)
    {
      return this.set(this.getDateGroupAlias(), alias, alias);
    }
    else
    {
      return this.set(View.DATE_GROUPS.getView(), alias, alias);
    }
  }
  
  public String rawSwap(String a, String b)
  {
    return "(CASE WHEN "+a+" IS NOT NULL THEN "+a+" ELSE "+b+" END)";
  }
  
  public String caseSwap(String alias, String other)
  {
    if(hasActual && hasPlanned)
    {
      return "(CASE WHEN "+ACTUAL_ALIAS+"."+alias+" IS NOT NULL THEN "+ACTUAL_ALIAS+"."+alias+" ELSE "+PLANNED_ALIAS+"."+other+" END)";
    }
    else if(hasActual)
    {
      return ACTUAL_ALIAS+"."+alias;
    }
    else
    {
      return PLANNED_ALIAS+"."+alias;
    }
  }
  
  public String caseSwap(Alias alias, Alias other)
  {
    return caseSwap(alias.getAlias(), other.getAlias());
  }
  
  public String caseSwap(Alias alias)
  {
    return caseSwap(alias, alias);
  }
  
  public String setPlannedDate(Alias alias)
  {
    return hasPlanned ? set(PLANNED_ALIAS, alias, alias) : setNULL(alias);
  }
  
  public String setSprayOperator(Alias alias)
  {
    return caseSwap(alias);
  }
  
  public String setSprayTeam(Alias alias)
  {
    return caseSwap(alias);
  }

  public String setAggregationLevel(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setGeoEntity(Alias alias)
  {
    return caseSwap(alias);
  }
  
  public String setParentGeoEntity(Alias alias)
  {
    return hasPlanned ? set(PLANNED_ALIAS, Alias.GEO_ENTITY, alias) : setNULL(alias);
  }
  
  public String setSprayMethod(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }
  
  public String setTargetWeek(Alias alias)
  {
    return caseSwap(alias);
  }
  
  public String setBrand(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }
  
  public String setSurfaceType(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }
  
  public String setAreaPlannedTarget(Alias alias)
  {

    return hasPlanned ? set(PLANNED_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setBedNets(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setDisease(Alias alias)
  {

    return caseSwap(alias);
  }

  public String setHouseholdId(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setHouseholdUnsprayed(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setHouseholds(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setId(Alias alias)
  {

    return caseSwap(alias);
  }
  
  public String setUniqueSprayId(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }
  
  public String setUniquePlannedId(Alias alias)
  {
    return hasPlanned ? set(PLANNED_ALIAS, alias, alias) : setNULL(alias);
  }
  
  public String setCreateDate(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setLastUpdateDate(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setCreatedBy(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setLastUpdatedBy(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setLocked(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setOperatorActualTarget(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setOperatorPlannedTarget(Alias alias)
  {

    return hasPlanned ? set(PLANNED_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setOther(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }
  
  public String setWrongSurface(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setPeople(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setPrevSprayedHouseholds(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setPrevSprayedStructures(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setReceived(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setRefills(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setRefused(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setReturned(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setRoomUnsprayed(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setRooms(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setRoomsWithBedNets(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setSprayDate(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setSprayLeaderDefaultLocale(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setSprayOperatorDefaultLocale(Alias alias)
  {
    return caseSwap(alias);
  }
  
  public String setSprayOperatorPersonId(Alias alias)
  {
    return caseSwap(alias);
  }

  public String setSprayOperatorBirthdate(Alias alias)
  {
    return caseSwap(alias);
  }
  
  public String setSprayOperatorSex(Alias alias)
  {
    return caseSwap(alias);
  }

  public String setSprayOperatorPerson(Alias alias)
  {
    return caseSwap(alias);
  }

  public String setSpraySeason(Alias alias)
  {

    return caseSwap(alias);
  }

  // public String setSprayTeam(Alias alias)
  // {
  //
  // return setNULL(alias);
  // }

  public String setSprayTeamDefaultLocale(Alias alias)
  {

    return caseSwap(alias);
  }

  public String setSprayedHouseholds(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setSprayedHouseholdsShare(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setSprayedRooms(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setSprayedRoomsShare(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setSprayedStructures(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setSprayedStructuresShare(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setStructureId(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setStructureUnsprayed(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setStructures(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setTeamActualTarget(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setTeamPlannedTarget(Alias alias)
  {

    return hasPlanned ? set(PLANNED_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setUsed(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setZoneSuperVisorDefaultLocale(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setSprayLeaderPersonId(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }
  
  public String setSprayLeaderBirthdate(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }
  
  public String setSprayLeaderSex(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }
  
  public String setSprayLeaderPerson(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setZoneSuperVisorPersonId(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }
  
  public String setZoneSuperVisorBirthdate(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }
  
  public String setZoneSuperVisorSex(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }
  
  public String setZoneSuperVisorPerson(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }
}
