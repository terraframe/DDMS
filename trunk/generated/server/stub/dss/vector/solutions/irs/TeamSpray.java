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

public class TeamSpray extends TeamSprayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860702014L;

  public TeamSpray()
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
    else 
    {
      return this.buildKey();
    }
  }

  @Override
  protected String buildKey()
  {
    if (this.getBrand() != null && this.getGeoEntity() != null && this.getSprayDate() != null && this.getSprayMethodForIndex() != null  && this.getSprayTeam() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      String dateFormat = format.format(this.getSprayDate());
      String methodName = this.getSprayMethodForIndex();

      return this.getBrand().getKey() + "." + this.getGeoEntity().getGeoId() + "." + dateFormat + "." + methodName + "." + this.getSprayTeam().getKey();
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
  
  public TeamSprayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public TeamSprayView lockView()
  {
    this.lock();

    return this.getView();
  }

  public TeamSprayView getView()
  {
    TeamSprayView view = new TeamSprayView();

    view.populateView(this);

    return view;
  }

  public static TeamSpray get(Date sprayDate, GeoEntity geoEntity, InsecticideBrand brand, SprayTeam sprayTeam, SprayMethod[] sprayMethod)
  {
    TeamSprayQuery query = new TeamSprayQuery(new QueryFactory());
    query.WHERE(query.getSprayDate().EQ(sprayDate));
    query.WHERE(query.getGeoEntity().EQ(geoEntity));
    query.WHERE(query.getBrand().EQ(brand));
    query.WHERE(query.getSprayMethod().containsAll(sprayMethod));
    query.AND(query.getSprayTeam().EQ(sprayTeam));

    OIterator<? extends TeamSpray> it = query.getIterator();

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

  public static String getTempTableSQL(String viewName)
  {
    MdEntityDAOIF operSprayStatusMd = MdEntityDAO.getMdEntityDAO(OperatorSprayStatus.CLASS);
    String operSprayStatusTable = operSprayStatusMd.getTableName();
    String sprayCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.SPRAY);
    String sprayOperatorCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.SPRAYOPERATOR);
//    String operSprayWeekCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.OPERATORSPRAYWEEK);
    String receivedCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.RECEIVED);
    String usedCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.USED);
    String refillsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.REFILLS);
    String returnCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.RETURNED);
    String roomsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.ROOMS);
    String structuresCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.STRUCTURES);
    String householdsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.HOUSEHOLDS);
    String sprayedRoomsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.SPRAYEDROOMS);
    String sprayedStructuresCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.SPRAYEDSTRUCTURES);
    String sprayedHouseholdsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.SPRAYEDHOUSEHOLDS);
    String prevSprayedStructuresCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.PREVSPRAYEDSTRUCTURES);
    String prevSprayedHouseholdsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.PREVSPRAYEDHOUSEHOLDS);
    String peopleCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.PEOPLE);
    String bedNetsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.BEDNETS);
    String roomsWithBedNetsCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.ROOMSWITHBEDNETS);
    String lockedCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.LOCKED);
    String refusedCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.REFUSED);
    String otherCol = QueryUtil.getColumnName(operSprayStatusMd, OperatorSprayStatus.OTHER);
    
    MdEntityDAOIF personMd = MdEntityDAO.getMdEntityDAO(Person.CLASS);
    String personTable = personMd.getTableName();
    String firstNameCol = QueryUtil.getColumnName(personMd, Person.FIRSTNAME);
    String lastNameCol = QueryUtil.getColumnName(personMd, Person.LASTNAME);
    
    MdEntityDAOIF teamMemberMd = MdEntityDAO.getMdEntityDAO(TeamMember.CLASS);
    String teamMemberTable = teamMemberMd.getTableName();
    String memberIdCol = QueryUtil.getColumnName(teamMemberMd, TeamMember.MEMBERID);
    String personCol = QueryUtil.getColumnName(teamMemberMd, TeamMember.PERSON);
    
    MdEntityDAOIF sprayTeamMd = MdEntityDAO.getMdEntityDAO(SprayTeam.CLASS);
    String teamIdCold = QueryUtil.getColumnName(sprayTeamMd, SprayTeam.TEAMID);
    String sprayTeamTable = sprayTeamMd.getTableName();
    
    MdEntityDAOIF teamSprayMd = MdEntityDAO.getMdEntityDAO(TeamSpray.CLASS);
    String teamSprayTable = teamSprayMd.getTableName();
    String teamLeaderCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.TEAMLEADER);
    String sprayTeamCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.SPRAYTEAM);
//    String teamSprayWeekCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.TEAMSPRAYWEEK);
    String targetCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.TARGET);
    
    MdEntityDAOIF abstractSprayMd = MdEntityDAO.getMdEntityDAO(AbstractSpray.CLASS);
    String abstractSprayTable = abstractSprayMd.getTableName();
    String geoEntityCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.GEOENTITY);
    String sprayDateCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.SPRAYDATE);
    
    MdEntityDAOIF malariaSeasonMd = MdEntityDAO.getMdEntityDAO(MalariaSeason.CLASS);
    String malariaSeasonTable = malariaSeasonMd.getTableName();
    String startDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.STARTDATE);
    String endDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.ENDDATE);
    
    String select = "SELECT "+teamSprayTable+".id,\n";

    select += "'2'::TEXT AS aggregation_level,\n";
    // operator stuff
    select += "'' AS household_id,\n";
    select += "'' AS structure_id,\n";
    select += ""+operSprayStatusTable+"." + sprayOperatorCol + " AS sprayoperator,\n";
    select += "sprayoperator."+memberIdCol+" || ' - ' || "+personTable+"."+firstNameCol+" || ' ' || "+personTable+"."+lastNameCol+" AS sprayoperator_defaultLocale,\n";
