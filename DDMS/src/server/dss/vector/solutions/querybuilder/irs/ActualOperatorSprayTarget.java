/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.querybuilder.irs;

import java.util.List;
import java.util.Set;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.irs.HouseholdSprayStatus;
import dss.vector.solutions.irs.OperatorSpray;
import dss.vector.solutions.irs.Supervisor;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;
import dss.vector.solutions.util.QueryUtil;

/**
 * LEVEL 1
 */
public class ActualOperatorSprayTarget extends ActualTargetUnion implements Reloadable
{
  private String diseaseCol;

  private String householdSprayStatusTable;

  private String sprayCol;

  private String reasonNotSprayedCol;

  private String structureTypeCol;
  
  private String householdIdCol;

  private String structureIdCol;

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

  private String operSprayTable;

  private String sprayTeamCol;

  private String teamLeaderCol;

  private String targetCol;

  private String receivedCol;

  private String usedCol;

  private String refillsCol;

  private String returnCol;

  private String sprayOperatorCol;

  private String wrongSurfaceCol;

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
  
  // New as of 3792:
  private String structuresNotSprayedSick;
  
  private String structuresNotSprayedLocked;
  
  private String structuresNotSprayedFuneral;
  
  private String structuresNotSprayedRefused;
  
  private String structuresNotSprayedNoOneHome;
  
  private String structuresNotSprayedOther;
  
  private String numberMalesProtected;
  
  private String numberFemalesProtected;
  
  private String numberPregnantWomenProtected;
  
  private String numberChildrenUnderFiveProtected;
  
  private String numberRoomsNotSprayedSick;
  
  private String numberItnsInUse;
  
  private String numberPeopleSleepingUnderItns;
  
  private String numberPregnantWomenSleepingUnderItns;
  
  private String numberChildrenUnderFiveSleepingUnderItns;
  
