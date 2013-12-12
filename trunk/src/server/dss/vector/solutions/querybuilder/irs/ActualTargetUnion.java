package dss.vector.solutions.querybuilder.irs;

import java.util.List;
import java.util.Set;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Condition;

import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;
import dss.vector.solutions.util.QueryUtil;

public abstract class ActualTargetUnion extends AbstractTargetUnion implements Reloadable
{
  protected String seasonDiseaseCol;
  
  public ActualTargetUnion(IRSQB irsQB)
  {
    super(irsQB);
    this.seasonDiseaseCol = QueryUtil.getColumnName(MalariaSeason.getDiseaseMd());
  }
  
  @Override
  protected final View getView()
  {
    return View.ALL_ACTUALS;
  }
  
  /**
   * Adds the season table join.
   * @param tables
   */
  protected void addSeasonTableDependency(List<TableDependency> tables)
  {
    tables.add(
        new TableDependency(this, malariaSeasonTable, new Alias[]{
            Alias.SPRAY_SEASON
        },
        "LEFT JOIN "+ malariaSeasonTable + " AS "+ malariaSeasonTable + " "+
          "ON "+abstractSprayTable+"."+sprayDateCol+" BETWEEN "+ malariaSeasonTable + "."+startDateCol+
          " AND "+ malariaSeasonTable + "."+endDateCol+" AND '"+this.irsQB.getDiseaseId()+"' = "+ malariaSeasonTable + "."+seasonDiseaseCol+" \n"
        ));
  }
  
  @Override
  public final String WHERE()
  {
    return "";
//    return this.getDateCriteriaSQL();
  }
  
  /**
   * Loads the dependencies for Levels 1, 2, and 3.
   */
  @Override
  public void loadDependencies()
  {
    super.loadDependencies();
    
    Set<Alias> selectAliases = this.irsQB.getSelectAliases();
    for(Alias select : selectAliases)
    {
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, select);
    }

