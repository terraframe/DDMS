package dss.vector.solutions.querybuilder.irs;

import java.util.List;
import java.util.Set;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.irs.HouseholdSprayStatus;
import dss.vector.solutions.irs.OperatorSpray;
import dss.vector.solutions.irs.OperatorSprayStatus;
import dss.vector.solutions.irs.SprayTeam;
import dss.vector.solutions.irs.Supervisor;
import dss.vector.solutions.irs.TeamSpray;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;
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
  
  private String nozzlesUsedCol;
  
  private String pumpsUsedCol;

  private String verandasCol;

  private String verandasSprayedCol;

  private String verandasLockedCol;

  private String verandasRefusedCol;

  private String verandasOtherCol;

  private String cattleShedsCol;

  private String cattleShedsSprayedCol;

  private String cattleShedsLockedCol;

  private String cattleShedsRefusedCol;

  private String cattleShedsOtherCol;
  private String numberOfPeopleCol;
  
  public ActualTeamSprayTarget(IRSQB irsQB)
  {
    super(irsQB);
    
    MdEntityDAOIF teamSprayMd = MdEntityDAO.getMdEntityDAO(TeamSpray.CLASS);
    this.teamSprayTable = teamSprayMd.getTableName();
    teamLeaderCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.TEAMLEADER);
    sprayTeamCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.SPRAYTEAM);
    diseaseCol = QueryUtil.getColumnName(TeamSpray.getDiseaseMd());
    targetCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.TARGET);
    supervisorCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.SUPERVISOR);
    
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
    this.nozzlesUsedCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.NOZZLESUSED);
    this.pumpsUsedCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.PUMPSUSED);
    this.verandasCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.VERANDAS);
    this.verandasSprayedCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.VERANDASSPRAYED);
    this.verandasLockedCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.VERANDASLOCKED);
    this.verandasRefusedCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.VERANDASREFUSED);
    this.verandasOtherCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.VERANDASOTHER);
    this.cattleShedsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.CATTLESHEDS);
    this.cattleShedsSprayedCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.CATTLESHEDSSPRAYED);
    this.cattleShedsLockedCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.CATTLESHEDSLOCKED);
    this.cattleShedsRefusedCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.CATTLESHEDSREFUSED);
    this.cattleShedsOtherCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.CATTLESHEDSOTHER);
    this.numberOfPeopleCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.NUMBEROFPEOPLE);

    
    supervisorTable = MdEntityDAO.getMdEntityDAO(Supervisor.CLASS).getTableName();
    supervisorPersonCol = QueryUtil.getColumnName(Supervisor.getPersonMd());
    
    MdEntityDAOIF sprayTeamMd = MdEntityDAO.getMdEntityDAO(SprayTeam.CLASS);
    sprayTeamTable = sprayTeamMd.getTableName();
  }
  
  @Override
  public void loadDependencies()
  {
    super.loadDependencies();
    
    Set<Alias> selected = this.irsQB.getSelectAliases();
    
    if(selected.contains(Alias.TEAM_TARGETED_COVERAGE))
    {
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.TEAM_ACTUAL_TARGET);
    }
  }
  
  @Override
  public List<TableDependency> loadTableDependencies()
  {
    List<TableDependency> tables = super.loadTableDependencies();
    
    // Level 2 join on parent
    tables.add(new TableDependency(this, teamSprayTable, new Alias[]{
        Alias.ID,
        Alias.SPRAY_DATE,
        Alias.DISEASE
    },
      teamSprayTable + " AS "+teamSprayTable+" \n"+
      "INNER JOIN "+abstractSprayTable+"" + " AS "+abstractSprayTable+" ON "+abstractSprayTable+"."+idCol+" = "+teamSprayTable+"."+idCol+" \n"+
      "AND "+teamSprayTable+"."+diseaseCol+" = '"+this.irsQB.getDiseaseId()+"' \n"
    ));
    
    // join the team
    tables.add(new TableDependency(this, teamSprayTable, new Alias[]{
        Alias.SPRAY_TEAM,
        Alias.SPRAY_TEAM_DEFAULT_LOCALE
    },
      "INNER JOIN "+sprayTeamTable+" AS "+sprayTeamTable+" ON "+teamSprayTable+"."+sprayTeamCol+" = "+sprayTeamCol+"."+idCol+" \n"
    ));
    
    
    // spray details
    TableDependency sprayDetails = new TableDependency(this, operSprayStatusTable, new Alias[]{
        Alias.UNIQUE_SPRAY_ID,
        Alias.BEDNETS,
        Alias.HOUSEHOLDS,
        Alias.LOCKED,
        Alias.OPERATOR_ACTUAL_TARGET,
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
        Alias.USED,
        Alias.NOZZLES_USED,
        Alias.PUMPS_USED,
        Alias.VERANDAS,
        Alias.VERANDAS_SPRAYED,
        Alias.VERANDAS_LOCKED,
        Alias.VERANDAS_REFUSED,
        Alias.VERANDAS_OTHER,
        Alias.CATTLESHEDS,
        Alias.CATTLESHEDS_SPRAYED,
        Alias.CATTLESHEDS_LOCKED,
        Alias.CATTLESHEDS_REFUSED,
        Alias.CATTLESHEDS_OTHER,
        Alias.NUMBER_OF_PEOPLE
    },
    "LEFT JOIN "+operSprayStatusTable + " AS "+operSprayStatusTable+" ON "+teamSprayTable+".id = "+operSprayStatusTable+"."+sprayCol+" \n"
        );
    tables.add(sprayDetails);
    
    
    // operator person
    tables.add(new TableDependency(this, OPERATOR_PERSON, new Alias[]{
        Alias.SPRAY_OPERATOR,
        Alias.SPRAY_OPERATOR_DEFAULT_LOCALE,
        Alias.SPRAY_OPERATOR_PERSON,
        Alias.SPRAY_OPERATOR_PERSON_ID,
        Alias.SPRAY_OPERATOR_SEX,
        Alias.SPRAY_OPERATOR_BIRTHDATE,
    },
      "LEFT JOIN "+teamMemberTable+"" + " AS "+OPERATOR_MEMBER+" ON "+operSprayStatusTable+"."+sprayOperatorCol+" = "+OPERATOR_MEMBER+".id \n"+
      "LEFT JOIN "+personTable + " AS "+OPERATOR_PERSON+" ON "+OPERATOR_MEMBER+"."+personCol+" = "+OPERATOR_PERSON+"."+idCol+" \n"
    , sprayDetails));
  

    // leader person
    tables.add(new TableDependency(this, LEADER_PERSON, new Alias[]{
        Alias.SPRAY_LEADER_DEFAULT_LOCALE,
        Alias.SPRAY_LEADER_PERSON,
        Alias.SPRAY_LEADER_PERSON_ID,
        Alias.SPRAY_LEADER_SEX,
        Alias.SPRAY_LEADER_BIRTHDATE,
    },    
       "LEFT JOIN "+teamMemberTable+" AS "+LEADER_MEMBER+" ON "+teamSprayTable+"."+teamLeaderCol+" = "+LEADER_MEMBER+"."+idCol+" \n"+
       "LEFT JOIN "+personTable +" AS "+LEADER_PERSON+" ON "+LEADER_MEMBER+"."+personCol+ " = "+LEADER_PERSON + "." + idCol+" \n"
    ));
 
    
    // supervisor person
    tables.add(new TableDependency(this, SUPERVISOR_PERSON, new Alias[]{
        Alias.ZONE_SUPERVISOR_DEFAULT_LOCALE,
        Alias.ZONE_SUPERVISOR_PERSON,
        Alias.ZONE_SUPERVISOR_PERSON_ID,
        Alias.ZONE_SUPERVISOR_SEX,
        Alias.ZONE_SUPERVISOR_BIRTHDATE,
    },
      "LEFT JOIN "+ supervisorTable + " " + supervisorTable + " ON " + supervisorTable + "." + idCol + " = " + teamSprayTable + "." + supervisorCol + " \n"+
      "LEFT JOIN "+personTable+ " AS "+SUPERVISOR_PERSON+ " ON "+ supervisorTable+"."+supervisorPersonCol+" = "+SUPERVISOR_PERSON+"."+idCol+" \n"
    ));
    
    // season
    this.addSeasonTableDependency(tables);  
    
    return tables;
  }
  
  public String setId(Alias alias)
  {
    return set(this.teamSprayTable, this.idCol, alias);
  }
  
  @Override
  public String setOperatorActualTarget(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.operSprayStatusTable, this.operTarget, alias);
  }
  
  protected String getLevel()
  {
    return "2";
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
    this.irsQB.addParentAggregate(alias);
    return set(teamSprayTable, targetCol, alias);
  }
  
  @Override
  public String setRooms(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, roomsCol, alias);
  }
  
  @Override
  public String setStructures(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, structuresCol, alias);
  }
  
  @Override
  public String setHouseholds(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, householdsCol, alias);
  }
  
  @Override
  public String setNumberOfPeople(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, numberOfPeopleCol, alias);
  }
  
  @Override
  public String setSprayedRooms(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, sprayedRoomsCol, alias);
  }
  
  @Override
  public String setSprayedStructures(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, sprayedStructuresCol, alias);
  }
  
  @Override
  public String setSprayedHouseholds(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, sprayedHouseholdsCol, alias);
  }
  
  @Override
  public String setPrevSprayedStructures(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, prevSprayedStructuresCol, alias);
  }

  @Override
  public String setPrevSprayedHouseholds(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, prevSprayedHouseholdsCol, alias);
  }
  
  @Override
  public String setPeople(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, peopleCol, alias);
  }
  
  @Override
  public String setBedNets(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, bedNetsCol, alias);
  }
  
  @Override
  public String setRoomsWithBedNets(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, roomsWithBedNetsCol, alias);
  }
  
  @Override
  public String setLocked(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, lockedCol, alias);
  }

  @Override
  public String setRefused(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, refusedCol, alias);
  }
  
  @Override
  public String setOther(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
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
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, receivedCol, alias);
  }
  
  @Override
  public String setUsed(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, usedCol, alias);
  }
  
  @Override
  public String setRefills(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, refillsCol, alias);
  }
  
  @Override
  public String setReturned(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(operSprayStatusTable, returnCol, alias);
  }
  
  /* No longer set here, but at the end of the query instead.
  @Override
  public String setRoomUnsprayed(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return setNULL(alias);
    //return set("("+roomsCol+" - "+sprayedRoomsCol+")", alias);
  }
  
  @Override
  public String setStructureUnsprayed(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return setNULL(alias);
    //return set("("+structuresCol+" - "+sprayedStructuresCol+")", alias);
  }
  
  @Override
  public String setHouseholdUnsprayed(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return setNULL(alias);
    //return set("("+householdsCol+" - "+sprayedHouseholdsCol+")", alias);
  }
  */
  
  @Override
  public String setSprayedRoomsShare(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set("1", alias);
  }
  
  @Override
  public String setSprayedStructuresShare(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set("1", alias);
  }
  
  @Override
  public String setSprayedHouseholdsShare(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set("1", alias);
  }
  
  @Override
  public String setUniqueSprayId(Alias alias)
  {
    String sql = this.operSprayStatusTable+"."+this.keyName;
    return set(sql, alias);
    //    return set(this.sprayOperatorCol, alias); old code, not unique enough
  }
  
  @Override
  public String setNozzlesUsed(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.operSprayStatusTable, this.nozzlesUsedCol, alias);
  }

  @Override
  public String setPumpsUsed(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.operSprayStatusTable, this.pumpsUsedCol, alias);
  }

  @Override
  public String setVerandas(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.operSprayStatusTable, this.verandasCol, alias);
  }

  @Override
  public String setVerandasSprayed(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.operSprayStatusTable, this.verandasSprayedCol, alias);
  }

  @Override
  public String setVerandasLocked(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.operSprayStatusTable, this.verandasLockedCol, alias);
  }

  @Override
  public String setVerandasRefused(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.operSprayStatusTable, this.verandasRefusedCol, alias);
  }

  @Override
  public String setVerandasOther(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.operSprayStatusTable, this.verandasOtherCol, alias);
  }

  @Override
  public String setCattleSheds(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.operSprayStatusTable, this.cattleShedsCol, alias);
  }

  @Override
  public String setCattleShedsSprayed(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.operSprayStatusTable, this.cattleShedsSprayedCol, alias);
  }

  @Override
  public String setCattleShedsLocked(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.operSprayStatusTable, this.cattleShedsLockedCol, alias);
  }

  @Override
  public String setCattleShedsRefused(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.operSprayStatusTable, this.cattleShedsRefusedCol, alias);
  }

  @Override
  public String setCattleShedsOther(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.operSprayStatusTable, this.cattleShedsOtherCol, alias);
  }
}
