package dss.vector.solutions.irs;

import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.Person;

public class TeamSpray extends TeamSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860702014L;

  public TeamSpray()
  {
    super();
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

    this.populateView(view);

    return view;
  }

  public void populateView(TeamSprayView view)
  {
    super.populateView(view);

    view.setSprayTeam(this.getSprayTeam());
  }

  @Override
  public SprayStatusView getSprayStatusView()
  {
    return new TeamSprayStatusView();
  }

  public static TeamSprayView getView(String id)
  {
    return TeamSpray.get(id).getView();
  }

  public static TeamSpray find(SprayData data, SprayTeam team)
  {
    TeamSprayQuery query = new TeamSprayQuery(new QueryFactory());
    query.WHERE(query.getSprayData().EQ(data));
    query.AND(query.getSprayTeam().EQ(team));

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

  public static TeamSpray findOrCreate(SprayData data, SprayTeam team)
  {
    TeamSpray spray = TeamSpray.find(data, team);

    if(spray == null)
    {
      spray = new TeamSpray();
    }

    return spray;
  }

  public static void createTempTable(String tableName)
  {
    String sql = "DROP VIEW IF EXISTS " + tableName + ";\n";
    sql += "CREATE VIEW " + tableName + " AS ";
    sql += TeamSpray.getTempTableSQL()+ ";\n";
    System.out.println(sql);
    Database.parseAndExecute(sql);
  }

  public static String getTempTableSQL()
  {
    String select = "SELECT spraystatus.id,\n";

    select += "'2' AS aggregation_level,\n";
    //operator stuff
    select += "'' AS household_id,\n";
    select += "'' AS structure_id,\n";
    select += "operatorspray."+OperatorSpray.SPRAYOPERATOR+" AS sprayoperator,\n";
    select += "sprayoperator.operatorid || ' - ' || person.firstname || ' - ' || person.lastname AS sprayoperator_defaultLocale,\n";
    select += "operatorspray."+OperatorSpray.OPERATORSPRAYWEEK+" AS operator_week,\n";
    select += "NULL AS operator_target,\n";
    //team stuff
    select += "sprayteam."+SprayTeam.ID+" AS sprayteam,\n";
    select += "sprayteam."+SprayTeam.TEAMID+" AS sprayteam_defaultLocale,\n";
    select += "actorspray."+ActorSpray.TEAMLEADER+" AS sprayleader,\n";
    select += "sprayleader.operatorid || ' - ' || person2.firstname || ' - ' || person2.lastname AS sprayleader_defaultLocale,\n";
    select += "actorspray."+ActorSpray.TEAMSPRAYWEEK+" AS team_week,\n";
    select += "actorspray."+ActorSpray.TARGET+" AS team_target,\n";
    //zone stuff
    select += "'' AS zone_supervisor,\n";
    select += "CAST(NULL AS INT)  AS zone_week,\n";
    select += "CAST(NULL AS INT) AS zone_target,\n";



    String from = " FROM ";
    //get the main tables
    from += MdBusiness.getMdBusiness(TeamSpray.CLASS).getTableName() + " AS teamspray,\n";
    from += MdBusiness.getMdBusiness(SprayTeam.CLASS).getTableName() + " AS sprayteam,\n";
    from += "inteam AS inteam,\n";
    from += MdBusiness.getMdBusiness(OperatorSpray.CLASS).getTableName() + " AS operatorspray,\n";
    from += MdBusiness.getMdBusiness(ActorSpray.CLASS).getTableName() + " AS actorspray,\n";
    from += MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName() + " AS abstractspray_team,\n";
    from += MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName() + " AS abstractspray_operator,\n";
    from += MdBusiness.getMdBusiness(SprayStatus.CLASS).getTableName() + " AS spraystatus,\n";
    from += MdBusiness.getMdBusiness(SprayData.CLASS).getTableName() + " AS spraydata,\n";
    from += MdBusiness.getMdBusiness(SprayOperator.CLASS).getTableName() + " AS sprayoperator,\n";
    from += MdBusiness.getMdBusiness(Person.CLASS).getTableName() + " AS person,\n";
    from += MdBusiness.getMdBusiness(SprayOperator.CLASS).getTableName() + " AS sprayleader,\n";
    from += MdBusiness.getMdBusiness(Person.CLASS).getTableName() + " AS person2,\n";

    String where = "";
    //join the team spray to the operator spray
    where += "AND abstractspray_team.spraydata = abstractspray_operator.spraydata \n";

    // join the teamspray to the team
    where += "AND teamspray.sprayteam = sprayteam.id \n";
    where += "AND sprayteam.id = inteam.parent_id  \n";
    where += "AND sprayoperator.id = inteam.child_id \n";

    //join abstractspray_team to the team spray
    where += "AND teamspray.id = abstractspray_team.id \n";
    where += "AND teamspray.id = actorspray.id \n";
    where += "AND spraydata.id = abstractspray_team.spraydata \n";

    //join abstractspray_oporator
    where += "AND abstractspray_operator.id = operatorspray.id \n";
    where += "AND spraystatus.spray = operatorspray.id \n";

    //join the people
    where += "AND operatorspray.sprayoperator = sprayoperator.id \n";
    where += "AND person.id = sprayoperator.person \n";
    where += "AND actorspray.teamleader = sprayleader.id \n";
    where += "AND person2.id = sprayleader.person \n";



    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);

    return select + "\n" + from + "\n" + where;
  }
}
