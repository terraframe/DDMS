package dss.vector.solutions.querybuilder.irs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Selectable;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

public abstract class TargetJoin extends AbstractSprayProvider implements Reloadable
{
  public static final String ACTUAL_ALIAS  = "a";

  public static final String PLANNED_ALIAS = "p";

  protected List<Alias>      groupBy;

  protected Set<Alias>       agg;

  public TargetJoin(IRSQB irsQB)
  {
    super(irsQB);
    groupBy = new LinkedList<Alias>();
    agg = new HashSet<Alias>();
  }

  protected String GROUP_BY()
  {
    if (this.groupBy.size() > 0)
    {
      List<String> groupStr = new LinkedList<String>();
      for (Alias a : this.groupBy)
      {
        // Spray date needs to be qualified with a prefix because it's
        // ambiguous in some cases.
        String groupCol;
        if (Alias.SPRAY_DATE.equals(a))
        {
          groupCol = ACTUAL_ALIAS + "." + a;
        }
        else
        {
          groupCol = a.getAlias();
        }

        groupStr.add(groupCol);
      }

      return "\n GROUP BY \n" + StringUtils.join(groupStr, ",");
    }
    else
    {
      return "";
    }
  }

  // @Override
  // protected void preProcess(Alias alias)
  // {
  // if(this.irsQB.hasChildAggregate(alias) && this.irsQB.isAggregate(alias))
  // {
  // this.agg.add(alias);
  // }
  // else
  // {
  // this.groupBy.add(alias);
  // }
  // }

  @Override
  public String postProcess(Alias alias, String sql)
  {
    if (this.irsQB.hasParentAggregate(alias))
    {
      this.groupBy.add(alias);
      return sql + " /*[parent agg]*/";
    }
    else if (this.irsQB.hasChildAggregate(alias))
    {
      if (this.irsQB.isAggregate(alias))
      {
        Selectable s = this.irsQB.get(alias);
        return set(s.getSQL(), alias) + " /*[child agg]*/";
      }
      else
      {
        // NOTE: This behavior is an assumption, but should hold
        // true when calculations require this field.
        return set("SUM(" + alias + ")", alias) + " /*[default agg]*/";

        // this.groupBy.add(alias);
        // return sql+" /*[child non-agg]*/";
      }
    }
    else
    {
      this.groupBy.add(alias);
      return sql + " /*[regular]*/";
    }
  }

  @Override
  protected View getView()
  {
    return View.SPRAY_VIEW;
  }

  @Override
  public void loadDependencies()
  {
    // HEY! THIS CODE NEVER GETS EXECUTED.
    // The reason is because the AreaJoin is used in the SprayView and this only gets new instanced when the SQL gets generated,
    // which is far too late for the dependencies to be loaded. If you want to modify the dependencies do it in the SprayView.
    
    // Set<Alias> selectAliases = this.irsQB.getSelectAliases();

    // need to do anything here?
    
    this.getIrsQB().addRequiredAlias(View.SPRAY_VIEW, Alias.AGGREGATION_LEVEL);
  }

  protected String dateGroupJoin(String joinTable, String joinDate)
  {
    if (this.irsQB.getDategroups().size() > 0)
    {
      String v = View.DATE_GROUPS.getView();
      return " LEFT JOIN " + v + " ON " + v + "." + Alias.SPRAY_DATE + " = " + joinTable + "."
          + joinDate + " \n";
    }
    else
    {
      return "";
    }
  }

  public String getDateGroupAlias()
  {
    return this.getView().getView() + "_" + View.DATE_GROUPS.getView();
  }

