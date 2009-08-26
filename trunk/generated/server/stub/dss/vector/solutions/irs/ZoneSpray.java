package dss.vector.solutions.irs;

import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.Person;
import dss.vector.solutions.general.MalariaSeason;

public class ZoneSpray extends ZoneSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860676906L;

  public ZoneSpray()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    if(this.getSprayData() != null)
    {
      return this.getSprayData().getKey();
    }
    return this.getId();
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

    this.populateView(view);

    return view;
  }

  public void populateView(ZoneSprayView view)
  {
    super.populateView(view);

    view.setSupervisorName(this.getSupervisorName());
    view.setSupervisorSurname(this.getSupervisorSurname());
    view.setTarget(this.getTarget());
    view.setSprayWeek(this.getSprayWeek());
  }

  @Override
  public SprayStatusView getSprayStatusView()
  {
    return null;
  }

  public static ZoneSprayView getView(String id)
  {
    return ZoneSpray.get(id).getView();
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
    String select = "SELECT spraystatus.id,\n";

    select += "'3' AS aggregation_level,\n";
    //operator stuff
    select += "'' AS household_id,\n";
    select += "'' AS structure_id,\n";
    select += "'' AS sprayoperator,\n";
    select += "'' AS sprayoperator_defaultLocale,\n";
    select += "NULL AS operator_week,\n";
    select += "NULL AS operator_target,\n";
    //team stuff
    select += "sprayteam."+SprayTeam.ID+" AS sprayteam,\n";
    select += "sprayteam."+SprayTeam.TEAMID+" AS sprayteam_defaultLocale,\n";
    select += "actorspray."+ActorSpray.TEAMLEADER+" AS sprayleader,\n";
    select += "sprayleader.operatorid || ' - ' || person.firstname || ' ' || person.lastname AS sprayleader_defaultLocale,\n";
    select += "actorspray."+ActorSpray.TEAMSPRAYWEEK+" AS team_week,\n";
    select += "actorspray."+ActorSpray.TARGET+" AS team_target,\n";

    //zone stuff
    select += "zonespray."+ZoneSpray.SUPERVISORNAME+" || ' '|| zonespray."+ZoneSpray.SUPERVISORSURNAME+" AS zone_supervisor,\n";
    select += "zonespray."+ZoneSpray.SPRAYWEEK+" AS zone_week,\n";
    select += "zonespray."+ZoneSpray.TARGET+" AS zone_target,\n";
    //target stuff
    //select += "sprayseason.id  AS spray_season,\n";
    select += "NULL  AS planed_operator_target,\n";
    select += "(SELECT weekly_target FROM " + viewName + " AS  spray_target_view WHERE "+
           "spray_target_view.target_id = sprayteam.id \n"+
           "AND spray_target_view.season_id = sprayseason.id \n" +
           "AND spray_target_view.target_week = actorspray."+ActorSpray.TEAMSPRAYWEEK+") AS planed_team_target,\n";
    select += "(SELECT weekly_target FROM " + viewName + " AS  spray_target_view WHERE "+
           "spray_target_view.target_id = spraydata.geoentity  \n"+
           "AND spray_target_view.season_id = sprayseason.id \n" +
           "AND spray_target_view.target_week = EXTRACT(WEEK FROM spraydata.spraydate)"+") AS planed_area_target,\n";


    String from = " FROM ";
    from += MdBusiness.getMdBusiness(ZoneSpray.CLASS).getTableName() + " AS zonespray,\n";
    from += MdBusiness.getMdBusiness(TeamSpray.CLASS).getTableName() + " AS teamspray,\n";
    from += MdBusiness.getMdBusiness(SprayTeam.CLASS).getTableName() + " AS sprayteam,\n";
    from += "inteam AS inteam,\n";
    from += MdBusiness.getMdBusiness(ActorSpray.CLASS).getTableName() + " AS actorspray,\n";
    from += MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName() + " AS abstractspray_team,\n";
    from += MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName() + " AS abstractspray_zone,\n";
    from += MdBusiness.getMdBusiness(SprayStatus.CLASS).getTableName() + " AS spraystatus,\n";
    from += MdBusiness.getMdBusiness(SprayOperator.CLASS).getTableName() + " AS sprayleader,\n";
    from += MdBusiness.getMdBusiness(Person.CLASS).getTableName() + " AS person,\n";
    from += MdBusiness.getMdBusiness(SprayData.CLASS).getTableName() + " AS spraydata \n";
    from += " LEFT JOIN " ;
    from += MdBusiness.getMdBusiness(MalariaSeason.CLASS).getTableName() + " AS sprayseason ";
    from += "ON spraydata.spraydate BETWEEN sprayseason.startdate AND sprayseason.enddate,\n";

    String where = "";
    //join the team spray to the operator spray
    where += "AND abstractspray_team.spraydata = abstractspray_zone.spraydata \n";

    // join the teamspray to the team
    where += "AND teamspray.sprayteam = sprayteam.id \n";
    where += "AND teamspray.id = actorspray.id  \n";
    where += "AND sprayteam.id = inteam.parent_id  \n";
    where += "AND sprayleader.id = inteam.child_id \n";

    //join abstractspray_team to the team spray
    where += "AND teamspray.id = abstractspray_team.id \n";
    where += "AND teamspray.id = actorspray.id \n";
    where += "AND spraydata.id = abstractspray_team.spraydata \n";

    //join abstractspray_zone
    where += "AND abstractspray_zone.id = zonespray.id \n";
    where += "AND spraystatus.spray = teamspray.id \n";

    //join the people
    where += "AND actorspray.teamleader = sprayleader.id \n";
    where += "AND person.id = sprayleader.person \n";

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);

    return select + "\n" + from + "\n" + where;
  }

}
