package dss.vector.solutions.irs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.Person;
import dss.vector.solutions.general.MalariaSeason;

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
    System.out.println(sql);
    Database.parseAndExecute(sql);
  }

  
  public static String getTempTableSQL(String viewName)
  {
    String select = "SELECT zonespray.id,\n";

    select += "'3'::TEXT AS aggregation_level,\n";
    // operator stuff
    select += "'' AS household_id,\n";
    select += "'' AS structure_id,\n";
    select += "'' AS sprayoperator,\n";
    select += "'' sprayoperator_defaultLocale,\n";
    select += "NULL AS operator_week,\n";
    select += "NULL AS operator_target,\n";
    // team stuff
    select += "teamspraystatus." + TeamSprayStatus.SPRAYTEAM + " AS sprayteam,\n";
    select += "(SELECT st." + SprayTeam.TEAMID + " FROM "+MdBusiness.getMdBusiness(SprayTeam.CLASS).getTableName()+" st WHERE st.id = teamspraystatus." + TeamSprayStatus.SPRAYTEAM + ") AS sprayteam_defaultLocale,\n";
    select += "teamspraystatus." + TeamSpray.TEAMLEADER + " AS sprayleader,\n";
    select += "(SELECT tm."+TeamMember.MEMBERID+" || ' - ' || p.firstname || p.lastname FROM "+MdBusiness.getMdBusiness(TeamMember.CLASS).getTableName()+" tm , "+MdBusiness.getMdBusiness(Person.CLASS).getTableName() + " AS p WHERE p.id = tm.id AND tm.id = teamspraystatus." + TeamSprayStatus.TEAMLEADER + ") AS sprayleader_defaultLocale,\n";
    select += "teamspraystatus." + TeamSprayStatus.TEAMSPRAYWEEK + " AS team_week,\n";
    select += "teamspraystatus." + TeamSprayStatus.TARGET + " AS team_target,\n";
    // zone stuff
    select += "zonespray." + ZoneSpray.SUPERVISOR + "  AS zone_supervisor,\n";
    select += "(SELECT  p.firstname || p.lastname FROM "+MdBusiness.getMdBusiness(Person.CLASS).getTableName() + " AS p WHERE p.id = zonespray." + ZoneSpray.SUPERVISOR + ") AS zone_supervisor_defaultLocale,\n";
    select += "zonespray." + ZoneSpray.SPRAYWEEK + " AS zone_week,\n";
    select += "zonespray." + ZoneSpray.TARGET + " AS zone_target,\n";
    // target stuff
    select += "sprayseason.id  AS spray_season,\n";
    
    select += "NULL AS planed_operator_target,\n";
    
    select += "(SELECT weekly_target FROM " + viewName + " AS  spray_target_view WHERE " + "spray_target_view.target_id = teamspraystatus." + TeamSprayStatus.SPRAYTEAM + " \n" + "AND spray_target_view.season_id = sprayseason.id \n" + "AND spray_target_view.target_week = teamspraystatus." + TeamSprayStatus.TEAMSPRAYWEEK
        + ") AS planed_team_target,\n";

    select += "get_seasonal_spray_target_by_geoEntityId_and_date(abstractspray."+AbstractSpray.GEOENTITY+",abstractspray."+AbstractSpray.SPRAYDATE
        + ") AS planed_area_target,\n";
    //spray stuff
    select += "rooms,\n";
    select += "structures,\n";
    select += "households,\n";
    select += "sprayedrooms,\n";
    select += "sprayedstructures,\n";
    select += "sprayedhouseholds,\n";
    select += "prevsprayedstructures,\n";
    select += "prevsprayedhouseholds,\n";
    select += "people,\n";
    select += "bednets,\n";
    select += "roomswithbednets,\n";
    select += "locked,\n";
    select += "refused,\n";
    select += "other,\n";
    select += "teamspraystatus.received,\n";
    select += "teamspraystatus.used,\n";
    select += "teamspraystatus.refills,\n";
    select += "teamspraystatus.returned,\n";
    select += "(rooms - sprayedrooms) AS room_unsprayed,\n";
    select += "(structures - sprayedstructures) AS structure_unsprayed,\n";
    select += "(households - sprayedhouseholds) AS household_unsprayed,\n";
    
    select += "1 AS sprayedrooms_share,\n";
    select += "1 AS sprayedstructures_share,\n";
    select += "1 AS sprayedhouseholds_share,\n";
    
    

    String from = " FROM ";
    // get the main tables
    from += MdBusiness.getMdBusiness(ZoneSpray.CLASS).getTableName() + " AS zonespray,\n";
    from += MdBusiness.getMdBusiness(TeamSprayStatus.CLASS).getTableName() + " AS teamspraystatus,\n";
    from += MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName() + " AS abstractspray\n";
    from += " LEFT JOIN ";
    from += MdBusiness.getMdBusiness(MalariaSeason.CLASS).getTableName() + " AS sprayseason ";
    from += "ON abstractspray.spraydate BETWEEN sprayseason.startdate AND sprayseason.enddate \n";

    String where = "";

    // join main tables
    where += "AND abstractspray.id = zonespray.id \n";
    where += "AND zonespray.id = teamspraystatus.spray \n";

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);

    return select + "\n" + from + "\n" + where;
  }
  
}
