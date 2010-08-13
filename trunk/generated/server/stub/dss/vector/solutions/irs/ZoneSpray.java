package dss.vector.solutions.irs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;

import dss.vector.solutions.Person;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.util.QueryUtil;

public class ZoneSpray extends ZoneSprayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860676906L;

  public ZoneSpray()
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
    if (this.getBrand() != null && this.getGeoEntity() != null && this.getSprayDate() != null && this.getSprayMethodForIndex() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      String dateFormat = format.format(this.getSprayDate());
      String methodName = this.getSprayMethodForIndex();

      return this.getBrand().getKey() + "." + this.getGeoEntity().getGeoId() + "." + dateFormat + "." + methodName;
    }

    return this.getId();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    List<SprayMethod> _sprayMethod = this.getSprayMethod();

    if (_sprayMethod.size() > 0)
    {
      this.setSprayMethodForIndex(_sprayMethod.get(0).getEnumName());
    }

    this.setGeoEntityForIndex(this.getGeoEntity());
    this.setBrandForIndex(this.getBrand());
    this.setSprayDateForIndex(this.getSprayDate());

    super.apply();
  }

  public ZoneSprayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public ZoneSprayView lockView()
  {
    this.lock();

    return this.getView();
  }

  public ZoneSprayView getView()
  {
    ZoneSprayView view = new ZoneSprayView();

    view.populateView(this);

    return view;
  }

  public static void createTempTable(String tableName, String viewName)
  {
    String sql = "DROP TABLE IF EXISTS " + tableName + ";\n";
    sql += "CREATE TEMP TABLE " + tableName + " AS ";
    sql += ZoneSpray.getTempTableSQL(viewName) + ";\n";
    Database.parseAndExecute(sql);
  }

  public static String getTempTableSQL(String viewName)
  {
    MdEntityDAOIF zoneSprayMd = MdEntityDAO.getMdEntityDAO(ZoneSpray.CLASS);
    String zoneSprayTable = zoneSprayMd.getTableName();
    String supervisorCol = QueryUtil.getColumnName(zoneSprayMd, ZoneSpray.SUPERVISOR);
//    String zsTargetCol = QueryUtil.getColumnName(zoneSprayMd, ZoneSpray.TARGET);
//    String sprayWeekCol = QueryUtil.getColumnName(zoneSprayMd, ZoneSpray.SPRAYWEEK);

    MdEntityDAOIF teamSprayStatusMd = MdEntityDAO.getMdEntityDAO(TeamSprayStatus.CLASS);
    String teamSprayStatusTable = teamSprayStatusMd.getTableName();
    String sprayCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.SPRAY);
    String sprayTeamCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.SPRAYTEAM);
    String teamLeaderCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.TEAMLEADER);
//    String teamSprayWeekCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.TEAMSPRAYWEEK);
    String targetCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.TARGET);
    String receivedCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.RECEIVED);
    String usedCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.USED);
    String refillsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.REFILLS);
    String returnCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.RETURNED);
    String roomsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.ROOMS);
    String structuresCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.STRUCTURES);
    String householdsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.HOUSEHOLDS);
    String sprayedRoomsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.SPRAYEDROOMS);
    String sprayedStructuresCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.SPRAYEDSTRUCTURES);
    String sprayedHouseholdsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.SPRAYEDHOUSEHOLDS);
    String prevSprayedStructuresCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.PREVSPRAYEDSTRUCTURES);
    String prevSprayedHouseholdsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.PREVSPRAYEDHOUSEHOLDS);
    String peopleCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.PEOPLE);
    String bedNetsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.BEDNETS);
    String roomsWithBedNetsCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.ROOMSWITHBEDNETS);
    String lockedCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.LOCKED);
    String refusedCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.REFUSED);
    String otherCol = QueryUtil.getColumnName(teamSprayStatusMd, TeamSprayStatus.OTHER);

    MdEntityDAOIF personMd = MdEntityDAO.getMdEntityDAO(Person.CLASS);
    String personTable = personMd.getTableName();
    String firstNameCol = QueryUtil.getColumnName(personMd, Person.FIRSTNAME);
    String lastNameCol = QueryUtil.getColumnName(personMd, Person.LASTNAME);

    MdEntityDAOIF teamMemberMd = MdEntityDAO.getMdEntityDAO(TeamMember.CLASS);
    String teamMemberTable = teamMemberMd.getTableName();
    String memberIdCol = QueryUtil.getColumnName(teamMemberMd, TeamMember.MEMBERID);
    String personCol = QueryUtil.getColumnName(teamMemberMd, TeamMember.PERSON);

    MdEntityDAOIF sprayTeamMd = MdEntityDAO.getMdEntityDAO(SprayTeam.CLASS);
    String sprayTeamTable = sprayTeamMd.getTableName();
    String teamIdCol = QueryUtil.getColumnName(sprayTeamMd, SprayTeam.TEAMID);

    MdEntityDAOIF abstractSprayMd = MdEntityDAO.getMdEntityDAO(AbstractSpray.CLASS);
    String abstractSprayTable = abstractSprayMd.getTableName();
    String geoEntityCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.GEOENTITY);
    String sprayDateCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.SPRAYDATE);

    MdEntityDAOIF malariaSeasonMd = MdEntityDAO.getMdEntityDAO(MalariaSeason.CLASS);
    String malariaSeasonTable = malariaSeasonMd.getTableName();
    String startDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.STARTDATE);
    String endDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.ENDDATE);

    String select = "SELECT " + zoneSprayTable + ".id,\n";

    select += "'3'::TEXT AS aggregation_level,\n";
    // operator stuff
    select += "'' AS household_id,\n";
    select += "'' AS structure_id,\n";
    select += "'' AS sprayoperator,\n";
    select += "'' sprayoperator_defaultLocale,\n";
