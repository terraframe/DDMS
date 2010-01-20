package dss.vector.solutions.irs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;

public class TeamSpray extends TeamSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
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

  public static void createTempTable(String tableName)
  {
    String sql = "DROP VIEW IF EXISTS " + tableName + ";\n";
    sql += "CREATE VIEW " + tableName + " AS ";
    sql += TeamSpray.getTempTableSQL("")+ ";\n";
    System.out.println(sql);
    Database.parseAndExecute(sql);
  }

  public static String getTempTableSQL(String viewName)
  {
//    String select = "SELECT spraystatus.id,\n";
//
//    select += "'2' AS aggregation_level,\n";
//    //operator stuff
//    select += "'' AS household_id,\n";
//    select += "'' AS structure_id,\n";
//    select += "operatorspray."+OperatorSpray.SPRAYOPERATOR+" AS sprayoperator,\n";
//    select += "sprayoperator.operatorid || ' - ' || person.firstname || ' - ' || person.lastname AS sprayoperator_defaultLocale,\n";
//    select += "operatorspray."+OperatorSpray.OPERATORSPRAYWEEK+" AS operator_week,\n";
//    select += "NULL AS operator_target,\n";
//    //team stuff
//    select += "sprayteam."+SprayTeam.ID+" AS sprayteam,\n";
//    select += "sprayteam."+SprayTeam.TEAMID+" AS sprayteam_defaultLocale,\n";
//    select += "actorspray."+ActorSpray.TEAMLEADER+" AS sprayleader,\n";
//    select += "sprayleader.operatorid || ' - ' || person2.firstname || ' - ' || person2.lastname AS sprayleader_defaultLocale,\n";
//    select += "actorspray."+ActorSpray.TEAMSPRAYWEEK+" AS team_week,\n";
//    select += "actorspray."+ActorSpray.TARGET+" AS team_target,\n";
//    //zone stuff
//    select += "'' AS zone_supervisor,\n";
//    select += "'' AS zone_supervisor_defaultLocale,\n";
//    select += "CAST(NULL AS INT)  AS zone_week,\n";
//    select += "CAST(NULL AS INT) AS zone_target,\n";
//    //target stuff
//    //select += "sprayseason.id  AS spray_season,\n";
//    select += "(SELECT weekly_target FROM " + viewName + " AS  spray_target_view WHERE "+
//              "spray_target_view.target_id = sprayoperator.id \n"+
//              "AND spray_target_view.season_id = sprayseason.id \n" +
//              "AND spray_target_view.target_week = operatorspray."+OperatorSpray.OPERATORSPRAYWEEK+")  AS planed_operator_target,\n";
//    select += "(SELECT weekly_target FROM " + viewName + " AS  spray_target_view WHERE "+
//              "spray_target_view.target_id = sprayteam.id \n"+
//              "AND spray_target_view.season_id = sprayseason.id \n" +
//              "AND spray_target_view.target_week = actorspray."+ActorSpray.TEAMSPRAYWEEK+") AS planed_team_target,\n";
//    select += "(SELECT weekly_target FROM " + viewName + " AS  spray_target_view WHERE "+
//              "spray_target_view.target_id = spraydata.geoentity  \n"+
//              "AND spray_target_view.season_id = sprayseason.id \n" +
//              "AND spray_target_view.target_week = EXTRACT(WEEK FROM spraydata.spraydate)"+") AS planed_area_target,\n";
//
//
//    String from = " FROM ";
//    //get the main tables
//    from += MdBusiness.getMdBusiness(TeamSpray.CLASS).getTableName() + " AS teamspray,\n";
//    from += MdBusiness.getMdBusiness(SprayTeam.CLASS).getTableName() + " AS sprayteam,\n";
//    from += "inteam AS inteam,\n";
//    from += MdBusiness.getMdBusiness(OperatorSpray.CLASS).getTableName() + " AS operatorspray,\n";
//    from += MdBusiness.getMdBusiness(ActorSpray.CLASS).getTableName() + " AS actorspray,\n";
//    from += MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName() + " AS abstractspray_team,\n";
//    from += MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName() + " AS abstractspray_operator,\n";
//    from += MdBusiness.getMdBusiness(SprayStatus.CLASS).getTableName() + " AS spraystatus,\n";
//    from += MdBusiness.getMdBusiness(SprayOperator.CLASS).getTableName() + " AS sprayoperator,\n";
//    from += MdBusiness.getMdBusiness(Person.CLASS).getTableName() + " AS person,\n";
//    from += MdBusiness.getMdBusiness(SprayOperator.CLASS).getTableName() + " AS sprayleader,\n";
//    from += MdBusiness.getMdBusiness(Person.CLASS).getTableName() + " AS person2,\n";
//    from += MdBusiness.getMdBusiness(SprayData.CLASS).getTableName() + " AS spraydata \n";
//    from += " LEFT JOIN " ;
//    from += MdBusiness.getMdBusiness(MalariaSeason.CLASS).getTableName() + " AS sprayseason ";
//    from += "ON spraydata.spraydate BETWEEN sprayseason.startdate AND sprayseason.enddate \n";
//
//    String where = "";
//    //join the team spray to the operator spray
//    where += "AND abstractspray_team.spraydata = abstractspray_operator.spraydata \n";
//
//    // join the teamspray to the team
//    where += "AND teamspray.sprayteam = sprayteam.id \n";
//    where += "AND sprayteam.id = inteam.parent_id  \n";
//    where += "AND sprayoperator.id = inteam.child_id \n";
//
//    //join abstractspray_team to the team spray
//    where += "AND teamspray.id = abstractspray_team.id \n";
//    where += "AND teamspray.id = actorspray.id \n";
//    where += "AND spraydata.id = abstractspray_team.spraydata \n";
//
//    //join abstractspray_oporator
//    where += "AND abstractspray_operator.id = operatorspray.id \n";
//    where += "AND spraystatus.spray = operatorspray.id \n";
//
//    //join the people
//    where += "AND operatorspray.sprayoperator = sprayoperator.id \n";
//    where += "AND person.id = sprayoperator.person \n";
//    where += "AND actorspray.teamleader = sprayleader.id \n";
//    where += "AND person2.id = sprayleader.person \n";
//
//
//
//    select = select.substring(0, select.length() - 2);
//    from = from.substring(0, from.length() - 2);
//    where = "WHERE " + where.substring(3, where.length() - 2);
//
//    return select + "\n" + from + "\n" + where;
    
    return "";
  }
}