  public String setDategroupEpiWeek(Alias alias)
  {
    if (this.hasActual && this.hasPlanned)
    {
      return this.rawSwap(View.DATE_GROUPS.getView() + "." + alias, this.getDateGroupAlias() + "."
          + alias);
    }
    else if (this.hasPlanned)
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
    if (this.hasActual && this.hasPlanned)
    {
      return this.rawSwap(View.DATE_GROUPS.getView() + "." + alias, this.getDateGroupAlias() + "."
          + alias);
    }
    else if (this.hasPlanned)
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
    if (this.hasActual && this.hasPlanned)
    {
      return this.rawSwap(View.DATE_GROUPS.getView() + "." + alias, this.getDateGroupAlias() + "."
          + alias);
    }
    else if (this.hasPlanned)
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
    if (this.hasActual && this.hasPlanned)
    {
      return this.rawSwap(View.DATE_GROUPS.getView() + "." + alias, this.getDateGroupAlias() + "."
          + alias);
    }
    else if (this.hasPlanned)
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
    if (this.hasActual && this.hasPlanned)
    {
      return this.rawSwap(View.DATE_GROUPS.getView() + "." + alias, this.getDateGroupAlias() + "."
          + alias);
    }
    else if (this.hasPlanned)
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
    if (this.hasActual && this.hasPlanned)
    {
      return this.rawSwap(View.DATE_GROUPS.getView() + "." + alias, this.getDateGroupAlias() + "."
          + alias);
    }
    else if (this.hasPlanned)
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
    return "(CASE WHEN " + a + " IS NOT NULL THEN " + a + " ELSE " + b + " END)";
  }

  public String caseSwap(String alias, String other)
  {
    if (hasActual && hasPlanned)
    {
      return "(CASE WHEN " + ACTUAL_ALIAS + "." + alias + " IS NOT NULL THEN " + ACTUAL_ALIAS + "."
          + alias + " ELSE " + PLANNED_ALIAS + "." + other + " END)";
    }
    else if (hasActual)
    {
      return ACTUAL_ALIAS + "." + alias;
    }
    else
    {
      return PLANNED_ALIAS + "." + alias;
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
    if (this.getLevel() != null) {
      return set("'"+this.getLevel()+"'", alias);
    }
    
    if (hasActual && this.irsQB.getRequiredAlias(View.ALL_ACTUALS).contains(Alias.AGGREGATION_LEVEL)) {
      return set(ACTUAL_ALIAS, alias, alias);
    }
    
    return setNULL(alias);
  }
  
  public String getLevel() {
    return null;
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

  // Convert the epiweek into the target week.
  public String setTargetWeek(Alias alias)
  {
    if (this.hasActual && this.hasPlanned)
    {
      return set(
          this.rawSwap(View.DATE_GROUPS.getView() + "." + Alias.DATEGROUP_EPIWEEK,
              this.getDateGroupAlias() + "." + Alias.DATEGROUP_EPIWEEK), alias);
    }
    else if (this.hasPlanned)
    {
      return this.set(this.getDateGroupAlias(), Alias.DATEGROUP_EPIWEEK, alias);
    }
    else
    {
      return this.set(View.DATE_GROUPS.getView(), Alias.DATEGROUP_EPIWEEK, alias);
    }
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

    return hasPlanned ? set(PLANNED_ALIAS, alias, alias) : null;
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

  /*
   * public String setHouseholdUnsprayed(Alias alias) {
   * 
   * return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias); }
   */

  public String setHouseholds(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setId(Alias alias)
  {
    return caseSwap(alias);
  }

  /*
   * public String setUniqueSprayId(Alias alias) { return hasActual ?
   * set(ACTUAL_ALIAS, alias, alias) : setNULL(alias); }
   * 
   * public String setUniquePlannedId(Alias alias) { return hasPlanned ?
   * set(PLANNED_ALIAS, alias, alias) : setNULL(alias); }
   */

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

  public String setNozzlesUsed(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setPumpsUsed(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setVerandas(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setVerandasSprayed(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setVerandasLocked(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setVerandasRefused(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setVerandasOther(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }
  
  public String setNumberOfPeople(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setCattleSheds(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setCattleShedsSprayed(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setCattleShedsLocked(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setCattleShedsRefused(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setCattleShedsOther(Alias alias)
  {
    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setOperatorActualTarget(Alias alias)
  {

    return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias);
  }

  public String setOperatorPlannedTarget(Alias alias)
  {

    return hasPlanned ? set(PLANNED_ALIAS, alias, alias) : null;
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

  /*
   * public String setRoomUnsprayed(Alias alias) { return hasActual ?
   * set(ACTUAL_ALIAS, alias, alias) : setNULL(alias); }
   */

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

  /*
   * public String setStructureUnsprayed(Alias alias) {
   * 
   * return hasActual ? set(ACTUAL_ALIAS, alias, alias) : setNULL(alias); }
   */

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

    return hasPlanned ? set(PLANNED_ALIAS, alias, alias) : null;
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