  public ActualOperatorSprayTarget(IRSQB irsQB)
  {
    super(irsQB);

    diseaseCol = QueryUtil.getColumnName(OperatorSpray.getDiseaseMd());

    MdEntityDAOIF operSprayMd = MdEntityDAO.getMdEntityDAO(OperatorSpray.CLASS);
    this.operSprayTable = operSprayMd.getTableName();
    this.teamLeaderCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.TEAMLEADER);
    this.sprayTeamCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.SPRAYTEAM);
    this.targetCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.TARGET);
    this.receivedCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.RECEIVED);
    this.usedCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.USED);
    this.refillsCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.REFILLS);
    this.returnCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.RETURNED);
    this.sprayOperatorCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.SPRAYOPERATOR);
    this.supervisorCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.SUPERVISOR);
    this.nozzlesUsedCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.NOZZLESUSED);
    this.pumpsUsedCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.PUMPSUSED);

    MdEntityDAOIF householdSprayStatusMd = MdEntityDAO.getMdEntityDAO(HouseholdSprayStatus.CLASS);
    this.householdSprayStatusTable = householdSprayStatusMd.getTableName();
    this.sprayCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.SPRAY);
    this.householdIdCol = QueryUtil.getColumnName(householdSprayStatusMd,
        HouseholdSprayStatus.HOUSEHOLDID);
    this.structureIdCol = QueryUtil.getColumnName(householdSprayStatusMd,
        HouseholdSprayStatus.STRUCTUREID);
    this.roomsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.ROOMS);
    this.structuresCol = QueryUtil
        .getColumnName(householdSprayStatusMd, HouseholdSprayStatus.STRUCTURES);
    this.householdsCol = QueryUtil
        .getColumnName(householdSprayStatusMd, HouseholdSprayStatus.HOUSEHOLDS);
    this.sprayedRoomsCol = QueryUtil.getColumnName(householdSprayStatusMd,
        HouseholdSprayStatus.SPRAYEDROOMS);
    this.sprayedStructuresCol = QueryUtil.getColumnName(householdSprayStatusMd,
        HouseholdSprayStatus.SPRAYEDSTRUCTURES);
    this.sprayedHouseholdsCol = QueryUtil.getColumnName(householdSprayStatusMd,
        HouseholdSprayStatus.SPRAYEDHOUSEHOLDS);
    this.prevSprayedStructuresCol = QueryUtil.getColumnName(householdSprayStatusMd,
        HouseholdSprayStatus.PREVSPRAYEDSTRUCTURES);
    this.prevSprayedHouseholdsCol = QueryUtil.getColumnName(householdSprayStatusMd,
        HouseholdSprayStatus.PREVSPRAYEDHOUSEHOLDS);
    this.peopleCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.PEOPLE);
    this.bedNetsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.BEDNETS);
    this.roomsWithBedNetsCol = QueryUtil.getColumnName(householdSprayStatusMd,
        HouseholdSprayStatus.ROOMSWITHBEDNETS);
    this.lockedCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.LOCKED);
    this.refusedCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.REFUSED);
    this.otherCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.OTHER);
    this.wrongSurfaceCol = QueryUtil.getColumnName(householdSprayStatusMd,
        HouseholdSprayStatus.WRONGSURFACE);
    this.keyName = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.KEYNAME);
    this.verandasCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.VERANDAS);
    this.verandasSprayedCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.VERANDASSPRAYED);
    this.verandasLockedCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.VERANDASLOCKED);
    this.verandasRefusedCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.VERANDASREFUSED);
    this.verandasOtherCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.VERANDASOTHER);
    this.cattleShedsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.CATTLESHEDS);
    this.cattleShedsSprayedCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.CATTLESHEDSSPRAYED);
    this.cattleShedsLockedCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.CATTLESHEDSLOCKED);
    this.cattleShedsRefusedCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.CATTLESHEDSREFUSED);
    this.cattleShedsOtherCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.CATTLESHEDSOTHER);
    this.numberOfPeopleCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.NUMBEROFPEOPLE);
    this.reasonNotSprayedCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.REASONNOTSPRAYED);
    this.structureTypeCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.STRUCTURETYPE);
    
    // New as of 3792:
    this.structuresNotSprayedSick = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.STRUCTURESNOTSPRAYEDSICK);
    this.structuresNotSprayedLocked = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.STRUCTURESNOTSPRAYEDLOCKED);
    this.structuresNotSprayedFuneral = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.STRUCTURESNOTSPRAYEDFUNERAL);
    this.structuresNotSprayedRefused = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.STRUCTURESNOTSPRAYEDREFUSED);
    this.structuresNotSprayedNoOneHome = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.STRUCTURESNOTSPRAYEDNOONEHOME);
    this.structuresNotSprayedOther = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.STRUCTURESNOTSPRAYEDOTHER);
    this.numberMalesProtected = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.NUMBERMALESPROTECTED);
    this.numberFemalesProtected = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.NUMBERFEMALESPROTECTED);
    this.numberPregnantWomenProtected = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.NUMBERPREGNANTWOMENPROTECTED);
    this.numberChildrenUnderFiveProtected = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.NUMBERCHILDRENUNDERFIVEPROTECTED);
    this.numberRoomsNotSprayedSick = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.NUMBERROOMSNOTSPRAYEDSICK);
    this.numberItnsInUse = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.NUMBERITNSINUSE);
    this.numberPeopleSleepingUnderItns = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.NUMBERPEOPLESLEEPINGUNDERITNS);
    this.numberPregnantWomenSleepingUnderItns = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.NUMBERPREGNANTWOMENSLEEPINGUNDERITNS);
    this.numberChildrenUnderFiveSleepingUnderItns = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS);
    
    supervisorTable = MdEntityDAO.getMdEntityDAO(Supervisor.CLASS).getTableName();
    supervisorPersonCol = QueryUtil.getColumnName(Supervisor.getPersonMd());
  }

  @Override
  public void loadDependencies()
  {
    super.loadDependencies();
    
    Set<Alias> selected = this.irsQB.getSelectAliases();
    
    if(selected.contains(Alias.OPERATOR_TARGETED_COVERAGE))
    {
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.OPERATOR_ACTUAL_TARGET);
    }
  }
  
  @Override
  public List<TableDependency> loadTableDependencies()
  {
    List<TableDependency> tables = super.loadTableDependencies();
    
    String spraySummaryView = IRSQB.View.SPRAY_SUMMARY_VIEW.getView();
    
    // Level 1 join on parent
    tables.add(new TableDependency(this, operSprayTable, new Alias[]{
        Alias.ID,
        Alias.SPRAY_DATE,
        Alias.DISEASE,
        Alias.OPERATOR_ACTUAL_TARGET,
        Alias.RECEIVED,
        Alias.REFILLS,
        Alias.RETURNED,
        Alias.SPRAY_OPERATOR,
        Alias.TEAM_ACTUAL_TARGET,
        Alias.USED,
        Alias.NOZZLES_USED,
        Alias.PUMPS_USED,
    },
      operSprayTable + " AS "+operSprayTable+" \n"+
      "INNER JOIN "+abstractSprayTable + " AS "+abstractSprayTable+" ON "+abstractSprayTable+"."+idCol+" = "+operSprayTable+"."+idCol+" \n"+
      "AND " + operSprayTable + "." + diseaseCol + " = '" + this.irsQB.getDisease().getId() + "' \n"
    ));
    
    // spray details
    tables.add(new TableDependency(this, householdSprayStatusTable, new Alias[]{
        Alias.UNIQUE_SPRAY_ID,
        Alias.BEDNETS,
        Alias.HOUSEHOLD_ID,
        Alias.HOUSEHOLDS,
        Alias.LOCKED,
        Alias.OTHER,
        Alias.PEOPLE,
        Alias.PREV_SPRAYED_HOUSEHOLDS,
        Alias.PREV_SPRAYED_STRUCTURES,
        Alias.REFUSED,
        Alias.ROOMS,
        Alias.ROOMS_WITH_BED_NETS,
        Alias.SPRAYED_HOUSEHOLDS,
        Alias.SPRAYED_ROOMS,
        Alias.SPRAYED_STRUCTURES,
        Alias.STRUCTURE_ID,
        Alias.STRUCTURES,
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
        Alias.NUMBER_OF_PEOPLE,
        Alias.REASON_NOT_SPRAYED,
        Alias.STRUCTURE_TYPE,
        
        // New as of 3792:
        Alias.STRUCTURES_NOT_SPRAYED_SICK,
        Alias.STRUCTURES_NOT_SPRAYED_LOCKED,
        Alias.STRUCTURES_NOT_SPRAYED_FUNERAL,
        Alias.STRUCTURES_NOT_SPRAYED_REFUSED,
        Alias.STRUCTURES_NOT_SPRAYED_NO_ONE_HOME,
        Alias.STRUCTURES_NOT_SPRAYED_OTHER,
        Alias.NUMBER_MALES_PROTECTED,
        Alias.NUMBER_FEMALES_PROTECTED,
        Alias.NUMBER_PREGNANT_WOMEN_PROTECTED,
        Alias.NUMBER_CHILDREN_UNDER_FIVE_PROTECTED,
        Alias.NUMBER_ROOMS_NOT_SPAYED_SICK,
        Alias.NUMBER_ITNS_IN_USE,
        Alias.NUMBER_PEOPLE_SLEEPING_UNDER_ITNS,
        Alias.NUMBER_PREGNANT_WOMEN_SLEEPING_UNDER_ITNS,
        Alias.NUMBER_CHILDREN_UNDER_FIVE_SLEEPING_UNDER_ITNS
    },
      "LEFT JOIN "+householdSprayStatusTable + " AS "+householdSprayStatusTable+" ON "+operSprayTable+".id = "+householdSprayStatusTable+"."+sprayCol+" \n"
    ));
    
    // spray summary
    tables.add(new TableDependency(this, spraySummaryView, new Alias[]{
        Alias.SPRAYED_HOUSEHOLDS_SHARE,
        Alias.SPRAYED_STRUCTURES_SHARE,
        Alias.SPRAYED_ROOMS_SHARE
    }, "LEFT JOIN "+spraySummaryView+" ON "+spraySummaryView+"."+IRSQB.OPERATOR_SPRAY_ID+" = "+operSprayTable+".id \n"));

    // operator person
    tables.add(new TableDependency(this, OPERATOR_PERSON, new Alias[]{
        Alias.SPRAY_OPERATOR,
        Alias.SPRAY_OPERATOR_DEFAULT_LOCALE,
        Alias.SPRAY_OPERATOR_PERSON,
        Alias.SPRAY_OPERATOR_PERSON_ID,
        Alias.SPRAY_OPERATOR_SEX,
        Alias.SPRAY_OPERATOR_BIRTHDATE,
    },
      "LEFT JOIN "+teamMemberTable + " AS "+OPERATOR_MEMBER + " ON "+OPERATOR_MEMBER + ".id = "+operSprayTable+"."+sprayOperatorCol+" \n"+
      "LEFT JOIN "+personTable + " AS "+OPERATOR_PERSON+" ON "+OPERATOR_MEMBER + "."+personCol+" = "+OPERATOR_PERSON+".id \n"));

    
    // leader person
    tables.add(new TableDependency(this, LEADER_PERSON, new Alias[]{
        Alias.SPRAY_LEADER_DEFAULT_LOCALE,
        Alias.SPRAY_LEADER_PERSON,
        Alias.SPRAY_LEADER_PERSON_ID,
        Alias.SPRAY_LEADER_SEX,
        Alias.SPRAY_LEADER_BIRTHDATE,
    },
    "LEFT JOIN "+teamMemberTable+" AS "+LEADER_MEMBER+" ON "+operSprayTable+"."+teamLeaderCol+" = "+LEADER_MEMBER+"."+idCol+" \n"+
        "LEFT JOIN "+personTable +" AS "+LEADER_PERSON+" ON "+LEADER_MEMBER+"."+personCol+ " = "+LEADER_PERSON + "." + idCol+" \n"));

    
    // supervisor person
    tables.add(new TableDependency(this, SUPERVISOR_PERSON, new Alias[]{
        Alias.ZONE_SUPERVISOR_DEFAULT_LOCALE,
        Alias.ZONE_SUPERVISOR_PERSON,
        Alias.ZONE_SUPERVISOR_PERSON_ID,
        Alias.ZONE_SUPERVISOR_SEX,
        Alias.ZONE_SUPERVISOR_BIRTHDATE,
    },
      "LEFT JOIN "+ supervisorTable + " " + supervisorTable + " ON " + supervisorTable + "." + idCol + " = "+ operSprayTable + "." + supervisorCol + " \n"+
      "LEFT JOIN "+personTable+ " AS "+SUPERVISOR_PERSON+ " ON "+ supervisorTable+"."+supervisorPersonCol+" = "+SUPERVISOR_PERSON+"."+idCol+" \n"));

    
    // spray team    
    tables.add(new TableDependency(this, SUPERVISOR_PERSON, new Alias[]{
        Alias.SPRAY_TEAM,
        Alias.SPRAY_TEAM_DEFAULT_LOCALE,
    },
      "LEFT JOIN "+sprayTeamTable+" AS "+sprayTeamTable+" ON "+sprayTeamTable+"."+idCol+" = "+operSprayTable+"."+sprayTeamCol+" \n"));

    
    // season
    this.addSeasonTableDependency(tables);
    
    return tables;
  }

  public String setId(Alias alias)
  {
    return set(this.operSprayTable, this.idCol, alias);
  }

  @Override
  public String setSprayOperator(Alias alias)
  {
    return set(this.operSprayTable, this.sprayOperatorCol, alias);
  }
  
  protected String getLevel()
  {
    return "1";
  }

  @Override
  public String setStructureType(Alias alias)
  {
    return set(this.householdSprayStatusTable, structureTypeCol, alias);
  }
  
  @Override
  public String setReasonNotSprayed(Alias alias)
  {
    return set(this.householdSprayStatusTable, reasonNotSprayedCol, alias);
  }
  
  public String setHouseholdId(Alias alias)
  {
    return set(this.householdSprayStatusTable, householdIdCol, alias);
  }

  public String setStructureId(Alias alias)
  {
    return set(this.householdSprayStatusTable, structureIdCol, alias);
  }

  @Override
  public String setOperatorActualTarget(Alias alias)
  {
    return set(operSprayTable, targetCol, alias);
  }

  @Override
  public String setTeamActualTarget(Alias alias)
  {
    // Estimate the team actual target by adding the operator actual targets
    return set(operSprayTable, targetCol, alias);
  }

  @Override
  public String setSprayTeamDefaultLocale(Alias alias)
  {
    return set(sprayTeamTable, teamIdCol, alias);
  }
  
  @Override
  public String setNumberOfPeople(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(householdSprayStatusTable, numberOfPeopleCol, alias);
  }

  @Override
  public String setRooms(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(householdSprayStatusTable, roomsCol, alias);
  }

  @Override
  public String setStructures(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(householdSprayStatusTable, structuresCol, alias);
  }

  @Override
  public String setHouseholds(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(householdSprayStatusTable, householdsCol, alias);
  }

  @Override
  public String setSprayedRooms(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(householdSprayStatusTable, sprayedRoomsCol, alias);
  }

  @Override
  public String setSprayedStructures(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(householdSprayStatusTable, sprayedStructuresCol, alias);
  }

  @Override
  public String setSprayedHouseholds(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(householdSprayStatusTable, sprayedHouseholdsCol, alias);
  }

  @Override
  public String setPrevSprayedStructures(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(householdSprayStatusTable, prevSprayedStructuresCol, alias);
  }

  @Override
  public String setPrevSprayedHouseholds(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(householdSprayStatusTable, prevSprayedHouseholdsCol, alias);
  }

  @Override
  public String setPeople(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(householdSprayStatusTable, peopleCol, alias);
  }

  @Override
  public String setBedNets(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(householdSprayStatusTable, bedNetsCol, alias);
  }

  @Override
  public String setRoomsWithBedNets(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(householdSprayStatusTable, roomsWithBedNetsCol, alias);
  }

  @Override
  public String setLocked(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(householdSprayStatusTable, lockedCol, alias);
  }

  @Override
  public String setRefused(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(householdSprayStatusTable, refusedCol, alias);
  }

  @Override
  public String setOther(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(householdSprayStatusTable, otherCol, alias);
  }

  @Override
  public String setWrongSurface(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(wrongSurfaceCol, alias);
  }

  @Override
  public String setDisease(Alias alias)
  {
    return set(operSprayTable, diseaseCol, alias);
  }

  @Override
  public String setReceived(Alias alias)
  {
    this.irsQB.addParentAggregate(alias);
    return set(operSprayTable, receivedCol, alias);
  }

  @Override
  public String setUsed(Alias alias)
  {
    this.irsQB.addParentAggregate(alias);
    return set(operSprayTable, usedCol, alias);
  }

  @Override
  public String setRefills(Alias alias)
  {
    this.irsQB.addParentAggregate(alias);
    return set(operSprayTable, refillsCol, alias);
  }

  @Override
  public String setReturned(Alias alias)
  {
    this.irsQB.addParentAggregate(alias);
    return set(operSprayTable, returnCol, alias);
  }

  /* No longer set here, but at the end of the query instead.
  @Override
  public String setRoomUnsprayed(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return setNULL(alias);
    //return set("(" + roomsCol + " - " + sprayedRoomsCol + ")", alias);
  }

  @Override
  public String setStructureUnsprayed(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return setNULL(alias);
    //return set("(" + structuresCol + " - " + sprayedStructuresCol + ")", alias);
  }

  @Override
  public String setHouseholdUnsprayed(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return setNULL(alias);
    //return set("(" + householdsCol + " - " + sprayedHouseholdsCol + ")", alias);
  }
  */

  @Override
  public String setSprayedRoomsShare(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set("" + sprayedRoomsCol + "/nullif(" + IRSQB.SPRAYED_ROOMS_SUM + ",0)::float", alias);
  }

  @Override
  public String setSprayedStructuresShare(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set("" + sprayedStructuresCol + "/nullif(" + IRSQB.SPRAYED_STRUCTURES_SUM + ",0)::float",
        alias);
  }

  @Override
  public String setSprayedHouseholdsShare(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set("" + sprayedHouseholdsCol + "/nullif(" + IRSQB.SPRAYED_HOUSEHOLDS_SUM + ",0)::float",
        alias);
  }

  @Override
  public String setUniqueSprayId(Alias alias)
  {
    // old code: not unique enough
    // String sql = "";
    // sql +=
    // "(CASE WHEN household_id IS NOT NULL AND structure_id IS NOT NULL THEN household_id || structure_id";
    // sql +=
    // " WHEN structure_id IS NOT NULL THEN structure_id WHEN household_id IS NOT NULL THEN household_id";
    // sql += " ELSE abstract_spray.key_name END)";

    String sql = this.householdSprayStatusTable + "." + this.keyName;
    return set(sql, alias);
  }

  @Override
  public String setNozzlesUsed(Alias alias)
  {
    this.irsQB.addParentAggregate(alias);
    return set(this.operSprayTable, this.nozzlesUsedCol, alias);
  }

  @Override
  public String setPumpsUsed(Alias alias)
  {
    this.irsQB.addParentAggregate(alias);
    return set(this.operSprayTable, this.pumpsUsedCol, alias);
  }

  @Override
  public String setVerandas(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.verandasCol, alias);
  }

  @Override
  public String setVerandasSprayed(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.verandasSprayedCol, alias);
  }

  @Override
  public String setVerandasLocked(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.verandasLockedCol, alias);
  }

  @Override
  public String setVerandasRefused(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.verandasRefusedCol, alias);
  }

  @Override
  public String setVerandasOther(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.verandasOtherCol, alias);
  }

  @Override
  public String setCattleSheds(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.cattleShedsCol, alias);
  }

  @Override
  public String setCattleShedsSprayed(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.cattleShedsSprayedCol, alias);
  }

  @Override
  public String setCattleShedsLocked(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.cattleShedsLockedCol, alias);
  }

  @Override
  public String setCattleShedsRefused(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.cattleShedsRefusedCol, alias);
  }

  @Override
  public String setCattleShedsOther(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.cattleShedsOtherCol, alias);
  }
  
  //New as of 3792:
  @Override
  public String setStructuresNotSprayedSick(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.structuresNotSprayedSick, alias);
  }
  
  @Override
  public String setStructuresNotSprayedLocked(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.structuresNotSprayedLocked, alias);
  }
  
  @Override
  public String setStructuresNotSprayedFuneral(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.structuresNotSprayedFuneral, alias);
  }
  
  @Override
  public String setStructuresNotSprayedRefused(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.structuresNotSprayedRefused, alias);
  }
  
  @Override
  public String setStructuresNotSprayedNoOneHome(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.structuresNotSprayedNoOneHome, alias);
  }
  
  @Override
  public String setStructuresNotSprayedOther(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.structuresNotSprayedOther, alias);
  }
  
  @Override
  public String setNumberMalesProtected(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.numberMalesProtected, alias);
  }
  
  @Override
  public String setNumberFemalesProtected(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.numberFemalesProtected, alias);
  }
  
  @Override
  public String setNumberPregnantWomenProtected(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.numberPregnantWomenProtected, alias);
  }
  
  @Override
  public String setNumberChildrenUnderFiveProtected(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.numberChildrenUnderFiveProtected, alias);
  }
  
  @Override
  public String setNumberRoomsNotSprayedSick(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.numberRoomsNotSprayedSick, alias);
  }
  
  @Override
  public String setNumberItnsInUse(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.numberItnsInUse, alias);
  }
  
  @Override
  public String setNumberPeopleSleepingUnderItns(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.numberPeopleSleepingUnderItns, alias);
  }
  
  @Override
  public String setNumberPregnantWomenSleepingUnderItns(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.numberPregnantWomenSleepingUnderItns, alias);
  }
  
  @Override
  public String setNumberChildrenUnderFiveSleepingUnderItns(Alias alias)
  {
    this.irsQB.addChildAggregate(alias);
    return set(this.householdSprayStatusTable, this.numberChildrenUnderFiveSleepingUnderItns, alias);
  }
}
