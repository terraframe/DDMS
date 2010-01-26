package dss.vector.solutions.surveillance;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.business.rbac.Operation;
import com.terraframe.mojo.dataaccess.MdAttributeConcreteDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.MdViewDAOIF;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO;
import com.terraframe.mojo.dataaccess.metadata.MdViewDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryException;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectableSQLDouble;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryCSVExporter;
import com.terraframe.mojo.query.ValueQueryExcelExporter;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.session.SessionFacade;
import com.terraframe.mojo.session.SessionIF;
import com.terraframe.mojo.session.StartSession;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;
import dss.vector.solutions.util.QueryUtil;

public class AggregatedCase extends AggregatedCaseBase implements com.terraframe.mojo.generation.loader.Reloadable
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
    if (this.getGeoEntity() != null && this.getStartDate() != null && this.getEndDate() != null && this.getStartAge() != null && this.getEndAge() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);

      String period = format.format(this.getStartDate()) + "-" + format.format(this.getEndDate());
      String ageRange = this.getStartAge() + "-" + this.getEndAge();

      return this.getGeoEntity().getGeoId() + ", " + period + ", " + ageRange;
    }
    return this.getId();
  }

  @StartSession
  public static java.lang.String[] getVisibleAttributeNames()
  {
    MdBusinessDAOIF aggregateCaseMdBusiness = MdBusinessDAO.getMdBusinessDAO(AggregatedCase.CLASS);
    List<? extends MdAttributeConcreteDAOIF> aggregateCaseAttributes = aggregateCaseMdBusiness.getAllDefinedMdAttributes();

    MdViewDAOIF aggregatedCaseMdView = MdViewDAO.getMdViewDAO(AggregatedCaseView.CLASS);

    List<MdViewDAOIF> aggregatedCaseViewSubClasses = aggregatedCaseMdView.getSubClasses();

    // Key is the class name, value is a map of attributes
    Map<String, Map<String, ? extends MdAttributeDAOIF>> subClassAttrMap = new HashMap<String, Map<String, ? extends MdAttributeDAOIF>>();

    for (MdViewDAOIF mdViewDAOIF : aggregatedCaseViewSubClasses)
    {
      subClassAttrMap.put(mdViewDAOIF.definesType(), mdViewDAOIF.getAllDefinedMdAttributeMap());
    }

    LinkedList<String> visibleAttributeNameList = new LinkedList<String>();

    for (MdAttributeConcreteDAOIF mdAttribute : aggregateCaseAttributes)
    {
      for (MdViewDAOIF mdViewDAOIF : aggregatedCaseViewSubClasses)
      {
        boolean hasVisibility = hasVisibility(mdAttribute.definesAttribute(), subClassAttrMap.get(mdViewDAOIF.definesType()));
        if (hasVisibility)
        {
          visibleAttributeNameList.add(mdAttribute.definesAttribute());
          break;
        }
      }
    }

    String[] visibleAttributeNames = new String[visibleAttributeNameList.size()];

    visibleAttributeNameList.toArray(visibleAttributeNames);

    return visibleAttributeNames;
  }

  /**
   * Returns true if the given attribute is defined by a
   * <code>MdAttributeDAOIF</code> in the given map and the current user has
   * permission to view the attribute, false otherwise.
   * 
   * <br>
   * Precondition:</br> <code>Session.getCurrentSession()</code> does not return
   * null.
   * 
   * @param attributeName
   * @param viewCaseAttributeMap
   * @return true if the given attribute is defined by a
   *         <code>MdAttributeDAOIF</code> in the given map and the current user
   *         has permission to view the attribute, false otherwise.
   */
  private static boolean hasVisibility(String attributeName, Map<String, ? extends MdAttributeDAOIF> viewCaseAttributeMap)
  {
    String attrNameLowerCase = attributeName.toLowerCase();

    boolean hasVisibility = false;

    SessionIF session = Session.getCurrentSession();

    if (viewCaseAttributeMap.containsKey(attrNameLowerCase))
    {
      MdAttributeDAOIF mdAttributeDAOIF = viewCaseAttributeMap.get(attrNameLowerCase);
      hasVisibility = SessionFacade.checkAttributeAccess(session.getId(), Operation.READ, mdAttributeDAOIF);
    }
    return hasVisibility;
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

    this.setStartAge(this.getAgeGroup().getStartAge());
    this.setEndAge(this.getAgeGroup().getEndAge());

    super.apply();
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    // Lock the grid relationship also, this must be in the same transaction
    for (CaseDiagnostic diagnostic : this.getAllDiagnosticMethodRel())
    {
      diagnostic.lock();
    }

    for (CaseReferral referral : this.getAllReferralRel())
    {
      referral.lock();
    }

    for (CaseTreatment treatment : this.getAllTreatmentRel())
    {
      treatment.lock();
    }

    for (CaseTreatmentMethod method : this.getAllTreatmentMethodRel())
    {
      method.lock();
    }

    for (CaseTreatmentStock stock : this.getAllTreatmentStockRel())
    {
      stock.lock();
    }
  }

  @Override
  @Transaction
  public void unlock()
  {
    // Unlock the grid relationship also, this must be in the same transaction
    for (CaseDiagnostic diagnostic : this.getAllDiagnosticMethodRel())
    {
      diagnostic.unlock();
    }

    for (CaseReferral referral : this.getAllReferralRel())
    {
      referral.unlock();
    }

    for (CaseTreatment treatment : this.getAllTreatmentRel())
    {
      treatment.unlock();
    }

    for (CaseTreatmentMethod method : this.getAllTreatmentMethodRel())
    {
      method.unlock();
    }

    for (CaseTreatmentStock stock : this.getAllTreatmentStockRel())
    {
      stock.unlock();
    }

    super.unlock();
  }

  @Override
  @Transaction
  public void applyAll(CaseTreatment[] treatments, CaseTreatmentMethod[] treatmentMethods, CaseTreatmentStock[] stock, CaseDiagnostic[] diagnosticMethods, CaseReferral[] referrals)
  {
    this.apply();

    for (CaseDiagnostic method : diagnosticMethods)
    {
      method.overwriteParentId(this.getId());
      method.apply();
    }

    for (CaseReferral referral : referrals)
    {
      referral.overwriteParentId(this.getId());
      referral.apply();
    }

    for (CaseTreatment treatment : treatments)
    {
      treatment.overwriteParentId(this.getId());
      treatment.apply();
    }

    for (CaseTreatmentMethod method : treatmentMethods)
    {
      method.overwriteParentId(this.getId());
      method.apply();
    }

    for (CaseTreatmentStock s : stock)
    {
      s.overwriteParentId(this.getId());
      s.apply();
    }

  }

  public static AggregatedCase searchByGeoEntityAndDate(GeoEntity geoEntity, Date startDate, Date endDate, AggregatedAgeGroup ageGroup)
  {
    AggregatedCaseQuery query = new AggregatedCaseQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(geoEntity));
    query.AND(query.getStartDate().EQ(startDate));
    query.AND(query.getEndDate().EQ(endDate));
    query.AND(query.getStartAge().EQ(ageGroup.getStartAge()));
    query.AND(query.getEndAge().EQ(ageGroup.getEndAge()));

    OIterator<? extends AggregatedCase> it = query.getIterator();

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

  public AggregatedCaseView getView()
  {
    AggregatedCaseView view = this.getAgeGroup().getView();

    view.populateView(this);

    return view;
  }

  @Transaction
  public static AggregatedCaseView searchByGeoEntityAndEpiDate(GeoEntity geoEntity, PeriodType periodType, Integer period, Integer year, AggregatedAgeGroup ageGroup)
  {
    EpiDate.validate(periodType, period - 1, year);

    EpiDate date = EpiDate.getInstanceByPeriod(periodType, period - 1, year);
    AggregatedCase c = AggregatedCase.searchByGeoEntityAndDate(geoEntity, date.getStartDate(), date.getEndDate(), ageGroup);

    if (c != null)
    {
      AggregatedCaseView view = c.getView();
      view.setAgeGroup(ageGroup);
      view.setCaseId(c.getId());

      return view;
    }

    AggregatedCaseView view = ageGroup.getView();
    view.setGeoEntity(geoEntity);
    view.setPeriod(period);
    view.addPeriodType(periodType);
    view.setPeriodYear(year);
    view.setAgeGroup(ageGroup);

    return view;
  }

  public static AggregatedCaseView searchByDates(GeoEntity geoEntity, Date startDate, Date endDate, AggregatedAgeGroup ageGroup)
  {
    AggregatedCase c = AggregatedCase.searchByGeoEntityAndDate(geoEntity, startDate, endDate, ageGroup);

    if (c != null)
    {
      AggregatedCaseView view = c.getView();
      view.setAgeGroup(ageGroup);
      view.setCaseId(c.getId());

      return view;
    }

    AggregatedCaseView view = ageGroup.getView();
    view.setGeoEntity(geoEntity);
    view.setStartDate(startDate);
    view.setEndDate(endDate);
    view.setAgeGroup(ageGroup);

    return view;
  }

  public static AggregatedCaseView getView(String id)
  {
    return AggregatedCase.get(id).getView();
  }

  public static AggregatedCaseView lockView(String id)
  {
    return AggregatedCase.lock(id).getView();
  }

  public static AggregatedCaseView unlockView(String id)
  {
    return AggregatedCase.unlock(id).getView();
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

    for (String gridAlias : queryMap.keySet())
    {
      GeneratedEntityQuery generatedQuery = queryMap.get(gridAlias);

      String termAlias = gridAlias + "_Term";
      TermQuery termQuery = (TermQuery) queryMap.get(termAlias);

      if (generatedQuery instanceof CaseTreatmentStockQuery)
      {
        CaseTreatmentStockQuery ctsq = (CaseTreatmentStockQuery) generatedQuery;
        valueQuery.AND(aggregatedCaseQuery.treatmentStock(ctsq));
        valueQuery.AND(ctsq.hasChild(termQuery));
      }
      else if (generatedQuery instanceof CaseTreatmentQuery)
      {
        CaseTreatmentQuery ctq = (CaseTreatmentQuery) generatedQuery;
        valueQuery.AND(aggregatedCaseQuery.treatment(ctq));
        valueQuery.AND(ctq.hasChild(termQuery));
      }
      else if (generatedQuery instanceof CaseReferralQuery)
      {
        CaseReferralQuery crq = (CaseReferralQuery) generatedQuery;
        valueQuery.AND(aggregatedCaseQuery.referral(crq));
        valueQuery.AND(crq.hasChild(termQuery));
      }
      else if (generatedQuery instanceof CaseDiagnosticQuery)
      {
        CaseDiagnosticQuery cdq = (CaseDiagnosticQuery) generatedQuery;
        valueQuery.AND(aggregatedCaseQuery.diagnosticMethod(cdq));
        valueQuery.AND(cdq.hasChild(termQuery));
      }
      else if (generatedQuery instanceof CaseTreatmentMethodQuery)
      {
        CaseTreatmentMethodQuery ctmq = (CaseTreatmentMethodQuery) generatedQuery;
        valueQuery.AND(aggregatedCaseQuery.treatmentMethod(ctmq));
        valueQuery.AND(ctmq.hasChild(termQuery));
      }
    }

    try
    {
      SelectableSQLDouble calc = (SelectableSQLDouble) valueQuery.getSelectableRef("sqldouble__cfr");
      String sql = "(SUM(deaths::FLOAT)/SUM(cases))*100.0";
      calc.setSQL(sql);
    }
    catch (QueryException e)
    {
    }

    calculateIncidence(valueQuery, aggregatedCaseQuery, queryConfig, xml, 100);
    calculateIncidence(valueQuery, aggregatedCaseQuery, queryConfig, xml, 1000);
    calculateIncidence(valueQuery, aggregatedCaseQuery, queryConfig, xml, 10000);
    calculateIncidence(valueQuery, aggregatedCaseQuery, queryConfig, xml, 100000);
    calculateIncidence(valueQuery, aggregatedCaseQuery, queryConfig, xml, 1000000);

    String sd = aggregatedCaseQuery.getStartDate().getQualifiedName();
    String ed = aggregatedCaseQuery.getEndDate().getQualifiedName();

    System.out.println(valueQuery.getSQL());
    return QueryUtil.setQueryDates(xml, valueQuery, sd, ed);

  }

  private static void calculateIncidence(ValueQuery valueQuery, AggregatedCaseQuery caseQuery, JSONObject queryConfig, String xml, Integer multiplier)
  {
    try
    {
      SelectableSQLDouble calc = (SelectableSQLDouble) valueQuery.getSelectableRef("sqldouble__incidence_" + multiplier);

      String geoType = null;

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

      String timePeriod = "yearly";

      if (xml.indexOf("season") > 0)
      {
        timePeriod = "seasonal";
      }

      Selectable s = valueQuery.getSelectableRef(geoType);

      String columnAlias = s.getQualifiedName();

      String sql = "(SUM(cases::FLOAT)/";
      sql += " NULLIF(AVG(get_" + timePeriod + "_population_by_geoid_and_date(" + columnAlias + ", " + AggregatedCase.STARTDATE + ")),0))*" + multiplier;

      calc.setSQL(sql);
    }
    catch (QueryException e)
    {
    }
    catch (JSONException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * Returns the alias for the relationship query that maps to the grid query
   * with the given alias.
   */
  private static String getRelationshipAlias(String gridAlias)
  {
    // int firstIndex = gridAlias.indexOf("_", 0);
    int index = gridAlias.lastIndexOf("_");

    return gridAlias.substring(0, index);
  }

  /**
   * Queries for AggregatedCases.
   * 
   * @param xml
   */
  @Transaction
  @Authenticate
  public static com.terraframe.mojo.query.ValueQuery queryAggregatedCase(String xml, String config, Integer pageNumber, Integer pageSize)
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

    ValueQueryCSVExporter exporter = new ValueQueryCSVExporter(query);
    return exporter.exportStream();
  }
}