    // add the default dependencies
    this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.ID);
    
    // The unique spray id is used whenever an aggregation is requested (eg, SUM) so that
    // a unique column can be specified as a discriminator to avoid cross-products.
    if(this.irsQB.needsSprayedUnits() || this.irsQB.hasPlannedTargets())
    {
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.UNIQUE_SPRAY_ID);
    }
    
    // START: audit fields
    if(selectAliases.contains(Alias.AUDIT_CREATE_DATE)  || selectAliases.contains(Alias.AUDIT_IMPORTED))
    {
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.CREATE_DATE);
    }

    if(selectAliases.contains(Alias.AUDIT_LAST_UPDATE_DATE))
    {
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.LAST_UPDATE_DATE);
    }

    if(selectAliases.contains(Alias.AUDIT_CREATED_BY))
    {
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.CREATED_BY);
    }
    
    if(selectAliases.contains(Alias.AUDIT_LAST_UPDATED_BY))
    {
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.LAST_UPDATED_BY);
    }
    // END: audit fields
    
    
    // sprayed units
    if(selectAliases.contains(Alias.SPRAYED_UNITS) || this.irsQB.needsSprayedUnits())
    {
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAYED_ROOMS, Alias.SPRAYED_STRUCTURES, Alias.SPRAYED_HOUSEHOLDS);
      this.irsQB.addRequiredView(View.INSECTICIDE_VIEW);
    }
    
    // unsprayed units
    boolean unitsUnsprayed = false;
    if(selectAliases.contains(Alias.UNITS_UNSPRAYED) || selectAliases.contains(Alias.REFILLS))
    {
      unitsUnsprayed = true;
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.ROOMS_UNSPRAYED, Alias.STRUCTURES_UNSPRAYED, Alias.HOUSEHOLDS_UNSPRAYED);
      this.irsQB.addRequiredView(View.INSECTICIDE_VIEW);
    }
    
    if(unitsUnsprayed || selectAliases.contains(Alias.ROOMS_UNSPRAYED))
    {
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.ROOMS, Alias.SPRAYED_ROOMS);
    }
    
    if(unitsUnsprayed || selectAliases.contains(Alias.HOUSEHOLDS_UNSPRAYED))
    {
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.HOUSEHOLDS, Alias.SPRAYED_HOUSEHOLDS);
    }
    
    if(unitsUnsprayed || selectAliases.contains(Alias.STRUCTURES_UNSPRAYED))
    {
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.STRUCTURES, Alias.SPRAYED_STRUCTURES);
    }
    
    // calcs
    boolean needsSpraySummary = false;
    if(selectAliases.contains(Alias.UNIT_APPLICATION_RATE))
    {
      needsSpraySummary = true;
      this.irsQB.addRequiredView(View.INSECTICIDE_VIEW);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.USED, Alias.SPRAYED_ROOMS_SHARE, Alias.SPRAYED_HOUSEHOLDS_SHARE, Alias.SPRAYED_STRUCTURES_SHARE);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAYED_ROOMS, Alias.SPRAYED_STRUCTURES, Alias.SPRAYED_HOUSEHOLDS);
    }
    
    if(selectAliases.contains(Alias.UNIT_APPLICATION_RATE_MG))
    {
      needsSpraySummary = true;
      this.irsQB.addRequiredView(View.INSECTICIDE_VIEW);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.USED, Alias.SPRAYED_ROOMS_SHARE, Alias.SPRAYED_HOUSEHOLDS_SHARE, Alias.SPRAYED_STRUCTURES_SHARE);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAYED_ROOMS, Alias.SPRAYED_STRUCTURES, Alias.SPRAYED_HOUSEHOLDS);
    }
    
    if(selectAliases.contains(Alias.UNIT_APPLICATION_RATIO))
    {
      needsSpraySummary = true;
      this.irsQB.addRequiredView(View.INSECTICIDE_VIEW);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.USED, Alias.SPRAYED_ROOMS_SHARE, Alias.SPRAYED_HOUSEHOLDS_SHARE, Alias.SPRAYED_STRUCTURES_SHARE);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAYED_ROOMS, Alias.SPRAYED_STRUCTURES, Alias.SPRAYED_HOUSEHOLDS);
    }
    
    
    if(selectAliases.contains(Alias.UNIT_OPERATIONAL_COVERAGE))
    {
      needsSpraySummary = true;
      this.irsQB.addRequiredView(View.INSECTICIDE_VIEW);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.USED, Alias.ROOMS, Alias.HOUSEHOLDS, Alias.STRUCTURES);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAYED_ROOMS, Alias.SPRAYED_STRUCTURES, Alias.SPRAYED_HOUSEHOLDS);
    }
    
    
    if(selectAliases.contains(Alias.CALCULATED_ROOMS_SPRAYED))
    {
      needsSpraySummary = true;
      this.irsQB.addRequiredView(View.INSECTICIDE_VIEW);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.USED, Alias.ROOMS, Alias.HOUSEHOLDS, Alias.STRUCTURES);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAYED_ROOMS, Alias.SPRAYED_STRUCTURES, Alias.SPRAYED_HOUSEHOLDS);
    }
    
    
    if(selectAliases.contains(Alias.CALCULATED_STRUCTURES_SPRAYED))
    {
      needsSpraySummary = true;
      this.irsQB.addRequiredView(View.INSECTICIDE_VIEW);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.USED, Alias.ROOMS, Alias.HOUSEHOLDS, Alias.STRUCTURES);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAYED_ROOMS, Alias.SPRAYED_STRUCTURES, Alias.SPRAYED_HOUSEHOLDS);
    }
    
    
    if(selectAliases.contains(Alias.CALCULATED_HOUSEHOLDS_SPRAYED))
    {
      needsSpraySummary = true;
      this.irsQB.addRequiredView(View.INSECTICIDE_VIEW);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.USED, Alias.ROOMS, Alias.HOUSEHOLDS, Alias.STRUCTURES);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAYED_ROOMS, Alias.SPRAYED_STRUCTURES, Alias.SPRAYED_HOUSEHOLDS);
    }

    
    // spray summary view
    if(needsSpraySummary)
    {
      this.irsQB.addRequiredView(View.SPRAY_SUMMARY_VIEW);
    }
    
    if(selectAliases.contains(Alias.DATEGROUP_SEASON) ||
       selectAliases.contains(Alias.DATEGROUP_YEAR) || 
       selectAliases.contains(Alias.DATEGROUP_QUARTER) || 
       selectAliases.contains(Alias.DATEGROUP_MONTH) || 
       selectAliases.contains(Alias.DATEGROUP_EPIYEAR) || 
       selectAliases.contains(Alias.DATEGROUP_EPIWEEK) || 
       selectAliases.contains(Alias.RATIO) || 
       selectAliases.contains(Alias.COUNT) 
        )
    {
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAY_DATE, Alias.DISEASE); 
    }
  }
  
  @Override
  protected List<String> COMMENTS()
  {
    List<String> comments = super.COMMENTS();
    
    comments.add("LEVEL: "+this.getLevel());
    
    return comments;
  }
  
  protected abstract String getLevel();
  
  public final String setAggregationLevel(Alias alias)
  {
    return set("'"+this.getLevel()+"'", alias);
  }
  
  public final String setSprayLeaderDefaultLocale(Alias alias)
  {
    return set(LEADER_MEMBER+"."+memberIdCol+" || ' - ' || "
      +LEADER_PERSON+"."+firstNameCol+" || ' ' || "+LEADER_PERSON+"."+lastNameCol, alias);
  }
  
  public final String setZoneSuperVisorDefaultLocale(Alias alias)
  {
    return set(SUPERVISOR_PERSON+"." + firstNameCol + " || ' ' || "+SUPERVISOR_PERSON+"." + lastNameCol, alias);
  }
  
  public String setSprayOperator(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setSprayTeam(Alias alias)
  {
    return setNULL(alias);
  }

  public String setBedNets(Alias alias)
  {

    return setNULL(alias);
  }

  public String setHouseholdId(Alias alias)
  {

    return setNULL(alias);
  }

  public String setHouseholdUnsprayed(Alias alias)
  {

    return setNULL(alias);
  }

  public String setHouseholds(Alias alias)
  {

    return setNULL(alias);
  }

  public String setId(Alias alias)
  {

    return setNULL(alias);
  }
  
  public String setLocked(Alias alias)
  {

    return setNULL(alias);
  }

  public String setOperatorActualTarget(Alias alias)
  {

    return setNULL(alias);
  }

  public String setOther(Alias alias)
  {

    return setNULL(alias);
  }
  
  public String setWrongSurface(Alias alias)
  {

    return setNULL(alias);
  }

  public String setPeople(Alias alias)
  {

    return setNULL(alias);
  }

  public String setPrevSprayedHouseholds(Alias alias)
  {

    return setNULL(alias);
  }

  public String setPrevSprayedStructures(Alias alias)
  {

    return setNULL(alias);
  }

  public String setReceived(Alias alias)
  {

    return setNULL(alias);
  }

  public String setRefills(Alias alias)
  {

    return setNULL(alias);
  }

  public String setRefused(Alias alias)
  {

    return setNULL(alias);
  }

  public String setReturned(Alias alias)
  {

    return setNULL(alias);
  }

  public String setRoomUnsprayed(Alias alias)
  {

    return setNULL(alias);
  }

  public String setRooms(Alias alias)
  {

    return setNULL(alias);
  }

  public String setRoomsWithBedNets(Alias alias)
  {

    return setNULL(alias);
  }
  
  public String setSprayTeamDefaultLocale(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayedHouseholds(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayedHouseholdsShare(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayedRooms(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayedRoomsShare(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayedStructures(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayedStructuresShare(Alias alias)
  {

    return setNULL(alias);
  }

  public String setStructureId(Alias alias)
  {

    return setNULL(alias);
  }

  public String setStructureUnsprayed(Alias alias)
  {

    return setNULL(alias);
  }

  public String setStructures(Alias alias)
  {

    return setNULL(alias);
  }

  public String setTeamActualTarget(Alias alias)
  {

    return setNULL(alias);
  }

  public String setUsed(Alias alias)
  {

    return setNULL(alias);
  }

  public abstract String setDisease(Alias alias);
  
  
  public final String setCreateDate(Alias alias)
  {
    return set(this.abstractSprayTable, this.createDateCol, alias);
  }

  public final String setLastUpdateDate(Alias alias)
  {
    return set(this.abstractSprayTable, this.lastUpdateDateCol, alias);
  }
  
  public final String setCreatedBy(Alias alias)
  {
    return set(this.abstractSprayTable, this.createdByCol, alias);
  }

  public final String setLastUpdatedBy(Alias alias)
  {
    return set(this.abstractSprayTable, this.lastUpdatedByCol, alias);
  }
  
  public final String setSprayDate(Alias alias)
  {
    return set(this.sprayDateCol, alias);
  }

  public final String setSpraySeason(Alias alias)
  {
    return set(malariaSeasonTable, idCol, alias);
  };
  
  public final String setGeoEntity(Alias alias)
  {
    return set(this.geoEntityCol, alias);
  }
  
  public final String setSprayMethod(Alias alias)
  {
    return set(this.sprayMethodCol, alias);
  }
  
  public final String setSurfaceType(Alias alias)
  {
    return set(this.surfaceTypeCol, alias);
  }
  
  public final String setBrand(Alias alias)
  {
    return set(this.brandCol, alias);
  }
  
  public final String setTargetWeek(Alias alias)
  {
    return set("get_epiWeek_from_Date("+abstractSprayTable+"."+sprayDateCol+", "+this.irsQB.getStartDay()+")", alias); 
  }

  public abstract String setUniqueSprayId(Alias alias);
  
  public final String setUniquePlannedId(Alias alias)
  {
    return this.setNULL(alias);
  }
  
  public String setSprayOperatorDefaultLocale(Alias alias)
  {
    return set(OPERATOR_MEMBER + "."+memberIdCol+" || ' - ' || "+OPERATOR_PERSON+"."+firstNameCol+
        " || ' ' || "+OPERATOR_PERSON+"."+lastNameCol, alias);
  }
  
  public String setSprayOperatorPersonId(Alias alias)
  {
    return set(OPERATOR_PERSON, this.identifierCol, alias);
  }

  public String setSprayOperatorBirthdate(Alias alias)
  {
    return set(OPERATOR_PERSON, this.birthdateCol, alias);
  }

  public String setSprayOperatorSex(Alias alias)
  {
    return set(OPERATOR_PERSON, this.sexCol, alias);
  }

  public String setSprayOperatorPerson(Alias alias)
  {
    return set(OPERATOR_PERSON, this.idCol, alias);
  }

  public final String setSprayLeaderPersonId(Alias alias)
  {
    return set(LEADER_PERSON, this.identifierCol, alias);
  }
  
  public final String setSprayLeaderBirthdate(Alias alias)
  {
    return set(LEADER_PERSON, this.birthdateCol, alias);
  }
  
  public final String setSprayLeaderSex(Alias alias)
  {
    return set(LEADER_PERSON, this.sexCol, alias);
  }
  
  public final String setSprayLeaderPerson(Alias alias)
  {
    return set(LEADER_PERSON, this.idCol, alias);
  }
  
  public final String setZoneSuperVisorPersonId(Alias alias)
  {
    return set(SUPERVISOR_PERSON, this.identifierCol, alias);
  }
  
  public final String setZoneSuperVisorBirthdate(Alias alias)
  {
    return set(SUPERVISOR_PERSON, this.birthdateCol, alias);
  }
  
  public final String setZoneSuperVisorSex(Alias alias)
  {
    return set(SUPERVISOR_PERSON, this.sexCol, alias);
  }
  
  public final String setZoneSuperVisorPerson(Alias alias)
  {
    return set(SUPERVISOR_PERSON, this.idCol, alias);
  }
  
  protected String getDateCriteriaSQL()
  {
    String oDOB = column(OPERATOR_PERSON, this.birthdateCol);
    String tDOB = column(LEADER_PERSON, this.birthdateCol);
    String sDOB = column(SUPERVISOR_PERSON, this.birthdateCol);
    
    Condition cond = this.irsQB.addDateCriteria(null, false, oDOB, tDOB, sDOB);
    if(cond != null)
    {
      return cond.getSQL() + " \n";
    }
    else
    {
      return "";
    }
  }
}
