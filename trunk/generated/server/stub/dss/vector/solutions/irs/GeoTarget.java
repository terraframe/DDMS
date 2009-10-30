package dss.vector.solutions.irs;

import java.util.List;

import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.generated.GeoEntity;

public class GeoTarget extends GeoTargetBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240267420514L;

  public GeoTarget()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    if (this.getGeoEntity() != null && this.getSeason() != null)
    {
      return this.getGeoEntity().getGeoId() + "." + this.getSeason().getKey();
    }
    return this.getId();
  }

  public GeoTargetView getView()
  {
    GeoTargetView view = new GeoTargetView();

    view.populateView(this);

    return view;
  }

  public static GeoTargetView getView(String id)
  {
    return GeoTarget.get(id).getView();
  }

  public static GeoTargetView find(GeoEntity resource)
  {
    GeoTargetQuery query = new GeoTargetQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(resource));

    OIterator<? extends GeoTarget> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next().getView();
      }
      else
      {
        GeoTargetView view = new GeoTargetView();
        view.setGeoEntity(resource);

        return view;
      }
    }
    finally
    {
      it.close();
    }
  }

  public static GeoTargetView findByGeoEntityIdAndSeason(String resource, MalariaSeason season)
  {
    GeoTargetQuery query = new GeoTargetQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().getId().EQ(resource));
    query.AND(query.getSeason().EQ(season));

    OIterator<? extends GeoTarget> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next().getView();
      }
      else
      {
        GeoEntity ge = GeoEntity.get(resource);

        GeoTargetView view = new GeoTargetView();
        view.setGeoEntity(ge);
        view.setEntityName(ge);
        view.setSeason(season);

        return view;
      }
    }
    finally
    {
      it.close();
    }
  }

  /**
   * @param entity
   *          GeoEntity
   * @param date
   *          Date
   * 
   * @return A calulated value for outbreak
   */
  public static Integer getCalculatedValue(String geoid, String malariaSeasonId, String attribute)
  {

    QueryFactory queryFactory = new QueryFactory();
    ValueQuery valueQuery = new ValueQuery(queryFactory);
    Integer sum = 0;

    String allPaths = MdBusiness.getMdBusiness(AllPaths.CLASS).getTableName();
    String geoTarget = MdBusiness.getMdBusiness(GeoTarget.CLASS).getTableName();

    valueQuery.SELECT(valueQuery.aSQLInteger("summed_value", "summed_value"));

    String sql = "(WITH RECURSIVE \n";
    sql += " recursive_rollup AS ( \n";
    sql += " SELECT child_id, parent_id ,\n";
    sql += " COALESCE((\n";
    // this is the table with the sumable value
    sql += " SELECT " + attribute + " FROM geotarget \n";
    sql += "    WHERE geotarget.season = '" + malariaSeasonId + "'\n";
    sql += "    AND geotarget.geoentity = locatedin.child_id\n";
    sql += "  ),0)as sumvalue\n";
    sql += "  FROM locatedin\n";
    // the root geoentity
    sql += " WHERE parent_id = '" + geoid + "'\n";
    //this is the recursive case
    sql += " UNION\n";
    sql += " SELECT b.child_id, b.parent_id, \n";
    sql += " COALESCE((\n";
    sql += " SELECT " + attribute + " FROM geotarget \n";
    sql += "    WHERE geotarget.season = '" + malariaSeasonId + "'\n";
    sql += "    AND geotarget.geoentity = b.child_id\n";
    sql += " ),0)\n";
    sql += " FROM recursive_rollup a, locatedin b \n";
    sql += " WHERE a.child_id = b.parent_id\n";
    // --this will stop the recursion as soon as sumvalue is not null\n";
    sql += " AND a.sumvalue = 0\n";
    sql += " )\n";
    sql += " select sum(sumvalue) as summed_value from recursive_rollup \n";
    sql += " )\n";
    valueQuery.FROM(sql, "rr");

    System.out.println(valueQuery.getSQL());

    List<ValueObject> valueObjectList = valueQuery.getIterator().getAll();

    for (ValueObject valueObject : valueObjectList)
    {
      String value = valueObject.getValue("summed_value");
      if (!value.equals(""))
      {
        sum += Integer.parseInt(value);
      }
    }

    if (sum == 0)
    {
      return null;
    }

    return sum;
  }

  public static Integer[] getCalculatedTargets(String geoid, String malariaSeasonId)
  {

    Integer[] results = new Integer[53];

    for (int i = 0; i < 53; i++)
    {
      results[i] = getCalculatedValue(geoid, malariaSeasonId, "target_" + i);
    }

    return results;
  }
}
