package dss.vector.solutions.irs;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.util.QueryUtil;

public class ActualOperatorSprayTarget extends ActualTargetUnion
{
  public String setId(ALIAS alias)
  {
    return set(this.q.operSprayTable, this.q.idCol, alias);
  }
  
  public String setAggregationLevel(ALIAS alias)
  {
    return set("'1'::TEXT", alias);
  }
  
  public String setHouseholdId(ALIAS alias)
  {
    if(this.q.isGrouped())
    {
      return setNULL(alias);
    }
    else
    {
      String householdIdCol = QueryUtil.getColumnName(HouseholdSprayStatus.getHouseholdIdMd());

      return set(this.q.householdSprayStatusTable, householdIdCol, alias);
    }
  }
  
  public String setStructureId(ALIAS alias)
  {
    if(this.q.isGrouped())
    {
      return setNULL(alias);
    }
    else
    {
      String structureIdCol = QueryUtil.getColumnName(HouseholdSprayStatus.getStructureIdMd());
      
      return set(this.q.householdSprayStatusTable, structureIdCol, alias);
    }
  }
  
  @Override
  public String setSprayOperator(ALIAS alias)
  {
    return set(this.q.operSprayTable, this.q.sprayOperatorCol, alias);
  }
  
  @Override
  public String setSprayOperatorDefaultLocale(ALIAS alias)
  {
    return set("sprayoperator."+q.memberIdCol+" || ' - ' || person."+q.firstNameCol+
        " || ' ' || "+q.personTable+"."+q.lastNameCol, alias);
  }
  
  @Override
  public String setOperatorActualTarget(ALIAS alias)
  {
    return set(q.operSprayTable, q.targetCol, alias);
  }
  
  @Override
  public String setSprayTeam(ALIAS alias)
  {
    return set(q.operSprayTable, q.sprayTeamCol, alias);
  }
  
  @Override
  public String setSprayTeamDefaultLocale(ALIAS alias)
  {
    return set("(SELECT st." + q.teamId + " FROM "+ q.sprayTeamTable +
        " st WHERE st.id = "+q.operSprayTable+"." + q.sprayTeamCol + ")", alias);
  }
  
  @Override
  public String setSprayLeader(ALIAS alias)
  {
    return set(q.operSprayTable, q.teamLeaderCol, alias);
  }
  
  @Override
  public String setSprayLeaderDefaultLocale(ALIAS alias)
  {
    return set("(SELECT tm."+q.memberIdCol+" || ' - ' || p."+q.firstNameCol+" || ' ' || p."+q.lastNameCol+" FROM "+q.teamMemberTable+
        " tm , "+q.personTable + " AS p WHERE p.id = tm."+q.personCol+" AND tm.id = "+q.operSprayTable+"." + q.teamLeaderCol + ")", alias);
  }

  @Override
  public String setRooms(ALIAS alias)
  {
    return set(q.householdSprayStatusTable, q.roomsCol, alias);
  }
  
  @Override
  public String setStructures(ALIAS alias)
  {
    return set(q.householdSprayStatusTable, q.structuresCol, alias);
  }
  
  @Override
  public String setHouseholds(ALIAS alias)
  {
    return set(q.householdSprayStatusTable, q.householdsCol, alias);
  }
  
  @Override
  public String setSprayedRooms(ALIAS alias)
  {
    return set(q.householdSprayStatusTable, q.sprayedRoomsCol, alias);
  }
  
  @Override
  public String setSprayedStructures(ALIAS alias)
  {
    return set(q.householdSprayStatusTable, q.sprayedStructuresCol, alias);
  }
  
  @Override
  public String setSprayedHouseholds(ALIAS alias)
  {
    return set(q.householdSprayStatusTable, q.sprayedHouseholdsCol, alias);
  }
  
  @Override
  public String setPrevSprayedStructures(ALIAS alias)
  {
    return set(q.householdSprayStatusTable, q.prevSprayedStructuresCol, alias);
  }

  @Override
  public String setPrevSprayedHouseholds(ALIAS alias)
  {
    return set(q.householdSprayStatusTable, q.prevSprayedHouseholdsCol, alias);
  }
  
  @Override
  public String setPeople(ALIAS alias)
  {
    return set(q.householdSprayStatusTable, q.peopleCol, alias);
  }
  
