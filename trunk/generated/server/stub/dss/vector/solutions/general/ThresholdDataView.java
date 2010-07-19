package dss.vector.solutions.general;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.ConfigurationException;
import com.runwaysdk.SystemException;
import com.runwaysdk.dataaccess.MdViewDAOIF;
import com.runwaysdk.dataaccess.metadata.MdViewDAO;
import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ViewArrayExcelExporter;
import com.runwaysdk.session.Session;

import dss.vector.solutions.InstallProperties;
import dss.vector.solutions.geo.generated.GeoEntity;

public class ThresholdDataView extends ThresholdDataViewBase implements com.runwaysdk.generation.loader.Reloadable
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

    this.setSeason(concrete.getSeason());

    this.populateViewAttributes(concrete);
  }

  private void populateViewAttributes(ThresholdData concrete)
  {
    OIterator<? extends WeeklyThreshold> it = concrete.getAllEpiWeeksRel();

    try
    {
      while (it.hasNext())
      {
        WeeklyThreshold threshold = it.next();

        EpiWeek week = threshold.getChild();

        int index = ( week.getPeriod() % EpiDate.getNumberOfEpiWeeks(week.getYearOfWeek()) );

        Double notification = ( this.getThresholdType() ? threshold.getNotification() : threshold.getFacilityNotification() );
        Double ident = ( this.getThresholdType() ? threshold.getIdentification() : threshold.getFacilityIdentification() );

        this.populateAttributes(this, "setOutbreak_" + index, notification);
        this.populateAttributes(this, "setIdentification_" + index, ident);
      }
    }
    finally
    {
      it.close();
    }
  }

  private void populateConcrete(ThresholdData concrete)
  {
    String geoId = this.getGeoEntity();

    if (geoId != null && !geoId.equals(""))
    {
      GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);

      concrete.setGeoEntity(geoEntity);
    }

    concrete.setSeason(this.getSeason());
  }

  private void populateAttributes(Object to, String accessor, Double value)
  {
    try
    {
      Method setter = to.getClass().getMethod(accessor, Double.class);

      setter.invoke(to, value);
    }
    catch (Exception e)
    {
      throw new SystemException(e);
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
  @Transaction
  public void apply()
  {
    InstallProperties.validateMasterOperation();

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

    this.populateThresholds(concrete);

    this.populateView(concrete);
  }

  @Transaction
  private void populateThresholds(ThresholdData data)
  {
    EpiDate[] weeks = this.getSeason().getEpiWeeks();

    for (EpiDate week : weeks)
    {
      int index = ( week.getPeriod() % week.getNumberOfEpiWeeks() );

      Double notification = this.getOutbreak(index);
      Double identification = this.getIdentification(index);

      if (notification != null || identification != null)
      {
        EpiWeek epiWeek = EpiWeek.getEpiWeek(week.getPeriod(), week.getYear());

        WeeklyThreshold threshold = data.getEpiWeeksRel(epiWeek);

        if (threshold == null)
        {
          threshold = new WeeklyThreshold(data, epiWeek);
        }
        else
        {
          threshold.lock();
        }

        new AttributeNotificationMap(threshold, WeeklyThreshold.IDENTIFICATION, this, ThresholdDataView.IDENTIFICATION + index);
        new AttributeNotificationMap(threshold, WeeklyThreshold.NOTIFICATION, this, ThresholdDataView.OUTBREAK + index);
        new AttributeNotificationMap(threshold, WeeklyThreshold.FACILITYIDENTIFICATION, this, ThresholdDataView.IDENTIFICATION + index);
        new AttributeNotificationMap(threshold, WeeklyThreshold.FACILITYNOTIFICATION, this, ThresholdDataView.OUTBREAK + index);

        if (this.getThresholdType())
        {
          threshold.setNotification(notification);
          threshold.setIdentification(identification);
        }
        else
        {
          threshold.setFacilityNotification(notification);
          threshold.setFacilityIdentification(identification);
        }

        threshold.setCalculationType(null);
        threshold.apply();
      }
    }
  }

  private Double getIdentification(int index)
  {
    try
    {
      return (Double) this.getClass().getMethod("getIdentification_" + index).invoke(this);
    }
    catch (Exception e)
    {
      throw new ConfigurationException(e);
    }
  }

  private Double getOutbreak(int index)
  {
    try
    {
      return (Double) this.getClass().getMethod("getOutbreak_" + index).invoke(this);
    }
    catch (Exception e)
    {
      throw new ConfigurationException(e);
    }
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

    for (GeoEntity child : geoEntity.getPopulationChildren())
    {
      ThresholdDataView view = ThresholdDataView.getView(child, season, true);

      list.add(view);
    }

    list.add(ThresholdDataView.getView(geoEntity, season, true));

    return list.toArray(new ThresholdDataView[list.size()]);
  }

  public static ThresholdDataView[] getFacilityViews(String geoId, MalariaSeason season)
  {
    GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);

    List<ThresholdDataView> list = new LinkedList<ThresholdDataView>();
    List<GeoEntity> children = geoEntity.getFacilityChildren();

    for (GeoEntity child : children)
    {
      ThresholdDataView view = ThresholdDataView.getView(child, season, false);

      list.add(view);
    }

    return list.toArray(new ThresholdDataView[list.size()]);
  }

  public static ThresholdDataView getView(GeoEntity entity, MalariaSeason season, boolean political)
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
        ThresholdDataView view = it.next().getView(political);

        return view;
      }

      ThresholdDataView view = new ThresholdDataView();
      view.setGeoEntity(entity.getGeoId());
      view.setSeason(season);
      view.setEntityLabel(entity);
      view.setThresholdType(political);

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
    InstallProperties.validateMasterOperation();

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
      for (int i = 0; i < 53; i++)
      {
        attributes.add(OUTBREAK + i);
        attributes.add(IDENTIFICATION + i);
      }
    }
    return attributes;
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
  public static WeeklyThreshold getThresholds(GeoEntity entity, Date date)
  {
    return ThresholdData.getThresholds(entity, date);
  }

  @Override
  public Double[] getCalculatedThresholds()
  {
    Double[] thresholds = new Double[106];
    EpiDate[] weeks = this.getSeason().getEpiWeeks();
    GeoEntity entity = GeoEntity.searchByGeoId(this.getGeoEntity());

    if (weeks.length > 0)
    {
      int startWeek = weeks[0].getPeriod();
      int weeksInYear = weeks[0].getNumberOfEpiWeeks();

      int i = startWeek;

      for (EpiDate week : weeks)
      {
        EpiWeek epiWeek = EpiWeek.getEpiWeek(week);

        Double notification = ThresholdData.getCalculatedValue(entity, epiWeek, WeeklyThreshold.NOTIFICATION);
        Double identificaiton = ThresholdData.getCalculatedValue(entity, epiWeek, WeeklyThreshold.IDENTIFICATION);

        thresholds[i * 2] = notification;
        thresholds[i * 2 + 1] = identificaiton;
        i++;

        i = ( i % weeksInYear );
      }
    }

    return thresholds;
  }
}
