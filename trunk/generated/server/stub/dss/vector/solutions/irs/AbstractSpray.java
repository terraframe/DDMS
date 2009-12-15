package dss.vector.solutions.irs;

import com.terraframe.mojo.dataaccess.database.Database;

public abstract class AbstractSpray extends AbstractSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597926952L;

  public AbstractSpray()
  {
    super();
  }

  @Override
  public void delete()
  {
    SprayData data = this.getSprayData();

    super.delete();

    try
    {
      data.delete();
    }
    catch (Exception e)
    {
      // The spray data is still being referenced by some other spray so do not
      // delete it
    }
  }

  public abstract SprayStatusView getSprayStatusView();

  public void populateView(AbstractSprayView view)
  {
    SprayData data = this.getSprayData();
    view.setBrand(data.getBrand());
    view.setGeoEntity(data.getGeoEntity());
    view.setSprayDate(data.getSprayDate());
    view.setSurfaceType(data.getSurfaceType());

    view.clearSprayMethod();

    for (SprayMethod method : data.getSprayMethod())
    {
      view.addSprayMethod(method);
    }

    view.setSprayData(data);
    view.setSprayId(this.getId());
  }

  

  

  public static void createTempTable(String tableName, String viewName)
  {
    // InsecticideBrand.createTempTable(insecticideTable);

    String sql = "DROP TABLE IF EXISTS " + tableName + ";\n";
    sql += "CREATE TEMP TABLE " + tableName + " AS \n";
    sql += AbstractSpray.getSubquerySql(viewName);
    sql += ";\n";
    // System.out.println(sql);
    Database.parseAndExecute(sql);
  }

  public static String getSubquerySql(String viewName)
  {
    String sql = "";
    sql += OperatorSpray.getTempTableSQL(viewName);
    sql += "\n UNION \n";
    sql += TeamSpray.getTempTableSQL(viewName);
    sql += "\n UNION \n";
    sql += ZoneSpray.getTempTableSQL(viewName);
    sql += "\n";

    return sql;
  }

}
