package dss.vector.solutions.querybuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.GeneratedTableClassQuery;
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQLFloat;
import com.runwaysdk.query.SelectableSingle;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.ThresholdCalculationTypeView;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.query.IncidencePopulationException;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.surveillance.AggregatedCase;
import dss.vector.solutions.surveillance.AggregatedCaseQuery;
import dss.vector.solutions.surveillance.AggregatedCaseView;
import dss.vector.solutions.surveillance.CaseDiagnosisType;
import dss.vector.solutions.surveillance.CaseDiagnosisTypeAmount;
import dss.vector.solutions.surveillance.CaseDiagnosisTypeView;
import dss.vector.solutions.surveillance.CaseDiagnostic;
import dss.vector.solutions.surveillance.CaseDiseaseManifestation;
import dss.vector.solutions.surveillance.CaseDiseaseManifestationAmount;
import dss.vector.solutions.surveillance.CaseDiseaseManifestationView;
import dss.vector.solutions.surveillance.CasePatientType;
import dss.vector.solutions.surveillance.CasePatientTypeAmount;
import dss.vector.solutions.surveillance.CasePatientTypeView;
import dss.vector.solutions.surveillance.CaseReferral;
import dss.vector.solutions.surveillance.CaseStockReferral;
import dss.vector.solutions.surveillance.CaseTreatment;
import dss.vector.solutions.surveillance.CaseTreatmentStock;
import dss.vector.solutions.util.QueryUtil;

public class AggregatedCaseQB extends AbstractQB implements Reloadable
{

