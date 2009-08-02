package dss.vector.solutions.irs;

import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.system.metadata.MdBusiness;

public class SprayStatus extends SprayStatusBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860690116L;

  public SprayStatus()
  {
    super();
  }

  public SprayStatusView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public SprayStatusView lockView()
  {
    this.lock();

    return this.getView();
  }

  public SprayStatusView getView()
  {
    SprayStatusView view = this.getSpray().getSprayStatusView();

    view.populate(this);

    return view;
  }

  public static SprayStatusView getView(String id)
  {
    return SprayStatus.get(id).getView();
  }

  public static void createTempTable(String tableName)
  {
    String sql = "DROP VIEW IF EXISTS " + tableName + ";\n";
    sql += "CREATE VIEW " + tableName + " AS ";
    sql += SprayStatus.getTempTableSQL() + ";\n";
    System.out.println(sql);
    Database.parseAndExecute(sql);
  }

  public static String getTempTableSQL()
  {
    String select = "SELECT spraystatus.id,\n";

    //insecticide stuff
    //select += "active_ingredient_per_can_view.active_ingredient_per_can,\n";
    select += "active_ingredient_per_can_view.active_ingredient_per_can /  areastandards.unitnozzleareacoverage AS standard_application_rate,\n";
    select += "nozzle_defaultLocale AS nozzle_defaultLocale,\n";
    select += "nozzle_ratio AS nozzle_ratio,\n";

    // --application rate is:
    // --(# can refills * amount of active ingredient per can refill) / (# units
    // sprayed *average size of unit).\n";
    select += "(CAST(refills AS float) * active_ingredient_per_can_view.active_ingredient_per_can) / NULLIF(spraystatus.sprayedrooms      * areastandards.room * room_total, 0) AS room_application_rate,\n";
    select += "(CAST(refills AS float) * active_ingredient_per_can_view.active_ingredient_per_can) / NULLIF(spraystatus.sprayedstructures * areastandards.structurearea * structure_total, 0) AS structure_application_rate,\n";
    select += "(CAST(refills AS float) * active_ingredient_per_can_view.active_ingredient_per_can) / NULLIF(spraystatus.sprayedhouseholds * areastandards.household * household_total, 0) AS household_application_rate,\n";

    // --application ratio is:\n";
    // --"--(# can refills (30)* ave m_ per can refill (10)*nozzle ratio (6)) / (total units sprayed (11, 12 or 13)*ave m_ per unit (38, 37 or 36))\n";
    select += "(CAST(refills AS float) * areastandards.unitnozzleareacoverage * active_ingredient_per_can_view.nozzle_ratio) / NULLIF(spraystatus.sprayedrooms      * areastandards.room, 0) AS room_application_ratio,\n";
    select += "(CAST(refills AS float) * areastandards.unitnozzleareacoverage * active_ingredient_per_can_view.nozzle_ratio) / NULLIF(spraystatus.sprayedstructures * areastandards.structurearea, 0) AS structure_application_ratio,\n";
    select += "(CAST(refills AS float) * areastandards.unitnozzleareacoverage * active_ingredient_per_can_view.nozzle_ratio) / NULLIF(spraystatus.sprayedhouseholds * areastandards.household, 0) AS household_application_ratio,\n";

    // --operational coverage is:\n";
    // --(Total units sprayed / Total units available) *100 (to calculate percentage of units sprayed). \n";
    select += "CAST(spraystatus.sprayedrooms      AS float) / NULLIF(spraystatus.rooms      ,0) * 100 AS room_operational_coverage,\n";
    select += "CAST(spraystatus.sprayedstructures AS float) / NULLIF(spraystatus.structures ,0) * 100 AS structure_operational_coverage,\n";
    select += "CAST(spraystatus.sprayedhouseholds AS float) / NULLIF(spraystatus.households ,0) * 100 AS household_operational_coverage,\n";

    // unsprayed
    select += "(spraystatus.rooms - spraystatus.sprayedrooms) AS room_unsprayed,\n";
    select += "(spraystatus.structures - spraystatus.sprayedstructures) AS structure_unsprayed,\n";
    select += "(spraystatus.households - spraystatus.sprayedhouseholds) AS household_unsprayed,\n";

    String from = " FROM ";
    // get the main tables
    from += MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName() + " AS abstractspray,\n";
    from += MdBusiness.getMdBusiness(SprayStatus.CLASS).getTableName() + " AS spraystatus,\n";
    from += MdBusiness.getMdBusiness(SprayData.CLASS).getTableName() + " AS spraydata,\n";
    from += MdBusiness.getMdBusiness(AreaStandards.CLASS).getTableName() + " AS areastandards,\n";
    from += MdBusiness.getMdBusiness(ActorSpray.CLASS).getTableName() + " AS actorspray,\n";

    // get views
    from += "(" + InsecticideBrand.getTempTableSQL() + ") AS active_ingredient_per_can_view,\n";
    from += "("+ActorSpray.getUnitTotalsSQL()+") AS unit_totals_view,\n";

    String where = "";
    // join main tables
    where += "AND spraydata.id = abstractspray.spraydata \n";
    where += "AND spraystatus.spray = abstractspray.id \n";
    where += "AND abstractspray.id = actorspray.id \n";
    // join views
    where += "AND spraydata.brand = active_ingredient_per_can_view.id \n";
    where += "AND actorspray.id = unit_totals_view.id \n";

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);

    return select + "\n" + from + "\n" + where;
  }

}
