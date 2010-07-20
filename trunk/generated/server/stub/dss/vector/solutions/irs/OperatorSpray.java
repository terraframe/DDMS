package dss.vector.solutions.irs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.Person;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.util.QueryUtil;

public class OperatorSpray extends OperatorSprayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long          serialVersionUID = 1240853391117L;

  public OperatorSpray()
  {
    super();
  }
  
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    
    return this.getClassDisplayLabel();
  }

  @Override
  protected String buildKey()
  {
    if (this.getBrand() != null && this.getGeoEntity() != null && this.getSprayDate() != null && this.getSprayMethodForIndex() != null  && this.getSprayOperator() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      String dateFormat = format.format(this.getSprayDate());
      String methodName = this.getSprayMethodForIndex();

      return this.getBrand().getKey() + "." + this.getGeoEntity().getGeoId() + "." + dateFormat + "." + methodName + "." + this.getSprayOperator().getKey();
    }

    return this.getId();
  }
  
  @Override
  public void apply()
  {
    List<SprayMethod> _sprayMethod = this.getSprayMethod();

    if(_sprayMethod.size() > 0)
    {
      this.setSprayMethodForIndex(_sprayMethod.get(0).getEnumName());      
    }
    
    this.setGeoEntityForIndex(this.getGeoEntity());
    this.setBrandForIndex(this.getBrand());
    this.setSprayDateForIndex(this.getSprayDate());
    
    
	if (this.isNew() && this.getDisease() == null) {
		this.setDisease(Disease.getCurrent());
	}
	
    super.apply();
  }

  public OperatorSprayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public OperatorSprayView lockView()
  {
    this.lock();

    return this.getView();
  }

  public OperatorSprayView getView()
  {
    OperatorSprayView view = new OperatorSprayView();

    view.populateView(this);

    return view;
  }
  
  public static OperatorSpray get(Date sprayDate, GeoEntity geoEntity, InsecticideBrand brand, TeamMember operator, SprayMethod... sprayMethod)
  {
    OperatorSprayQuery query = new OperatorSprayQuery(new QueryFactory());
    query.WHERE(query.getSprayDate().EQ(sprayDate));
    query.WHERE(query.getGeoEntity().EQ(geoEntity));
    query.WHERE(query.getBrand().EQ(brand));
    query.WHERE(query.getSprayMethod().containsAll(sprayMethod));
    query.AND(query.getSprayOperator().EQ(operator));

    OIterator<? extends OperatorSpray> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }

  public static String getTempTableSQL(String targetView, boolean grouped)
  {
    grouped = false;
    
    MdEntityDAOIF operSprayMd = MdEntityDAO.getMdEntityDAO(OperatorSpray.CLASS);
    String operSprayTable = operSprayMd.getTableName();
    String sprayOperatorCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.SPRAYOPERATOR);
    String operSprayWeekCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.OPERATORSPRAYWEEK);
    String teamLeaderCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.TEAMLEADER);
    String sprayTeamCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.SPRAYTEAM);
    String teamSprayWeekCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.TEAMSPRAYWEEK);
    String targetCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.TARGET);
    String receivedCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.RECEIVED);
    String usedCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.USED);
    String refillsCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.REFILLS);
    String returnCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.RETURNED);    
    
    MdEntityDAOIF householdSprayStatusMd = MdEntityDAO.getMdEntityDAO(HouseholdSprayStatus.CLASS);
    String householdSprayStatusTable = householdSprayStatusMd.getTableName();
    String sprayCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.SPRAY);
    String householdIdCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.HOUSEHOLDID);
    String structureIdCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.STRUCTUREID);
    String roomsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.ROOMS);
    String structuresCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.STRUCTURES);
    String householdsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.HOUSEHOLDS);
    String sprayedRoomsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.SPRAYEDROOMS);
    String sprayedStructuresCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.SPRAYEDSTRUCTURES);
    String sprayedHouseholdsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.SPRAYEDHOUSEHOLDS);
    String prevSprayedStructuresCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.PREVSPRAYEDSTRUCTURES);
    String prevSprayedHouseholdsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.PREVSPRAYEDHOUSEHOLDS);
    String peopleCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.PEOPLE);
    String bedNetsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.BEDNETS);
    String roomsWithBedNetsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.ROOMSWITHBEDNETS);
    String lockedCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.LOCKED);
    String refusedCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.REFUSED);
    String otherCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.OTHER);
    
    MdEntityDAOIF personMd = MdEntityDAO.getMdEntityDAO(Person.CLASS);
    String personTable = personMd.getTableName();
    String firstNameCol = QueryUtil.getColumnName(personMd, Person.FIRSTNAME);
    String lastNameCol = QueryUtil.getColumnName(personMd, Person.LASTNAME);
    
    MdEntityDAOIF teamMemberMd = MdEntityDAO.getMdEntityDAO(TeamMember.CLASS);
    String teamMemberTable = teamMemberMd.getTableName();
    String memberIdCol = QueryUtil.getColumnName(teamMemberMd, TeamMember.MEMBERID);
    String personCol = QueryUtil.getColumnName(teamMemberMd, TeamMember.PERSON);
    
    MdEntityDAOIF sprayTeamMd = MdEntityDAO.getMdEntityDAO(SprayTeam.CLASS);
    String teamId = QueryUtil.getColumnName(sprayTeamMd, SprayTeam.TEAMID);
    String sprayTeamTable = sprayTeamMd.getTableName();
    
    MdEntityDAOIF abstractSprayMd = MdEntityDAO.getMdEntityDAO(AbstractSpray.CLASS);
    String abstractSprayTable = abstractSprayMd.getTableName();
    String geoEntityCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.GEOENTITY);
    String sprayDateCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.SPRAYDATE);
    
    MdEntityDAOIF malariaSeasonMd = MdEntityDAO.getMdEntityDAO(MalariaSeason.CLASS);
    String malariaSeasonTable = malariaSeasonMd.getTableName();
    String startDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.STARTDATE);
    String endDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.ENDDATE);
    
    String select = "SELECT "+operSprayTable+".id,\n";

    select += "'1'::TEXT AS aggregation_level,\n";
    
    if (grouped)
    {
      select += "NULL AS household_id,\n";
      select += "NULL AS structure_id,\n";
    }
    else
    {
      select += ""+householdSprayStatusTable+"." + householdIdCol + " AS household_id,\n";
      select += ""+householdSprayStatusTable+"." + structureIdCol + " AS structure_id,\n";
    }
    // operator stuff
    select += ""+operSprayTable+"." + sprayOperatorCol + " AS sprayoperator,\n";
    select += "sprayoperator."+memberIdCol+" || ' - ' || person."+firstNameCol+" || ' ' || "+personTable+"."+lastNameCol+" AS sprayoperator_defaultLocale,\n";
    select += ""+operSprayTable+"." + operSprayWeekCol + " AS operator_week,\n";
    select += ""+operSprayTable+"." + targetCol + " AS operator_target,\n";
    // team stuff
    select += ""+operSprayTable+"." + sprayTeamCol + " AS sprayteam,\n";
    select += "(SELECT st." + teamId + " FROM "+ sprayTeamTable +" st WHERE st.id = "+operSprayTable+"." + sprayTeamCol + ") AS sprayteam_defaultLocale,\n";
    select += ""+operSprayTable+"." + teamLeaderCol + " AS sprayleader,\n";
    select += "(SELECT tm."+memberIdCol+" || ' - ' || p."+firstNameCol+" || ' ' || p."+lastNameCol+" FROM "+teamMemberTable+" tm , "+personTable + " AS p WHERE p.id = tm.id AND tm.id = "+operSprayTable+"." + teamLeaderCol + ") AS sprayleader_defaultLocale,\n";
    select += ""+operSprayTable+"." + teamSprayWeekCol + " AS team_week,\n";
    select += "NULL AS team_target,\n";
    // zone stuff
    select += "''::TEXT AS zone_supervisor,\n";
    select += "''::TEXT AS zone_supervisor_defaultLocale,\n";
    select += "NULL::INT  AS zone_week,\n";
    select += "NULL::INT  AS zone_target,\n";
    // target stuff
    select += "sprayseason.id  AS spray_season,\n";
    
    select += "(SELECT weekly_target FROM " + targetView + " AS  spray_target_view WHERE " + "spray_target_view.target_id = sprayoperator.id \n" 
    + "AND spray_target_view.season_id = sprayseason.id \n" + "AND spray_target_view.target_week = "+operSprayTable+"." + operSprayWeekCol
        + ") AS planed_operator_target,\n";
    
    select += "(SELECT weekly_target FROM " + targetView + " AS  spray_target_view WHERE " + "spray_target_view.target_id = "+operSprayTable+"." + sprayTeamCol + " \n" 
    + "AND spray_target_view.season_id = sprayseason.id \n" + "AND spray_target_view.target_week = "+operSprayTable+"." + teamSprayWeekCol
        + ") AS planed_team_target,\n";

    String diseaseCol = QueryUtil.getColumnName(OperatorSpray.getDiseaseMd());
    select += "get_seasonal_spray_target_by_geoEntityId_and_date("+abstractSprayTable+"."+geoEntityCol+","+abstractSprayTable+"."+sprayDateCol+","+operSprayTable+"."+diseaseCol+""
        + ") AS planed_area_target,\n";
   
    //spray stuff
    select += ""+roomsCol+",\n";
    select += ""+structuresCol+",\n";
    select += ""+householdsCol+",\n";
    select += ""+sprayedRoomsCol+" AS sprayedRooms,\n";
    select += ""+sprayedStructuresCol+" AS sprayedStructures,\n";
    select += ""+sprayedHouseholdsCol+" AS sprayedHouseholds,\n";
    select += ""+prevSprayedStructuresCol+" AS prevSprayedStructures,\n";
    select += ""+prevSprayedHouseholdsCol+" AS prevSprayedHouseholds,\n";
    select += ""+peopleCol+",\n";
    select += ""+bedNetsCol+" AS bedNets,\n";
    select += ""+roomsWithBedNetsCol+" AS roomsWithBedNets,\n";
    select += ""+lockedCol+",\n";
    select += ""+refusedCol+",\n";
    select += ""+otherCol+",\n";
    select += ""+operSprayTable+"."+diseaseCol+" AS disease,\n";    
    select += ""+receivedCol+",\n";
    select += ""+usedCol+",\n";
    select += ""+refillsCol+",\n";
    select += ""+returnCol+",\n";
    select += "("+roomsCol+" - "+sprayedRoomsCol+") AS room_unsprayed,\n";
    select += "("+structuresCol+" - "+sprayedStructuresCol+") AS structure_unsprayed,\n";
    select += "("+householdsCol+" - "+sprayedHouseholdsCol+") AS household_unsprayed,\n";
    
    select += ""+sprayedRoomsCol+"/nullif((SELECT SUM("+sprayedRoomsCol+") from "+householdSprayStatusTable+" hss where "+operSprayTable+".id = hss.spray),0)::float AS sprayedrooms_share,\n";
    select += ""+sprayedStructuresCol+"/nullif((SELECT SUM("+sprayedStructuresCol+") from "+householdSprayStatusTable+" hss where "+operSprayTable+".id = hss.spray),0)::float AS sprayedstructures_share,\n";
    select += ""+sprayedHouseholdsCol+"/nullif((SELECT SUM("+sprayedHouseholdsCol+") from "+householdSprayStatusTable+" hss where "+operSprayTable+".id = hss.spray),0)::float AS sprayedhouseholds_share,\n";
    
    String from = " FROM ";
    
    //must group to fix coverage...
    
    if (grouped)
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
      
    }
    else
    {
      from += householdSprayStatusTable + " AS "+householdSprayStatusTable+",\n";
    }
    
    // get the main tables
    from += operSprayTable + " AS "+operSprayTable+",\n";
    from += teamMemberTable + " AS sprayoperator,\n";
    from += personTable + " AS "+personTable+",\n";
    from += abstractSprayTable + " AS "+abstractSprayTable+"\n";
    from += " LEFT JOIN ";
    from += malariaSeasonTable + " AS sprayseason ";
    
    String seasonDiseaseCol = QueryUtil.getColumnName(MalariaSeason.getDiseaseMd());
    String diseaseId = Disease.getCurrent().getId();
    from += "ON "+abstractSprayTable+"."+sprayDateCol+" BETWEEN sprayseason."+startDateCol+" AND sprayseason."+endDateCol+" AND '"+diseaseId+"' = sprayseason."+seasonDiseaseCol+" \n";

    String where = "";

    // join main tables
    where += "AND "+abstractSprayTable+".id = "+operSprayTable+".id\n";
    where += "AND "+operSprayTable+".id = "+householdSprayStatusTable+"."+sprayCol+"\n";
    where += "AND "+operSprayTable+"."+sprayOperatorCol+" = sprayoperator.id \n";
    where += "AND sprayoperator."+personCol+" = "+personTable+".id \n";
    where += "AND "+operSprayTable+"."+diseaseCol+" = '"+diseaseId+"' \n";

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);

    return select + "\n" + from + "\n" + where;
  }
}
