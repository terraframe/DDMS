package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.util.QueryUtil;

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
  private String teamIdCold;
  private String sprayTeamTable;
  private String targetCol;
  
  public ActualTeamSprayTarget()
  {
    super();
    
    MdEntityDAOIF teamSprayMd = MdEntityDAO.getMdEntityDAO(TeamSpray.CLASS);
    this.teamSprayTable = teamSprayMd.getTableName();
    teamLeaderCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.TEAMLEADER);
    sprayTeamCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.SPRAYTEAM);
    diseaseCol = QueryUtil.getColumnName(TeamSpray.getDiseaseMd());
    targetCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.TARGET);
    
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
   
    MdEntityDAOIF sprayTeamMd = MdEntityDAO.getMdEntityDAO(SprayTeam.CLASS);
    teamIdCold = QueryUtil.getColumnName(sprayTeamMd, SprayTeam.TEAMID);
    sprayTeamTable = sprayTeamMd.getTableName();
  }
  
  public String setId(Alias alias)
  {
    return set(this.teamSprayTable, this.q.idCol, alias);
  }
  
  public String setAggregationLevel(Alias alias)
  {
    return set("'2'::TEXT", alias);
  }
  
//  @Override
//  public String setSprayOperator(Alias alias)
//  {
//    return set(this.operSprayStatusTable, this.sprayOperatorCol, alias);
//  }
  
  @Override
  public String setSprayOperatorDefaultLocale(Alias alias)
  {
    return set("sprayoperator."+q.memberIdCol+" || ' - ' || person."+q.firstNameCol+
        " || ' ' || "+q.personTable+"."+q.lastNameCol, alias);
  }
  
//  @Override
//  public String setSprayTeam(Alias alias)
//  {
//    return set(teamSprayTable, sprayTeamCol, alias);
//  }
  
  @Override
  public String setSprayTeamDefaultLocale(Alias alias)
  {
    return set("(SELECT st." + teamIdCold + " FROM "+sprayTeamTable+
        " st WHERE st.id = "+teamSprayTable+"." + sprayTeamCol + ")", alias);
  }
  
//  @Override
//  public String setSprayLeader(Alias alias)
//  {
//    return set(teamSprayTable, teamLeaderCol, alias);
//  }
  
  @Override
  public String setSprayLeaderDefaultLocale(Alias alias)
  {
    return set("(SELECT tm."+q.memberIdCol+" || ' - ' || p."+q.firstNameCol+" || ' ' || p."+q.lastNameCol+" FROM "+q.teamMemberTable+" tm , "+q.personTable + 
        " AS p WHERE p.id = tm."+q.personCol+" AND tm.id = "+teamSprayTable+"." + teamLeaderCol + ")", alias);
  }
  
  @Override
  public String setTeamActualTarget(Alias alias)
  {
    return set(teamSprayTable, targetCol, alias);
  }
  
//  @Override
//  public String setOperatorPlannedTarget(Alias alias)
//  {
//    return set("(SELECT weekly_target FROM resourceTargetView AS  spray_target_view WHERE "
//        + "spray_target_view.target_id = sprayoperator.id \n"
//        + "AND spray_target_view.season_id = sprayseason.id \n"
//        + "AND spray_target_view.target_week = get_epiWeek_from_date("+q.sprayDateCol+"," + startDay + ")-1"
//        + ")", alias);
//  }
//  
//  @Override
//  public String setTeamPlannedTarget(Alias alias)
//  {
//    return set("(SELECT weekly_target FROM resourceTargetView AS  spray_target_view WHERE " + "spray_target_view.target_id = "+teamSprayTable+"." + sprayTeamCol + " \n"
//        + "AND spray_target_view.season_id = sprayseason.id \n"
//        + "AND spray_target_view.target_week = get_epiWeek_from_date("+q.sprayDateCol+"," + startDay + ")-1"
//        + ")", alias);
//  }
//  
//  @Override
//  public String setAreaPlannedTarget(Alias alias)
//  {
//    return set("get_seasonal_spray_target_by_geoEntityId_and_date("+q.abstractSprayTable+"."+q.geoEntityCol+
//        ", "+q.abstractSprayTable+"."+q.sprayDateCol+","+teamSprayTable+"."+diseaseCol+")", alias);
//  }
  
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
    //team_spray team_spray LEFT JOIN operator_spray_status AS operator_spray_status ON team_spray.id = operator_spray_status.spray
    //LEFT JOIN team_member AS sprayoperator ON operator_spray_status.spray_operator = sprayoperator.id LEFT JOIN person person ON sprayoperator.person = person.id,
    
    String from = "";
    from += teamSprayTable + " AS "+teamSprayTable+" LEFT JOIN "+operSprayStatusTable + " AS "+operSprayStatusTable+" ON "+teamSprayTable+".id = "+operSprayStatusTable+"."+sprayCol+" \n";
    from += " LEFT JOIN "+q.teamMemberTable+"" + " AS sprayoperator ON "+operSprayStatusTable+"."+sprayOperatorCol+" = sprayoperator.id \n";
    from += " LEFT JOIN "+q.personTable + " AS "+q.personTable+" ON sprayoperator."+q.personCol+" = "+q.personTable+"."+q.idCol+", \n";
    from += ""+q.abstractSprayTable+"" + " AS "+q.abstractSprayTable+"\n";
    from += " LEFT JOIN ";
    from += q.malariaSeasonTable + " AS sprayseason ";
    
    String seasonDiseaseCol = QueryUtil.getColumnName(MalariaSeason.getDiseaseMd());
    String diseaseId = Disease.getCurrent().getId();
    from += "ON "+q.abstractSprayTable+"."+q.sprayDateCol+" BETWEEN sprayseason."+q.startDateCol+" AND sprayseason."+q.endDateCol+" AND '"+diseaseId+"' = sprayseason."+seasonDiseaseCol+" \n";
    
    
    return from;
  }
  
  @Override
  public String where()
  {
    String where = "";

    where += ""+q.abstractSprayTable+".id = "+teamSprayTable+".id\n";
    where += "AND "+teamSprayTable+"."+diseaseCol+" = '"+diseaseId+"' \n";
    
    return where;
  }
}