//    select += ""+operSprayStatusTable+"." + operSprayWeekCol + " AS operator_week,\n";
    select += "NULL AS operator_target,\n";
    // team stuff
    select += ""+teamSprayTable+"." + sprayTeamCol + " AS "+sprayTeamCol+",\n";
    select += "(SELECT st." + teamIdCold + " FROM "+sprayTeamTable+" st WHERE st.id = "+teamSprayTable+"." + sprayTeamCol + ") AS sprayteam_defaultLocale,\n";
    select += ""+teamSprayTable+"." + teamLeaderCol + " AS sprayleader,\n";
    select += "(SELECT tm."+memberIdCol+" || ' - ' || p."+firstNameCol+" || ' ' || p."+lastNameCol+" FROM "+teamMemberTable+" tm , "+personTable + " AS p WHERE p.id = tm."+personCol+" AND tm.id = "+teamSprayTable+"." + teamLeaderCol + ") AS sprayleader_defaultLocale,\n";
//    select += ""+teamSprayTable+"." +teamSprayWeekCol + " AS team_week,\n";
    select += ""+teamSprayTable+"." + targetCol + " AS team_target,\n";
    // zone stuff
    select += "''::TEXT AS zone_supervisor,\n";
    select += "''::TEXT AS zone_supervisor_defaultLocale,\n";
    select += "NULL::INT  AS zone_week,\n";
    select += "NULL::INT  AS zone_target,\n";
    // target stuff
    select += "sprayseason.id  AS spray_season,\n";
    
    select += "(SELECT weekly_target FROM " + viewName + " AS  spray_target_view WHERE "
        + "spray_target_view.target_id = sprayoperator.id \n"
        + "AND spray_target_view.season_id = sprayseason.id \n"
//        + "AND spray_target_view.target_week = "+operSprayStatusTable+"." + operSprayWeekCol
        + ") AS planed_operator_target,\n";
    
    select += "(SELECT weekly_target FROM " + viewName + " AS  spray_target_view WHERE " + "spray_target_view.target_id = "+teamSprayTable+"." + sprayTeamCol + " \n"
        + "AND spray_target_view.season_id = sprayseason.id \n"
//        + "AND spray_target_view.target_week = " + teamSprayTable+ "." + teamSprayWeekCol
        + ") AS planed_team_target,\n";

    String diseaseCol = QueryUtil.getColumnName(TeamSpray.getDiseaseMd());
    select += "get_seasonal_spray_target_by_geoEntityId_and_date("+abstractSprayTable+"."+geoEntityCol+", "+abstractSprayTable+"."+sprayDateCol+","+teamSprayTable+"."+diseaseCol+""
         +") AS planed_area_target,\n";
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
    select += ""+teamSprayTable+"."+diseaseCol+" AS disease,\n";
    select += ""+operSprayStatusTable+"."+receivedCol+",\n";
    select += ""+operSprayStatusTable+"."+usedCol+",\n";
    select += ""+operSprayStatusTable+"."+refillsCol+",\n";
    select += ""+operSprayStatusTable+"."+returnCol+",\n";
    select += "("+roomsCol+" - "+sprayedRoomsCol+") AS room_unsprayed,\n";
    select += "("+structuresCol+" - "+sprayedStructuresCol+") AS structure_unsprayed,\n";
    select += "("+householdsCol+" - "+sprayedHouseholdsCol+") AS household_unsprayed,\n";
    
    select += "1 AS sprayedrooms_share,\n";
    select += "1 AS sprayedstructures_share,\n";
    select += "1 AS sprayedhouseholds_share,\n";
    
    

    String from = " FROM ";
    // get the main tables
    from += teamSprayTable + " AS "+teamSprayTable+",\n";
    from += operSprayStatusTable + " AS "+operSprayStatusTable+",\n";
    from += ""+teamMemberTable+"" + " AS sprayoperator,\n";
    from += personTable + " AS "+personTable+",\n";
    from += ""+abstractSprayTable+"" + " AS "+abstractSprayTable+"\n";
    from += " LEFT JOIN ";
    from += malariaSeasonTable + " AS sprayseason ";
    
    String seasonDiseaseCol = QueryUtil.getColumnName(MalariaSeason.getDiseaseMd());
    String diseaseId = Disease.getCurrent().getId();
    from += "ON "+abstractSprayTable+"."+sprayDateCol+" BETWEEN sprayseason."+startDateCol+" AND sprayseason."+endDateCol+" AND '"+diseaseId+"' = sprayseason."+seasonDiseaseCol+" \n";

    String where = "";

    // join main tables
    where += "AND "+abstractSprayTable+".id = "+teamSprayTable+".id\n";
    where += "AND "+teamSprayTable+".id = "+operSprayStatusTable+"."+sprayCol+"\n";
    where += "AND "+operSprayStatusTable+"."+sprayOperatorCol+" = sprayoperator.id \n";
    where += "AND sprayoperator."+personCol+" = "+personTable+".id \n";
    where += "AND "+teamSprayTable+"."+diseaseCol+" = '"+diseaseId+"' \n";

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);

    return select + "\n" + from + "\n" + where;
  }
  
 
}
