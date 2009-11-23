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

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.surveillance.PeriodType;
import dss.vector.solutions.util.QueryUtil;

public class ITNData extends ITNDataBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245774436880L;
  
  public ITNData()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    if(this.getGeoEntity() != null && this.getStartDate() != null && this.getEndDate() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      
      return this.getGeoEntity().getGeoId() + "." + format.format(this.getStartDate()) + "-" + format.format(this.getEndDate());

    }
    return this.getId();
  }
  
  public static ITNData searchByGeoEntityAndDate(GeoEntity geoEntity, Date startDate, Date endDate)
  {
    ITNDataQuery query = new ITNDataQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(geoEntity));
    query.AND(query.getStartDate().EQ(startDate));
    query.AND(query.getEndDate().EQ(endDate));

    OIterator<? extends ITNData> it = query.getIterator();

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

  
  public static ITNDataView searchByGeoEntityAndEpiDate(GeoEntity geoEntity, PeriodType periodType, Integer period, Integer year)
  {
    EpiDate.validate(periodType, period, year); 

    EpiDate date = EpiDate.getInstanceByPeriod(periodType, period, year);
    
    ITNData concrete = searchByGeoEntityAndDate(geoEntity, date.getStartDate(), date.getEndDate());

    if (concrete != null)
    {     
      return concrete.getView();
    }

    ITNDataView view = new ITNDataView();
    view.setGeoId(geoEntity.getGeoId());
    view.setPeriod(period);
    view.addPeriodType(periodType);
    view.setPeriodYear(year);

    return view;
  }
  
  public static ITNDataView getView(String id)
  {
    return ITNData.get(id).getView();
  }


  public ITNDataView getView()
  {
    ITNDataView view = new ITNDataView();
    view.populateView(this);
    
    return view;
  }
  
  @Override
  @Transaction
  public void lock()
  {
    super.lock();
    
    // Lock the grid relationship also, this must be in the same transaction
    for (ITNService service : this.getAllServicesRel())
    {
      service.lock();
    }

    for (ITNTargetGroup group : this.getAllTargetGroupsRel())
    {
      group.lock();
    }

    for (ITNNet net : this.getAllNetsRel())
    {
      net.lock();
    }
  }
  
  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();
    
    // Unlock the grid relationship also, this must be in the same transaction
    for (ITNService service : this.getAllServicesRel())
    {
      service.unlock();
    }

    for (ITNTargetGroup group : this.getAllTargetGroupsRel())
    {
      group.unlock();
    }

    for (ITNNet net : this.getAllNetsRel())
    {
      net.unlock();
    }
  }
  
  @Override
  public ITNDataView unlockView()
  {
    this.unlock();
    
    return this.getView();
  }
  
  @Override
  public ITNDataView lockView()
  {
    this.lock();
    
    return this.getView();
  }
  
  @Override
  public void apply()
  {    
    //Validate the amount of currency recieved
    this.validateCurrencyReceived();    
    this.validateStartDate();
    this.validateEndDate();
    
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

  
  @Override
  public void validateCurrencyReceived()
  {
    if(this.getCurrencyReceived() != null)
    {
      if(this.getNumberSold() == null || this.getNumberSold() == 0)
      {
        String msg = "Currency received cannot be set when the total number of ITNs sold is zero.";
        CurrencyAmountProblem p = new CurrencyAmountProblem(msg);
        p.setNotification(this, ITNData.CURRENCYRECEIVED);
        p.apply();
        
        p.throwIt();
      }
    }
      
  }

  
  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   *
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Boolean includeGeometry, ThematicLayer thematicLayer)
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
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, thematicLayer, includeGeometry, AggregatedIPT.CLASS, AggregatedIPT.GEOENTITY);   
   
    ITNDataQuery itnQuery = (ITNDataQuery) queryMap.get(ITNData.CLASS);

    QueryUtil.getSingleAttribteGridSql(valueQuery,itnQuery.getTableAlias());
    
    QueryUtil.joinGeoDisplayLabels(valueQuery,ITNData.CLASS,itnQuery);
   
    String sd = itnQuery.getStartDate().getQualifiedName();
    String ed = itnQuery.getEndDate().getQualifiedName();

    return QueryUtil.setQueryDates(xml, valueQuery, sd, ed);

  }
  

}
