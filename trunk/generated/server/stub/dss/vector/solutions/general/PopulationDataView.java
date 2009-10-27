package dss.vector.solutions.general;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

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
    this.setGrowthRate(concrete.getGrowthRate() * 100);    
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
    concrete.setGrowthRate(this.getGrowthRate() / 100);
    concrete.setEstimated(this.getEstimated());
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
