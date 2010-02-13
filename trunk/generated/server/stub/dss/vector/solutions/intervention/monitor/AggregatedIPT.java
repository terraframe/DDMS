package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.surveillance.PeriodType;
import dss.vector.solutions.util.QueryUtil;

public class AggregatedIPT extends AggregatedIPTBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1244737089690L;

  public AggregatedIPT()
  {
    super();
  }
    
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else if (this.getGeoEntity() != null && this.getStartDate() != null && this.getEndDate() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, Session.getCurrentLocale());

      String entityLabel = this.getGeoEntity().getLabel();
      String startLabel = format.format(this.getStartDate());
      String endLabel = format.format(this.getEndDate());
      
      return this.getClassDisplayLabel() + ": (" + entityLabel + ", " + startLabel + " - " + endLabel +")";
    }

    
    return this.buildKey();
  }

  @Override
  protected String buildKey()
  {
    if (this.getGeoEntity() != null && this.getStartDate() != null && this.getEndDate() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);

      return this.getGeoEntity().getGeoId() + "." + format.format(this.getStartDate()) + "-" + format.format(this.getEndDate());

    }
    return this.getId();
  }

  public static AggregatedIPT searchByGeoEntityAndDate(GeoEntity geoEntity, Date startDate, Date endDate)
  {
    AggregatedIPTQuery query = new AggregatedIPTQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(geoEntity));
    query.AND(query.getStartDate().EQ(startDate));
    query.AND(query.getEndDate().EQ(endDate));

    OIterator<? extends AggregatedIPT> it = query.getIterator();

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

  @Override
  public void apply()
  {
    validateStartDate();
    validateEndDate();

    super.apply();
  }

  @Override
  public void validateStartDate()
  {
    if (this.getStartDate() != null)
    {
      super.validateStartDate();

      Date current = new Date();

      if (current.before(this.getStartDate()))
      {
        String msg = "It is impossible to have a start date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getStartDate());
        p.setCurrentDate(current);
        p.setNotification(this, STARTDATE);
        p.apply();
        p.throwIt();
      }
    }
  }
  
  @Override
  public void validateEndDate()
  {
    if (this.getEndDate() != null)
    {
      super.validateEndDate();

      Date current = new Date();

      if (current.before(this.getEndDate()))
      {
        String msg = "It is impossible to have a end date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getEndDate());
        p.setCurrentDate(current);
        p.setNotification(this, ENDDATE);
        p.apply();
        p.throwIt();
      }
    }
  }

  public static AggregatedIPTView searchByGeoEntityAndEpiDate(GeoEntity geoEntity, PeriodType periodType, Integer period, Integer year)
  {
    // IMPORTANT: WEEK is 0 based while MONTH and QUARTER are 1 based. Thus we
    // need to offset the 'period' for WEEK
    Integer _period = ( periodType.equals(PeriodType.WEEK) ? period - 1 : period );

    EpiDate.validate(periodType, _period, year);

    EpiDate date = EpiDate.getInstanceByPeriod(periodType, _period, year);

    Date endDate = date.getEndDate();
    Date startDate = date.getStartDate();
    
    AggregatedIPT concrete = searchByGeoEntityAndDate(geoEntity, startDate, endDate);

    if (concrete != null)
    {
      return concrete.getView();
    }

    AggregatedIPTView view = new AggregatedIPTView();
    view.setGeoId(geoEntity.getGeoId());
    view.setPeriod(period);
    view.addPeriodType(periodType);
    view.setPeriodYear(year);
    view.setStartDate(startDate);
    view.setEndDate(endDate);

    return view;
  }
  
  public static AggregatedIPTView searchByDate(GeoEntity geoEntity, Date startDate, Date endDate)
  {
    AggregatedIPT concrete = searchByGeoEntityAndDate(geoEntity, startDate, endDate);
    
    if (concrete != null)
    {
      return concrete.getView();
    }
    
    AggregatedIPTView view = new AggregatedIPTView();
    view.setGeoId(geoEntity.getGeoId());
    view.setStartDate(startDate);
    view.setEndDate(endDate);
    
    return view;
  }

  public static AggregatedIPTView getView(String id)
  {
    return AggregatedIPT.get(id).getView();
  }

  public AggregatedIPTView getView()
  {
    AggregatedIPTView view = new AggregatedIPTView();
    view.populateView(this);

    return view;
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    // Lock the grid relationship also, this must be in the same transaction
    for (IPTPatients patients : this.getAllPatientsRel())
    {
      patients.lock();
    }

    for (IPTANCVisit visit : this.getAllANCVisitsRel())
    {
      visit.lock();
    }

    for (IPTDose dose : this.getAllDosesRel())
    {
      dose.lock();
    }

    for (IPTTreatment treatment : this.getAllTreatmentsRel())
    {
      treatment.lock();
    }
  }

  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();

    // Lock the grid relationship also, this must be in the same transaction
    for (IPTPatients patients : this.getAllPatientsRel())
    {
      patients.unlock();
    }

    for (IPTANCVisit visit : this.getAllANCVisitsRel())
    {
      visit.unlock();
    }

    for (IPTDose dose : this.getAllDosesRel())
    {
      dose.unlock();
    }

    for (IPTTreatment treatment : this.getAllTreatmentsRel())
    {
      treatment.unlock();
    }
  }

  @Override
  public AggregatedIPTView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public AggregatedIPTView lockView()
  {
    this.lock();

    return this.getView();
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   *
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer)
  {
    JSONObject queryConfig;
    try
    {
      queryConfig = new JSONObject(config);
    }
    catch (JSONException e1)
    {
      throw new ProgrammingErrorException(e1);
    }
    
    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, layer);   
   
    AggregatedIPTQuery aggregatedIPTQuery = (AggregatedIPTQuery) queryMap.get(AggregatedIPT.CLASS);
    
    
    //this is a hack to force valueQuery to include the aggreated cases table
    valueQuery.WHERE(aggregatedIPTQuery.id().NE("0"));
    
    QueryUtil.joinGeoDisplayLabels(valueQuery, AggregatedIPT.CLASS, aggregatedIPTQuery);
    
    QueryUtil.getSingleAttribteGridSql(valueQuery,aggregatedIPTQuery.getTableAlias());
    
    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);
    
    QueryUtil.setTermRestrictions(valueQuery, queryMap);

   
    String sd = aggregatedIPTQuery.getStartDate().getQualifiedName();
    String ed = aggregatedIPTQuery.getEndDate().getQualifiedName();

    return QueryUtil.setQueryDates(xml, valueQuery, sd, ed);

  }
  
}
