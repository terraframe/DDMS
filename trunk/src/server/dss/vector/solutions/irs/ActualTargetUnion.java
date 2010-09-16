package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;



public abstract class ActualTargetUnion extends AbstractTargetUnion implements Reloadable
{
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

  public String setSprayLeaderDefaultLocale(Alias alias)
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

  public String setZoneSuperVisorDefaultLocale(Alias alias)
  {

    return setNULL(alias);
  }
  
  public abstract String setDisease(Alias alias);
  
  
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
}
