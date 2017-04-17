package dss.vector.solutions.general;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.constants.MdTypeInfo;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdType;

import dss.vector.solutions.geo.AllowedIn;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.LocatedIn;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.util.QueryUtil;

public class PopulationDataView extends PopulationDataViewBase implements com.runwaysdk.generation.loader.Reloadable
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
    
    String geoHierarchyTable = MdEntityDAO.getMdEntityDAO(GeoHierarchy.CLASS).getTableName();
    String geoEntityClassCol = QueryUtil.getColumnName(GeoHierarchy.getGeoEntityClassMd());
    String politicalCol = QueryUtil.getColumnName(GeoHierarchy.getPoliticalMd());
    String sprayTargetAllowedCol = QueryUtil.getColumnName(GeoHierarchy.getSprayTargetAllowedMd());
    String populationAllowedCol = QueryUtil.getColumnName(GeoHierarchy.getPopulationAllowedMd());
      
    
    String mdTypeTable = MdEntityDAO.getMdEntityDAO(MdTypeInfo.CLASS).getTableName();
    String pckNameCol = QueryUtil.getColumnName(MdType.getPackageNameMd());
    String nameCol = QueryUtil.getColumnName(MdType.getTypeNameMd());
    
    String allowedInTable = MdEntityDAO.getMdEntityDAO(AllowedIn.CLASS).getTableName();

    
    String geoEntityTable = MdEntityDAO.getMdEntityDAO(GeoEntity.CLASS).getTableName();
    String typeCol = QueryUtil.getColumnName(GeoEntity.getTypeMd());

    String populationDataTable = MdEntityDAO.getMdEntityDAO(PopulationData.CLASS).getTableName();
    String populationCol = QueryUtil.getColumnName(PopulationData.getPopulationMd());
    String yearOfDataCol = QueryUtil.getColumnName(PopulationData.getYearOfDataMd());
    String geoEntityCol = QueryUtil.getColumnName(PopulationData.getGeoEntityMd());
    
    String locatedInTable = MdEntityDAO.getMdEntityDAO(LocatedIn.CLASS).getTableName();

    String idCol = QueryUtil.getIdColumn();
    
    valueQuery.SELECT(valueQuery.aSQLInteger("summed_value", "summed_value"));
    String sql = "(WITH RECURSIVE geohierarchy_flags AS(\n";
    sql += " SELECT  (t1."+pckNameCol+" || '.' || t1."+nameCol+") AS parent_type,\n";
    sql += "  g1."+politicalCol+" AS parent_political,\n";
    sql += "  g1."+sprayTargetAllowedCol+" AS parent_spraytargetallowed,\n";
    sql += "  g1."+populationAllowedCol+" AS parent_populationallowed,\n";
    sql += "  (t2."+pckNameCol+" || '.' || t2."+nameCol+") AS child_type,\n";
    sql += "  g2."+politicalCol+" AS child_political,\n";
    sql += "  g2."+sprayTargetAllowedCol+" AS child_spraytargetallowed,\n";
    sql += "  g2."+populationAllowedCol+" AS child_populationallowed\n";
    sql += " FROM "+allowedInTable+" ,\n";
    sql += "  "+geoHierarchyTable+" g1,\n";
    sql += "  "+geoHierarchyTable+" g2,\n";
    sql += "  "+mdTypeTable+" t1 ,\n";
    sql += "  "+mdTypeTable+" t2 \n";
    sql += " WHERE  "+allowedInTable+"."+RelationshipDAOIF.PARENT_ID_COLUMN+" = g1.id\n";
    sql += "  AND "+allowedInTable+"."+RelationshipDAOIF.CHILD_ID_COLUMN+" = g2.id\n";
    sql += "  AND t1.id = g1."+geoEntityClassCol+"\n";
    sql += "  AND t2.id = g2."+geoEntityClassCol+"\n";
    sql += " )\n";
    sql += ", recursive_rollup AS ( \n";
    sql += " SELECT "+RelationshipDAOIF.CHILD_ID_COLUMN+", "+RelationshipDAOIF.PARENT_ID_COLUMN+" , 0 AS depth , "+geoEntityTable+"."+typeCol+" , \n";
    sql += " COALESCE((";
    // this is the table with the sumable value
    sql += " SELECT "+populationCol+" FROM "+populationDataTable+" pd \n";
    sql += "    WHERE pd."+yearOfDataCol+"  = " + this.getYearOfData() + "\n";
    sql += "     AND pd."+geoEntityCol+" = li."+RelationshipDAOIF.CHILD_ID_COLUMN+"\n";
    sql += "     AND geohierarchy_flags.parent_populationallowed = 1\n";    
    sql += "  ), 0) as sumvalue\n";
    sql += "  FROM "+locatedInTable+" li, geohierarchy_flags, "+geoEntityTable+" \n";
    // the root geoentity
    sql += " WHERE "+RelationshipDAOIF.PARENT_ID_COLUMN+" = '" + entity.getId() + "'\n";
    sql += "  AND li."+RelationshipDAOIF.CHILD_ID_COLUMN+" = "+geoEntityTable+"."+idCol+"";
    sql += "  AND "+geoEntityTable+"."+typeCol+" = geohierarchy_flags.parent_type";
    sql += "  AND geohierarchy_flags.parent_political = 1";    
    //this is the recursive case
    sql += " UNION\n";
    sql += " SELECT li."+RelationshipDAOIF.CHILD_ID_COLUMN+", li."+RelationshipDAOIF.PARENT_ID_COLUMN+" , rr.depth+1 , "+geoEntityTable+"."+typeCol+" , \n";
    sql += " rr.sumvalue +  COALESCE((\n";
    sql += "    SELECT "+populationCol+" FROM "+populationDataTable+" pd \n";
    sql += "    WHERE pd."+yearOfDataCol+"  = " + this.getYearOfData() + "\n";
    sql += "    AND pd."+geoEntityCol+" = li."+RelationshipDAOIF.CHILD_ID_COLUMN+"\n";
    sql += "    AND geohierarchy_flags.parent_populationallowed = 1\n";    
    sql += "  ), 0)\n";
    sql += " FROM recursive_rollup rr, "+locatedInTable+" li, geohierarchy_flags, "+geoEntityTable+" \n";
    sql += " WHERE rr."+RelationshipDAOIF.CHILD_ID_COLUMN+" = li."+RelationshipDAOIF.PARENT_ID_COLUMN+"\n";
    sql += " AND li."+RelationshipDAOIF.CHILD_ID_COLUMN+" = "+geoEntityTable+"."+idCol+"";
    sql += " AND "+geoEntityTable+"."+typeCol+" = geohierarchy_flags.parent_type";
    sql += " AND geohierarchy_flags.parent_political = 1";
    
    // --this will stop the recursion as soon as sumvalue is not null\n";
    sql += " AND rr.sumvalue = 0";
    sql += " )\n";
    sql += " select sum(sumvalue) as summed_value from recursive_rollup \n";
    sql += " )\n";
    valueQuery.FROM(sql, "rr");

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
