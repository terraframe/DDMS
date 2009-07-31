package dss.vector.solutions.irs;

import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.Person;

public class ZoneSpray extends ZoneSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860676906L;

  public ZoneSpray()
  {
    super();
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

    view.setSupervisorName(this.getSupervisorName());
    view.setSupervisorSurname(this.getSupervisorSurname());
    view.setTarget(this.getTarget());
    view.setSprayWeek(this.getSprayWeek());
  }

  @Override
  public SprayStatusView getSprayStatusView()
  {
    return null;
  }

  public static ZoneSprayView getView(String id)
  {
    return ZoneSpray.get(id).getView();
  }

  public static void createTempTable(String tableName,String activeIngredientView)
  {
    String sql = "DROP TABLE IF EXISTS " + tableName + ";\n";
    sql += "CREATE TEMP TABLE " + tableName + " AS ";
    sql += ZoneSpray.getTempTableSQL(activeIngredientView) + ";\n";
    System.out.println(sql);
    //Database.parseAndExecute(sql);
  }


  public static String getTempTableSQL(String activeIngredientView)
  {
    String select = "SELECT spraystatus.id,\n";

    select += "householdspraystatus."+HouseholdSprayStatus.HOUSEHOLDID+",\n";
    select += "householdspraystatus."+HouseholdSprayStatus.STRUCTUREID+",\n";

    select += "actorspray."+ActorSpray.TEAMLEADER+",\n";
    select += "actorspray."+ActorSpray.TARGET+",\n";


    select += "operatorspray."+OperatorSpray.SPRAYOPERATOR+",\n";
    select += "sprayoperator.operatorid || ' - ' || person.firstname || ' - ' || person.lastname as sprayoperator_displaylabel,\n";
    select += "operatorspray."+OperatorSpray.OPERATORSPRAYWEEK+",\n";

    select += "spraydata."+SprayData.GEOENTITY+",\n";
    select += "spraydata."+SprayData.SPRAYDATE+",\n";
    select += "spraydata.spraymethod_c AS spraymethod,\n";
    select += "metadatadisplaylabel_2.defaultLocale AS spraymethod_displaylabel,\n";
    select += "spraydata."+SprayData.BRAND+",\n";
    select += "spraydata.surfacetype_c AS surfacetype,\n";
    select += "metadatadisplaylabel_1.defaultLocale AS surfacetype_displaylabel,\n";

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

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);

    return select + "\n" + from + "\n" + where;
  }

}
