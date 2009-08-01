package dss.vector.solutions.irs;

import java.lang.reflect.Method;

import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.Person;
import dss.vector.solutions.general.MalariaSeason;

public class ResourceTarget extends ResourceTargetBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240257020374L;

  public ResourceTarget()
  {
    super();
  }

  public static String getTargeterName(Targeter targeter)
  {
    if (targeter instanceof SprayOperator)
    {
      SprayOperator so = (SprayOperator) targeter;
      return ( so.getOperatorId() + " - " + so.getPerson().getFirstName() + " " + so.getPerson().getLastName() );
    }

    if (targeter instanceof SprayTeam)
    {
      SprayTeam st = (SprayTeam) targeter;

      if (!st.getAllTeamLeader().getAll().isEmpty())
      {
        Person leader = st.getAllTeamLeader().getAll().get(0).getPerson();
        String leader_name = " - " + leader.getFirstName() + " " + leader.getLastName();
      }

      return ( st.getTeamId() + "" );
    }
    return null;
  }

  public String getTargeterName()
  {
    return ResourceTarget.getTargeterName(this.getTargeter());
  }

  public ResourceTargetView getView()
  {
    ResourceTargetView view = new ResourceTargetView();
    view.setTargeter(this.getTargeter());
    view.setSeason(this.getSeason());
    view.setTargetId(this.getId());
    view.setTargeterName(this.getTargeterName());

    for (int i = 0; i < 53; i++)
    {
      String setterName = "setTarget_" + i;
      String getterName = "getTarget_" + i;

      try
      {
        Method setter = ResourceTargetView.class.getMethod(setterName, Integer.class);
        Method getter = ResourceTarget.class.getMethod(getterName);

        setter.invoke(view, (Integer) getter.invoke(this));
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }

    return view;
  }

  public static ResourceTargetView getView(String id)
  {
    return ResourceTarget.get(id).getView();
  }

  public static ResourceTargetView searchByTargeterAndSeason(Targeter resource, MalariaSeason season)
  {
    ResourceTargetQuery query = new ResourceTargetQuery(new QueryFactory());
    query.WHERE(query.getTargeter().EQ(resource));
    query.AND(query.getSeason().EQ(season));

    OIterator<? extends ResourceTarget> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next().getView();
      }
      else
      {
        ResourceTargetView view = new ResourceTargetView();
        view.setTargeter(resource);
        view.setSeason(season);
        view.setTargeterName(ResourceTarget.getTargeterName(resource));

        return view;
      }
    }
    finally
    {
      it.close();
    }
  }

  public static ResourceTargetView searchByTargeterIdAndSeason(String id, MalariaSeason season)
  {
    return searchByTargeterAndSeason(Targeter.get(id), season);
  }

  public static void createTempTable(String tableName)
  {
    String sql = "DROP VIEW IF EXISTS " + tableName + ";\n";
    sql += "CREATE VIEW " + tableName + " AS ";
    sql += ResourceTarget.getTempTableSQL(MdBusiness.getMdBusiness(ResourceTarget.CLASS).getTableName());
    sql += " UNION \n";
    sql += ResourceTarget.getTempTableSQL(MdBusiness.getMdBusiness(GeoTarget.CLASS).getTableName());
    sql += "ORDER BY season_id;\n";
    System.out.println(sql);
    Database.parseAndExecute(sql);

  }

  public static String getTempTableSQL(String tableName)
  {
    Integer number_of_weeks = 53;

    String weeks = "";
    for (Integer i = 0; i < number_of_weeks; i++)
    {
      weeks += "target_" + i + ",";
      if(i%10 == 0) weeks += "\n";
    }
    weeks = weeks.substring(0, weeks.length() - 1);

    String select = "SELECT id AS target_id,\n";
    select += "season AS season_id,\n";
    select += "spray_week AS target_week,\n";
    select += "startdate AS season_start,\n";
    select += "enddate AS season_end,\n";
    select += "target_array[spray_week] AS target,\n";

    /*
    switch (targetUnit)
    {
      case HOUSEHOLD:
        select += "(spraystatus.sprayedrooms / spraystatus.rooms) AS operational_coverage,\n";
        break;
      case STRUCTURE:
        select += "(spraystatus.sprayedrooms / spraystatus.rooms) AS operational_coverage,\n";
        break;
      case ROOM:
        select += "(spraystatus.sprayedrooms / spraystatus.rooms) AS operational_coverage,\n";
        break;
    }
    */

    String seasonTable = MdBusiness.getMdBusiness(MalariaSeason.CLASS).getTableName();

    String from = "FROM ";
    from += "(SELECT tar.id,season,startdate,enddate, ARRAY["+weeks+"] AS target_array FROM "+tableName+" AS tar " +
    		"JOIN "+seasonTable+" AS ms ON  tar.season = ms.id) AS resourcetargets \n";
    from += "CROSS JOIN generate_series(1, " + ( number_of_weeks + 1 ) + ") AS spray_week \n";

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);

    return select + "\n" + from + "\n";
  }
}
