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

public class ZoneSpray extends ZoneSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long          serialVersionUID = 1240860676906L;

  /**
   * Lock used when applying a ZoneSpray. An ZoneSpray has a uniqueness
   * constraint on (Spray Date,Geo Entity, Insecticide Brand, Spray Method).
   * However these fields are not on the same database table, so a manual
   * runtime check and lock are required.
   */
  private static final ReentrantLock lock             = new ReentrantLock();

  public ZoneSpray()
  {
    super();
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
    if (this.getSprayData() != null)
    {
      Date sprayDate = this.getSprayData().getSprayDate();
      GeoEntity geoEntity = this.getSprayData().getGeoEntity();
      InsecticideBrand brand = this.getSprayData().getBrand();
      List<SprayMethod> sprayMethod = this.getSprayData().getSprayMethod();

      ZoneSpray duplicate = ZoneSpray.get(sprayDate, geoEntity, brand, sprayMethod.toArray(new SprayMethod[sprayMethod.size()]));

      if (duplicate != null && !this.getId().equals(duplicate.getId()))
      {
        String msg = "Team Spray must be unique with respect to Spray Date, Geo Entity, Insecticide Brand, and Spray Method";

        UniqueZoneSprayException e = new UniqueZoneSprayException(msg);
        e.setBrand(brand.getBrandName());
        e.setSprayDate(sprayDate);
        e.setGeoEntity(geoEntity.getGeoId());

        for (SprayMethod method : sprayMethod)
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
    if (this.getSprayData() != null)
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

    view.setSupervisor(this.getSupervisor());
    view.setTarget(this.getTarget());
    view.setSprayWeek(this.getSprayWeek());
  }

  @Override
  public SprayStatusView getSprayStatusView()
  {
    return null;
  }

  private static ZoneSpray get(Date sprayDate, GeoEntity geoEntity, InsecticideBrand brand, SprayMethod[] method)
  {
    ZoneSprayQuery query = new ZoneSprayQuery(new QueryFactory());
    query.WHERE(query.getSprayData().getSprayDate().EQ(sprayDate));
    query.WHERE(query.getSprayData().getGeoEntity().EQ(geoEntity));
    query.WHERE(query.getSprayData().getBrand().EQ(brand));
    query.WHERE(query.getSprayData().getSprayMethod().containsAll(method));

    OIterator<? extends ZoneSpray> it = query.getIterator();

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
    // operator stuff
    select += "'' AS household_id,\n";
    select += "'' AS structure_id,\n";
    select += "'' AS sprayoperator,\n";
    select += "'' AS sprayoperator_defaultLocale,\n";
    select += "NULL AS operator_week,\n";
    select += "NULL AS operator_target,\n";
    // team stuff
    select += "sprayteam." + SprayTeam.ID + " AS sprayteam,\n";
    select += "sprayteam." + SprayTeam.TEAMID + " AS sprayteam_defaultLocale,\n";
    select += "actorspray." + ActorSpray.TEAMLEADER + " AS sprayleader,\n";
    select += "sprayleader.operatorid || ' - ' || person.firstname || ' ' || person.lastname AS sprayleader_defaultLocale,\n";
    select += "actorspray." + ActorSpray.TEAMSPRAYWEEK + " AS team_week,\n";
    select += "actorspray." + ActorSpray.TARGET + " AS team_target,\n";

    // zone stuff
    // FIXME there is no longer a supervisor name and supervisor surname, just a reference to supervisor
//    select += "zonespray." + ZoneSpray.SUPERVISORNAME + " || ' '|| zonespray." + ZoneSpray.SUPERVISORSURNAME + " AS zone_supervisor,\n";
    select += "zonespray." + ZoneSpray.SPRAYWEEK + " AS zone_week,\n";
    select += "zonespray." + ZoneSpray.TARGET + " AS zone_target,\n";
    // target stuff
    // select += "sprayseason.id  AS spray_season,\n";
    select += "NULL  AS planed_operator_target,\n";
    select += "(SELECT weekly_target FROM " + viewName + " AS  spray_target_view WHERE " + "spray_target_view.target_id = sprayteam.id \n" + "AND spray_target_view.season_id = sprayseason.id \n" + "AND spray_target_view.target_week = actorspray." + ActorSpray.TEAMSPRAYWEEK
        + ") AS planed_team_target,\n";
    select += "(SELECT weekly_target FROM " + viewName + " AS  spray_target_view WHERE " + "spray_target_view.target_id = spraydata.geoentity  \n" + "AND spray_target_view.season_id = sprayseason.id \n" + "AND spray_target_view.target_week = EXTRACT(WEEK FROM spraydata.spraydate)"
        + ") AS planed_area_target,\n";

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
    from += " LEFT JOIN ";
    from += MdBusiness.getMdBusiness(MalariaSeason.CLASS).getTableName() + " AS sprayseason ";
    from += "ON spraydata.spraydate BETWEEN sprayseason.startdate AND sprayseason.enddate,\n";

    String where = "";
    // join the team spray to the operator spray
    where += "AND abstractspray_team.spraydata = abstractspray_zone.spraydata \n";

    // join the teamspray to the team
    where += "AND teamspray.sprayteam = sprayteam.id \n";
    where += "AND teamspray.id = actorspray.id  \n";
    where += "AND sprayteam.id = inteam.parent_id  \n";
    where += "AND sprayleader.id = inteam.child_id \n";

    // join abstractspray_team to the team spray
    where += "AND teamspray.id = abstractspray_team.id \n";
    where += "AND teamspray.id = actorspray.id \n";
    where += "AND spraydata.id = abstractspray_team.spraydata \n";

    // join abstractspray_zone
    where += "AND abstractspray_zone.id = zonespray.id \n";
    where += "AND spraystatus.spray = teamspray.id \n";

    // join the people
    where += "AND actorspray.teamleader = sprayleader.id \n";
    where += "AND person.id = sprayleader.person \n";

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);

    return select + "\n" + from + "\n" + where;
  }

}
