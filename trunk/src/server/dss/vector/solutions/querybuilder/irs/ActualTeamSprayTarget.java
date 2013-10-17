package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.irs.OperatorSprayStatus;
import dss.vector.solutions.irs.SprayTeam;
import dss.vector.solutions.irs.Supervisor;
import dss.vector.solutions.irs.TeamSpray;
import dss.vector.solutions.util.QueryUtil;

/**
 * LEVEL 2
 */
public class ActualTeamSprayTarget extends ActualTargetUnion implements Reloadable
{
  private String teamSprayTable;
  private String teamLeaderCol;
  private String sprayTeamCol;
  private String diseaseCol;
  
  private String sprayCol;
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
  private String operSprayStatusTable;
  private String sprayOperatorCol;
  private String receivedCol;
  private String usedCol;
  private String refillsCol;
  private String returnCol;
  private String sprayTeamTable;
  private String targetCol;
  private String operTarget;
  private String keyName;
  private String supervisorCol;
  private String supervisorTable;
  private String supervisorPersonCol;
  
  public ActualTeamSprayTarget()
  {
    super();
    
    MdEntityDAOIF teamSprayMd = MdEntityDAO.getMdEntityDAO(TeamSpray.CLASS);
    this.teamSprayTable = teamSprayMd.getTableName();
    teamLeaderCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.TEAMLEADER);
    sprayTeamCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.SPRAYTEAM);
    diseaseCol = QueryUtil.getColumnName(TeamSpray.getDiseaseMd());
    targetCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.TARGET);
    supervisorCol = QueryUtil.getColumnName(TeamSpray.getSupervisorMd());
    
    MdEntityDAOIF operSprayStatusMd = MdEntityDAO.getMdEntityDAO(OperatorSprayStatus.CLASS);
    operSprayStatusTable = operSprayStatusMd.getTableName();
    sprayCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.SPRAY);
    sprayOperatorCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.SPRAYOPERATOR);
    receivedCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.RECEIVED);
    usedCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.USED);
    refillsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.REFILLS);
    returnCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.RETURNED);
    roomsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.ROOMS);
    structuresCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.STRUCTURES);
    householdsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.HOUSEHOLDS);
    sprayedRoomsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.SPRAYEDROOMS);
    sprayedStructuresCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.SPRAYEDSTRUCTURES);
    sprayedHouseholdsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.SPRAYEDHOUSEHOLDS);
    prevSprayedStructuresCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.PREVSPRAYEDSTRUCTURES);
    prevSprayedHouseholdsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.PREVSPRAYEDHOUSEHOLDS);
    peopleCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.PEOPLE);
    bedNetsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.BEDNETS);
    roomsWithBedNetsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.ROOMSWITHBEDNETS);
    lockedCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.LOCKED);
    refusedCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.REFUSED);
    otherCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.OTHER);
    operTarget = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.OPERATORTARGET);
    keyName = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.KEYNAME);
   
    supervisorTable = MdEntityDAO.getMdEntityDAO(Supervisor.CLASS).getTableName();
    supervisorPersonCol = QueryUtil.getColumnName(Supervisor.getPersonMd());
    
    MdEntityDAOIF sprayTeamMd = MdEntityDAO.getMdEntityDAO(SprayTeam.CLASS);
    sprayTeamTable = sprayTeamMd.getTableName();
  }
  
  public String setId(Alias alias)
  {
    return set(this.teamSprayTable, this.idCol, alias);
  }
  
  @Override
  public String setOperatorActualTarget(Alias alias)
  {
    return set(this.operSprayStatusTable, this.operTarget, alias);
  }
  
  public String setAggregationLevel(Alias alias)
  {
    return set("'2'", alias);
  }
  
  @Override
  public String setSprayTeam(Alias alias)
  {
    return set(this.teamSprayTable, this.sprayTeamCol, alias);
  }
  
  @Override
  public String setSprayTeamDefaultLocale(Alias alias)
  {
    return set(sprayTeamTable, teamIdCol, alias);
  }
  
  @Override
  public String setTeamActualTarget(Alias alias)
  {
    return set(teamSprayTable, targetCol, alias);
  }
  
  @Override
  public String setRooms(Alias alias)
  {
    return set(operSprayStatusTable, roomsCol, alias);
  }
  
  @Override
  public String setStructures(Alias alias)
  {
    return set(operSprayStatusTable, structuresCol, alias);
  }
  
  @Override
  public String setHouseholds(Alias alias)
  {
    return set(operSprayStatusTable, householdsCol, alias);
  }
  
  @Override
  public String setSprayedRooms(Alias alias)
  {
    return set(operSprayStatusTable, sprayedRoomsCol, alias);
  }
  
  @Override
  public String setSprayedStructures(Alias alias)
  {
    return set(operSprayStatusTable, sprayedStructuresCol, alias);
  }
  
  @Override
  public String setSprayedHouseholds(Alias alias)
  {
    return set(operSprayStatusTable, sprayedHouseholdsCol, alias);
  }
  
  @Override
  public String setPrevSprayedStructures(Alias alias)
  {
    return set(operSprayStatusTable, prevSprayedStructuresCol, alias);
  }

  @Override
  public String setPrevSprayedHouseholds(Alias alias)
  {
    return set(operSprayStatusTable, prevSprayedHouseholdsCol, alias);
  }
  
  @Override
  public String setPeople(Alias alias)
  {
    return set(operSprayStatusTable, peopleCol, alias);
  }
  
  @Override
  public String setBedNets(Alias alias)
  {
    return set(operSprayStatusTable, bedNetsCol, alias);
  }
  
  @Override
  public String setRoomsWithBedNets(Alias alias)
  {
    return set(operSprayStatusTable, roomsWithBedNetsCol, alias);
  }
  
  @Override
  public String setLocked(Alias alias)
  {
    return set(operSprayStatusTable, lockedCol, alias);
  }

  @Override
  public String setRefused(Alias alias)
  {
    return set(operSprayStatusTable, refusedCol, alias);
  }
  
  @Override
  public String setOther(Alias alias)
  {
    return set(operSprayStatusTable, otherCol, alias);
  }
  
  @Override
  public String setDisease(Alias alias)
  {
    return set(teamSprayTable, diseaseCol, alias);
  }
  
  @Override
  public String setReceived(Alias alias)
  {
    return set(operSprayStatusTable, receivedCol, alias);
  }
  
  @Override
  public String setUsed(Alias alias)
  {
    return set(operSprayStatusTable, usedCol, alias);
  }
  
  @Override
  public String setRefills(Alias alias)
  {
    return set(operSprayStatusTable, refillsCol, alias);
  }
  
  @Override
  public String setReturned(Alias alias)
  {
    return set(operSprayStatusTable, returnCol, alias);
  }
  
  @Override
  public String setRoomUnsprayed(Alias alias)
  {
    return set("("+roomsCol+" - "+sprayedRoomsCol+")", alias);
  }
  
  @Override
  public String setStructureUnsprayed(Alias alias)
  {
    return set("("+structuresCol+" - "+sprayedStructuresCol+")", alias);
  }
  
  @Override
  public String setHouseholdUnsprayed(Alias alias)
  {
    return set("("+householdsCol+" - "+sprayedHouseholdsCol+")", alias);
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
  
  @Override
  public String from()
  {
    String from = "";
    
    // Level 2 join on parent
    from += teamSprayTable + " AS "+teamSprayTable+" \n";
    from += "INNER JOIN "+abstractSprayTable+"" + " AS "+abstractSprayTable+" ON "+abstractSprayTable+"."+idCol+" = "+teamSprayTable+"."+idCol+" \n";

    // join the team
    from += "INNER JOIN "+sprayTeamTable+" AS "+sprayTeamTable+" ON "+teamSprayTable+"."+sprayTeamCol+" = "+sprayTeamCol+"."+idCol+" \n";
    
    // spray details
    from += "LEFT JOIN "+operSprayStatusTable + " AS "+operSprayStatusTable+" ON "+teamSprayTable+".id = "+operSprayStatusTable+"."+sprayCol+" \n";
    
    // operator person
    from += "LEFT JOIN "+teamMemberTable+"" + " AS "+OPERATOR_MEMBER+" ON "+operSprayStatusTable+"."+sprayOperatorCol+" = "+OPERATOR_MEMBER+".id \n";
    from += "LEFT JOIN "+personTable + " AS "+OPERATOR_PERSON+" ON "+OPERATOR_MEMBER+"."+personCol+" = "+OPERATOR_PERSON+"."+idCol+" \n";
    
    
    // leader person
    from += "LEFT JOIN "+teamMemberTable+" AS "+LEADER_MEMBER+" ON "+teamSprayTable+"."+teamLeaderCol+" = "+LEADER_MEMBER+"."+idCol+" \n";
    from += "LEFT JOIN "+personTable +" AS "+LEADER_PERSON+" ON "+LEADER_MEMBER+"."+personCol+ " = "+LEADER_PERSON + "." + idCol+" \n";
 
    
    // supervisor person
    from += "LEFT JOIN "
        + supervisorTable + " " + supervisorTable + " ON " + supervisorTable + "." + idCol + " = "
        + teamSprayTable + "." + supervisorCol + " \n";
    from += "LEFT JOIN "+personTable+ " AS "+SUPERVISOR_PERSON+ " ON "+ supervisorTable+"."+supervisorPersonCol+" = "+SUPERVISOR_PERSON+"."+idCol+" \n";
    
    
    // season
    from += "LEFT JOIN "+ malariaSeasonTable + " AS sprayseason ";
    String seasonDiseaseCol = QueryUtil.getColumnName(MalariaSeason.getDiseaseMd());
    from += "ON "+abstractSprayTable+"."+sprayDateCol+" BETWEEN sprayseason."+startDateCol+" AND sprayseason."+endDateCol+" AND '"+this.q.getDiseaseId()+"' = sprayseason."+seasonDiseaseCol+" \n";
    
    
    return from;
  }
  
  @Override
  public String where()
  {
    String where = "";
    where += " "+teamSprayTable+"."+diseaseCol+" = '"+this.q.getDiseaseId()+"' \n";
    
    return where;
  }
  
  @Override
  public String setUniqueSprayId(Alias alias)
  {
    String sql = this.operSprayStatusTable+"."+this.keyName;
    return set(sql, alias);
    //    return set(this.sprayOperatorCol, alias); old code, not unique enough
  }
}