//    select += "NULL AS operator_week,\n";
    select += "NULL AS operator_target,\n";
    // team stuff
    select += "" + teamSprayStatusTable + "." + sprayTeamCol + " AS sprayteam,\n";
    select += "(SELECT st." + teamIdCol + " FROM " + sprayTeamTable + " st WHERE st.id = " + teamSprayStatusTable + "." + sprayTeamCol + ") AS sprayteam_defaultLocale,\n";
    select += "" + teamSprayStatusTable + "." + teamLeaderCol + " AS sprayleader,\n";
    select += "(SELECT tm." + memberIdCol + " || ' - ' || p." + firstNameCol + " || ' ' || p." + lastNameCol + " FROM " + teamMemberTable + " tm , " + personTable + " AS p WHERE p.id = tm."+personCol+" AND tm.id = " + teamSprayStatusTable + "." + teamLeaderCol + ") AS sprayleader_defaultLocale,\n";
//    select += "" + teamSprayStatusTable + "." + teamSprayWeekCol + " AS team_week,\n";
    select += "" + teamSprayStatusTable + "." + targetCol + " AS team_target,\n";
    // zone stuff
    select += "" + zoneSprayTable + "." + supervisorCol + "  AS zone_supervisor,\n";
    select += "(SELECT  p." + firstNameCol + " || ' ' || p." + lastNameCol + " FROM " + personTable + " AS p WHERE p.id = " + zoneSprayTable + "." + supervisorCol + ") AS zone_supervisor_defaultLocale,\n";
//    select += "" + zoneSprayTable + "." + sprayWeekCol + " AS zone_week,\n";
//    select += "" + zoneSprayTable + "." + zsTargetCol + " AS zone_target,\n";
    // target stuff
    select += "sprayseason.id  AS spray_season,\n";

    select += "NULL AS planed_operator_target,\n";

    select += "(SELECT weekly_target FROM " + viewName + " AS  spray_target_view WHERE " + "spray_target_view.target_id = " + teamSprayStatusTable + "." + sprayTeamCol + " \n"
           + "AND spray_target_view.season_id = sprayseason.id \n"
//           + "AND spray_target_view.target_week = " + teamSprayStatusTable + "." + teamSprayWeekCol
           + ") AS planed_team_target,\n";

    String diseaseCol = QueryUtil.getColumnName(ZoneSpray.getDiseaseMd());
    select += "get_seasonal_spray_target_by_geoEntityId_and_date(" + abstractSprayTable + "." + geoEntityCol + "," + abstractSprayTable + "." + sprayDateCol+","+zoneSprayTable+"."+diseaseCol+""
    + ") AS planed_area_target,\n";
    // spray stuff
    select += "" + roomsCol + ",\n";
    select += "" + structuresCol + ",\n";
    select += "" + householdsCol + ",\n";
    select += "" + sprayedRoomsCol+" AS sprayedRooms,\n";
    select += "" + sprayedStructuresCol+" AS sprayedStructures,\n";
    select += "" + sprayedHouseholdsCol+" AS sprayedHouseholds,\n";
    select += "" + prevSprayedStructuresCol+" AS prevSprayedStructures,\n";
    select += "" + prevSprayedHouseholdsCol+" AS prevSprayedHouseholds,\n";
    select += "" + peopleCol+",\n";
    select += "" + bedNetsCol+" AS bedNets,\n";
    select += "" + roomsWithBedNetsCol+" AS roomsWithBedNets,\n";
    select += "" + lockedCol + ",\n";
    select += "" + refusedCol + ",\n";
    select += "" + otherCol + ",\n";
    select += "" + zoneSprayTable+"."+diseaseCol+" AS disease,\n";
    select += "" + teamSprayStatusTable + "." + receivedCol + ",\n";
    select += "" + teamSprayStatusTable + "." + usedCol + ",\n";
    select += "" + teamSprayStatusTable + "." + refillsCol + ",\n";
    select += "" + teamSprayStatusTable + "." + returnCol + ",\n";
    select += "(" + roomsCol + " - " + sprayedRoomsCol + ") AS room_unsprayed,\n";
    select += "(" + structuresCol + " - " + sprayedStructuresCol + ") AS structure_unsprayed,\n";
    select += "(" + householdsCol + " - " + sprayedHouseholdsCol + ") AS household_unsprayed,\n";

    select += "1 AS sprayedrooms_share,\n";
    select += "1 AS sprayedstructures_share,\n";
    select += "1 AS sprayedhouseholds_share,\n";

    String from = " FROM ";
    // get the main tables
    from += zoneSprayTable + " AS " + zoneSprayTable + ",\n";
    from += teamSprayStatusTable + " AS " + teamSprayStatusTable + ",\n";
    from += abstractSprayTable + " AS " + abstractSprayTable + "\n";
    from += " LEFT JOIN ";
    from += malariaSeasonTable + " AS sprayseason ";
    
    String seasonDiseaseCol = QueryUtil.getColumnName(MalariaSeason.getDiseaseMd());
    String diseaseId = Disease.getCurrent().getId();
    from += "ON " + abstractSprayTable + "." + sprayDateCol + " BETWEEN sprayseason." + startDateCol + " AND sprayseason." + endDateCol + " AND '"+diseaseId+"' = sprayseason."+seasonDiseaseCol+" \n";

    String where = "";

    // join main tables
    where += "AND " + abstractSprayTable + ".id = " + zoneSprayTable + ".id \n";
    where += "AND " + zoneSprayTable + ".id = " + teamSprayStatusTable + "." + sprayCol + " \n";
    where += "AND "+zoneSprayTable+"."+diseaseCol+" = '"+diseaseId+"' \n";

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);

    return select + "\n" + from + "\n" + where;
  }

}
