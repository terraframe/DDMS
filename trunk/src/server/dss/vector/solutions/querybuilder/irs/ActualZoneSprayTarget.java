package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.irs.Supervisor;
import dss.vector.solutions.irs.TeamSprayStatus;
import dss.vector.solutions.irs.ZoneSpray;
import dss.vector.solutions.util.QueryUtil;

/**
 * LEVEL 3
 */
public class ActualZoneSprayTarget extends ActualTargetUnion implements Reloadable
{
  private String teamLeaderCol;

  private String sprayTeamCol;

  private String diseaseCol;

  private String roomsCol;

  private String structuresCol;

  private String householdsCol;

  private String sprayedRoomsCol;

  private String sprayedStructuresCol;

  private String sprayedHouseholdsCol;

  private String prevSprayedStructuresCol;

  private String prevSprayedHouseholdsCol;

  private String peopleCol;

  private String bedNetsCol;

  private String roomsWithBedNetsCol;

  private String lockedCol;

  private String refusedCol;

  private String otherCol;

  private String receivedCol;

  private String usedCol;

  private String refillsCol;

  private String returnCol;

  private String zoneSprayTable;

  private String teamSprayStatusTable;

  private String sprayCol;

  private String supervisorCol;

  private String supervisorTable;

  private String supervisorPersonCol;
  
  private String geoEntityCol;
  
  private String targetCol;
  
  private String keyName;
  
