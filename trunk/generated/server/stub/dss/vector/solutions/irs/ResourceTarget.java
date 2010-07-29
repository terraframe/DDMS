package dss.vector.solutions.irs;

import java.lang.reflect.Method;

import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.MdssLog;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MalariaSeason;

public class ResourceTarget extends ResourceTargetBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240257020374L;

  public ResourceTarget()
  {
    super();
  }
  
  @Override
  public void apply() {
	    if (this.isNew() && this.getDisease() == null) {
	    	this.setDisease(Disease.getCurrent());
	    }
	    
	super.apply();
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
      return this.getClassDisplayLabel();
    }
  }

  @Override
  protected String buildKey()
  {
    if (this.getTargeter() != null && this.getSeason() != null)
    {
      return this.getTargeterId() + "." + this.getSeason().getKey();
    }
    return this.getId();
  }

  private String getTargeterId()
  {
    if (this.getTargeter() instanceof TeamMember)
    {
      return ( (TeamMember) this.getTargeter() ).getMemberId();
    }

    return ( (SprayTeam) this.getTargeter() ).getTeamId();
  }

  public static String getTargeterName(Targeter targeter)
  {
    if (targeter instanceof TeamMember)
    {
      TeamMember so = (TeamMember) targeter;
      return ( so.getLabel() );
    }

    if (targeter instanceof SprayTeam)
    {
      SprayTeam st = (SprayTeam) targeter;

      return ( st.getLabel() );
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

  public static void createDatabaseView(String viewName)
  {
    String sql = "DROP VIEW IF EXISTS " + viewName + ";\n";
    sql += "CREATE VIEW " + viewName + " AS ";
    sql += ResourceTarget.getTempTableSQL();
    // sql += "ORDER BY season_id;\n";
    sql += ";\n";
    MdssLog.debug(sql);
    Database.parseAndExecute(sql);

  }

  public static String getTempTableSQL()
  {
    String sql = "";
    sql += ResourceTarget.getTargetSQL(MdBusiness.getMdBusiness(ResourceTarget.CLASS).getTableName(), "targeter");
    sql += " ";
    return sql;

  }

  public static String getTargetSQL(String tableName, String targetColumn)
  {
    Integer number_of_weeks = 53;

    String weeks = "";
    for (Integer i = 0; i < number_of_weeks; i++)
    {
      weeks += "target_" + i + ",";
      if (i % 10 == 0)
        weeks += "\n";
    }
    weeks = weeks.substring(0, weeks.length() - 1);

    String select = "SELECT tar.targeter AS target_id,\n";
    select += "tar.season AS season_id,\n";
    select += "i AS target_week,\n";
    select += "target_array[i] AS weekly_target,\n";

    String from = "FROM ";
    from += "(SELECT " + targetColumn + " AS targeter, season, ARRAY[" + weeks + "] AS target_array FROM " + tableName + ") AS tar ";
    from += "CROSS JOIN generate_series(1, " + ( number_of_weeks + 1 ) + ") AS i \n";

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);

    return select + "\n" + from + "\n";
  }
}
