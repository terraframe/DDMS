package dss.vector.solutions.irs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.Person;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.generated.GeoEntity;

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
    String select = "SELECT teamspray.id,\n";

    select += "'2'::TEXT AS aggregation_level,\n";
    // operator stuff
    select += "'' AS household_id,\n";
    select += "'' AS structure_id,\n";
    select += "operatorspraystatus." + OperatorSprayStatus.SPRAYOPERATOR + " AS sprayoperator,\n";
    select += "sprayoperator."+TeamMember.MEMBERID+" || ' - ' || person.firstname  || person.lastname AS sprayoperator_defaultLocale,\n";
    select += "operatorspraystatus." + OperatorSprayStatus.OPERATORSPRAYWEEK + " AS operator_week,\n";
    select += "NULL AS operator_target,\n";
    // team stuff
    select += "teamspray." + TeamSpray.SPRAYTEAM + " AS sprayteam,\n";
    select += "(SELECT st." + SprayTeam.TEAMID + " FROM "+MdBusiness.getMdBusiness(SprayTeam.CLASS).getTableName()+" st WHERE st.id = teamspray." + TeamSpray.SPRAYTEAM + ") AS sprayteam_defaultLocale,\n";
    select += "teamspray." + TeamSpray.TEAMLEADER + " AS sprayleader,\n";
    select += "(SELECT tm."+TeamMember.MEMBERID+" || ' - ' || p.firstname || p.lastname FROM "+MdBusiness.getMdBusiness(TeamMember.CLASS).getTableName()+" tm , "+MdBusiness.getMdBusiness(Person.CLASS).getTableName() + " AS p WHERE p.id = tm.id AND tm.id = teamspray." + TeamSpray.TEAMLEADER + ") AS sprayleader_defaultLocale,\n";
    select += "teamspray." + TeamSpray.TEAMSPRAYWEEK + " AS team_week,\n";
    select += "teamspray." + TeamSpray.TARGET + " AS team_target,\n";
    // zone stuff
    select += "''::TEXT AS zone_supervisor,\n";
    select += "''::TEXT AS zone_supervisor_defaultLocale,\n";
    select += "NULL::INT  AS zone_week,\n";
    select += "NULL::INT  AS zone_target,\n";
    // target stuff
    select += "sprayseason.id  AS spray_season,\n";
    
    select += "(SELECT weekly_target FROM " + viewName + " AS  spray_target_view WHERE " + "spray_target_view.target_id = sprayoperator.id \n" + "AND spray_target_view.season_id = sprayseason.id \n" + "AND spray_target_view.target_week = operatorspraystatus." + OperatorSprayStatus.OPERATORSPRAYWEEK
        + ") AS planed_operator_target,\n";
    
    select += "(SELECT weekly_target FROM " + viewName + " AS  spray_target_view WHERE " + "spray_target_view.target_id = teamspray." + TeamSpray.SPRAYTEAM + " \n" + "AND spray_target_view.season_id = sprayseason.id \n" + "AND spray_target_view.target_week = teamspray." + TeamSpray.TEAMSPRAYWEEK
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
    select += "operatorspraystatus.received,\n";
    select += "operatorspraystatus.used,\n";
    select += "operatorspraystatus.refills,\n";
    select += "operatorspraystatus.returned,\n";
    select += "(rooms - sprayedrooms) AS room_unsprayed,\n";
    select += "(structures - sprayedstructures) AS structure_unsprayed,\n";
    select += "(households - sprayedhouseholds) AS household_unsprayed,\n";
    
    

    String from = " FROM ";
    // get the main tables
    from += MdBusiness.getMdBusiness(TeamSpray.CLASS).getTableName() + " AS teamspray,\n";
    from += MdBusiness.getMdBusiness(OperatorSprayStatus.CLASS).getTableName() + " AS operatorspraystatus,\n";
    from += MdBusiness.getMdBusiness(TeamMember.CLASS).getTableName() + " AS sprayoperator,\n";
    from += MdBusiness.getMdBusiness(Person.CLASS).getTableName() + " AS person,\n";
    from += MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName() + " AS abstractspray\n";
    from += " LEFT JOIN ";
    from += MdBusiness.getMdBusiness(MalariaSeason.CLASS).getTableName() + " AS sprayseason ";
    from += "ON abstractspray.spraydate BETWEEN sprayseason.startdate AND sprayseason.enddate \n";

    String where = "";

    // join main tables
    where += "AND abstractspray.id = teamspray.id\n";
    where += "AND teamspray.id = operatorspraystatus.spray\n";
    where += "AND operatorspraystatus.sprayoperator = sprayoperator.id \n";
    where += "AND sprayoperator.person = person.id \n";

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);

    return select + "\n" + from + "\n" + where;
  }
  
 
}