  public AggregatedCaseQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageSize, pageSize, disease);
  }

  @Override
  protected String getAuditClassAlias()
  {
    return AggregatedCase.CLASS;
  }
  
  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedTableClassQuery> queryMap, String xml, JSONObject config)
  {
    AggregatedCaseQuery aggregatedCaseQuery = (AggregatedCaseQuery) queryMap.get(AggregatedCase.CLASS);
    valueQuery.FROM(aggregatedCaseQuery.getMdClassIF().getTableName(), aggregatedCaseQuery.getTableAlias());

    QueryUtil.getSingleAttribteGridSql(valueQuery, aggregatedCaseQuery.getTableAlias(), QueryUtil.getColumnName(CaseTreatment.getAggregatedCaseMd()), QueryUtil.getColumnName(CaseTreatment.getTermMd()));

    QueryUtil.getSingleAttribteGridSql(valueQuery, aggregatedCaseQuery.getTableAlias(), QueryUtil.getColumnName(CaseTreatmentStock.getAggregatedCaseMd()), QueryUtil.getColumnName(CaseTreatmentStock.getTermMd()));

    QueryUtil.getSingleAttribteGridSql(valueQuery, aggregatedCaseQuery.getTableAlias(), QueryUtil.getColumnName(CaseReferral.getAggregatedCaseMd()), QueryUtil.getColumnName(CaseReferral.getTermMd()));

    QueryUtil.getSingleAttribteGridSql(valueQuery, aggregatedCaseQuery.getTableAlias(), QueryUtil.getColumnName(CaseStockReferral.getAggregatedCaseMd()), QueryUtil.getColumnName(CaseStockReferral.getTermMd()));

    QueryUtil.getSingleAttribteGridSql(valueQuery, aggregatedCaseQuery.getTableAlias(), QueryUtil.getColumnName(CaseDiagnostic.getAggregatedCaseMd()), QueryUtil.getColumnName(CaseDiagnostic.getTermMd()));

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
      String sql = "(SUM(" + deathsCol + "::FLOAT)/NULLIF((" + casesSQL + "),0))*100.0";
      calc.setSQL(sql);
    }

    calculatePopulation(valueQuery, aggregatedCaseQuery, config, xml);

    calculateIncidence(valueQuery, aggregatedCaseQuery, config, xml, 100);
    calculateIncidence(valueQuery, aggregatedCaseQuery, config, xml, 1000);
    calculateIncidence(valueQuery, aggregatedCaseQuery, config, xml, 10000);
    calculateIncidence(valueQuery, aggregatedCaseQuery, config, xml, 100000);
    calculateIncidence(valueQuery, aggregatedCaseQuery, config, xml, 1000000);

    joinPatientTypes(valueQuery, aggregatedCaseQuery);
    joinDiagnosisTypes(valueQuery, aggregatedCaseQuery);
    joinDiseaseManifestations(valueQuery, aggregatedCaseQuery);

    this.addGeoDisplayLabelQuery(aggregatedCaseQuery);
    QueryUtil.setQueryDates(xml, valueQuery, aggregatedCaseQuery, aggregatedCaseQuery.getStartDate(), aggregatedCaseQuery.getEndDate(), aggregatedCaseQuery.getDisease());

    valueQuery.WHERE(aggregatedCaseQuery.getDisease().EQ(this.getDisease()));

    this.setNumericRestrictions(valueQuery, config);

    // Add a group by
    if (valueQuery.hasSelectableRef(QueryConstants.POPULATION))
    {
      List<Selectable> refs = new ArrayList<Selectable>(valueQuery.getSelectableRefs());
      Iterator<Selectable> it = refs.iterator();
      while (it.hasNext())
      {
        if (it.next().isAggregateFunction())
        {
          it.remove();
        }
      }
      valueQuery.GROUP_BY(refs.toArray(new SelectableSingle[refs.size()]));
    }
    
    return valueQuery;
  }

  private void calculatePopulation(ValueQuery valueQuery, AggregatedCaseQuery caseQuery, JSONObject queryConfig, String xml)
  {
    if (!valueQuery.hasSelectableRef(QueryConstants.POPULATION))
    {
      return;
    }

    SelectableSQLFloat popSel = (SelectableSQLFloat) valueQuery.getSelectableRef(QueryConstants.POPULATION);

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

    String geoColumn = s.getDbQualifiedName();

    String startDateCol = QueryUtil.getColumnName(caseQuery.getMdClassIF(), AggregatedCase.STARTDATE);

    String sql = "get_" + timePeriod + "_population_by_geoid_and_date(" + geoColumn + ", " + startDateCol + ")";

    popSel.setSQL(sql);
  }

  private void calculateIncidence(ValueQuery valueQuery, AggregatedCaseQuery caseQuery, JSONObject queryConfig, String xml, Integer multiplier)
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

    String startDateCol = QueryUtil.getColumnName(caseQuery.getMdClassIF(), AggregatedCase.STARTDATE);

    String sql = "(" + getTotalCasesSQL(caseQuery) + ")";
    String columnAlias = s.getDbQualifiedName();
    sql += "/";
    sql += " NULLIF(AVG(get_" + timePeriod + "_population_by_geoid_and_date(" + columnAlias + ", " + startDateCol + ")),0.0)*" + multiplier;

    calc.setSQL(sql);

    // OLD calc
    // String sql = "(SUM(" + casesCol + "::FLOAT)/";

    // Total Cases = Sum(Cases with confirmed positive diagnosis) +
    // (Sum(Cases with clinical diagnosis) *
    // (Sum(Cases with confirmed positive diagnosis) /
    // (Sum(Cases with confirmed positive diagnosis) + Sum(Cases with confirmed
    // negative diagnosis))))
    // calc.setSQL(sql);

  }

  private String getTotalCasesSQL(AggregatedCaseQuery caseQuery)
  {
    String casesCol = QueryUtil.getColumnName(caseQuery.getMdClassIF(), AggregatedCase.CASES);
    String posCasesCol = QueryUtil.getColumnName(caseQuery.getMdClassIF(), AggregatedCase.POSITIVECASES);
    String negCasesCol = QueryUtil.getColumnName(caseQuery.getMdClassIF(), AggregatedCase.NEGATIVECASES);

    String caseAlias = caseQuery.getTableAlias();
    String idCol = QueryUtil.getIdColumn();
    String casesSum = this.sumColumnForId(caseAlias, idCol, null, casesCol);
    String posSum = this.sumColumnForId(caseAlias, idCol, null, posCasesCol);
    String negSum = this.sumColumnForId(caseAlias, idCol, null, negCasesCol);

    // TODO cache this per request
    String percentPositive = ThresholdCalculationTypeView.getCalculationThreshold().getClinicalPositivePercentage().toString();

    String sql = "CASE WHEN COALESCE(" + posSum + ") = 0 THEN (COALESCE(" + posSum + ",0.0) + (COALESCE(" + casesSum + ",0.0) * " + percentPositive + "/100.00)) ELSE ";
    sql += "COALESCE(" + posSum + ",0.0)::float +  \n";
    sql += "( \n";
    sql += " COALESCE(" + casesSum + ",0.0)::float * \n";
    sql += " ( \n";
    sql += "   COALESCE(" + posSum + ",0.0)::float / \n";
    sql += "  ( \n";
    sql += "   NULLIF \n";
    sql += "   ( \n";
    sql += "     COALESCE(" + posSum + ",0.0)::float + COALESCE(" + negSum + ",0.0)::float \n";
    sql += "     ,0.0 \n";
    sql += "   ) \n";
    sql += "  ) \n";
    sql += " ) \n";
    sql += ") \n";
    sql += "END \n";

    return sql;
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
    for (Term patientType : TermRootCache.getRoots(AggregatedCaseView.getCaseDiagnosisTypeMd()))
    {
      // String patientTypeMoID = patientType.getTermId().replace(":", "");
      String patientTypeMoID = QueryUtil.aliasTerms(patientType);

      for (Term patientTypeAmount : TermRootCache.getRoots(CaseDiagnosisTypeView.getDiagnosisCategoryMd()))
      {
        // String patientTypeAmountMoID =
        // patientTypeAmount.getTermId().replace(":", "");
        String patientTypeAmountMoID = QueryUtil.aliasTerms(patientTypeAmount);
        String ammountCol = AggregatedCaseView.CASEDIAGNOSISTYPE + "__" + patientTypeMoID + patientTypeAmountMoID;

        if (valueQuery.hasSelectableRef(ammountCol))
        {
          needsJoin = true;
          patientTypeSql = "SELECT " + amount + "\n";
          patientTypeSql += " FROM " + diagTypeAmountTable + " pta JOIN " + diagTypeTable + " pt ON pta." + parent_id + "  = pt." + id + "\n ";
          patientTypeSql += " WHERE " + child_id + " = '" + patientTypeAmount.getId() + "'  AND pt." + term + " = '" + patientType.getId() + "'";
          patientTypeSql += " AND " + aggregatedCaseQuery.getTableAlias() + ".id = pt." + aggCase + "\n ";

          QueryUtil.setSelectabeSQL(valueQuery, ammountCol, patientTypeSql);
        }
      }

    }

    if (needsJoin)
    {
      valueQuery.AND(aggregatedCaseQuery.getId().EQ(aggregatedCaseQuery.getId()));
    }
  }

  private void joinDiseaseManifestations(ValueQuery valueQuery, AggregatedCaseQuery aggregatedCaseQuery)
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
    String patientTypeAmountTable = MdBusiness.getMdEntity(CaseDiseaseManifestationAmount.CLASS).getTableName();

    boolean needsJoin = false;

    String patientTypeSql = "SELECT ac." + id + " as aggcase\n";
    for (Term patientType : TermRootCache.getRoots(AggregatedCaseView.getCaseDiseaseManifestationMd()))
    {
      // String patientTypeMoID = patientType.getTermId().replace(":", "");
      String patientTypeMoID = QueryUtil.aliasTerms(patientType);

      for (Term patientTypeAmount : TermRootCache.getRoots(CaseDiseaseManifestationView.getDiseaseCategoryMd()))
      {
        // String patientTypeAmountMoID =
        // patientTypeAmount.getTermId().replace(":", "");
        String patientTypeAmountMoID = QueryUtil.aliasTerms(patientTypeAmount);
        String ammountCol = AggregatedCaseView.CASEDISEASEMANIFESTATION + "__" + patientTypeMoID + patientTypeAmountMoID;

        if (valueQuery.hasSelectableRef(ammountCol))
        {
          needsJoin = true;
          patientTypeSql = "SELECT " + amount + "\n";
          patientTypeSql += " FROM " + patientTypeAmountTable + " pta JOIN " + patientTypeTable + " pt ON pta." + parent_id + "  = pt." + id + "\n ";
          patientTypeSql += " WHERE " + child_id + " = '" + patientTypeAmount.getId() + "'  AND pt." + term + " = '" + patientType.getId() + "'";
          patientTypeSql += " AND " + aggregatedCaseQuery.getTableAlias() + ".id = pt." + aggCase + "\n ";

          QueryUtil.setSelectabeSQL(valueQuery, ammountCol, patientTypeSql);
        }
      }

    }

    if (needsJoin)
    {
      valueQuery.AND(aggregatedCaseQuery.getId().EQ(aggregatedCaseQuery.getId()));
    }
  }

  private void joinPatientTypes(ValueQuery valueQuery, AggregatedCaseQuery aggregatedCaseQuery)
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
    for (Term patientType : TermRootCache.getRoots(AggregatedCaseView.getCasePatientTypeMd()))
    {
      // String patientTypeMoID = patientType.getTermId().replace(":", "");
      String patientTypeMoID = QueryUtil.aliasTerms(patientType);

      for (Term patientTypeAmount : TermRootCache.getRoots(CasePatientTypeView.getPatientCategoryMd()))
      {
        // String patientTypeAmountMoID =
        // patientTypeAmount.getTermId().replace(":", "");
        String patientTypeAmountMoID = QueryUtil.aliasTerms(patientTypeAmount);
        String ammountCol = AggregatedCaseView.CASEPATIENTTYPE + "__" + patientTypeMoID + patientTypeAmountMoID;

        if (valueQuery.hasSelectableRef(ammountCol))
        {
          needsJoin = true;
          patientTypeSql = "SELECT " + amount + "\n";
          patientTypeSql += " FROM " + patientTypeAmountTable + " pta JOIN " + patientTypeTable + " pt ON pta." + parent_id + "  = pt." + id + "\n ";
          patientTypeSql += " WHERE " + child_id + " = '" + patientTypeAmount.getId() + "'  AND pt." + term + " = '" + patientType.getId() + "'";
          patientTypeSql += " AND " + aggregatedCaseQuery.getTableAlias() + ".id = pt." + aggCase + "\n ";

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
