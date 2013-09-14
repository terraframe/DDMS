package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.irs.HouseholdSprayStatus;
import dss.vector.solutions.irs.OperatorSpray;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.util.QueryUtil;

public class ActualOperatorSprayTarget extends ActualTargetUnion implements Reloadable
{
  private String diseaseCol;
  private String householdSprayStatusTable;
  private String sprayCol;
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
  
  public ActualOperatorSprayTarget()
  {
    super();
    
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
    
    MdEntityDAOIF householdSprayStatusMd = MdEntityDAO.getMdEntityDAO(HouseholdSprayStatus.CLASS);
    this.householdSprayStatusTable = householdSprayStatusMd.getTableName();
    this.sprayCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.SPRAY);
    this.householdIdCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.HOUSEHOLDID);
    this.structureIdCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.STRUCTUREID);
    this.roomsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.ROOMS);
    this.structuresCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.STRUCTURES);
    this.householdsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.HOUSEHOLDS);
    this.sprayedRoomsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.SPRAYEDROOMS);
    this.sprayedStructuresCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.SPRAYEDSTRUCTURES);
    this.sprayedHouseholdsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.SPRAYEDHOUSEHOLDS);
    this.prevSprayedStructuresCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.PREVSPRAYEDSTRUCTURES);
    this.prevSprayedHouseholdsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.PREVSPRAYEDHOUSEHOLDS);
    this.peopleCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.PEOPLE);
    this.bedNetsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.BEDNETS);
    this.roomsWithBedNetsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.ROOMSWITHBEDNETS);
    this.lockedCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.LOCKED);
    this.refusedCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.REFUSED);
    this.otherCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.OTHER);
    this.wrongSurfaceCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.WRONGSURFACE);
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
  
  public String setAggregationLevel(Alias alias)
  {
    return set("'1'::TEXT", alias);
  }
  
  public String setHouseholdId(Alias alias)
  {
//    if(this.q.isGrouped())
//    {
//      return setNULL(alias);
//    }
//    else
//    {
      return set(this.householdSprayStatusTable, householdIdCol, alias);
//    }
  }
  
  public String setStructureId(Alias alias)
  {
//    if(this.q.isGrouped())
//    {
//      return setNULL(alias);
//    }
//    else
//    {
      return set(this.householdSprayStatusTable, structureIdCol, alias);
//    }
  }
  
  @Override
  public String setSprayOperatorDefaultLocale(Alias alias)
  {
    return set(""+teamMemberTable + "."+memberIdCol+" || ' - ' || person."+firstNameCol+
        " || ' ' || "+personTable+"."+lastNameCol, alias);
  }
  
