package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.util.QueryUtil;

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
  
  public ActualZoneSprayTarget()
  {
    super();
    
    MdEntityDAOIF zoneSprayMd = MdEntityDAO.getMdEntityDAO(ZoneSpray.CLASS);
    zoneSprayTable = zoneSprayMd.getTableName();
    diseaseCol = QueryUtil.getColumnName(ZoneSpray.getDiseaseMd());
    
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
    prevSprayedStructuresCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.PREVSPRAYEDSTRUCTURES);
    prevSprayedHouseholdsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.PREVSPRAYEDHOUSEHOLDS);
    peopleCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.PEOPLE);
    bedNetsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.BEDNETS);
    roomsWithBedNetsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.ROOMSWITHBEDNETS);
    lockedCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.LOCKED);
    refusedCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.REFUSED);
    otherCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.OTHER);
  }
  
  public String setId(Alias alias)
  {
    return set(this.zoneSprayTable, this.q.idCol, alias);
  }
  
  public String setAggregationLevel(Alias alias)
  {
    return set("'3'::TEXT", alias);
  }
  
  @Override
  public String setSprayTeam(Alias alias)
  {
    return set(teamSprayStatusTable, sprayTeamCol, alias);
  }
  
  @Override
  public String setSprayTeamDefaultLocale(Alias alias)
  {
    return set("(SELECT st." + q.teamIdCol + " FROM " + q.sprayTeamTable + 
        " st WHERE st.id = " + teamSprayStatusTable + "." + sprayTeamCol + ")", alias);
  }
  
  @Override
  public String setSprayLeader(Alias alias)
  {
    return set(teamSprayStatusTable, teamLeaderCol, alias);
  }
  
  @Override
  public String setSprayLeaderDefaultLocale(Alias alias)
  {
    return set("(SELECT tm." + q.memberIdCol + " || ' - ' || p." + q.firstNameCol + " || ' ' || p." + q.lastNameCol + " FROM " + q.teamMemberTable + " tm , " + q.personTable + 
        " AS p WHERE p.id = tm."+q.personCol+" AND tm.id = " + teamSprayStatusTable + "." + teamLeaderCol + ")", alias);
  }
  
  @Override
  public String setTeamPlannedTarget(Alias alias)
  {
    return set("(SELECT weekly_target FROM resourceTargetView AS  spray_target_view WHERE " + "spray_target_view.target_id = " + teamSprayStatusTable + "." + sprayTeamCol + " \n"
        + "AND spray_target_view.season_id = sprayseason.id \n"
        + "AND spray_target_view.target_week = get_epiWeek_from_date("+q.sprayDateCol+"," + startDay + ")-1"
        + ")", alias);
  }
  
  @Override
  public String setAreaPlannedTarget(Alias alias)
  {
    return set("get_seasonal_spray_target_by_geoEntityId_and_date(" + q.abstractSprayTable + "." + q.geoEntityCol + "," + 
        q.abstractSprayTable + "." + q.sprayDateCol+","+zoneSprayTable+"."+diseaseCol+")", alias);
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
    from += zoneSprayTable + " AS " + zoneSprayTable + ",\n";
    from += teamSprayStatusTable + " AS " + teamSprayStatusTable + ",\n";
    from += q.abstractSprayTable + " AS " + q.abstractSprayTable + "\n";
    from += " LEFT JOIN ";
    from += q.malariaSeasonTable + " AS sprayseason ";
    
    String seasonDiseaseCol = QueryUtil.getColumnName(MalariaSeason.getDiseaseMd());
    String diseaseId = Disease.getCurrent().getId();
    from += "ON " + q.abstractSprayTable + "." + q.sprayDateCol + " BETWEEN sprayseason." + q.startDateCol + 
      " AND sprayseason." + q.endDateCol + " AND '"+diseaseId+"' = sprayseason."+seasonDiseaseCol+" \n";
    
    
    return from;
  }
  
  @Override
  public String where()
  {
    String where = "";
    where += "" + q.abstractSprayTable + ".id = " + zoneSprayTable + ".id \n";
    where += "AND " + zoneSprayTable + ".id = " + teamSprayStatusTable + "." + sprayCol + " \n";
    where += "AND "+zoneSprayTable+"."+diseaseCol+" = '"+diseaseId+"' \n";
    
    return where;
  }
  
}
