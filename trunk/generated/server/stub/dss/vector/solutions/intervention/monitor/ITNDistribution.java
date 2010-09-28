package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;

import dss.vector.solutions.Person;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class ITNDistribution extends ITNDistributionBase implements com.runwaysdk.generation.loader.Reloadable
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
      return "New: " + this.getClassDisplayLabel();
    }
    else if (this.getDistributionDate() != null && this.getFacility() != null && this.getRecipient() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, Session.getCurrentLocale());
      Person person = this.getRecipient().getPerson();

      String entityLabel = this.getFacility().getLabel();
      String dateLabel = format.format(this.getDistributionDate());
      String personLabel = person.getFirstName() + " " + person.getLastName();

      return this.getClassDisplayLabel() + ": (" + entityLabel + ", " + dateLabel + ", " + personLabel + ")";
    }

    return this.getClassDisplayLabel();
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
    if (recip != null && this.getDistributionDate() != null && this.getFacility() != null)
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

    QueryUtil.joinGeoDisplayLabels(valueQuery, ITNDistribution.CLASS, itnQuery);
    QueryUtil.joinTermAllpaths(valueQuery, ITNDistribution.CLASS, itnQuery);
    QueryUtil.getSingleAttribteGridSql(valueQuery, itnQuery.getTableAlias());

    dss.vector.solutions.PersonQuery personQuery = (dss.vector.solutions.PersonQuery) queryMap.get(dss.vector.solutions.Person.CLASS);
    valueQuery.WHERE(personQuery.getItnRecipientDelegate().EQ(itnQuery.getRecipient()));
    QueryUtil.joinTermAllpaths(valueQuery, dss.vector.solutions.Person.CLASS, personQuery);
    QueryUtil.joinGeoDisplayLabels(valueQuery, dss.vector.solutions.Person.CLASS, personQuery);

    try
    {
      Selectable sel = valueQuery.getSelectableRef("age");
      SelectableSQLInteger dobSel = (SelectableSQLInteger)
        (sel.isAggregateFunction() ? sel.getAggregateFunction().getSelectable() : sel);

      String personTableAlias = personQuery.getTableAlias();
      String distDateCol = QueryUtil.getColumnName(itnQuery.getMdClassIF(), ITNDistribution.DISTRIBUTIONDATE);
      String dateOfBirthCol = QueryUtil.getColumnName(personQuery.getMdClassIF(), Person.DATEOFBIRTH);
      
      String sql = "EXTRACT(year from AGE("+distDateCol+", " + personTableAlias + "."+dateOfBirthCol+"))";
      dobSel.setSQL(sql);
    }
    catch (QueryException e)
    {
      // Person.DOB not included in query.
    }

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, itnQuery.getDisease());

    return valueQuery;

  }

  @Override
  public void validateCurrencyReceived()
  {
    if (this.getCurrencyReceived() != null)
    {
      if (this.getNumberSold() == null || this.getNumberSold() == 0)
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
    // Validate the amount of currency recieved
    this.validateCurrencyReceived();

    if (this.isNew() && this.getDisease() == null) {
    	this.setDisease(Disease.getCurrent());
    }
    
    super.apply();
  }
}
