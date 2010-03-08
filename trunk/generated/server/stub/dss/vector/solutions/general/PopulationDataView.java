package dss.vector.solutions.general;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.geo.generated.GeoEntity;

public class PopulationDataView extends PopulationDataViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256052009378L;

  public PopulationDataView()
  {
    super();
  }

  public void populateView(PopulationData concrete)
  {
    this.setConcreteId(concrete.getId());

    GeoEntity entity = concrete.getGeoEntity();
    
    if (entity != null)
    {
      this.setGeoEntity(entity.getGeoId());
      this.setEntityLabel(entity.getLabel());
    }

    this.setYearOfData(concrete.getYearOfData());
    
    if(concrete.getGrowthRate() != null)
    
    {
      this.setGrowthRate(concrete.getGrowthRate() * 100);    
    }

    this.setEstimated(false);
    
    if(!concrete.getEstimated())
    {
      this.setPopulation(concrete.getPopulation());
    }
  }

  private void populateConcrete(PopulationData concrete)
  {
    String geoId = this.getGeoEntity();

    if (geoId != null && !geoId.equals(""))
    {
      GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);

      concrete.setGeoEntity(geoEntity);
    }

    concrete.setYearOfData(this.getYearOfData());
    concrete.setPopulation(this.getPopulation());
    concrete.setEstimated(this.getEstimated());

    if(this.getGrowthRate() != null)
    {
      concrete.setGrowthRate(this.getGrowthRate() / 100);
    }
    else
    {
      concrete.setGrowthRate(null);
    }
  }

  private void buildAttributeMap(PopulationData concrete)
  {
    new AttributeNotificationMap(concrete, PopulationData.GEOENTITY, this, PopulationDataView.GEOENTITY);
    new AttributeNotificationMap(concrete, PopulationData.YEAROFDATA, this, PopulationDataView.YEAROFDATA);
    new AttributeNotificationMap(concrete, PopulationData.GROWTHRATE, this, PopulationDataView.GROWTHRATE);
    new AttributeNotificationMap(concrete, PopulationData.POPULATION, this, PopulationDataView.POPULATION);
    new AttributeNotificationMap(concrete, PopulationData.ESTIMATED, this, PopulationDataView.ESTIMATED);
  }

  @Override
  public void apply()
  {
    PopulationData concrete = new PopulationData();

    if (this.hasConcrete())
    {
      concrete = PopulationData.lock(this.getConcreteId());
    }

    // Build the attribute map between PopulationData and
    // PopulationDataView for error handling
    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Override
  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      PopulationData.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  public void setEntityLabel(GeoEntity entity)
  {
    this.setEntityLabel(entity.getLabel());
  }
  
  @Override
  public Long getCalculatedPopulation()
  {
    QueryFactory queryFactory = new QueryFactory();
    ValueQuery valueQuery = new ValueQuery(queryFactory);
    Long sum = 0L;

//    String allPaths = MdBusiness.getMdBusiness(AllPaths.CLASS).getTableName();
//    String geoTarget = MdBusiness.getMdBusiness(GeoTarget.CLASS).getTableName();
    
    GeoEntity entity = GeoEntity.searchByGeoId(this.getGeoEntity());
    

    valueQuery.SELECT(valueQuery.aSQLInteger("summed_value", "summed_value"));
    String sql = "(WITH RECURSIVE geohierarchy_flags AS(\n";
    sql += " SELECT  (t1.packagename || '.' || t1.typename) AS parent_type,\n";
    sql += "  g1.political AS parent_political,\n";
    sql += "  g1.spraytargetallowed AS parent_spraytargetallowed,\n";
    sql += "  g1.populationallowed AS parent_populationallowed,\n";
    sql += "  (t2.packagename || '.' || t2.typename) AS child_type,\n";
    sql += "  g2.political AS child_political,\n";
    sql += "  g2.spraytargetallowed AS child_spraytargetallowed,\n";
    sql += "  g2.populationallowed AS child_populationallowed\n";
    sql += " FROM allowedin ,\n";
    sql += "  geohierarchy g1,\n";
    sql += "  geohierarchy g2,\n";
    sql += "  md_type t1 ,\n";
    sql += "  md_type t2 \n";
    sql += " WHERE  allowedin.parent_id = g1.id\n";
    sql += "  AND allowedin.child_id = g2.id\n";
    sql += "  AND t1.id = g1.geoentityclass\n";
    sql += "  AND t2.id = g2.geoentityclass\n";
    sql += " )\n";
    sql += ", recursive_rollup AS ( \n";
    sql += " SELECT child_id, parent_id , 0 AS depth , geoentity.type , \n";
    sql += " COALESCE((";
    // this is the table with the sumable value
    sql += " SELECT population FROM populationdata pd \n";
    sql += "    WHERE pd.yearofdata  = " + this.getYearOfData() + "\n";
    sql += "     AND pd.geoentity = li.child_id\n";
    sql += "     AND geohierarchy_flags.parent_populationallowed = 1\n";    
    sql += "  ), 0) as sumvalue\n";
    sql += "  FROM locatedin li, geohierarchy_flags, geoentity \n";
    // the root geoentity
    sql += " WHERE parent_id = '" + entity.getId() + "'\n";
    sql += "  AND li.child_id = geoentity.id";
    sql += "  AND geoentity.type = geohierarchy_flags.parent_type";
    sql += "  AND geohierarchy_flags.parent_political = 1";    
    //this is the recursive case
    sql += " UNION\n";
    sql += " SELECT li.child_id, li.parent_id , rr.depth+1 , geoentity.type , \n";
    sql += " rr.sumvalue +  COALESCE((\n";
    sql += "    SELECT population FROM populationdata pd \n";
    sql += "    WHERE pd.yearofdata  = " + this.getYearOfData() + "\n";
    sql += "    AND pd.geoentity = li.child_id\n";
    sql += "    AND geohierarchy_flags.parent_populationallowed = 1\n";    
    sql += "  ), 0)\n";
    sql += " FROM recursive_rollup rr, locatedin li, geohierarchy_flags, geoentity \n";
    sql += " WHERE rr.child_id = li.parent_id\n";
    sql += " AND li.child_id = geoentity.id";
    sql += " AND geoentity.type = geohierarchy_flags.parent_type";
    sql += " AND geohierarchy_flags.parent_political = 1";
    
    // --this will stop the recursion as soon as sumvalue is not null\n";
    sql += " AND rr.sumvalue = 0";
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
        sum += Long.parseLong(value);
      }
    }

    if (sum == 0)
    {
      return null;
    }

    return sum;
  }

  public static PopulationDataView[] getViews(String geoId, Integer yearOfData)
  {
    validateYear(yearOfData);
    
    GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);
    List<PopulationDataView> list = new LinkedList<PopulationDataView>();

    for (GeoEntity child : geoEntity.getPopulationChildren())
    {
      PopulationDataView view = PopulationDataView.getView(child, yearOfData);

      list.add(view);
    }
    
    
    list.add(PopulationDataView.getView(geoEntity, yearOfData));

    return list.toArray(new PopulationDataView[list.size()]);
  }

  private static void validateYear(Integer year)
  {
    if(!PopulationData.isValidYear(year))
    {
      Calendar calendar = Calendar.getInstance();

      int max = calendar.get(Calendar.YEAR);
      int min = calendar.getActualMinimum(Calendar.YEAR);

      String msg = "The year [" + year + " ] must be between [" + min + "] and [" + max + "]";

      YearValueException e = new YearValueException(msg);
      e.setMinYear(min);
      e.setMaxYear(max);
      e.setYearValue(year);
      e.apply();
      
      throw e;
    }
  }
  
  public static PopulationDataView[] getFacilityViews(String geoId, Integer yearOfData)
  {
    validateYear(yearOfData);
    
    GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);
    List<PopulationDataView> list = new LinkedList<PopulationDataView>();

    for (GeoEntity child : geoEntity.getFacilityChildren())
    {
      PopulationDataView view = PopulationDataView.getView(child, yearOfData);

      list.add(view);
    }    
    
    return list.toArray(new PopulationDataView[list.size()]);
  }


  public static PopulationDataView getView(GeoEntity entity, Integer yearOfData)
  {
    PopulationDataQuery query = new PopulationDataQuery(new QueryFactory());

    Condition geoEntities = query.getGeoEntity().EQ(entity);

    Condition condition = AND.get(geoEntities, query.getYearOfData().EQ(yearOfData));

    query.WHERE(condition);

    OIterator<? extends PopulationData> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next().getView();
      }

      PopulationDataView view = new PopulationDataView();
      view.setGeoEntity(entity.getGeoId());
      view.setYearOfData(yearOfData);
      view.setEntityLabel(entity);
      
      return view;
    }
    finally
    {
      it.close();
    }
  }

  @Transaction
  public static PopulationDataView[] applyAll(PopulationDataView[] views)
  {
    for (PopulationDataView view : views)
    {
      view.apply();
    }

    return views;
  }
}