  @Override
  public String setBedNets(ALIAS alias)
  {
    return set(q.householdSprayStatusTable, q.bedNetsCol, alias);
  }
  
  @Override
  public String setRoomsWithBedNets(ALIAS alias)
  {
    return set(q.householdSprayStatusTable, q.roomsWithBedNetsCol, alias);
  }
  
  @Override
  public String setLocked(ALIAS alias)
  {
    return set(q.householdSprayStatusTable, q.lockedCol, alias);
  }

  @Override
  public String setRefused(ALIAS alias)
  {
    return set(q.householdSprayStatusTable, q.refusedCol, alias);
  }
  
  @Override
  public String setOther(ALIAS alias)
  {
    return set(q.householdSprayStatusTable, q.otherCol, alias);
  }
  
  @Override
  public String setDisease(ALIAS alias)
  {
    String diseaseCol = QueryUtil.getColumnName(OperatorSpray.getDiseaseMd());
    return set(q.operSprayTable, diseaseCol, alias);
  }
  
  @Override
  public String setReceived(ALIAS alias)
  {
    return set(q.operSprayTable, q.receivedCol, alias);
  }
  
  @Override
  public String setUsed(ALIAS alias)
  {
    return set(q.operSprayTable, q.usedCol, alias);
  }
  
  @Override
  public String setRefills(ALIAS alias)
  {
    return set(q.operSprayTable, q.refillsCol, alias);
  }
  
  @Override
  public String setReturn(ALIAS alias)
  {
    return set(q.operSprayTable, q.returnCol, alias);
  }
  
  @Override
  public String setRoomUnsprayed(ALIAS alias)
  {
    return set("("+q.roomsCol+" - "+q.sprayedRoomsCol+")", alias);
  }
  
  @Override
  public String setStructureUnsprayed(ALIAS alias)
  {
    return set("("+q.structuresCol+" - "+q.sprayedStructuresCol+")", alias);
  }
  
  @Override
  public String setHouseholdUnsprayed(ALIAS alias)
  {
    return set("("+q.householdsCol+" - "+q.sprayedHouseholdsCol+")", alias);
  }
  
  @Override
  public String setSprayedRoomsShare(ALIAS alias)
  {
    return set(""+q.sprayedRoomsCol+"/nullif((SELECT SUM("+q.sprayedRoomsCol+") from "+q.householdSprayStatusTable+
        " hss where "+q.operSprayTable+".id = hss.spray),0)::float", alias);
  }
  
  @Override
  public String setSprayedStructuresShare(ALIAS alias)
  {
    return set(""+q.sprayedStructuresCol+"/nullif((SELECT SUM("+q.sprayedStructuresCol+") from "+
        q.householdSprayStatusTable+" hss where "+q.operSprayTable+".id = hss.spray),0)::float", alias);
  }
  
  @Override
  public String setSprayedHouseholdsShare(ALIAS alias)
  {
    return set(""+q.sprayedHouseholdsCol+"/nullif((SELECT SUM("+q.sprayedHouseholdsCol+") from "
        +q.householdSprayStatusTable+" hss where "+q.operSprayTable+".id = hss.spray),0)::float", alias);
  }
  
  @Override
  public String from()
  {
    // FIXME look at grouping in OperatorSpray to fix grouping
    String from = "";
    from += q.householdSprayStatusTable + " AS "+q.householdSprayStatusTable+", \n";
    from += q.operSprayTable + " AS "+q.operSprayTable+",\n";
    from += q.teamMemberTable + " AS sprayoperator,\n";
    from += q.personTable + " AS "+q.personTable+",\n";
    from += q.abstractSprayTable + " AS "+q.abstractSprayTable+"\n";
    from += " LEFT JOIN ";
    from += q.malariaSeasonTable + " AS sprayseason ";
    
    String seasonDiseaseCol = QueryUtil.getColumnName(MalariaSeason.getDiseaseMd());
    String diseaseId = Disease.getCurrent().getId();
    from += "ON "+q.abstractSprayTable+"."+q.sprayDateCol+" BETWEEN sprayseason."+q.startDateCol+
      " AND sprayseason."+q.endDateCol+" AND '"+diseaseId+"' = sprayseason."+seasonDiseaseCol+" \n";

    return from;
  }
  
  @Override
  public String where()
  {
    String where = "";
    
    return where;
  }
}
