package dss.vector.solutions.irs;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.Person;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.generated.GeoEntity;

public class OperatorSpray extends OperatorSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long          serialVersionUID = 1240853391117L;

  /**
   * Lock used when applying a OperatorSpray. An OperatorSpray has a uniqueness
   * constraint on (Spray Date,Geo Entity,Spray Method, Insecticide Brand, Spray Operator). However
   * these fields are not on the same database table, so a manual runtime check
   * and lock are required.
   */
  private static final ReentrantLock lock             = new ReentrantLock();

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
    else 
    {
      return this.buildKey();
    }
  }

  @Override
  public void apply()
  {
    // IMPORTANT: Lock before performing the uniqueness constraint check
    lock.lock();

    try
    {
      validateUniqueness();

      super.apply();
    }
    finally
    {
      lock.unlock();
    }
  }

  private void validateUniqueness()
  {
    if(this.getSprayData() != null)
    {
      Date sprayDate = this.getSprayData().getSprayDate();
      GeoEntity geoEntity = this.getSprayData().getGeoEntity();
      InsecticideBrand brand = this.getSprayData().getBrand();
      List<SprayMethod> sprayMethod = this.getSprayData().getSprayMethod();
      
      OperatorSpray duplicate = OperatorSpray.get(sprayDate, geoEntity, brand, this.getSprayOperator(), sprayMethod.toArray(new SprayMethod[sprayMethod.size()]));
      
      if(duplicate != null && !this.getId().equals(duplicate.getId()))
      {
        String msg = "Operator Spray must be unique with respect to Spray Date, Geo Entity, Insecticide Brand, Spray Method, and Spray Operator";

        UniqueOperatorSprayException e = new UniqueOperatorSprayException(msg);
        e.setBrand(brand.getBrandName());
        e.setSprayDate(sprayDate);
        e.setGeoEntity(geoEntity.getGeoId());
        e.setSprayOperator(this.getSprayOperator().getOperatorId());
        
        for(SprayMethod method : sprayMethod)
        {
          e.setSprayMethod(method.getDisplayLabel());
        }
        
        e.apply();
        
        throw e;
      }
    }
  }

  @Override
  protected String buildKey()
  {
    if (this.getSprayData() != null && this.getSprayOperator() != null)
    {
      return this.getSprayData().getKey() + "." + this.getSprayOperator().getKey();
    }
    return this.getId();
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
  

  public static OperatorSpray get(Date sprayDate, GeoEntity geoEntity, InsecticideBrand brand, SprayOperator operator, SprayMethod... sprayMethod)
  {
    OperatorSprayQuery query = new OperatorSprayQuery(new QueryFactory());
    query.WHERE(query.getSprayData().getSprayDate().EQ(sprayDate));
    query.WHERE(query.getSprayData().getGeoEntity().EQ(geoEntity));
    query.WHERE(query.getSprayData().getBrand().EQ(brand));
    query.WHERE(query.getSprayData().getSprayMethod().containsAll(sprayMethod));
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

  public static void createTempTable(String tableName)
  {
    String sql = "DROP TABLE IF EXISTS " + tableName + ";\n";
    sql += "CREATE TEMP TABLE " + tableName + " AS ";
    sql += OperatorSpray.getTempTableSQL("") + ";\n";
    System.out.println(sql);
    Database.parseAndExecute(sql);
  }

  public static String getTempTableSQL(String viewName)
  {
    String select = "SELECT spraystatus.id,\n";

    select += "'1' AS aggregation_level,\n";
    // operator stuff
    select += "householdspraystatus." + HouseholdSprayStatus.HOUSEHOLDID + " AS household_id,\n";
    select += "householdspraystatus." + HouseholdSprayStatus.STRUCTUREID + " AS structure_id,\n";
    select += "operatorspray." + OperatorSpray.SPRAYOPERATOR + " AS sprayoperator,\n";
    select += "sprayoperator.operatorid || ' - ' || person.firstname || ' - ' || person.lastname AS sprayoperator_defaultLocale,\n";
    select += "operatorspray." + OperatorSpray.OPERATORSPRAYWEEK + " AS operator_week,\n";
    select += "actorspray." + ActorSpray.TARGET + " AS operator_target,\n";
    // team stuff
    select += "sprayteam." + SprayTeam.ID + " AS sprayteam,\n";
    select += "sprayteam." + SprayTeam.TEAMID + " AS sprayteam_defaultLocale,\n";
    select += "actorspray." + ActorSpray.TEAMLEADER + " AS sprayleader,\n";
    select += "sprayleader.operatorid || ' - ' || person2.firstname || ' - ' || person2.lastname AS sprayleader_defaultLocale,\n";
    select += "actorspray." + ActorSpray.TEAMSPRAYWEEK + " AS team_week,\n";
    select += "NULL AS team_target,\n";
    // zone stuff
    select += "'' AS zone_supervisor,\n";
    select += "'' AS zone_supervisor_defaultLocale,\n";
    select += "CAST(NULL AS INT)  AS zone_week,\n";
    select += "CAST(NULL AS INT)  AS zone_target,\n";
    // target stuff
    // select += "sprayseason.id  AS spray_season,\n";
    select += "(SELECT weekly_target FROM " + viewName + " AS  spray_target_view WHERE " + "spray_target_view.target_id = sprayoperator.id \n" + "AND spray_target_view.season_id = sprayseason.id \n" + "AND spray_target_view.target_week = operatorspray." + OperatorSpray.OPERATORSPRAYWEEK
        + ") AS planed_operator_target,\n";
    select += "(SELECT weekly_target FROM " + viewName + " AS  spray_target_view WHERE " + "spray_target_view.target_id = sprayteam.id \n" + "AND spray_target_view.season_id = sprayseason.id \n" + "AND spray_target_view.target_week = actorspray." + ActorSpray.TEAMSPRAYWEEK
        + ") AS planed_team_target,\n";
    select += "(SELECT weekly_target FROM " + viewName + " AS  spray_target_view WHERE " + "spray_target_view.target_id = spraydata.geoentity  \n" + "AND spray_target_view.season_id = sprayseason.id \n" + "AND spray_target_view.target_week = EXTRACT(WEEK FROM spraydata.spraydate)"
        + ") AS planed_area_target,\n";

    String from = " FROM ";
    // get the main tables
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
    from += " LEFT JOIN ";
    from += MdBusiness.getMdBusiness(MalariaSeason.CLASS).getTableName() + " AS sprayseason ";
    from += "ON spraydata.spraydate BETWEEN sprayseason.startdate AND sprayseason.enddate \n";
    // from += " LEFT JOIN " ;
    // from += viewName + " AS operator_target_view \n";
    // from += "ON (,\n";
    // from += "viewName AS team_target_view,\n";
    // from += "viewName AS geo_target_view,\n";

    String where = "";
    // join the spray team to the oporator
    where += "AND sprayteam.id = inteam.parent_id  \n";
    where += "AND sprayoperator.id = inteam.child_id \n";

    // join main tables
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