//  @Override
//  public String setOperatorPlannedTarget(Alias alias)
//  {
//    String sql = "(SELECT SUM("+IRSQuery.WEEKLY_TARGET+") FROM "+IRSQuery.RESOURCE_TARGET_VIEW+" rtv WHERE rtv."+this.q.getTargeter()+" = "+this.operSprayTable+"."+this.sprayOperatorCol+" AND get_epiWeek_from_date("+this.sprayDateCol+", "+this.q.getStartDay()+") = "+IRSQuery.TARGET_WEEK+")";
//    return set(sql, alias);
//  }
  
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
  public String setSprayLeaderDefaultLocale(Alias alias)
  {
    return set("(SELECT tm."+memberIdCol+" || ' - ' || p."+firstNameCol+" || ' ' || p."+lastNameCol+" FROM "+teamMemberTable+
        " tm , "+personTable + " AS p WHERE p.id = tm."+personCol+" AND tm.id = "+operSprayTable+"." + teamLeaderCol + ")", alias);
  }
  
  @Override
  public String setRooms(Alias alias)
  {
    return set(householdSprayStatusTable, roomsCol, alias);
  }
  
  @Override
  public String setStructures(Alias alias)
  {
    return set(householdSprayStatusTable, structuresCol, alias);
  }
  
  @Override
  public String setHouseholds(Alias alias)
  {
    return set(householdSprayStatusTable, householdsCol, alias);
  }
  
  @Override
  public String setSprayedRooms(Alias alias)
  {
    return set(householdSprayStatusTable, sprayedRoomsCol, alias);
  }
  
  @Override
  public String setSprayedStructures(Alias alias)
  {
    return set(householdSprayStatusTable, sprayedStructuresCol, alias);
  }
  
  @Override
  public String setSprayedHouseholds(Alias alias)
  {
    return set(householdSprayStatusTable, sprayedHouseholdsCol, alias);
  }
  
  @Override
  public String setPrevSprayedStructures(Alias alias)
  {
    return set(householdSprayStatusTable, prevSprayedStructuresCol, alias);
  }

  @Override
  public String setPrevSprayedHouseholds(Alias alias)
  {
    return set(householdSprayStatusTable, prevSprayedHouseholdsCol, alias);
  }
  
  @Override
  public String setPeople(Alias alias)
  {
    return set(householdSprayStatusTable, peopleCol, alias);
  }
  
  @Override
  public String setBedNets(Alias alias)
  {
    return set(householdSprayStatusTable, bedNetsCol, alias);
  }
  
  @Override
  public String setRoomsWithBedNets(Alias alias)
  {
    return set(householdSprayStatusTable, roomsWithBedNetsCol, alias);
  }
  
  @Override
  public String setLocked(Alias alias)
  {
    return set(householdSprayStatusTable, lockedCol, alias);
  }

  @Override
  public String setRefused(Alias alias)
  {
    return set(householdSprayStatusTable, refusedCol, alias);
  }
  
  @Override
  public String setOther(Alias alias)
  {
    return set(householdSprayStatusTable, otherCol, alias);
  }
  
  @Override
  public String setWrongSurface(Alias alias)
  {
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
    return set(operSprayTable, receivedCol, alias);
  }
  
  @Override
  public String setUsed(Alias alias)
  {
    return set(operSprayTable, usedCol, alias);
  }
  
  @Override
  public String setRefills(Alias alias)
  {
    return set(operSprayTable, refillsCol, alias);
  }
  
  @Override
  public String setReturned(Alias alias)
  {
    return set(operSprayTable, returnCol, alias);
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
//    return set(""+sprayedRoomsCol+"/nullif((SELECT SUM("+sprayedRoomsCol+") from "+householdSprayStatusTable+
//        " hss where "+operSprayTable+".id = hss.spray),0)::float", alias);
    return set(""+sprayedRoomsCol+"/nullif("+IRSQB.SPRAYED_ROOMS_SUM+",0)::float", alias);
  }
  
  @Override
  public String setSprayedStructuresShare(Alias alias)
  {
//    return set(""+sprayedStructuresCol+"/nullif((SELECT SUM("+sprayedStructuresCol+") from "+
//        householdSprayStatusTable+" hss where "+operSprayTable+".id = hss.spray),0)::float", alias);
    return set(""+sprayedStructuresCol+"/nullif("+IRSQB.SPRAYED_STRUCTURES_SUM+",0)::float", alias);
  }
  
  @Override
  public String setSprayedHouseholdsShare(Alias alias)
  {
//    return set(""+sprayedHouseholdsCol+"/nullif((SELECT SUM("+sprayedHouseholdsCol+") from "
//        +householdSprayStatusTable+" hss where "+operSprayTable+".id = hss.spray),0)::float", alias);
    return set(""+sprayedHouseholdsCol+"/nullif("+IRSQB.SPRAYED_HOUSEHOLDS_SUM+",0)::float", alias);
  }
  
  @Override
  public String from()
  {
    String from = "";
    
    from += operSprayTable + " AS "+operSprayTable+" LEFT JOIN ";
    from += householdSprayStatusTable + " AS "+householdSprayStatusTable+" ON "+operSprayTable+".id = "+householdSprayStatusTable+"."+sprayCol+" \n";
    from += "LEFT JOIN "+IRSQB.SPRAY_SUMMARY+" ON "+IRSQB.SPRAY_SUMMARY+"."+IRSQB.OPERATOR_SPRAY_ID+" = "+operSprayTable+".id \n";
    from += "LEFT JOIN "+teamMemberTable + " AS "+teamMemberTable + " ON "+teamMemberTable + ".id = "+operSprayTable+"."+sprayOperatorCol+" \n"; 
    from += "LEFT JOIN "+personTable + " AS "+personTable+" ON "+teamMemberTable + "."+personCol+" = "+personTable+".id \n";
    from += "LEFT JOIN "+sprayTeamTable+" AS "+sprayTeamTable+" ON "+sprayTeamTable+"."+idCol+" = "+operSprayTable+"."+sprayTeamCol+", \n";
    
    from += abstractSprayTable + " AS "+abstractSprayTable+"\n";
    from += " LEFT JOIN ";
    from += malariaSeasonTable + " AS sprayseason ";
    
    String seasonDiseaseCol = QueryUtil.getColumnName(MalariaSeason.getDiseaseMd());
    from += "ON "+abstractSprayTable+"."+sprayDateCol+" BETWEEN sprayseason."+startDateCol+
      " AND sprayseason."+endDateCol+" AND '"+this.q.getDiseaseId()+"' = sprayseason."+seasonDiseaseCol+" \n";

    return from;
  }
  
  @Override
  public String where()
  {
    String where = "";
    where += ""+abstractSprayTable+".id = "+operSprayTable+".id\n";
    where += "AND "+operSprayTable+"."+diseaseCol+" = '"+this.q.getDiseaseId()+"' \n";
    
    
    return where;
  }
}
