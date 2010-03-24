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

public class OperatorSpray extends OperatorSprayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long          serialVersionUID = 1240853391117L;

  public OperatorSpray()
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
    if (this.getBrand() != null && this.getGeoEntity() != null && this.getSprayDate() != null && this.getSprayMethodForIndex() != null  && this.getSprayOperator() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      String dateFormat = format.format(this.getSprayDate());
      String methodName = this.getSprayMethodForIndex();

      return this.getBrand().getKey() + "." + this.getGeoEntity().getGeoId() + "." + dateFormat + "." + methodName + "." + this.getSprayOperator().getKey();
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

    view.populateView(this);

    return view;
  }
  
  public static OperatorSpray get(Date sprayDate, GeoEntity geoEntity, InsecticideBrand brand, TeamMember operator, SprayMethod... sprayMethod)
  {
    OperatorSprayQuery query = new OperatorSprayQuery(new QueryFactory());
    query.WHERE(query.getSprayDate().EQ(sprayDate));
    query.WHERE(query.getGeoEntity().EQ(geoEntity));
    query.WHERE(query.getBrand().EQ(brand));
    query.WHERE(query.getSprayMethod().containsAll(sprayMethod));
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

  public static String getTempTableSQL(String targetView, boolean grouped)
  {
    grouped = false;
    
    String select = "SELECT operatorspray.id,\n";

    select += "'1'::TEXT AS aggregation_level,\n";
    
    if (grouped)
    {
      select += "NULL AS household_id,\n";
      select += "NULL AS structure_id,\n";
    }
    else
    {
      select += "householdspraystatus." + HouseholdSprayStatus.HOUSEHOLDID + " AS household_id,\n";
      select += "householdspraystatus." + HouseholdSprayStatus.STRUCTUREID + " AS structure_id,\n";
    }
    // operator stuff
    select += "operatorspray." + OperatorSpray.SPRAYOPERATOR + " AS sprayoperator,\n";
    select += "sprayoperator."+TeamMember.MEMBERID+" || ' - ' || person.firstname  || person.lastname AS sprayoperator_defaultLocale,\n";
    select += "operatorspray." + OperatorSpray.OPERATORSPRAYWEEK + " AS operator_week,\n";
    select += "operatorspray." + OperatorSpray.TARGET + " AS operator_target,\n";
    // team stuff
    select += "operatorspray." + OperatorSpray.SPRAYTEAM + " AS sprayteam,\n";
    select += "(SELECT st." + SprayTeam.TEAMID + " FROM "+MdBusiness.getMdBusiness(SprayTeam.CLASS).getTableName()+" st WHERE st.id = operatorspray." + OperatorSpray.SPRAYTEAM + ") AS sprayteam_defaultLocale,\n";
    select += "operatorspray." + OperatorSpray.TEAMLEADER + " AS sprayleader,\n";
    select += "(SELECT tm."+TeamMember.MEMBERID+" || ' - ' || p.firstname || p.lastname FROM "+MdBusiness.getMdBusiness(TeamMember.CLASS).getTableName()+" tm , "+MdBusiness.getMdBusiness(Person.CLASS).getTableName() + " AS p WHERE p.id = tm.id AND tm.id = operatorspray." + OperatorSpray.TEAMLEADER + ") AS sprayleader_defaultLocale,\n";
    select += "operatorspray." + OperatorSpray.TEAMSPRAYWEEK + " AS team_week,\n";
    select += "NULL AS team_target,\n";
    // zone stuff
    select += "''::TEXT AS zone_supervisor,\n";
    select += "''::TEXT AS zone_supervisor_defaultLocale,\n";
    select += "NULL::INT  AS zone_week,\n";
    select += "NULL::INT  AS zone_target,\n";
    // target stuff
    select += "sprayseason.id  AS spray_season,\n";
    
    select += "(SELECT weekly_target FROM " + targetView + " AS  spray_target_view WHERE " + "spray_target_view.target_id = sprayoperator.id \n" + "AND spray_target_view.season_id = sprayseason.id \n" + "AND spray_target_view.target_week = operatorspray." + OperatorSpray.OPERATORSPRAYWEEK
        + ") AS planed_operator_target,\n";
    
    select += "(SELECT weekly_target FROM " + targetView + " AS  spray_target_view WHERE " + "spray_target_view.target_id = operatorspray." + OperatorSpray.SPRAYTEAM + " \n" + "AND spray_target_view.season_id = sprayseason.id \n" + "AND spray_target_view.target_week = operatorspray." + OperatorSpray.TEAMSPRAYWEEK
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
    
    select += "received,\n";
    select += "used,\n";
    select += "refills,\n";
    select += "returned,\n";
    select += "(rooms - sprayedrooms) AS room_unsprayed,\n";
    select += "(structures - sprayedstructures) AS structure_unsprayed,\n";
    select += "(households - sprayedhouseholds) AS household_unsprayed,\n";
    
    select += "sprayedrooms/nullif((SELECT SUM(sprayedrooms) from householdspraystatus hss where operatorspray.id = hss.spray),0)::float AS sprayedrooms_share,\n";
    select += "sprayedstructures/nullif((SELECT SUM(sprayedstructures) from householdspraystatus hss where operatorspray.id = hss.spray),0)::float AS sprayedstructures_share,\n";
    select += "sprayedhouseholds/nullif((SELECT SUM(sprayedhouseholds) from householdspraystatus hss where operatorspray.id = hss.spray),0)::float AS sprayedhouseholds_share,\n";
    
    String from = " FROM ";
    
    //must group to fix coverage...
    
    if (grouped)
    {
      String hss_grouped = "SELECT spray,\n" ;
        
      hss_grouped += "SUM(rooms) rooms,\n";
      hss_grouped += "SUM(structures) structures,\n";
      hss_grouped += "SUM(households) households,\n";
      hss_grouped += "SUM(sprayedrooms) sprayedrooms,\n";
      hss_grouped += "SUM(sprayedstructures) sprayedstructures,\n";
      hss_grouped += "SUM(sprayedhouseholds) sprayedhouseholds,\n";
      hss_grouped += "SUM(prevsprayedstructures) prevsprayedstructures,\n";
      hss_grouped += "SUM(prevsprayedhouseholds) prevsprayedhouseholds,\n";
      hss_grouped += "SUM(people) people,\n";
      hss_grouped += "SUM(bednets) bednets,\n";
      hss_grouped += "SUM(roomswithbednets) roomswithbednets,\n";
      hss_grouped += "SUM(refused) refused,\n";
      hss_grouped += "SUM(locked) locked,\n";
      hss_grouped += "SUM(other) other\n";
      hss_grouped += "FROM " +  MdBusiness.getMdBusiness(HouseholdSprayStatus.CLASS).getTableName() + "\n";
      hss_grouped += "GROUP BY spray";
      
      from += "("+hss_grouped +")" + " AS householdspraystatus,\n";
      
    }
    else
    {
      from += MdBusiness.getMdBusiness(HouseholdSprayStatus.CLASS).getTableName() + " AS householdspraystatus,\n";
    }
    
    // get the main tables
    from += MdBusiness.getMdBusiness(OperatorSpray.CLASS).getTableName() + " AS operatorspray,\n";
    from += MdBusiness.getMdBusiness(TeamMember.CLASS).getTableName() + " AS sprayoperator,\n";
    from += MdBusiness.getMdBusiness(Person.CLASS).getTableName() + " AS person,\n";
    from += MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName() + " AS abstractspray\n";
    from += " LEFT JOIN ";
    from += MdBusiness.getMdBusiness(MalariaSeason.CLASS).getTableName() + " AS sprayseason ";
    from += "ON abstractspray.spraydate BETWEEN sprayseason.startdate AND sprayseason.enddate \n";

    String where = "";

    // join main tables
    where += "AND abstractspray.id = operatorspray.id\n";
    where += "AND operatorspray.id = householdspraystatus.spray\n";
    where += "AND operatorspray.sprayoperator = sprayoperator.id \n";
    where += "AND sprayoperator.person = person.id \n";

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);

    return select + "\n" + from + "\n" + where;
  }
}
