package dss.vector.solutions.querybuilder.irs;

import java.util.List;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Condition;

import dss.vector.solutions.irs.Supervisor;
import dss.vector.solutions.irs.TeamSprayStatus;
import dss.vector.solutions.irs.ZoneSpray;
import dss.vector.solutions.querybuilder.IRSQB;
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
  
  public ActualZoneSprayTarget(IRSQB irsQB)
  {
    super(irsQB);

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
  
  @Override
  public List<TableDependency> loadTableDependencies()
  {
    List<TableDependency> tables = super.loadTableDependencies();
    
    // level 3 join on parent
    tables.add(new TableDependency(this, zoneSprayTable, new Alias[]{
        Alias.ID,
        Alias.SPRAY_DATE,
        Alias.DISEASE
    },
      zoneSprayTable + " AS " + zoneSprayTable + " \n"+
      "INNER JOIN "+ abstractSprayTable + " AS " + abstractSprayTable +" ON "+abstractSprayTable+"."+idCol+" = "+zoneSprayTable+"."+idCol+" \n"+
      "AND " + zoneSprayTable + "." + diseaseCol + " = '" + this.irsQB.getDiseaseId() + "' \n"+
      this.getDateCriteriaSQL()
    ));   
    
    // spray details
    TableDependency sprayDetails = new TableDependency(this, teamSprayStatusTable, new Alias[]{
        Alias.UNIQUE_SPRAY_ID,
        Alias.BEDNETS,
        Alias.HOUSEHOLDS,
        Alias.LOCKED,
        Alias.TEAM_ACTUAL_TARGET,
        Alias.OTHER,
        Alias.PEOPLE,
        Alias.PREV_SPRAYED_HOUSEHOLDS,
        Alias.PREV_SPRAYED_STRUCTURES,
        Alias.RECEIVED,
        Alias.REFILLS,
        Alias.REFUSED,
        Alias.RETURNED,
        Alias.ROOMS,
        Alias.ROOMS_WITH_BED_NETS,
        Alias.SPRAYED_HOUSEHOLDS,
        Alias.SPRAYED_ROOMS,
        Alias.SPRAYED_STRUCTURES,
        Alias.STRUCTURES,
        Alias.USED
      },
         "LEFT JOIN " + teamSprayStatusTable + " AS "+ teamSprayStatusTable + " ON \n"+
         zoneSprayTable + "." + idCol + " = " + teamSprayStatusTable + "." + sprayCol + " \n"
       );
    tables.add(sprayDetails);

    
    // join the team
    tables.add(new TableDependency(this, sprayTeamTable, new Alias[]{
        Alias.SPRAY_TEAM,
        Alias.SPRAY_TEAM_DEFAULT_LOCALE
    },
      "INNER JOIN "+sprayTeamTable+" AS "+sprayTeamTable+" ON "+teamSprayStatusTable+"."+sprayTeamCol+" = "+sprayTeamCol+"."+idCol+" \n"
    , sprayDetails));    
    
    // leader
    tables.add(new TableDependency(this, LEADER_PERSON, new Alias[]{
        Alias.SPRAY_LEADER_DEFAULT_LOCALE,
        Alias.SPRAY_LEADER_PERSON,
        Alias.SPRAY_LEADER_PERSON_ID,
        Alias.SPRAY_LEADER_SEX,
        Alias.SPRAY_LEADER_BIRTHDATE,
    },    
      "LEFT JOIN "+teamMemberTable+" AS "+LEADER_MEMBER+" ON "+teamSprayStatusTable+"."+teamLeaderCol+" = "+LEADER_MEMBER+"."+idCol+" \n"+
      "LEFT JOIN "+personTable +" AS "+LEADER_PERSON+" ON "+LEADER_MEMBER+"."+personCol+ " = "+LEADER_PERSON + "." + idCol+" \n"
    , sprayDetails));    
    
    // supervisor
    tables.add(new TableDependency(this, SUPERVISOR_PERSON, new Alias[]{
        Alias.ZONE_SUPERVISOR_DEFAULT_LOCALE,
        Alias.ZONE_SUPERVISOR_PERSON,
        Alias.ZONE_SUPERVISOR_PERSON_ID,
        Alias.ZONE_SUPERVISOR_SEX,
        Alias.ZONE_SUPERVISOR_BIRTHDATE,
    },
     "LEFT JOIN "+ supervisorTable + " " + supervisorTable + " ON " + supervisorTable + "." + idCol + " = "+zoneSprayTable + "." + supervisorCol + " \n"+
      "LEFT JOIN "+personTable+" AS "+SUPERVISOR_PERSON+ " ON " +SUPERVISOR_PERSON+"."+idCol+" = " +supervisorTable+"."+supervisorPersonCol+ " \n"    
    ));
    
    
    this.addSeasonTableDependency(tables);
    
    return tables;
  }
  
  protected String getDateCriteriaSQL()
  {
    // FIXME use selectable dependency to null out values
    String oDOB = null;
    String tDOB = column(LEADER_PERSON, this.birthdateCol);
    String sDOB = column(SUPERVISOR_PERSON, this.birthdateCol);
    
    Condition cond = this.irsQB.addDateCriteria(null, false, oDOB, tDOB, sDOB);
    if(cond != null)
    {
      return " AND "+cond.getSQL() + " \n";
    }
    else
    {
      return "";
    }
  }

  public String setId(Alias alias)
  {
    return set(this.zoneSprayTable, this.idCol, alias);
  }

  protected String getLevel()
  {
    return "3";
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
  public String setUniqueSprayId(Alias alias)
  {
    String sql = this.teamSprayStatusTable+"."+this.keyName;
    return set(sql, alias);
  }
}