  public ActualZoneSprayTarget()
  {
    super();

    supervisorTable = MdEntityDAO.getMdEntityDAO(Supervisor.CLASS).getTableName();
    supervisorPersonCol = QueryUtil.getColumnName(Supervisor.getPersonMd());

    MdEntityDAOIF zoneSprayMd = MdEntityDAO.getMdEntityDAO(ZoneSpray.CLASS);
    zoneSprayTable = zoneSprayMd.getTableName();
    diseaseCol = QueryUtil.getColumnName(ZoneSpray.getDiseaseMd());
    supervisorCol = QueryUtil.getColumnName(ZoneSpray.getSupervisorMd());
    geoEntityCol = QueryUtil.getColumnName(ZoneSpray.getGeoEntityMd());
    

    MdEntityDAOIF teamSprayStatusMd = MdEntityDAO.getMdEntityDAO(TeamSprayStatus.CLASS);
    teamSprayStatusTable = teamSprayStatusMd.getTableName();
    sprayCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.SPRAY);
    sprayTeamCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.SPRAYTEAM);
    teamLeaderCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.TEAMLEADER);
    receivedCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.RECEIVED);
    usedCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.USED);
    refillsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.REFILLS);
    returnCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.RETURNED);
    roomsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.ROOMS);
    structuresCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.STRUCTURES);
    householdsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.HOUSEHOLDS);
    sprayedRoomsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.SPRAYEDROOMS);
    sprayedStructuresCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.SPRAYEDSTRUCTURES);
    sprayedHouseholdsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.SPRAYEDHOUSEHOLDS);
    prevSprayedStructuresCol = QueryUtil.getColumnName(teamSprayStatusMd,
        TeamSprayStatus.PREVSPRAYEDSTRUCTURES);
    prevSprayedHouseholdsCol = QueryUtil.getColumnName(teamSprayStatusMd,
        TeamSprayStatus.PREVSPRAYEDHOUSEHOLDS);
    peopleCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.PEOPLE);
    bedNetsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.BEDNETS);
    roomsWithBedNetsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.ROOMSWITHBEDNETS);
    lockedCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.LOCKED);
    refusedCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.REFUSED);
    otherCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.OTHER);
    targetCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.TARGET);
    keyName = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.KEYNAME);
  }

  public String setId(Alias alias)
  {
    return set(this.zoneSprayTable, this.idCol, alias);
  }

  public String setAggregationLevel(Alias alias)
  {
    return set("'3'", alias);
  }
  
  @Override
  public String setTeamActualTarget(Alias alias)
  {
    return set(this.teamSprayStatusTable, this.targetCol, alias);
  }

  @Override
  public String setSprayTeamDefaultLocale(Alias alias)
  {
    return set(sprayTeamTable, teamIdCol, alias);
  }

  @Override
  public String setRooms(Alias alias)
  {
    return set(teamSprayStatusTable, roomsCol, alias);
  }

  @Override
  public String setStructures(Alias alias)
  {
    return set(teamSprayStatusTable, structuresCol, alias);
  }

  @Override
  public String setHouseholds(Alias alias)
  {
    return set(teamSprayStatusTable, householdsCol, alias);
  }

  @Override
  public String setSprayedRooms(Alias alias)
  {
    return set(teamSprayStatusTable, sprayedRoomsCol, alias);
  }

  @Override
  public String setSprayedStructures(Alias alias)
  {
    return set(teamSprayStatusTable, sprayedStructuresCol, alias);
  }

  @Override
  public String setSprayedHouseholds(Alias alias)
  {
    return set(teamSprayStatusTable, sprayedHouseholdsCol, alias);
  }

  @Override
  public String setPrevSprayedStructures(Alias alias)
  {
    return set(teamSprayStatusTable, prevSprayedStructuresCol, alias);
  }

  @Override
  public String setPrevSprayedHouseholds(Alias alias)
  {
    return set(teamSprayStatusTable, prevSprayedHouseholdsCol, alias);
  }

  @Override
  public String setPeople(Alias alias)
  {
    return set(teamSprayStatusTable, peopleCol, alias);
  }

  @Override
  public String setBedNets(Alias alias)
  {
    return set(teamSprayStatusTable, bedNetsCol, alias);
  }

  @Override
  public String setRoomsWithBedNets(Alias alias)
  {
    return set(teamSprayStatusTable, roomsWithBedNetsCol, alias);
  }

  @Override
  public String setLocked(Alias alias)
  {
    return set(teamSprayStatusTable, lockedCol, alias);
  }

  @Override
  public String setRefused(Alias alias)
  {
    return set(teamSprayStatusTable, refusedCol, alias);
  }

  @Override
  public String setOther(Alias alias)
  {
    return set(teamSprayStatusTable, otherCol, alias);
  }

  @Override
  public String setDisease(Alias alias)
  {
    return set(zoneSprayTable, diseaseCol, alias);
  }

  @Override
  public String setReceived(Alias alias)
  {
    return set(teamSprayStatusTable, receivedCol, alias);
  }

  @Override
  public String setUsed(Alias alias)
  {
    return set(teamSprayStatusTable, usedCol, alias);
  }

  @Override
  public String setRefills(Alias alias)
  {
    return set(teamSprayStatusTable, refillsCol, alias);
  }

  @Override
  public String setReturned(Alias alias)
  {
    return set(teamSprayStatusTable, returnCol, alias);
  }

  @Override
  public String setRoomUnsprayed(Alias alias)
  {
    return set("(" + roomsCol + " - " + sprayedRoomsCol + ")", alias);
  }

  @Override
  public String setStructureUnsprayed(Alias alias)
  {
    return set("(" + structuresCol + " - " + sprayedStructuresCol + ")", alias);
  }

  @Override
  public String setHouseholdUnsprayed(Alias alias)
  {
    return set("(" + householdsCol + " - " + sprayedHouseholdsCol + ")", alias);
  }

  @Override
  public String setSprayedRoomsShare(Alias alias)
  {
    return set("1", alias);
  }

  @Override
  public String setSprayedStructuresShare(Alias alias)
  {
    return set("1", alias);
  }

  @Override
  public String setSprayedHouseholdsShare(Alias alias)
  {
    return set("1", alias);
  }
  
  /**
   * Level 3 doesn't have operators.
   */
  @Override
  public String setSprayOperatorDefaultLocale(Alias alias)
  {
    return setNULL(alias);
  }
  
  /**
   * Level 3 doesn't have operators.
   */
  @Override
  public String setSprayOperatorPersonId(Alias alias)
  {
    return setNULL(alias);
  }

  /**
   * Level 3 doesn't have operators.
   */
  @Override
  public String setSprayOperatorBirthdate(Alias alias)
  {
    return setNULL(alias);
  }

  /**
   * Level 3 doesn't have operators.
   */
  @Override
  public String setSprayOperatorSex(Alias alias)
  {
    return setNULL(alias);
  }

  /**
   * Level 3 doesn't have operators.
   */
  @Override
  public String setSprayOperatorPerson(Alias alias)
  {
    return setNULL(alias);
  }

  @Override
  public String from()
  {
    String from = "";
    
    // level 3 join on parent
    from += zoneSprayTable + " AS " + zoneSprayTable + " \n";
    from +="INNER JOIN "+ abstractSprayTable + " AS " + abstractSprayTable +" ON "+abstractSprayTable+"."+idCol+" = "+zoneSprayTable+"."+idCol+" \n";
    
    from += "LEFT JOIN " + teamSprayStatusTable + " AS "
        + teamSprayStatusTable + " ON \n";
    from += zoneSprayTable + "." + idCol + " = " + teamSprayStatusTable + "." + sprayCol + " \n";
    from += " LEFT JOIN "
        + supervisorTable + " " + supervisorTable + " ON " + supervisorTable + "." + idCol + " = "
        + zoneSprayTable + "." + supervisorCol + " \n";
    
    // join the team
    from += "INNER JOIN "+sprayTeamTable+" AS "+sprayTeamTable+" ON "+teamSprayStatusTable+"."+sprayTeamCol+" = "+sprayTeamCol+"."+idCol+" \n";
    
    // leader
    from += "LEFT JOIN "+teamMemberTable+" AS "+LEADER_MEMBER+" ON "+teamSprayStatusTable+"."+teamLeaderCol+" = "+LEADER_MEMBER+"."+idCol+" \n";
    from += "LEFT JOIN "+personTable +" AS "+LEADER_PERSON+" ON "+LEADER_MEMBER+"."+personCol+ " = "+LEADER_PERSON + "." + idCol+" \n";
    
    // supervisor
    from += "LEFT JOIN "+personTable+" AS "+SUPERVISOR_PERSON+ " ON " +SUPERVISOR_PERSON+"."+idCol
        + " = " +supervisorTable+"."+supervisorPersonCol+ " \n";
       
    
    from += "LEFT JOIN "+ malariaSeasonTable + " AS sprayseason ";

    String seasonDiseaseCol = QueryUtil.getColumnName(MalariaSeason.getDiseaseMd());
    from += "ON " + abstractSprayTable + "." + sprayDateCol + " BETWEEN sprayseason." + startDateCol
        + " AND sprayseason." + endDateCol + " AND '" + this.q.getDiseaseId() + "' = sprayseason."
        + seasonDiseaseCol + " \n";

    return from;
  }

  @Override
  public String where()
  {
    String where = "";
    where += "" + abstractSprayTable + ".id = " + zoneSprayTable + ".id \n";
    where += "AND " + zoneSprayTable + "." + diseaseCol + " = '" + this.q.getDiseaseId() + "' \n";

    return where;
  }

  @Override
  public String setUniqueSprayId(Alias alias)
  {
    String sql = this.teamSprayStatusTable+"."+this.keyName;
    return set(sql, alias);
  }
}
