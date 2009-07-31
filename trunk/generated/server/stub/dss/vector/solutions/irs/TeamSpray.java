package dss.vector.solutions.irs;

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

  public static void createTempTable(String tableName,String activeIngredientView)
  {
    String sql = "DROP VIEW IF EXISTS " + tableName + ";\n";
    sql += "CREATE VIEW " + tableName + " AS ";
    sql += TeamSpray.getTempTableSQL(activeIngredientView);
    System.out.println(sql);
    //Database.parseAndExecute(sql);
  }

  public static String getTempTableSQL(String activeIngredientView)
  {
    String select = "SELECT spraystatus.id,\n";

    select += "'' AS "+HouseholdSprayStatus.HOUSEHOLDID+",\n";
    select += "'' AS "+HouseholdSprayStatus.STRUCTUREID+",\n";

    select += "actorspray."+ActorSpray.TEAMLEADER+",\n";
    select += "actorspray."+ActorSpray.TARGET+",\n";


    select += "operatorspray."+OperatorSpray.SPRAYOPERATOR+",\n";
    select += "sprayoperator.operatorid || ' - ' || person.firstname || ' - ' || person.lastname as sprayoperator_displaylabel,\n";
    select += "operatorspray."+OperatorSpray.OPERATORSPRAYWEEK+",\n";

    select += "spraydata.spraymethod_c AS spraymethod,\n";
    select += "metadatadisplaylabel_2.defaultLocale AS spraymethod_displaylabel,\n";
    select += "spraydata."+SprayData.BRAND+",\n";
    select += "spraydata.surfacetype_c AS surfacetype,\n";
    select += "metadatadisplaylabel_1.defaultLocale AS surfacetype_displaylabel,\n";

    select += "active_ingredient_per_can_view.brandname,\n";
    select += "active_ingredient_per_can_view.active_ingredient_per_can,\n";

    //targets
    //targets are by week and spraying is by day so this is the only way to make the rollup work
    //select += "(spray_target_view.target / 7) as planed_target,\n";

    //--application rate is:
    //--(# can refills * amount of active ingredient per can refill) / (# units sprayed *average size of unit).\n";
    select += "(refills * active_ingredient_per_can_view.active_ingredient_per_can)/(spraystatus.rooms * areastandards.room) AS room_application_rate,\n";
    select += "(refills * active_ingredient_per_can_view.active_ingredient_per_can)/(spraystatus.structures * areastandards.structurearea) AS structure_application_rate,\n";
    select += "(refills * active_ingredient_per_can_view.active_ingredient_per_can)/(spraystatus.households * areastandards.household) AS household_application_rate,\n";
    //--application ratio is:\n";
    //--"--(# can refills (30)* ave m_ per can refill (10)*nozzle ratio (6)) / (total units sprayed (11, 12 or 13)*ave m_ per unit (38, 37 or 36))\n";
    select += "(refills * areastandards.unitnozzleareacoverage * active_ingredient_per_can_view.nozzle_ratio) / (spraystatus.rooms * areastandards.room) AS room_application_ratio,\n";
    select += "(refills * areastandards.unitnozzleareacoverage * active_ingredient_per_can_view.nozzle_ratio) / (spraystatus.rooms * areastandards.structurearea) AS structure_application_ratio,\n";
    select += "(refills * areastandards.unitnozzleareacoverage * active_ingredient_per_can_view.nozzle_ratio) / (spraystatus.rooms * areastandards.household) AS household_application_ratio,\n";

    //--operational coverage is:\n";
    //--(Total units sprayed / Total units available) *100 (to calculate percentage of units sprayed). \n";
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

    String from = " FROM ";
    //get the main tables
    from += MdBusiness.getMdBusiness(OperatorSpray.CLASS).getTableName() + " AS operatorspray,\n";
    from += MdBusiness.getMdBusiness(ActorSpray.CLASS).getTableName() + " AS actorspray,\n";
    from += MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName() + " AS abstractspray,\n";
    from += MdBusiness.getMdBusiness(HouseholdSprayStatus.CLASS).getTableName() + " AS householdspraystatus,\n";
    from += MdBusiness.getMdBusiness(SprayStatus.CLASS).getTableName() + " AS spraystatus,\n";
    from += MdBusiness.getMdBusiness(SprayData.CLASS).getTableName() + " AS spraydata,\n";
    from += MdBusiness.getMdBusiness(SprayOperator.CLASS).getTableName() + " AS sprayoperator,\n";
    from += MdBusiness.getMdBusiness(Person.CLASS).getTableName() + " AS person,\n";
    from += MdBusiness.getMdBusiness(AreaStandards.CLASS).getTableName() + " AS areastandards,\n";
    from += "surfacetype AS surfacetype,\n";
    from += "surfacetypemaster AS surfacetypemaster,\n";
    from += "spraymethod AS spraymethod,\n";
    from += "spraymethodmaster AS spraymethodmaster,\n";
    //get the enums
    from += "enumeration_master AS enumeration_master_1\n";
    from += " LEFT JOIN metadatadisplaylabel metadatadisplaylabel_1 ON enumeration_master_1.displayLabel = metadatadisplaylabel_1.id,\n";
    from += "enumeration_master AS enumeration_master_2\n";
    from += " LEFT JOIN metadatadisplaylabel metadatadisplaylabel_2 ON enumeration_master_2.displayLabel = metadatadisplaylabel_2.id,\n";
    //get views
    from += activeIngredientView + " AS active_ingredient_per_can_view,\n";
    //from += sprayTargetView + " AS spray_target_view,\n";

    String where = "";
    //join main tables
    where += "AND spraydata.id = abstractspray.spraydata \n";
    where += "AND abstractspray.id = actorspray.id \n";
    where += "AND operatorspray.id = actorspray.id \n";
    where += "AND householdspraystatus.id = spraystatus.id \n";
    where += "AND spraystatus.spray = operatorspray.id \n";
    where += "AND operatorspray.sprayoperator = sprayoperator.id \n";
    where += "AND person.id = sprayoperator.person \n";
    //join enums
    where += "AND spraydata.surfacetype = surfacetype.set_id \n";
    where += "AND surfacetypemaster.id = surfacetype.item_id \n";
    where += "AND surfacetypemaster.id = enumeration_master_1.id \n";
    where += "AND spraydata.spraymethod = spraymethod.set_id \n";
    where += "AND spraymethodmaster.id = spraymethod.item_id \n";
    where += "AND spraymethodmaster.id = enumeration_master_2.id \n";
    //join views
    where += "AND spraydata.brand = active_ingredient_per_can_view.id \n";
    //where += "AND spray_target_view.week = operatorspray."+OperatorSpray.OPERATORSPRAYWEEK+" \n";
    //where += "AND spraydata."+SprayData.SPRAYDATE+" BETWEEN  spray_target_view.season_start AND spray_target_view.season_end \n";

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);

    return select + "\n" + from + "\n" + where;
  }
}
