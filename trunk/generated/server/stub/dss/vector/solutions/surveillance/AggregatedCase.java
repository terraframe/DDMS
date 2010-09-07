package dss.vector.solutions.surveillance;

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

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQLFloat;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.query.IncidencePopulationException;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

public class AggregatedCase extends AggregatedCaseBase implements
    com.runwaysdk.generation.loader.Reloadable
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
    if (this.getGeoEntity() != null && this.getStartDate() != null && this.getEndDate() != null
        && this.getAgeGroup() != null)
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

    for (CaseTreatmentMethod method : methods)
    {
      method.delete();
    }

    List<CaseTreatment> treatments = this.getTreatments();

    for (CaseTreatment treatment : treatments)
    {
      treatment.delete();
    }

    List<CaseTreatmentStock> stocks = this.getTreatmentStocks();

    for (CaseTreatmentStock stock : stocks)
    {
      stock.delete();
    }

    List<CaseDiagnostic> diagnostics = this.getDiagnosticMethods();

    for (CaseDiagnostic method : diagnostics)
    {
      method.delete();
    }

    List<CaseReferral> referrals = this.getReferrals();

    for (CaseReferral referral : referrals)
    {
      referral.delete();
    }

    List<CaseStockReferral> stockReferrals = this.getStockReferrals();

    for (CaseStockReferral referral : stockReferrals)
    {
      referral.delete();
    }

    List<CaseDiagnosisType> types = this.getDiagnosisTypes();

    for (CaseDiagnosisType type : types)
    {
      type.delete();
    }

    List<CaseDiseaseManifestation> manifestations = this.getDiseaseManifestations();

    for (CaseDiseaseManifestation manifestation : manifestations)
    {
      manifestation.delete();
    }

    List<CasePatientType> patientTypes = this.getPatientTypes();

    for (CasePatientType patientType : patientTypes)
    {
      patientType.delete();
    }

    super.delete();
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    List<CaseTreatmentMethod> methods = this.getTreatmentMethods();

    for (CaseTreatmentMethod method : methods)
    {
      method.lock();
    }

    List<CaseTreatment> treatments = this.getTreatments();

    for (CaseTreatment treatment : treatments)
    {
      treatment.lock();
    }

    List<CaseTreatmentStock> stocks = this.getTreatmentStocks();

    for (CaseTreatmentStock stock : stocks)
    {
      stock.lock();
    }

    List<CaseDiagnostic> diagnostics = this.getDiagnosticMethods();

    for (CaseDiagnostic method : diagnostics)
    {
      method.lock();
    }

    List<CaseReferral> referrals = this.getReferrals();

    for (CaseReferral referral : referrals)
    {
      referral.lock();
    }

    List<CaseStockReferral> stockReferrals = this.getStockReferrals();

    for (CaseStockReferral referral : stockReferrals)
    {
      referral.lock();
    }

    List<CaseDiagnosisType> types = this.getDiagnosisTypes();

    for (CaseDiagnosisType type : types)
    {
      type.lock();
    }

    List<CaseDiseaseManifestation> manifestations = this.getDiseaseManifestations();

    for (CaseDiseaseManifestation manifestation : manifestations)
    {
      manifestation.lock();
    }

    List<CasePatientType> patientTypes = this.getPatientTypes();

    for (CasePatientType patientType : patientTypes)
    {
      patientType.lock();
    }
  }

  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();

    List<CaseTreatmentMethod> methods = this.getTreatmentMethods();

    for (CaseTreatmentMethod method : methods)
    {
      method.unlock();
    }

    List<CaseTreatment> treatments = this.getTreatments();

    for (CaseTreatment treatment : treatments)
    {
      treatment.unlock();
    }

    List<CaseTreatmentStock> stocks = this.getTreatmentStocks();

    for (CaseTreatmentStock stock : stocks)
    {
      stock.unlock();
    }

    List<CaseDiagnostic> diagnostics = this.getDiagnosticMethods();

    for (CaseDiagnostic method : diagnostics)
    {
      method.unlock();
    }

    List<CaseReferral> referrals = this.getReferrals();

    for (CaseReferral referral : referrals)
    {
      referral.unlock();
    }

    List<CaseStockReferral> stockReferrals = this.getStockReferrals();

    for (CaseStockReferral referral : stockReferrals)
    {
      referral.unlock();
    }

    List<CaseDiagnosisType> types = this.getDiagnosisTypes();

    for (CaseDiagnosisType type : types)
    {
      type.unlock();
    }

    List<CaseDiseaseManifestation> manifestations = this.getDiseaseManifestations();

    for (CaseDiseaseManifestation manifestation : manifestations)
    {
      manifestation.unlock();
    }

    List<CasePatientType> patientTypes = this.getPatientTypes();

    for (CasePatientType patientType : patientTypes)
    {
      patientType.unlock();
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

  public List<CaseStockReferral> getStockReferrals()
  {
    List<CaseStockReferral> list = new LinkedList<CaseStockReferral>();

    CaseStockReferralQuery query = new CaseStockReferralQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));

    OIterator<? extends CaseStockReferral> it = query.getIterator();

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

  public List<CaseDiagnosisType> getDiagnosisTypes()
  {
    List<CaseDiagnosisType> list = new LinkedList<CaseDiagnosisType>();

    CaseDiagnosisTypeQuery query = new CaseDiagnosisTypeQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));

    OIterator<? extends CaseDiagnosisType> it = query.getIterator();

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

  public List<CaseDiseaseManifestation> getDiseaseManifestations()
  {
    List<CaseDiseaseManifestation> list = new LinkedList<CaseDiseaseManifestation>();

    CaseDiseaseManifestationQuery query = new CaseDiseaseManifestationQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));

    OIterator<? extends CaseDiseaseManifestation> it = query.getIterator();

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

  public List<CasePatientType> getPatientTypes()
  {
    List<CasePatientType> list = new LinkedList<CasePatientType>();

    CasePatientTypeQuery query = new CasePatientTypeQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));

    OIterator<? extends CasePatientType> it = query.getIterator();

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
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory,
        valueQuery, xml, queryConfig, layer);

    AggregatedCaseQuery aggregatedCaseQuery = (AggregatedCaseQuery) queryMap.get(AggregatedCase.CLASS);
    valueQuery.FROM(aggregatedCaseQuery.getMdClassIF().getTableName(), aggregatedCaseQuery
        .getTableAlias());

    QueryUtil.getSingleAttribteGridSql(valueQuery, aggregatedCaseQuery.getTableAlias(), QueryUtil
        .getColumnName(CaseTreatment.getTermMd()), QueryUtil.getColumnName(CaseTreatment
        .getAggregatedCaseMd()));

    QueryUtil.getSingleAttribteGridSql(valueQuery, aggregatedCaseQuery.getTableAlias(), QueryUtil
        .getColumnName(CaseTreatmentStock.getTermMd()), QueryUtil.getColumnName(CaseTreatmentStock
        .getAggregatedCaseMd()));

    QueryUtil.getSingleAttribteGridSql(valueQuery, aggregatedCaseQuery.getTableAlias(), QueryUtil
        .getColumnName(CaseReferral.getTermMd()), QueryUtil.getColumnName(CaseReferral
        .getAggregatedCaseMd()));

    QueryUtil.getSingleAttribteGridSql(valueQuery, aggregatedCaseQuery.getTableAlias(), QueryUtil
        .getColumnName(CaseStockReferral.getTermMd()), QueryUtil.getColumnName(CaseStockReferral
        .getAggregatedCaseMd()));

    QueryUtil.getSingleAttribteGridSql(valueQuery, aggregatedCaseQuery.getTableAlias(), QueryUtil
        .getColumnName(CaseDiagnostic.getTermMd()), QueryUtil.getColumnName(CaseDiagnostic
        .getAggregatedCaseMd()));

    if (valueQuery.hasSelectableRef("totalCases"))
    {
      SelectableSQLFloat calc = (SelectableSQLFloat) valueQuery.getSelectableRef("totalCases");
      String sql = getTotalCasesSQL(aggregatedCaseQuery);
      calc.setSQL(sql);
    }

    if (valueQuery.hasSelectableRef("cfr"))
    {
      String deathsCol = QueryUtil.getColumnName(AggregatedCase.getDeathsMd());
      String casesSQL = getTotalCasesSQL(aggregatedCaseQuery);

      SelectableSQLFloat calc = (SelectableSQLFloat) valueQuery.getSelectableRef("cfr");
      String sql = "(SUM(" + deathsCol + "::FLOAT)/NULLIF(("+casesSQL+"),0))*100.0";
      calc.setSQL(sql);
    }

    calculateIncidence(valueQuery, aggregatedCaseQuery, queryConfig, xml, 100);
    calculateIncidence(valueQuery, aggregatedCaseQuery, queryConfig, xml, 1000);
    calculateIncidence(valueQuery, aggregatedCaseQuery, queryConfig, xml, 10000);
    calculateIncidence(valueQuery, aggregatedCaseQuery, queryConfig, xml, 100000);
    calculateIncidence(valueQuery, aggregatedCaseQuery, queryConfig, xml, 1000000);

    joinPatientTypes(valueQuery, aggregatedCaseQuery);
    joinDiagnosisTypes(valueQuery, aggregatedCaseQuery);
    joinDiseaseManifestations(valueQuery, aggregatedCaseQuery);

    QueryUtil.joinGeoDisplayLabels(valueQuery, AggregatedCase.CLASS, aggregatedCaseQuery);
    QueryUtil.setQueryDates(xml, valueQuery, aggregatedCaseQuery, aggregatedCaseQuery.getStartDate(),
        aggregatedCaseQuery.getEndDate());

    QueryUtil.validateQuery(valueQuery);

    valueQuery.WHERE(aggregatedCaseQuery.getDisease().EQ(Disease.getCurrent()));

    return valueQuery;
  }

  private static void calculateIncidence(ValueQuery valueQuery, AggregatedCaseQuery caseQuery,
      JSONObject queryConfig, String xml, Integer multiplier)
  {
    SelectableSQLFloat calc;
    if (valueQuery.hasSelectableRef("incidence_" + multiplier))
    {
      calc = (SelectableSQLFloat) valueQuery.getSelectableRef("incidence_" + multiplier);
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
        if (universals.length() > 0
            && attributeKey.equals(AggregatedCase.CLASS + '.' + AggregatedCase.GEOENTITY))
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

    String startDateCol = QueryUtil.getColumnName(caseQuery.getMdClassIF(), AggregatedCase.STARTDATE);

    String sql = getTotalCasesSQL(caseQuery);
    String columnAlias = s.getDbQualifiedName();
    sql += "/";
    sql += " NULLIF(AVG(get_" + timePeriod + "_population_by_geoid_and_date(" + columnAlias + ", "
        + startDateCol + ")),0)*" + multiplier;

    calc.setSQL(sql);

    // OLD calc
    // String sql = "(SUM(" + casesCol + "::FLOAT)/";

    // Total Cases = Sum(Cases with confirmed positive diagnosis) +
    // (Sum(Cases with clinical diagnosis) *
    // (Sum(Cases with confirmed positive diagnosis) /
    // (Sum(Cases with confirmed positive diagnosis) + Sum(Cases with confirmed
    // negative diagnosis))))
//    calc.setSQL(sql);

  }

  private static String getTotalCasesSQL(AggregatedCaseQuery caseQuery)
  {
    String casesCol = QueryUtil.getColumnName(caseQuery.getMdClassIF(), AggregatedCase.CASES);
    String posCasesCol = QueryUtil.getColumnName(caseQuery.getMdClassIF(), AggregatedCase.POSITIVECASES);
    String negCasesCol = QueryUtil.getColumnName(caseQuery.getMdClassIF(), AggregatedCase.NEGATIVECASES);

//    String sql = "(SUM(" + posCasesCol + ")";
//    sql += "+";
//    sql += "(SUM(" + casesCol + ") * SUM(" + posCasesCol + ")/";
//    sql += "NULLIF(SUM(COALESCE(" + posCasesCol + ",0)) + SUM(COALESCE(" + negCasesCol + ",0)),0.0)))";
    
    String sql = "";
    sql += "SUM(COALESCE("+posCasesCol+",0.0))::float +  \n";
    sql += "( \n";
    sql += " SUM(COALESCE("+casesCol+",0.0))::float * \n";
    sql += " ( \n";
    sql += "  SUM(COALESCE("+posCasesCol+",0.0))::float / \n";
    sql += "  ( \n";
    sql += "   NULLIF \n";
    sql += "   ( \n";
    sql += "     SUM(COALESCE("+posCasesCol+",0.0))::float + SUM(COALESCE("+negCasesCol+",0.0))::float \n";
    sql += "     ,0.0 \n";
    sql += "   ) \n";
    sql += "  ) \n";
    sql += " ) \n";
    sql += ") \n";   

    return sql;
  }

  public static void joinPatientTypes(ValueQuery valueQuery, AggregatedCaseQuery aggregatedCaseQuery)
  {
    MdEntityDAOIF patientTypeMd = MdEntityDAO.getMdEntityDAO(CasePatientType.CLASS);

    String term = QueryUtil.getColumnName(patientTypeMd, CasePatientType.TERM);
    String aggCase = QueryUtil.getColumnName(patientTypeMd, CasePatientType.AGGREGATEDCASE);
    MdEntityDAOIF ammountMd = MdEntityDAO.getMdEntityDAO(CasePatientTypeAmount.CLASS);

    String amount = QueryUtil.getColumnName(ammountMd, CasePatientTypeAmount.AMOUNT);
    String id = QueryUtil.getColumnName(ammountMd, CasePatientTypeAmount.ID);
    String child_id = "child_id";
    String parent_id = "parent_id";
    String patientTypeTable = MdBusiness.getMdBusiness(CasePatientType.CLASS).getTableName();
    String patientTypeAmountTable = MdBusiness.getMdEntity(CasePatientTypeAmount.CLASS).getTableName();

    boolean needsJoin = false;

    String patientTypeSql = "SELECT ac." + id + " as aggcase\n";
    for (Term patientType : Term.getRootChildren(AggregatedCaseView.getCasePatientTypeMd()))
    {
      String patientTypeMoID = patientType.getTermId().replace(":", "");

      for (Term patientTypeAmount : Term.getRootChildren(CasePatientTypeView.getPatientCategoryMd()))
      {
        String patientTypeAmountMoID = patientTypeAmount.getTermId().replace(":", "");
        String ammountCol = AggregatedCaseView.CASEPATIENTTYPE + "__" + patientTypeMoID
            + patientTypeAmountMoID;

        if (valueQuery.hasSelectableRef(ammountCol))
        {
          needsJoin = true;
          patientTypeSql = "SELECT " + amount + "\n";
          patientTypeSql += " FROM " + patientTypeAmountTable + " pta JOIN " + patientTypeTable
              + " pt ON pta." + parent_id + "  = pt." + id + "\n ";
          patientTypeSql += " WHERE " + child_id + " = '" + patientTypeAmount.getId() + "'  AND pt."
              + term + " = '" + patientType.getId() + "'";
          patientTypeSql += " AND " + aggregatedCaseQuery.getTableAlias() + ".id = pt." + aggCase
              + "\n ";

          QueryUtil.setSelectabeSQL(valueQuery, ammountCol, patientTypeSql);
        }
      }

    }

    if (needsJoin)
    {
      valueQuery.AND(aggregatedCaseQuery.getId().EQ(aggregatedCaseQuery.getId()));
    }
  }

  public static void joinDiagnosisTypes(ValueQuery valueQuery, AggregatedCaseQuery aggregatedCaseQuery)
  {
    MdEntityDAOIF patientTypeMd = MdEntityDAO.getMdEntityDAO(CaseDiagnosisType.CLASS);

    String term = QueryUtil.getColumnName(patientTypeMd, CaseDiagnosisType.TERM);
    String aggCase = QueryUtil.getColumnName(patientTypeMd, CaseDiagnosisType.AGGREGATEDCASE);
    MdEntityDAOIF ammountMd = MdEntityDAO.getMdEntityDAO(CaseDiagnosisTypeAmount.CLASS);

    String amount = QueryUtil.getColumnName(ammountMd, CaseDiagnosisTypeAmount.AMOUNT);
    String id = QueryUtil.getColumnName(ammountMd, CaseDiagnosisTypeAmount.ID);
    String child_id = "child_id";
    String parent_id = "parent_id";
    String diagTypeTable = MdBusiness.getMdBusiness(CaseDiagnosisType.CLASS).getTableName();
    String diagTypeAmountTable = MdBusiness.getMdEntity(CaseDiagnosisTypeAmount.CLASS).getTableName();

    boolean needsJoin = false;

    String patientTypeSql = "SELECT ac." + id + " as aggcase\n";
    for (Term patientType : Term.getRootChildren(AggregatedCaseView.getCaseDiagnosisTypeMd()))
    {
      String patientTypeMoID = patientType.getTermId().replace(":", "");

      for (Term patientTypeAmount : Term.getRootChildren(CaseDiagnosisTypeView.getDiagnosisCategoryMd()))
      {
        String patientTypeAmountMoID = patientTypeAmount.getTermId().replace(":", "");
        String ammountCol = AggregatedCaseView.CASEDIAGNOSISTYPE + "__" + patientTypeMoID
            + patientTypeAmountMoID;

        if (valueQuery.hasSelectableRef(ammountCol))
        {
          needsJoin = true;
          patientTypeSql = "SELECT " + amount + "\n";
          patientTypeSql += " FROM " + diagTypeAmountTable + " pta JOIN " + diagTypeTable
              + " pt ON pta." + parent_id + "  = pt." + id + "\n ";
          patientTypeSql += " WHERE " + child_id + " = '" + patientTypeAmount.getId() + "'  AND pt."
              + term + " = '" + patientType.getId() + "'";
          patientTypeSql += " AND " + aggregatedCaseQuery.getTableAlias() + ".id = pt." + aggCase
              + "\n ";

          QueryUtil.setSelectabeSQL(valueQuery, ammountCol, patientTypeSql);
        }
      }

    }

    if (needsJoin)
    {
      valueQuery.AND(aggregatedCaseQuery.getId().EQ(aggregatedCaseQuery.getId()));
    }
  }

  public static void joinDiseaseManifestations(ValueQuery valueQuery,
      AggregatedCaseQuery aggregatedCaseQuery)
  {
    MdEntityDAOIF patientTypeMd = MdEntityDAO.getMdEntityDAO(CaseDiseaseManifestation.CLASS);

    String term = QueryUtil.getColumnName(patientTypeMd, CaseDiseaseManifestation.TERM);
    String aggCase = QueryUtil.getColumnName(patientTypeMd, CaseDiseaseManifestation.AGGREGATEDCASE);
    MdEntityDAOIF ammountMd = MdEntityDAO.getMdEntityDAO(CaseDiseaseManifestationAmount.CLASS);

    String amount = QueryUtil.getColumnName(ammountMd, CaseDiseaseManifestationAmount.AMOUNT);
    String id = QueryUtil.getColumnName(ammountMd, CaseDiseaseManifestationAmount.ID);
    String child_id = "child_id";
    String parent_id = "parent_id";
    String patientTypeTable = MdBusiness.getMdBusiness(CaseDiseaseManifestation.CLASS).getTableName();
    String patientTypeAmountTable = MdBusiness.getMdEntity(CaseDiseaseManifestationAmount.CLASS)
        .getTableName();

    boolean needsJoin = false;

    String patientTypeSql = "SELECT ac." + id + " as aggcase\n";
    for (Term patientType : Term.getRootChildren(AggregatedCaseView.getCaseDiseaseManifestationMd()))
    {
      String patientTypeMoID = patientType.getTermId().replace(":", "");

      for (Term patientTypeAmount : Term.getRootChildren(CaseDiseaseManifestationView
          .getDiseaseCategoryMd()))
      {
        String patientTypeAmountMoID = patientTypeAmount.getTermId().replace(":", "");
        String ammountCol = AggregatedCaseView.CASEDISEASEMANIFESTATION + "__" + patientTypeMoID
            + patientTypeAmountMoID;

        if (valueQuery.hasSelectableRef(ammountCol))
        {
          needsJoin = true;
          patientTypeSql = "SELECT " + amount + "\n";
          patientTypeSql += " FROM " + patientTypeAmountTable + " pta JOIN " + patientTypeTable
              + " pt ON pta." + parent_id + "  = pt." + id + "\n ";
          patientTypeSql += " WHERE " + child_id + " = '" + patientTypeAmount.getId() + "'  AND pt."
              + term + " = '" + patientType.getId() + "'";
          patientTypeSql += " AND " + aggregatedCaseQuery.getTableAlias() + ".id = pt." + aggCase
              + "\n ";

          QueryUtil.setSelectabeSQL(valueQuery, ammountCol, patientTypeSql);
        }
      }

    }

    if (needsJoin)
    {
      valueQuery.AND(aggregatedCaseQuery.getId().EQ(aggregatedCaseQuery.getId()));
    }
  }
}
