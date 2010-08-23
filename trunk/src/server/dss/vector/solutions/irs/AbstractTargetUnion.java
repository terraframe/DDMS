package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.general.Disease;


public abstract class AbstractTargetUnion implements IRSUnionIF, Reloadable
{
  public static final String EMPTY = "''";
  
  public static final String AS = "AS";
  
  public static final String NULL = "NULL";
  
  public static final String ZERO = "0";
  
  /**
   * The owning IRSQuery instance of this union.
   * The variable is protected for easy reading
   * by the subclasses.
   */
  protected IRSQuery q;
  
  protected int startDay;
  
  protected String diseaseId;

  public AbstractTargetUnion()
  {
    q = null;
    diseaseId = Disease.getCurrent().getId();
    startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
  }
  
  public final void setIRSQuery(IRSQuery irsQuery)
  {
    this.q = irsQuery;
  }
  
  public String set(String table, String column, Alias alias)
  {
    return set(table+"."+column, alias);
  }

  public String set(String value, Alias alias)
  {
    return value + " " + AS + " "+  alias;
  }
  
  public String setEmpty(Alias alias)
  {
    return set(EMPTY, alias);
  }
  
  public String setNULL(Alias alias)
  {
    return set(NULL, alias);
  }
  
  public String setZero(Alias alias)
  {
    return set(ZERO, alias);
  }

  public String setAggregationLevel(Alias alias)
  {

    return setNULL(alias);
  }

  public String setAreaPlannedTarget(Alias alias)
  {

    return setNULL(alias);
  }

  public String setBedNets(Alias alias)
  {

    return setNULL(alias);
  }

  public String setDisease(Alias alias)
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

  public String setOperatorPlannedTarget(Alias alias)
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

  public String setSprayDate(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayLeader(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayLeaderDefaultLocale(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayOperator(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayOperatorDefaultLocale(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSpraySeason(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayTeam(Alias alias)
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

  public String setTeamPlannedTarget(Alias alias)
  {

    return setNULL(alias);
  }

  public String setUsed(Alias alias)
  {

    return setNULL(alias);
  }

  public String setZoneActualTarget(Alias alias)
  {

    return setNULL(alias);
  }

  public String setZoneSuperVisor(Alias alias)
  {

    return setNULL(alias);
  }

  public String setZoneSuperVisorDefaultLocale(Alias alias)
  {

    return setNULL(alias);
  }
  
  public String from()
  {
    return EMPTY;
  }
  
  public String where()
  {
    return EMPTY;
  }
}
