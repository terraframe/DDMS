package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;



public abstract class ActualTargetUnion extends AbstractTargetUnion implements Reloadable
{
  
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

  public String setAggregationLevel(Alias alias)
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
    return set("sprayseason", idCol, alias);
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
    return set("get_epiWeek_from_Date("+abstractSprayTable+"."+sprayDateCol+", "+this.q.getStartDay()+")", alias); 
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
}
