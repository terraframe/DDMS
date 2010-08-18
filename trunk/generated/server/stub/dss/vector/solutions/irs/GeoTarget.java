package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableInteger;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.LocatedIn;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.util.QueryUtil;

public class GeoTarget extends GeoTargetBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240267420514L;

  public GeoTarget()
  {
    super();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    super.apply();
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
      return this.getClassDisplayLabel();
    }
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

  /*
   * @param entity
   *          GeoEntity
   * @param date
   *          Date
   * 
   * @return A calulated value for geoTarget
  public static Integer getCalculatedValue(String geoid, String malariaSeasonId, String attribute)
  {

    String locatedInTable = MdEntityDAO.getMdEntityDAO(LocatedIn.CLASS).getTableName();
    String geoTargetTable = MdEntityDAO.getMdEntityDAO(GeoTarget.CLASS).getTableName();
    String seasonCol = QueryUtil.getColumnName(GeoTarget.getSeasonMd());
    String geoEntityCol = QueryUtil.getColumnName(GeoTarget.getGeoEntityMd());
    
    QueryFactory queryFactory = new QueryFactory();
    ValueQuery valueQuery = new ValueQuery(queryFactory);
    Integer sum = 0;

    // String allPaths =
    // MdBusiness.getMdBusiness(AllPaths.CLASS).getTableName();
    // String geoTarget =
    // MdBusiness.getMdBusiness(GeoTarget.CLASS).getTableName();

    valueQuery.SELECT(valueQuery.aSQLInteger("summed_value", "summed_value"));
    String sql = "(WITH RECURSIVE \n";
    sql += " recursive_rollup AS ( \n";
    sql += " SELECT "+RelationshipDAOIF.CHILD_ID_COLUMN+", "+RelationshipDAOIF.PARENT_ID_COLUMN+" ,\n";
    // this is the table with the sumable value
    sql += " " + attribute + " as sumvalue\n";
    sql += "  FROM "+locatedInTable+" LEFT JOIN "+geoTargetTable+" ON "+geoTargetTable+"."+geoEntityCol+" = "+locatedInTable+"."+RelationshipDAOIF.CHILD_ID_COLUMN+"\n";
    sql += "  WHERE "+geoTargetTable+"."+seasonCol+" = '" + malariaSeasonId + "'\n";
    // the root geoentity
    sql += " AND "+RelationshipDAOIF.PARENT_ID_COLUMN+" = '" + geoid + "'\n";
    // filter to just those branches that lead somewhere
    // sql +=
    // " AND child_id IN (SELECT childgeoentity FROM allpaths0 join geotarget ON geoentity = childgeoentity";
    // sql += " WHERE parentgeoentity = '" + geoid +
    // "' AND geotarget.season = '" + malariaSeasonId + "')";
    // this is the recursive case
    sql += " UNION\n";
    sql += " SELECT b."+RelationshipDAOIF.CHILD_ID_COLUMN+", b."+RelationshipDAOIF.PARENT_ID_COLUMN+", \n";
    sql += " COALESCE(sumvalue," + attribute + ") as sumvalue\n";
    sql += " FROM recursive_rollup a, "+locatedInTable+" b LEFT JOIN "+geoTargetTable+" ON "+geoTargetTable+"."+geoEntityCol+" = b."+RelationshipDAOIF.CHILD_ID_COLUMN+"\n";
    sql += " WHERE a."+RelationshipDAOIF.CHILD_ID_COLUMN+" = b."+RelationshipDAOIF.PARENT_ID_COLUMN+"\n";
    sql += " AND "+geoTargetTable+"."+seasonCol+" = '" + malariaSeasonId + "'\n";
    // --this will stop the recursion as soon as sumvalue is not null\n";
    // sql += " AND a.sumvalue IS NULL\n";
    sql += " )\n";
    sql += " select sum(sumvalue) as summed_value from recursive_rollup \n";
    sql += " )\n";
    valueQuery.FROM(sql, "rr");

    MdssLog.debug(valueQuery.getSQL());

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
   */

  public static Integer[] getCalculatedTargets(String geoid, String malariaSeasonId)
  {

    Integer[] results = new Integer[53];
    SelectableInteger[] selectables = new SelectableInteger[53];

    QueryFactory queryFactory = new QueryFactory();
    ValueQuery valueQuery = new ValueQuery(queryFactory);

    String baseValue = "";

    String recursiveValue = "";

    String sums = "";

    for (int i = 0; i < selectables.length; i++)
    {
      baseValue += ", gt.target_" + i + "\n";
      recursiveValue += ", COALESCE(a.target_" + i + " * 0, gt.target_" + i + ")\n";
      sums += ",SUM(target_" + i + ") AS target_" + i;
      selectables[i] = valueQuery.aSQLInteger("target_" + i, "target_" + i);

    }

    sums = sums.substring(1);

    valueQuery.SELECT(selectables);

    String locatedInTable = MdEntityDAO.getMdEntityDAO(LocatedIn.CLASS).getTableName();
    String geoTargetTable = MdEntityDAO.getMdEntityDAO(GeoTarget.CLASS).getTableName();
    String seasonCol = QueryUtil.getColumnName(GeoTarget.getSeasonMd());
    String geoEntityCol = QueryUtil.getColumnName(GeoTarget.getGeoEntityMd());
    
    String sql = "(WITH RECURSIVE \n";
    sql += " recursive_rollup AS ( \n";
    sql += " SELECT "+RelationshipDAOIF.CHILD_ID_COLUMN+", "+RelationshipDAOIF.PARENT_ID_COLUMN+" \n";
    sql += baseValue;
    sql += "  FROM "+locatedInTable+" LEFT JOIN " + geoTargetTable + " gt ON gt."+geoEntityCol+" = "+locatedInTable+"."+RelationshipDAOIF.CHILD_ID_COLUMN+"\n";
    sql += "  AND gt."+seasonCol+" = '" + malariaSeasonId + "'\n";
    sql += " WHERE "+RelationshipDAOIF.PARENT_ID_COLUMN+" = '" + geoid + "'\n";
    // this is the recursive case
    sql += " UNION\n";
    sql += " SELECT b."+RelationshipDAOIF.CHILD_ID_COLUMN+", b."+RelationshipDAOIF.PARENT_ID_COLUMN+" \n";
    sql += recursiveValue;
    sql += " FROM recursive_rollup a, "+locatedInTable+" b LEFT JOIN " + geoTargetTable + " gt ON gt."+geoEntityCol+" = b."+RelationshipDAOIF.CHILD_ID_COLUMN+"\n";
    sql += " AND gt."+seasonCol+" = '" + malariaSeasonId + "'\n";
    sql += " WHERE a."+RelationshipDAOIF.CHILD_ID_COLUMN+" = b."+RelationshipDAOIF.PARENT_ID_COLUMN+"\n";
    sql += " )\n";
    sql += " select " + sums + " from recursive_rollup \n";
    sql += " )\n";

    valueQuery.FROM(sql, "rr");

    ValueObject valueObject = valueQuery.getIterator().next();

    for (int i = 0; i < selectables.length; i++)
    {
      String value = valueObject.getValue("target_" + i);
      if (!value.equals(""))
      {
        results[i] = Integer.parseInt(value);
      }
    }

    return results;
  }
}
