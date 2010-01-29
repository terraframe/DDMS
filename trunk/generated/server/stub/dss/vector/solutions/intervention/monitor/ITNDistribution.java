package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.QueryException;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableSQLInteger;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.Person;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class ITNDistribution extends ITNDistributionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253142897109L;
  
  public ITNDistribution()
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
    else if(this.getDistributionDate() != null && this.getFacility() != null && this.getRecipient() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, Session.getCurrentLocale());
      Person person = this.getRecipient().getPerson();
      
      String entityLabel = this.getFacility().getLabel();
      String dateLabel = format.format(this.getDistributionDate());
      String personLabel = person.getFirstName() + " " + person.getLastName();
      
      return this.getClassDisplayLabel() + ": (" + entityLabel + ", " + dateLabel + ", " + personLabel + ")";
    }
    
    return super.toString();
  }  
  
  public static ITNDistributionView getView(String id)
  {
    return ITNDistribution.get(id).getView();
  }

  public ITNDistributionView getView()
  {
    ITNDistributionView view = new ITNDistributionView();
    view.populateView(this);

    return view;
  }

  @Override
  public ITNDistributionView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public ITNDistributionView lockView()
  {
    this.lock();

    return this.getView();
  }

    
  @Override
  @Transaction
  public void lock()
  {
    super.lock();
    
    for (ITNDistributionTargetGroup group : this.getAllTargetGroupsRel())
    {
      group.lock();
    }
  }
  
  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();
    
    for (ITNDistributionTargetGroup group : this.getAllTargetGroupsRel())
    {
      group.unlock();
    }
  }
  
  @Override
  protected String buildKey()
  {
    ITNRecipient recip = this.getRecipient();
    if(recip != null && this.getDistributionDate() != null && this.getFacility() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      Person person = recip.getPerson();
      
      return person.getFirstName() + "." + person.getLastName() + "." + this.getFacility().getGeoId() + "." + format.format(this.getDistributionDate());
    }
    return this.getId();
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
   
    ITNDistributionQuery itnQuery = (ITNDistributionQuery) queryMap.get(ITNDistribution.CLASS);
    
    QueryUtil.joinGeoDisplayLabels(valueQuery,ITNDistribution.CLASS,itnQuery);
    QueryUtil.joinTermAllpaths(valueQuery,ITNDistribution.CLASS,itnQuery);
    QueryUtil.getSingleAttribteGridSql(valueQuery,itnQuery.getTableAlias());
    
    
    dss.vector.solutions.PersonQuery personQuery = (dss.vector.solutions.PersonQuery) queryMap.get(dss.vector.solutions.Person.CLASS);
    valueQuery.WHERE(personQuery.getItnRecipientDelegate().EQ(itnQuery.getRecipient()));
    QueryUtil.joinTermAllpaths(valueQuery,dss.vector.solutions.Person.CLASS,personQuery);
    QueryUtil.joinGeoDisplayLabels(valueQuery,dss.vector.solutions.Person.CLASS,personQuery);
    
    try
    {
      SelectableSQLInteger dobSel = (SelectableSQLInteger) valueQuery.getSelectableRef("age");

      String personTableAlias = personQuery.getTableAlias();
      String sql = "EXTRACT(year from AGE(NOW(), " + personTableAlias + ".dateofbirth))";
      dobSel.setSQL(sql);
    }
    catch (QueryException e)
    {
      // Person.DOB not included in query.
    }

    
    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);
    
    QueryUtil.setTermRestrictions(valueQuery, queryMap);
   
    String sd = itnQuery.getDistributionDate().getQualifiedName();

    return QueryUtil.setQueryDates(xml, valueQuery, sd);

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
  
  @Override
  public void apply()
  {    
    //Validate the amount of currency recieved
    this.validateCurrencyReceived();    
    
    super.apply();
  }
}
