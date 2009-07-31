package dss.vector.solutions.irs;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.Person;


public class OperatorSpray extends OperatorSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240853391117L;

  public OperatorSpray()
  {
    super();
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

  public static OperatorSpray find(SprayData data, SprayOperator operator)
  {
    if(data == null || operator == null)
    {
      return null;
    }

    OperatorSprayQuery query = new OperatorSprayQuery(new QueryFactory());
    query.WHERE(query.getSprayData().EQ(data));
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

  public static OperatorSpray findOrCreate(SprayData data, SprayOperator operator)
  {
    OperatorSpray spray = OperatorSpray.find(data, operator);

    if(spray == null)
    {
      spray = new OperatorSpray();
    }

    return spray;
  }

  public static void createTempTable(String tableName,String activeIngredientView)
  {
    String sql = "DROP TABLE IF EXISTS " + tableName + ";\n";
    sql += "CREATE TEMP TABLE " + tableName + " AS ";
    sql += OperatorSpray.getTempTableSQL(activeIngredientView);
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
