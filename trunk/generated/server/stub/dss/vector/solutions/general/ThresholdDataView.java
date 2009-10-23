package dss.vector.solutions.general;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.SystemException;
import com.terraframe.mojo.dataaccess.MdViewDAOIF;
import com.terraframe.mojo.dataaccess.metadata.MdViewDAO;
import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ViewArrayExcelExporter;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.geo.generated.GeoEntity;

public class ThresholdDataView extends ThresholdDataViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long  serialVersionUID = 1256068148309L;

  public static final String IDENTIFICATION   = "identification_";

  public static final String OUTBREAK         = "outbreak_";

  public ThresholdDataView()
  {
    super();
  }

  public void populateView(ThresholdData concrete)
  {
    this.setConcreteId(concrete.getId());

    GeoEntity entity = concrete.getGeoEntity();

    if (entity != null)
    {
      this.setGeoEntity(entity.getGeoId());
      this.setEntityLabel(entity.getLabel());
    }

    this.populateAttributes(this, concrete, "Outbreak");
    this.populateAttributes(this, concrete, "Identification");

    this.setSeason(concrete.getSeason());
  }

  private void populateConcrete(ThresholdData concrete)
  {
    String geoId = this.getGeoEntity();

    if (geoId != null && !geoId.equals(""))
    {
      GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);

      concrete.setGeoEntity(geoEntity);
    }

    this.populateAttributes(concrete, this, "Outbreak");
    this.populateAttributes(concrete, this, "Identification");

    concrete.setSeason(this.getSeason());
  }

  private void populateAttributes(Object to, Object from, String accessor)
  {
    for (int i = 0; i < 53; i++)
    {
      String setterName = "set" + accessor + "_" + i;
      String getterName = "get" + accessor + "_" + i;

      try
      {
        Method setter = to.getClass().getMethod(setterName, Integer.class);
        Method getter = from.getClass().getMethod(getterName);

        setter.invoke(to, (Integer) getter.invoke(from));
      }
      catch (Exception e)
      {
        throw new SystemException(e);
      }
    }
  }

  private void buildAttributeMap(ThresholdData concrete)
  {
    new AttributeNotificationMap(concrete, ThresholdData.GEOENTITY, this, ThresholdDataView.GEOENTITY);
    new AttributeNotificationMap(concrete, ThresholdData.SEASON, this, ThresholdDataView.SEASON);

    for (int i = 0; i < 53; i++)
    {
      new AttributeNotificationMap(concrete, ThresholdDataView.IDENTIFICATION + i, this, ThresholdDataView.IDENTIFICATION + i);
      new AttributeNotificationMap(concrete, ThresholdDataView.OUTBREAK + i, this, ThresholdDataView.OUTBREAK + i);
    }
  }

  @Override
  public void apply()
  {
    ThresholdData concrete = new ThresholdData();

    if (this.hasConcrete())
    {
      concrete = ThresholdData.lock(this.getConcreteId());
    }

    // Build the attribute map between ThresholdData and
    // ThresholdDataView for error handling
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
      ThresholdData.get(this.getConcreteId()).delete();
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

  public static ThresholdDataView[] getViews(String geoId, MalariaSeason season)
  {
    GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);
    List<ThresholdDataView> list = new LinkedList<ThresholdDataView>();

    for (GeoEntity child : geoEntity.getImmediatePopulationChildren())
    {
      ThresholdDataView view = ThresholdDataView.getView(child, season);

      list.add(view);
    }

    list.add(ThresholdDataView.getView(geoEntity, season));

    return list.toArray(new ThresholdDataView[list.size()]);

  }

  public static ThresholdDataView getView(GeoEntity entity, MalariaSeason season)
  {
    ThresholdDataQuery query = new ThresholdDataQuery(new QueryFactory());

    Condition geoEntities = query.getGeoEntity().EQ(entity);

    Condition condition = AND.get(geoEntities, query.getSeason().EQ(season));

    query.WHERE(condition);

    OIterator<? extends ThresholdData> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next().getView();
      }

      ThresholdDataView view = new ThresholdDataView();
      view.setGeoEntity(entity.getGeoId());
      view.setSeason(season);
      view.setEntityLabel(entity);

      return view;
    }
    finally
    {
      it.close();
    }
  }

  @Transaction
  public static ThresholdDataView[] applyAll(ThresholdDataView[] views)
  {
    for (ThresholdDataView view : views)
    {
      view.apply();
    }

    return views;
  }

  public static InputStream exportToExcel(ThresholdDataView[] views)
  {
    List<String> attributes = ThresholdDataView.getExportAttributes(views);

    MdViewDAOIF mdView = MdViewDAO.getMdViewDAO(CLASS);

    ViewArrayExcelExporter exporter = new ViewArrayExcelExporter(views, attributes, mdView, mdView.getDisplayLabel(Session.getCurrentLocale()));

    return exporter.exportStream();
  }

  private static List<String> getExportAttributes(ThresholdDataView[] views)
  {
    List<String> attributes = new LinkedList<String>();
    attributes.add(ENTITYLABEL);

    if (views.length > 0)
    {
      EpiDate[] weeks = views[0].getSeason().getEpiWeeks();
      for (EpiDate week : weeks)
      {
        int index = ( week.getPeriod() % week.getNumberOfEpiWeeks() );

        attributes.add(OUTBREAK + index);
        attributes.add(IDENTIFICATION + index);
      }

    }
    else
    {
      for (int i = 0; i < 52; i++)
      {
        attributes.add(OUTBREAK + i);
        attributes.add(IDENTIFICATION + i);
      }
    }
    return attributes;
  }

  public void setThresholds(Date date, Integer t1, Integer t2)
  {
    if (this.hasConcrete())
    {
      ThresholdData.get(this.getConcreteId()).setThresholds(date, t1, t2);
    }
  }

  public Integer[] getThresholds(Date date)
  {
    if (this.hasConcrete())
    {
      return ThresholdData.get(this.getConcreteId()).getThresholds(date);
    }

    return null;
  }

  /**
   * @param entity
   *          GeoEntity
   * @param date
   *          Date
   * 
   * @return An array of the Thresholds [T1, T2] for a given GeoEntity on the
   *         give Date. If thresholds are not defined then null is returned.
   */
  public static Integer[] getThresholds(GeoEntity entity, Date date)
  {
    return ThresholdData.getThresholds(entity, date);
  }

}
