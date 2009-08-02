package dss.vector.solutions.irs;

import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.Person;
import dss.vector.solutions.general.MalariaSeason;


public class OperatorSpray extends OperatorSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240853391117L;

  public OperatorSpray()
  {
    super();
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

    this.populateView(view);

    return view;
  }

  public void populateView(OperatorSprayView view)
  {
    super.populateView(view);

    view.setOperatorSprayWeek(this.getOperatorSprayWeek());
    view.setSprayOperator(this.getSprayOperator());
    view.setSprayId(this.getId());
  }

  @Override
  public SprayStatusView getSprayStatusView()
  {
    return new OperatorSprayStatusView();
  }

  public static OperatorSprayView getView(String id)
  {
    return OperatorSpray.get(id).getView();
  }

  public static OperatorSpray find(SprayData data, SprayOperator operator)
  {
    if(data == null || operator == null)
    {
      return null;
    }

    OperatorSprayQuery query = new OperatorSprayQuery(new QueryFactory());
    query.WHERE(query.getSprayData().EQ(data));
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

  public static OperatorSpray findOrCreate(SprayData data, SprayOperator operator)
  {
    OperatorSpray spray = OperatorSpray.find(data, operator);

    if(spray == null)
    {
      spray = new OperatorSpray();
    }

    return spray;
  }

  public static void createTempTable(String tableName)
  {
    String sql = "DROP TABLE IF EXISTS " + tableName + ";\n";
    sql += "CREATE TEMP TABLE " + tableName + " AS ";
    sql += OperatorSpray.getTempTableSQL()+ ";\n";
    System.out.println(sql);
    Database.parseAndExecute(sql);
  }

  public static String getTempTableSQL()
  {
    String select = "SELECT spraystatus.id,\n";

    select += "'1' AS aggregation_level,\n";
    //operator stuff
    select += "householdspraystatus."+HouseholdSprayStatus.HOUSEHOLDID+" AS household_id,\n";
    select += "householdspraystatus."+HouseholdSprayStatus.STRUCTUREID+" AS structure_id,\n";
    select += "operatorspray."+OperatorSpray.SPRAYOPERATOR+" AS sprayoperator,\n";
    select += "sprayoperator.operatorid || ' - ' || person.firstname || ' - ' || person.lastname AS sprayoperator_defaultLocale,\n";
    select += "operatorspray."+OperatorSpray.OPERATORSPRAYWEEK+" AS operator_week,\n";
    select += "actorspray."+ActorSpray.TARGET+" AS operator_target,\n";
    //team stuff
    select += "sprayteam."+SprayTeam.ID+" AS sprayteam,\n";
    select += "sprayteam."+SprayTeam.TEAMID+" AS sprayteam_defaultLocale,\n";
    select += "actorspray."+ActorSpray.TEAMLEADER+" AS sprayleader,\n";
    select += "sprayleader.operatorid || ' - ' || person2.firstname || ' - ' || person2.lastname AS sprayleader_defaultLocale,\n";
    select += "actorspray."+ActorSpray.TEAMSPRAYWEEK+" AS team_week,\n";
    select += "NULL AS team_target,\n";
    //zone stuff
    select += "'' AS zone_supervisor,\n";
    select += "CAST(NULL AS INT)  AS zone_week,\n";
    select += "CAST(NULL AS INT)  AS zone_target,\n";
    //target stuff
    select += "actorspray."+ActorSpray.TARGET+" AS daily_target,\n";
    select += "sprayoperator.id AS targetable_id,\n";
    select += "sprayseason.id  AS spray_season,\n";
    select += "operatorspray."+OperatorSpray.OPERATORSPRAYWEEK+" AS spray_week,\n";

    String from = " FROM ";
    //get the main tables
    from += MdBusiness.getMdBusiness(SprayTeam.CLASS).getTableName() + " AS sprayteam,\n";
    from += "inteam AS inteam,\n";
    from += MdBusiness.getMdBusiness(OperatorSpray.CLASS).getTableName() + " AS operatorspray,\n";
    from += MdBusiness.getMdBusiness(ActorSpray.CLASS).getTableName() + " AS actorspray,\n";
    from += MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName() + " AS abstractspray,\n";
    from += MdBusiness.getMdBusiness(HouseholdSprayStatus.CLASS).getTableName() + " AS householdspraystatus,\n";
    from += MdBusiness.getMdBusiness(SprayStatus.CLASS).getTableName() + " AS spraystatus,\n";
    from += MdBusiness.getMdBusiness(SprayOperator.CLASS).getTableName() + " AS sprayoperator,\n";
    from += MdBusiness.getMdBusiness(Person.CLASS).getTableName() + " AS person,\n";
    from += MdBusiness.getMdBusiness(SprayOperator.CLASS).getTableName() + " AS sprayleader,\n";
    from += MdBusiness.getMdBusiness(Person.CLASS).getTableName() + " AS person2,\n";
    from += MdBusiness.getMdBusiness(SprayData.CLASS).getTableName() + " AS spraydata \n";
    from += " LEFT JOIN " ;
    from += MdBusiness.getMdBusiness(MalariaSeason.CLASS).getTableName() + " AS sprayseason ";
    from += "ON spraydata.spraydate BETWEEN sprayseason.startdate AND sprayseason.enddate,\n";


    String where = "";
    // join the spray team to the oporator
    where += "AND sprayteam.id = inteam.parent_id  \n";
    where += "AND sprayoperator.id = inteam.child_id \n";

    //join main tables
    where += "AND spraydata.id = abstractspray.spraydata \n";
    where += "AND abstractspray.id = actorspray.id \n";
    where += "AND operatorspray.id = actorspray.id \n";
    where += "AND householdspraystatus.id = spraystatus.id \n";
    where += "AND spraystatus.spray = operatorspray.id \n";
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
