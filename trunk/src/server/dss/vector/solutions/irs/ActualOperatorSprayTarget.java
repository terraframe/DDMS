package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.MalariaSeason;
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
  private String sprayOperatorCol;
  private String sprayTeamCol;
  private String teamLeaderCol;
  private String targetCol;
  private String receivedCol;
  private String usedCol;
  private String refillsCol;
  private String returnCol;
  
  public ActualOperatorSprayTarget()
  {
    super();
    
    diseaseCol = QueryUtil.getColumnName(OperatorSpray.getDiseaseMd());
    
    MdEntityDAOIF operSprayMd = MdEntityDAO.getMdEntityDAO(OperatorSpray.CLASS);
    this.operSprayTable = operSprayMd.getTableName();
    this.sprayOperatorCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.SPRAYOPERATOR);
    this.teamLeaderCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.TEAMLEADER);
    this.sprayTeamCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.SPRAYTEAM);
    this.targetCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.TARGET);
    this.receivedCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.RECEIVED);
    this.usedCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.USED);
    this.refillsCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.REFILLS);
    this.returnCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.RETURNED);
    
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
  }
  
  public String setId(Alias alias)
  {
    return set(this.operSprayTable, this.idCol, alias);
  }
  
  public String setAggregationLevel(Alias alias)
  {
    return set("'1'::TEXT", alias);
  }
  
  public String setHouseholdId(Alias alias)
  {
    if(this.q.isGrouped())
    {
      return setNULL(alias);
    }
    else
    {
      return set(this.householdSprayStatusTable, householdIdCol, alias);
    }
  }
  
  public String setStructureId(Alias alias)
  {
    if(this.q.isGrouped())
    {
      return setNULL(alias);
    }
    else
    {
      return set(this.householdSprayStatusTable, structureIdCol, alias);
    }
  }
  
  @Override
  public String setSprayOperatorDefaultLocale(Alias alias)
  {
    return set("sprayoperator."+memberIdCol+" || ' - ' || person."+firstNameCol+
        " || ' ' || "+personTable+"."+lastNameCol, alias);
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
    return set("(SELECT st." + teamIdCol + " FROM "+ sprayTeamTable +
        " st WHERE st.id = "+operSprayTable+"." + sprayTeamCol + ")", alias);
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
    return set(""+sprayedRoomsCol+"/nullif((SELECT SUM("+sprayedRoomsCol+") from "+householdSprayStatusTable+
        " hss where "+operSprayTable+".id = hss.spray),0)::float", alias);
  }
  
  @Override
  public String setSprayedStructuresShare(Alias alias)
  {
    return set(""+sprayedStructuresCol+"/nullif((SELECT SUM("+sprayedStructuresCol+") from "+
        householdSprayStatusTable+" hss where "+operSprayTable+".id = hss.spray),0)::float", alias);
  }
  
  @Override
  public String setSprayedHouseholdsShare(Alias alias)
  {
    return set(""+sprayedHouseholdsCol+"/nullif((SELECT SUM("+sprayedHouseholdsCol+") from "
        +householdSprayStatusTable+" hss where "+operSprayTable+".id = hss.spray),0)::float", alias);
  }
  
  @Override
  public String from()
  {
    // FIXME look at grouping in OperatorSpray to fix grouping
    String from = "";
    
    if (q.isGrouped())
    {
      String hss_grouped = "SELECT spray,\n" ;
        
      hss_grouped += "SUM("+roomsCol+") "+roomsCol+",\n";
      hss_grouped += "SUM("+structuresCol+") "+structuresCol+",\n";
      hss_grouped += "SUM("+householdsCol+") "+householdsCol+",\n";
      hss_grouped += "SUM("+sprayedRoomsCol+") "+sprayedRoomsCol+",\n";
      hss_grouped += "SUM("+sprayedStructuresCol+") "+sprayedStructuresCol+",\n";
      hss_grouped += "SUM("+sprayedHouseholdsCol+") "+sprayedHouseholdsCol+",\n";
      hss_grouped += "SUM("+prevSprayedStructuresCol+") "+prevSprayedStructuresCol+",\n";
      hss_grouped += "SUM("+prevSprayedHouseholdsCol+") "+prevSprayedHouseholdsCol+",\n";
      hss_grouped += "SUM("+peopleCol+") "+peopleCol+",\n";
      hss_grouped += "SUM("+bedNetsCol+") "+bedNetsCol+",\n";
      hss_grouped += "SUM("+roomsWithBedNetsCol+") "+roomsWithBedNetsCol+",\n";
      hss_grouped += "SUM("+refusedCol+") "+refusedCol+",\n";
      hss_grouped += "SUM("+lockedCol+") "+lockedCol+",\n";
      hss_grouped += "SUM("+otherCol+") "+otherCol+"\n";
      hss_grouped += "FROM " +  householdSprayStatusTable + "\n";
      hss_grouped += "GROUP BY spray";
      
      from += "("+hss_grouped +")" + " AS "+householdSprayStatusTable+",\n";
      from += operSprayTable + " AS "+operSprayTable+",\n";
      
    }
    else
    {
      from += operSprayTable + " AS "+operSprayTable+" LEFT JOIN ";
      from += householdSprayStatusTable + " AS "+householdSprayStatusTable+" ON "+operSprayTable+".id = "+householdSprayStatusTable+"."+sprayCol+",\n";
    }    
    
    from += teamMemberTable + " AS sprayoperator,\n";
    from += personTable + " AS "+personTable+",\n";
    from += abstractSprayTable + " AS "+abstractSprayTable+"\n";
    from += " LEFT JOIN ";
    from += malariaSeasonTable + " AS sprayseason ";
    
    String seasonDiseaseCol = QueryUtil.getColumnName(MalariaSeason.getDiseaseMd());
    from += "ON "+abstractSprayTable+"."+sprayDateCol+" BETWEEN sprayseason."+startDateCol+
      " AND sprayseason."+endDateCol+" AND '"+diseaseId+"' = sprayseason."+seasonDiseaseCol+" \n";

    return from;
  }
  
  @Override
  public String where()
  {
    String where = "";
    where += ""+abstractSprayTable+".id = "+operSprayTable+".id\n";
    where += "AND "+operSprayTable+"."+sprayOperatorCol+" = sprayoperator.id \n";
    where += "AND sprayoperator."+personCol+" = "+personTable+".id \n";
    where += "AND "+operSprayTable+"."+diseaseCol+" = '"+diseaseId+"' \n";
    
    
    return where;
  }
}
