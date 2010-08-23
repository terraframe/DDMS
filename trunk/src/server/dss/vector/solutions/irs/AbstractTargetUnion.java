package dss.vector.solutions.irs;


public abstract class AbstractTargetUnion implements IRSUnionIF
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

  public AbstractTargetUnion()
  {
    q = null;
  }
  
  public final void setIRSQuery(IRSQuery irsQuery)
  {
    this.q = irsQuery;
  }
  
  public String set(String table, String column, IRSUnionIF.ALIAS alias)
  {
    return set(table+"."+column, alias);
  }

  public String set(String value, IRSUnionIF.ALIAS alias)
  {
    return value + " " + AS + " "+  alias;
  }
  
  public String setEmpty(IRSUnionIF.ALIAS alias)
  {
    return set(EMPTY, alias);
  }
  
  public String setNULL(IRSUnionIF.ALIAS alias)
  {
    return set(NULL, alias);
  }
  
  public String setZero(IRSUnionIF.ALIAS alias)
  {
    return set(ZERO, alias);
  }

  public String setAggregationLevel(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setAreaPlannedTarget(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setBedNets(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setDisease(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setHouseholdId(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setHouseholdUnsprayed(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setHouseholds(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setId(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setLocked(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setOperatorActualTarget(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setOperatorPlannedTarget(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setOther(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setPeople(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setPrevSprayedHouseholds(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setPrevSprayedStructures(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setReceived(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setRefills(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setRefused(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setReturn(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setRoomUnsprayed(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setRooms(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setRoomsWithBedNets(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setSprayDate(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setSprayLeader(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setSprayLeaderDefaultLocale(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setSprayOperator(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setSprayOperatorDefaultLocale(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setSpraySeason(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setSprayTeam(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setSprayTeamDefaultLocale(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setSprayedHouseholds(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setSprayedHouseholdsShare(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setSprayedRooms(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setSprayedRoomsShare(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setSprayedStructures(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setSprayedStructuresShare(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setStructureId(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setStructureUnsprayed(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setStructures(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setTeamActualTarget(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setTeamPlannedTarget(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setUsed(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setZoneActualTarget(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setZoneSuperVisor(ALIAS alias)
  {

    return setNULL(alias);
  }

  public String setZoneSuperVisorDefaultLocale(ALIAS alias)
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
