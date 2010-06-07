package dss.vector.solutions.surveillance;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQLDouble;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.ValueQueryCSVExporter;
import com.runwaysdk.query.ValueQueryExcelExporter;
import com.runwaysdk.session.Session;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.query.IncidencePopulationException;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;
import dss.vector.solutions.util.QueryUtil;

public class AggregatedCase extends AggregatedCaseBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693161773L;

  public AggregatedCase()
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

    return "(" + this.buildKey() + ")";
  }

  @Override
  protected String buildKey()
  {
    if (this.getGeoEntity() != null && this.getStartDate() != null && this.getEndDate() != null && this.getAgeGroup() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);

      String period = format.format(this.getStartDate()) + "-" + format.format(this.getEndDate());
      String ageRange = this.getAgeGroup().getKey();

      return this.getGeoEntity().getGeoId() + ", " + period + ", " + ageRange;
    }
    return this.getId();
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
  public void apply()
  {
    validateStartDate();
    validateEndDate();

    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    super.apply();
  }
  
  @Override
  public void delete()
  {
    List<CaseTreatmentMethod> methods = this.getTreatmentMethods();
    
    for(CaseTreatmentMethod method : methods)
    {
      method.delete();
    }
    
    List<CaseTreatment> treatments = this.getTreatments();
    
    for(CaseTreatment treatment : treatments)
    {
      treatment.delete();
    }
    
    List<CaseTreatmentStock> stocks = this.getTreatmentStocks();
    
    for(CaseTreatmentStock stock : stocks)
    {
      stock.delete();
    }
    
    List<CaseDiagnostic> diagnostics = this.getDiagnosticMethods();

    for(CaseDiagnostic method : diagnostics)
    {
      method.delete();
    }
    
    List<CaseReferral> referrals = this.getReferrals();
    
    for(CaseReferral referral : referrals)
    {
      referral.delete();
    }    

    super.delete();
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();
    
    List<CaseTreatmentMethod> methods = this.getTreatmentMethods();
    
    for(CaseTreatmentMethod method : methods)
    {
      method.lock();
    }
    
    List<CaseTreatment> treatments = this.getTreatments();
    
    for(CaseTreatment treatment : treatments)
    {
      treatment.lock();
    }
    
    List<CaseTreatmentStock> stocks = this.getTreatmentStocks();
    
    for(CaseTreatmentStock stock : stocks)
    {
      stock.lock();
    }
    
    List<CaseDiagnostic> diagnostics = this.getDiagnosticMethods();

    for(CaseDiagnostic method : diagnostics)
    {
      method.lock();
    }
    
    List<CaseReferral> referrals = this.getReferrals();
    
    for(CaseReferral referral : referrals)
    {
      referral.lock();
    }    
  }

  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();
    
    List<CaseTreatmentMethod> methods = this.getTreatmentMethods();
    
    for(CaseTreatmentMethod method : methods)
    {
      method.unlock();
    }
    
    List<CaseTreatment> treatments = this.getTreatments();
    
    for(CaseTreatment treatment : treatments)
    {
      treatment.unlock();
    }
    
    List<CaseTreatmentStock> stocks = this.getTreatmentStocks();
    
    for(CaseTreatmentStock stock : stocks)
    {
      stock.unlock();
    }
    
    List<CaseDiagnostic> diagnostics = this.getDiagnosticMethods();

    for(CaseDiagnostic method : diagnostics)
    {
      method.unlock();
    }
    
    List<CaseReferral> referrals = this.getReferrals();
    
    for(CaseReferral referral : referrals)
    {
      referral.unlock();
    }    
  }

  public List<CaseTreatmentMethod> getTreatmentMethods()
  {
    List<CaseTreatmentMethod> list = new LinkedList<CaseTreatmentMethod>();

    CaseTreatmentMethodQuery query = new CaseTreatmentMethodQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));

    OIterator<? extends CaseTreatmentMethod> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }

    return list;
  }

  public List<CaseTreatment> getTreatments()
  {
    List<CaseTreatment> list = new LinkedList<CaseTreatment>();
    
    CaseTreatmentQuery query = new CaseTreatmentQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));
    
    OIterator<? extends CaseTreatment> it = query.getIterator();
    
    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }
    
    return list;
  }
  
  public List<CaseDiagnostic> getDiagnosticMethods()
  {
    List<CaseDiagnostic> list = new LinkedList<CaseDiagnostic>();
    
    CaseDiagnosticQuery query = new CaseDiagnosticQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));
    
    OIterator<? extends CaseDiagnostic> it = query.getIterator();
    
    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }
    
    return list;
  }
  
  public List<CaseReferral> getReferrals()
  {
    List<CaseReferral> list = new LinkedList<CaseReferral>();
    
    CaseReferralQuery query = new CaseReferralQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));
    
    OIterator<? extends CaseReferral> it = query.getIterator();
    
    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }
    
    return list;
  }
  
  public List<CaseTreatmentStock> getTreatmentStocks()
  {
    List<CaseTreatmentStock> list = new LinkedList<CaseTreatmentStock>();
    
    CaseTreatmentStockQuery query = new CaseTreatmentStockQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));
    
    OIterator<? extends CaseTreatmentStock> it = query.getIterator();
    
    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }
    
    return list;
  }
  
  
  
  public AggregatedCaseView getView()
  {
    AggregatedCaseView view = new AggregatedCaseView();

    view.populateView(this);

    return view;
  }

  @Override
  public AggregatedCaseView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public AggregatedCaseView unlockView()
  {
    this.unlock();

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

    AggregatedCaseQuery aggregatedCaseQuery = (AggregatedCaseQuery) queryMap.get(AggregatedCase.CLASS);

    // for (String gridAlias : queryMap.keySet())
    // {
    // GeneratedEntityQuery generatedQuery = queryMap.get(gridAlias);
    //
    // String termAlias = gridAlias + "_Term";
    // TermQuery termQuery = (TermQuery) queryMap.get(termAlias);
    //
    // }

    if (valueQuery.hasSelectableRef("sqldouble__cfr"))
    {
      // String deathsCol =
      // QueryUtil.getColumnName(aggregatedCaseQuery.getMdClassIF(),
      // AggregatedCase.DEATHS);
      // String casesCol =
      // QueryUtil.getColumnName(aggregatedCaseQuery.getMdClassIF(),
      // AggregatedCase.CASES);
      //
      // SelectableSQLDouble calc = (SelectableSQLDouble)
      // valueQuery.getSelectableRef("sqldouble__cfr");
      // String sql = "(SUM(" + deathsCol + "::FLOAT)/NULLIF(SUM(" + casesCol +
      // "),0))*100.0";
      // calc.setSQL(sql);
    }

    calculateIncidence(valueQuery, aggregatedCaseQuery, queryConfig, xml, 100);
    calculateIncidence(valueQuery, aggregatedCaseQuery, queryConfig, xml, 1000);
    calculateIncidence(valueQuery, aggregatedCaseQuery, queryConfig, xml, 10000);
    calculateIncidence(valueQuery, aggregatedCaseQuery, queryConfig, xml, 100000);
    calculateIncidence(valueQuery, aggregatedCaseQuery, queryConfig, xml, 1000000);

    QueryUtil.joinGeoDisplayLabels(valueQuery, AggregatedCase.CLASS, aggregatedCaseQuery);
    QueryUtil.setQueryDates(xml, valueQuery, aggregatedCaseQuery, aggregatedCaseQuery.getStartDate(), aggregatedCaseQuery.getEndDate());

    QueryUtil.validateQuery(valueQuery);

    return valueQuery;
  }

  private static void calculateIncidence(ValueQuery valueQuery, AggregatedCaseQuery caseQuery, JSONObject queryConfig, String xml, Integer multiplier)
  {
    SelectableSQLDouble calc;
    if (valueQuery.hasSelectableRef("sqldouble__incidence_" + multiplier))
    {
      calc = (SelectableSQLDouble) valueQuery.getSelectableRef("sqldouble__incidence_" + multiplier);
    }
    else
    {
      return;
    }

    String geoType = null;
    try
    {
      String attributeKey = null;
      String[] selectedUniversals = null;

      JSONObject selectedUniMap = queryConfig.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);
      Iterator<?> keys = selectedUniMap.keys();
      while (keys.hasNext())
      {
        attributeKey = (String) keys.next();

        JSONArray universals = selectedUniMap.getJSONArray(attributeKey);
        if (universals.length() > 0 && attributeKey.equals(AggregatedCase.CLASS + '.' + AggregatedCase.GEOENTITY))
        {
          selectedUniversals = new String[universals.length()];
          for (int i = 0; i < universals.length(); i++)
          {
            selectedUniversals[i] = universals.getString(i);
          }
          // dss_vector_solutions_intervention_monitor_IndividualCase_probableSource__district_geoId
          geoType = GeoHierarchy.getMostChildishUniversialType(selectedUniversals);
          geoType = geoType.substring(geoType.lastIndexOf('.')).toLowerCase();
          geoType = attributeKey + '.' + geoType + '.' + GeoEntity.GEOID;
          geoType = geoType.replace('.', '_');
        }
      }
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }

    String timePeriod = "yearly";

    if (xml.indexOf("season") > 0)
    {
      timePeriod = "seasonal";
    }

    Selectable s;
    try
    {
      s = valueQuery.getSelectableRef(geoType);
    }
    catch (QueryException e)
    {
      throw new IncidencePopulationException(e);
    }

    String columnAlias = s.getDbQualifiedName();
    String casesCol = QueryUtil.getColumnName(caseQuery.getMdClassIF(), AggregatedCase.CASES);
    String startDateCol = QueryUtil.getColumnName(caseQuery.getMdClassIF(), AggregatedCase.STARTDATE);

    String sql = "(SUM(" + casesCol + "::FLOAT)/";
    sql += " NULLIF(AVG(get_" + timePeriod + "_population_by_geoid_and_date(" + columnAlias + ", " + startDateCol + ")),0))*" + multiplier;

    calc.setSQL(sql);
  }

  /**
   * Queries for AggregatedCases.
   * 
   * @param xml
   */
  @Transaction
  @Authenticate
  public static com.runwaysdk.query.ValueQuery queryAggregatedCase(String xml, String config, Integer pageNumber, Integer pageSize)
  {
    ValueQuery valueQuery = xmlToValueQuery(xml, config, null);

    valueQuery.restrictRows(pageSize, pageNumber);

    return valueQuery;
  }

  @Transaction
  public static InputStream exportQueryToExcel(String queryXML, String config, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to Excel without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);

    ValueQuery query = xmlToValueQuery(queryXML, config, null);

    ValueQueryExcelExporter exporter = new ValueQueryExcelExporter(query, search.getQueryName());
    return exporter.exportStream();
  }

  @Transaction
  public static InputStream exportQueryToCSV(String queryXML, String config, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to CSV without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    ValueQuery query = xmlToValueQuery(queryXML, config, null);

    DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT, Session.getCurrentLocale());

    ValueQueryCSVExporter exporter = new ValueQueryCSVExporter(query, dateFormat, null, null);

    return exporter.exportStream();
  }
}
