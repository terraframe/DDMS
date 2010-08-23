package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableInteger;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.EpiWeek;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.LocatedIn;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.util.QueryUtil;

public class GeoTarget extends GeoTargetBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String TARGET_WEEK = "target_week";
  
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

  public static String getTempTableSQL()
  {
    MdEntityDAOIF geoTargetMd = MdEntityDAO.getMdEntityDAO(GeoTarget.CLASS);
    String geoTargetTable = geoTargetMd.getTableName();
    String seasonCol = QueryUtil.getColumnName(GeoTarget.getSeasonMd());
    String geoEntityTargetCol = QueryUtil.getColumnName(GeoTarget.getGeoEntityMd());
    String diseaseCol = QueryUtil.getColumnName(GeoTarget.getDiseaseMd());
    
    String sql = "SELECT \n";
    sql += "current_date AS spray_date, \n";
    sql += "tar."+diseaseCol+" AS disease, \n";
    sql += "tar."+geoEntityTargetCol+" AS geo_entity, \n";
    sql += "tar."+seasonCol+" AS season, \n";
    sql += "i AS "+TARGET_WEEK+", \n";
    sql += "target_array[i] AS weekly_target \n";
    sql += "FROM (SELECT "+diseaseCol+", "+geoEntityTargetCol+", "+seasonCol+", ARRAY[target_0, \n";
    sql += "target_1,target_2,target_3,target_4,target_5,target_6,target_7,target_8,target_9,target_10, \n";
    sql += "target_11,target_12,target_13,target_14,target_15,target_16,target_17,target_18,target_19,target_20, \n";
    sql += "target_21,target_22,target_23,target_24,target_25,target_26,target_27,target_28,target_29,target_30, \n";
    sql += "target_31,target_32,target_33,target_34,target_35,target_36,target_37,target_38,target_39,target_40, \n";
    sql += "target_41,target_42,target_43,target_44,target_45,target_46,target_47,target_48,target_49,target_50, \n";
    sql += "target_51,target_52] AS target_array FROM "+geoTargetTable+") AS tar CROSS JOIN generate_series(1, " + ( EpiWeek.NUMBER_OF_WEEKS + 1 ) + ") AS i \n";
    
    return sql;
  }
  
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
