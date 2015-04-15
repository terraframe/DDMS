package dss.vector.solutions.querybuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.F;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SUM;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSQLFloat;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.SelectableSingle;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.Physician;
import dss.vector.solutions.PhysicianQuery;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.ThresholdCalculationTypeView;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.intervention.monitor.DiagnosisType;
import dss.vector.solutions.intervention.monitor.IndividualCase;
import dss.vector.solutions.intervention.monitor.IndividualCaseQuery;
import dss.vector.solutions.intervention.monitor.IndividualInstance;
import dss.vector.solutions.intervention.monitor.IndividualInstanceQuery;
import dss.vector.solutions.query.IncidenceProbableSourceException;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

public class IndividualCaseQB extends AbstractQB implements Reloadable
{
  private static String[] requiresGeoColumn = new String[]{QueryConstants.POPULATION, QueryConstants.THRESHOLD_IDENTIFICATION, QueryConstants.THRESHOLD_NOTIFICATION,
                                                           "incidence_100", "incidence_1000", "incidence_10000", "incidence_100000", "incidence_1000000"};
  
  private String mostChildishGeoType = null;
  
  private Selectable geoIdSelectable = null;
  
  private static final String dateGroupCol = "dategroup_epiweek";
  
  /**
   * Are these columns included in the query?
   */
  private boolean thresholdIdent = false;
  private boolean thresholdNot = false;
  
  public IndividualCaseQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageSize, pageSize, disease);
  }
  
  @Override
  protected String getAuditClassAlias()
  {
    return IndividualCase.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    if (valueQuery.hasSelectableRef(QueryConstants.THRESHOLD_NOTIFICATION)) { thresholdNot = true; }
    if (valueQuery.hasSelectableRef(QueryConstants.THRESHOLD_IDENTIFICATION)) { thresholdIdent = true; }
    
    // geoColumn will be null if the query does not contain any selectables in the 'requiresGeoColumn' array.
    geoIdSelectable = getGeoColumn(valueQuery, queryConfig);
    String geoIdColumn = null;
    if (geoIdSelectable != null) {
      geoIdColumn = geoIdSelectable.getDbColumnName();
    }
    
    IndividualCaseQuery caseQuery = (IndividualCaseQuery) queryMap.get(IndividualCase.CLASS);
    IndividualInstanceQuery instanceQuery = (IndividualInstanceQuery) queryMap.get(IndividualInstance.CLASS);
    dss.vector.solutions.PersonQuery personQuery = (dss.vector.solutions.PersonQuery) queryMap.get(dss.vector.solutions.Person.CLASS);
    PhysicianQuery physicianQuery = (PhysicianQuery) queryMap.get(Physician.CLASS);

    if (physicianQuery != null)
    {
      // valueQuery.WHERE(instanceQuery.getPhysician().LEFT_JOIN_EQ(physicianQuery));
      valueQuery.WHERE(instanceQuery.getPhysician().EQ(physicianQuery));
    }

    valueQuery.WHERE(personQuery.getPatientDelegate().EQ(caseQuery.getPatient()));

    valueQuery.WHERE(instanceQuery.getIndividualCase().EQ(caseQuery.getId()));

    this.addGeoDisplayLabelQuery(caseQuery);
    this.addGeoDisplayLabelQuery(personQuery);

    String idCol = QueryUtil.getIdColumn();
    QueryUtil.leftJoinTermDisplayLabels(valueQuery, instanceQuery, instanceQuery.getTableAlias() + "." + idCol);
    QueryUtil.leftJoinTermDisplayLabels(valueQuery, caseQuery, caseQuery.getTableAlias() + "." + idCol);


    QueryUtil.joinEnumerationDisplayLabels(valueQuery, IndividualInstance.CLASS, instanceQuery);

    if (valueQuery.hasSelectableRef("healthFacility"))
    {
      SelectableSQLCharacter hf = (SelectableSQLCharacter) valueQuery.getSelectableRef("healthFacility");
      QueryUtil.subselectGeoDisplayLabels(hf, IndividualInstance.CLASS, IndividualInstance.HEALTHFACILITY, instanceQuery.getTableAlias() + "." + idCol);
    }

    QueryUtil.joinTermAllpaths(valueQuery, dss.vector.solutions.Person.CLASS, personQuery, this.getTermRestrictions());

    if (valueQuery.hasSelectableRef("instances"))
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("instances");
      String sql = "COUNT(*)";
      calc.setSQL(sql);
    }

    // Map that stores the aliases of an inner valuequery used to detect if a
    // case
    // is clinical, positive, or negative. This map also acts like a flag that
    // if size is zero then the inner valuequery has not been added to the
    // primary value query.
    Map<String, String> diagnosisAliases = new HashMap<String, String>();
    

    if (valueQuery.hasSelectableRef("cases"))
    {
      String adjustedCases = getTotalCasesSQL(caseQuery, valueQuery, diagnosisAliases);
      SelectableSQLFloat calc = (SelectableSQLFloat) valueQuery.getSelectableRef("cases");
      // String sql = "SUM(1/(SELECT COUNT(*) FROM " + tableName +
      // " AS ii WHERE ii." + indCaseCol + " = " + tableAlias + ".id))";
      calc.setSQL(adjustedCases);
    }

    if (valueQuery.hasSelectableRef("deaths"))
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("deaths");
      String diedInFacCol = QueryUtil.getColumnName(instanceQuery.getMdClassIF(), IndividualInstance.DIEDINFACILITY);
      String sql = "SUM(" + diedInFacCol + ")";
      calc.setSQL(sql);
    }

    if (valueQuery.hasSelectableRef("cfr"))
    {
      String adjustedCases = getTotalCasesSQL(caseQuery, valueQuery, diagnosisAliases);
      // String indCaseCol =
      // QueryUtil.getColumnName(instanceQuery.getMdClassIF(),
      // IndividualInstance.INDIVIDUALCASE);
      String diedInFacCol = QueryUtil.getColumnName(instanceQuery.getMdClassIF(), IndividualInstance.DIEDINFACILITY);
      SelectableSQLFloat calc = (SelectableSQLFloat) valueQuery.getSelectableRef("cfr");
      // String tableName =
      // MdBusiness.getMdBusiness(IndividualInstance.CLASS).getTableName();
      // String sql = "(SUM(" + diedInFacCol + ")/SUM(1/(SELECT COUNT(*) FROM "
      // + tableName + " AS ii WHERE ii." + indCaseCol + " = " + tableAlias +
      // "." + idCol + ")))*100.0";
      String deathSum = this.sumColumnForId(instanceQuery.getTableAlias(), idCol, null, diedInFacCol);
      String sql = "(" + deathSum + "::float/NULLIF((" + adjustedCases + "),0))*100.0";
      calc.setSQL(sql);
    }
    
    calculateIncidence(valueQuery, geoIdColumn, caseQuery, instanceQuery, xml, 100, diagnosisAliases);
    calculateIncidence(valueQuery, geoIdColumn, caseQuery, instanceQuery, xml, 1000, diagnosisAliases);
    calculateIncidence(valueQuery, geoIdColumn, caseQuery, instanceQuery, xml, 10000, diagnosisAliases);
    calculateIncidence(valueQuery, geoIdColumn, caseQuery, instanceQuery, xml, 100000, diagnosisAliases);
    calculateIncidence(valueQuery, geoIdColumn, caseQuery, instanceQuery, xml, 1000000, diagnosisAliases);

    QueryUtil.getSingleAttribteGridSql(valueQuery, instanceQuery.getTableAlias());

    this.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, caseQuery.getDisease());

    valueQuery.AND(caseQuery.getDisease().EQ(this.getDisease()));
    
//    injectPopulationSQL(valueQuery, caseQuery, instanceQuery, queryConfig, xml);
    if (valueQuery.hasSelectableRef(QueryConstants.POPULATION)) {
      SelectableSQLFloat popSel = (SelectableSQLFloat) valueQuery.getSelectableRef(QueryConstants.POPULATION);
      String popSQL = getPopulationSQL(valueQuery, geoIdColumn, caseQuery, instanceQuery, xml);
      popSel.setSQL(popSQL);
    }
    
    // Add a group by
    if (!(valueQuery.hasSelectableRef(QueryConstants.THRESHOLD_NOTIFICATION) || valueQuery.hasSelectableRef(QueryConstants.THRESHOLD_IDENTIFICATION))) {
      List<Selectable> refs = new ArrayList<Selectable>(valueQuery.getSelectableRefs());
      Iterator<Selectable> it = refs.iterator();
      while (it.hasNext())
      {
        Selectable next = it.next();
        if (next.isAggregateFunction())
        {
          it.remove();
        }
      }
      valueQuery.GROUP_BY(refs.toArray(new SelectableSingle[refs.size()]));
    }
    
    return valueQuery;
  }
  
  private Selectable getGeoColumn(ValueQuery vq, JSONObject queryConfig) {
    // Do we have any selectables that require a geo column?
    boolean needsGeoColumn = false;
    for (String geoSel : requiresGeoColumn) {
      if (vq.hasSelectableRef(geoSel)) {
        needsGeoColumn = true;
        break;
      }
    }
    if (!needsGeoColumn) { return null; }
    
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
        if (universals.length() > 0 && attributeKey.equals(IndividualCase.CLASS + '.' + IndividualCase.PROBABLESOURCE))
        {
          selectedUniversals = new String[universals.length()];
          for (int i = 0; i < universals.length(); i++)
          {
            selectedUniversals[i] = universals.getString(i);
          }
          // dss_vector_solutions_intervention_monitor_IndividualCase_probableSource__district_geoId
          geoType = GeoHierarchy.getMostChildishUniversialType(selectedUniversals);
          mostChildishGeoType = geoType;
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
    
    Selectable s;
    try
    {
      s = vq.getSelectableRef(geoType);
    }
    catch (QueryException e)
    {
      throw new IncidenceProbableSourceException(e);
    }

    return s;
  }
  
  private void setThresholdSQL(ValueQuery vq) {
    SelectableSQLFloat threshSel;
    if (vq.hasSelectableRef(QueryConstants.THRESHOLD_NOTIFICATION)) {
      threshSel = (SelectableSQLFloat) vq.getSelectableRef(QueryConstants.THRESHOLD_NOTIFICATION);
      threshSel.setSQL("threshold_notification");
    }
    if (vq.hasSelectableRef(QueryConstants.THRESHOLD_IDENTIFICATION)) {
      threshSel = (SelectableSQLFloat) vq.getSelectableRef(QueryConstants.THRESHOLD_IDENTIFICATION);
      threshSel.setSQL("threshold_identification");
    }
  }
  
  /**
   * Because the calculated population field can be used in both the 'population' and also instance calculations we're adding it to the FROM clause
   * where it can be referenced by both. This is a performance enhancement that enables us to only calculate it once and reference it twice.
   */
//  private void injectPopulationSQL(ValueQuery vq, IndividualCaseQuery caseQuery, IndividualInstanceQuery instanceQuery, JSONObject queryConfig, String xml) {
//    if (!(vq.hasSelectableRef(QueryConstants.POPULATION) || vq.hasSelectableRef("incidence_100")  || vq.hasSelectableRef("incidence_1000")
//        || vq.hasSelectableRef("incidence_10000")  || vq.hasSelectableRef("incidence_100000") || vq.hasSelectableRef("incidence_1000000")))
//    {
//      return;
//    }
//    
//    String popSQL = getPopulationSQL(vq, caseQuery, instanceQuery, queryConfig, xml);
//    
//    String FROM = "(SELECT " + popSQL + " AS population FROM ??)";
//    
//    vq.FROM(FROM, "popCalc");
//    
//    if (valueQ.hasSelectableRef(QueryConstants.POPULATION)) {
//      SelectableSQLFloat popSel = (SelectableSQLFloat) valueQ.getSelectableRef(QueryConstants.POPULATION);
//      popSel.setSQL(popSQL);
//    }
//  }
  
  private String getPopulationSQL(ValueQuery valueQ, String geoColumn, IndividualCaseQuery caseQuery, IndividualInstanceQuery instanceQuery, String xml)
  {
    String timePeriod = "yearly";

    if (xml.indexOf("season") > 0)
    {
      timePeriod = "seasonal";
    }
    
    String diagnosisDate = QueryUtil.getColumnName(caseQuery.getMdClassIF(), IndividualCase.DIAGNOSISDATE);
    String symptomOnsetDate = QueryUtil.getColumnName(caseQuery.getMdClassIF(), IndividualCase.SYMPTOMONSET);
    
    // If the Symptom Onset Date is null, we use the Diagnosis Date instead (which is a required field).
    String coalesceDate = "COALESCE(" + symptomOnsetDate + ", " + diagnosisDate + ")";

    String sql = "get_" + timePeriod + "_population_by_geoid_and_date(" + geoColumn + ", " + coalesceDate + ")";
    
    return sql;
  }
  
  private void calculateIncidence(ValueQuery valueQuery, String geoColumn, IndividualCaseQuery caseQuery, IndividualInstanceQuery instanceQuery, String xml, Integer multiplier, Map<String, String> diagnosisAliases)
  {
    if (!valueQuery.hasSelectableRef("incidence_" + multiplier))
    {
      return;
    }
    
    SelectableSQLFloat calc = (SelectableSQLFloat) valueQuery.getSelectableRef("incidence_" + multiplier);

    String timePeriod = "yearly";

    if (xml.indexOf("season") > 0)
    {
      timePeriod = "seasonal";
    }

    String diagnosisDate = QueryUtil.getColumnName(caseQuery.getMdClassIF(), IndividualCase.DIAGNOSISDATE);
    String symptomOnsetDate = QueryUtil.getColumnName(caseQuery.getMdClassIF(), IndividualCase.SYMPTOMONSET);
    
    // If the Symptom Onset Date is null, we use the Diagnosis Date instead (which is a required field).
    String coalesceDate = "COALESCE(" + symptomOnsetDate + ", " + diagnosisDate + ")";

    String sql = "(" + getTotalCasesSQL(caseQuery, valueQuery, diagnosisAliases) + ")";
    sql += "/NULLIF(AVG(get_" + timePeriod + "_population_by_geoid_and_date(" + geoColumn + ", " + coalesceDate + ")),0.0)*" + multiplier;

    calc.setSQL(sql);
  }

  private String getTotalCasesSQL(IndividualCaseQuery caseQuery, ValueQuery valueQuery, Map<String, String> diagnosisAliases)
  {
    String posCases = "positiveCases";
    String negCases = "negativeCases";
    String clinCases = "clinicalCases";
    String cachedPercentPositive = "percentPositive";

    if (diagnosisAliases.size() == 0)
    {
      QueryFactory factory = caseQuery.getQueryFactory();
      IndividualInstanceQuery iQuery = new IndividualInstanceQuery(factory);

      ValueQuery innerQuery = new ValueQuery(factory);
      SUM positiveColumn = F.SUM(innerQuery.aSQLLong("positive", "(case when " + iQuery.getDiagnosisType().getDbColumnName() + "_c = '" + DiagnosisType.POSITIVE_DIAGNOSIS.getId() + "' then 1 else 0 end)"), "positive");
      SUM negativeColumn = F.SUM(innerQuery.aSQLLong("negative", "(case when " + iQuery.getDiagnosisType().getDbColumnName() + "_c = '" + DiagnosisType.NEGATIVE_DIAGNOSIS.getId() + "' then 1 else 0 end)"), "negative");
      SUM clinicalColumn = F.SUM(innerQuery.aSQLLong("clinical", "(case when " + iQuery.getDiagnosisType().getDbColumnName() + "_c = '" + DiagnosisType.CLINICAL_DIAGNOSIS.getId() + "' then 1 else 0 end)"), "clinical");
      innerQuery.SELECT(iQuery.getIndividualCase("ic_id"));
      innerQuery.SELECT(positiveColumn);
      innerQuery.SELECT(negativeColumn);
      innerQuery.SELECT(clinicalColumn);
      innerQuery.WHERE(iQuery.getIndividualCase().getDisease().EQ(this.getDisease()));

      ValueQuery vQuery = new ValueQuery(factory);
      vQuery.SELECT(F.SUM(vQuery.aSQLLong("positive", "(case when " + positiveColumn.getColumnAlias() + " > 0 then 1 else 0 end)"), posCases));
      vQuery.SELECT(F.SUM(vQuery.aSQLLong("negative", "(case when " + positiveColumn.getColumnAlias() + " = 0 and " + negativeColumn.getColumnAlias() + " > 0 then 1 else 0 end)"), negCases));
      vQuery.SELECT(F.SUM(vQuery.aSQLLong("clinical", "(case when " + positiveColumn.getColumnAlias() + " = 0 and " + negativeColumn.getColumnAlias() + " = 0 and " + clinicalColumn.getColumnAlias() + " > 0 then 1 else 0 end)"), clinCases));
      vQuery.SELECT(vQuery.aSQLCharacter("ic", innerQuery.getSelectableRef("ic_id").getColumnAlias()));
      vQuery.FROM("(" + innerQuery.getSQL() + ")", "innerQuery");

      valueQuery.FROM("(" + vQuery.getSQL() + ")", "diagnosisCheck");
      
      valueQuery.WHERE(vQuery.aSQLCharacter("ic", "diagnosisCheck.ic").EQ(caseQuery.getId()));

      diagnosisAliases.put(posCases, vQuery.getSelectableRef(posCases).getColumnAlias());
      diagnosisAliases.put(negCases, vQuery.getSelectableRef(negCases).getColumnAlias());
      diagnosisAliases.put(clinCases, vQuery.getSelectableRef(clinCases).getColumnAlias());

      // Safe operation: the value is required and already validated.
      String percentPositive = ThresholdCalculationTypeView.getCalculationThreshold().getClinicalPositivePercentage().toString();
      diagnosisAliases.put(cachedPercentPositive, percentPositive);
    }

    String idCol = QueryUtil.getIdColumn();
    String caseAlias = caseQuery.getTableAlias();
    String posSum = this.sumColumnForId(caseAlias, idCol, null, diagnosisAliases.get(posCases));
    String negSum = this.sumColumnForId(caseAlias, idCol, null, diagnosisAliases.get(negCases));
    String clinSum = this.sumColumnForId(caseAlias, idCol, null, diagnosisAliases.get(clinCases));
    String percentPositive = diagnosisAliases.get(cachedPercentPositive);

    // adjusted case count
    String sql = "CASE WHEN (" + posSum + ") = 0 THEN (" + posSum + " + (" + clinSum + " * " + percentPositive + "/100.00)) ELSE ";
    sql += "( \n";
    sql += "    (" + posSum + ") + \n";
    sql += "    ( \n";
    sql += "     (" + clinSum + ") * \n";
    sql += "     ( \n";
    sql += "      (" + posSum + ") / \n";
    sql += "      ( \n";
    sql += "       NULLIF \n";
    sql += "       ( \n";
    sql += "        (" + posSum + ") + \n";
    sql += "        (" + negSum + ") \n";
    sql += "        ,0.0 \n";
    sql += "       ) \n";
    sql += "      ) \n";
    sql += "     ) \n";
    sql += "    )  \n";
    sql += "  ) \n";
    sql += " END";
    return sql;
  }
  
  /**
   * We need to JOIN the thresholds view with the original query, which means we have to wrap the original query in a "final" query, just like in IRS.
   */
  @Override
  protected ValueQuery postProcess(ValueQuery originalVQ)
  {
    if (!(originalVQ.hasSelectableRef(QueryConstants.THRESHOLD_NOTIFICATION) || originalVQ.hasSelectableRef(QueryConstants.THRESHOLD_IDENTIFICATION))) {
      return originalVQ;
    }
    
    List<Selectable> extraGroupBys = new ArrayList<Selectable>();
    
    String ovq = "original_vq";
    
    ValueQuery finalVQ = new ValueQuery(originalVQ.getQueryFactory());
    
    // Remove the Threshold selectable from the originalVQ, as it doesn't exist on that side
    List<Selectable> origSels = originalVQ.getSelectableRefs();
    originalVQ.removeSelectable(QueryConstants.THRESHOLD_IDENTIFICATION);
    originalVQ.removeSelectable(QueryConstants.THRESHOLD_NOTIFICATION);
    
    // Select all of the columns from the original
    Selectable[] copies = this.copyAll(originalVQ, origSels, ovq, false, null);
    finalVQ.SELECT(copies);
    
    this.setThresholdSQL(finalVQ);
    
    // Take the WITHs from the originalVQ and put them as finalVQ's WITH. Also add originalVQ as a WITH of finalVQ.
    List<WITHEntry> withs = this.getWITHEntries();
    withs.addAll(this.getWITH(finalVQ));
    originalVQ.setSqlPrefix("");
    withs.add(new WITHEntry(ovq, originalVQ.getSQL()));
    this.setWITHClause(withs, false, finalVQ);
    
    // JOIN thresholds and originalVQ in the FROM clause.
    String epi_week = finalVQ.getSelectableRef(dateGroupCol).getResultAttributeName();
    String geo_id = finalVQ.getSelectableRef(geoIdSelectable.getResultAttributeName()).getDbColumnName();
    finalVQ.FROM(ovq + " FULL OUTER JOIN summedThresholds", " ON summedThresholds.epi_week = " + ovq + "." + epi_week + " AND summedThresholds.geo_id = " + ovq + "." + geo_id);
    
    // COALESCE the selectables that are common between thresholds and originalVQ.
    List<Selectable> finalSels = finalVQ.getSelectableRefs();
    for (Selectable finalSel : finalSels) {
      String sumSel = null;
      
      if (finalSel.getResultAttributeName().contains("entityLabel")) {
        sumSel = "geo_label";
        extraGroupBys.add(originalVQ.aSQLCharacter(sumSel, "summedThresholds." + sumSel));
      }
      else if (finalSel.getResultAttributeName().contains("geoId")) {
        sumSel = "geo_id";
        extraGroupBys.add(originalVQ.aSQLCharacter(sumSel, "summedThresholds." + sumSel));
      }
      else if (finalSel.getResultAttributeName().equals(dateGroupCol)) {
        sumSel = "epi_week";
        extraGroupBys.add(originalVQ.aSQLDate(sumSel, "summedThresholds." + sumSel));
      }
      
      if (sumSel != null) {
        String ovqSel = finalSel.getDbColumnName();
        ((SelectableSQL) finalSel).setSQL("COALESCE(" + ovq + "." + ovqSel + ", summedThresholds." + sumSel + ")");
      }
    }
    
    // Add a group by
    List<Selectable> refs = new ArrayList<Selectable>(finalVQ.getSelectableRefs());
    Iterator<Selectable> it = refs.iterator();
    while (it.hasNext())
    {
      if (it.next().isAggregateFunction())
      {
        it.remove();
      }
    }
    refs.addAll(extraGroupBys);
    finalVQ.GROUP_BY(refs.toArray(new SelectableSingle[refs.size()]));
    
    return finalVQ;
  }
  
  /**
   *  If we're calculating Thresholds then we need to define some views at the top of the SQL.
   */
  private List<WITHEntry> getWITH(ValueQuery vq) {
    List<WITHEntry> entries = new ArrayList<WITHEntry>();
    
    String universalId = MdBusiness.getMdBusiness(mostChildishGeoType).getId(); // "i73lfwvitbq7u9itwhdwl94c78x268nb00000000000000000000000000000001"; // Id of District
    
    /*
     * Build DateExtrapolationView
     */
    entries.add(new WITHEntry("dateExtrapolationView",
        "SELECT \n" + 
        " year_of_week AS year_of_week, \n" + 
        " period AS period, \n" + 
        " (get_epistart(year_of_week, 0) + (to_char((period)*7, '999')||' days')::interval)::date AS planned_date \n" + 
        "FROM epi_week \n"));
    
    /*
     * Build GeoThresholdView
     */
    String selects = "";
    ArrayList<String> groupBy = new ArrayList<String>();
    if (thresholdIdent) {
      selects += "    wt.identification identification,\n";
      groupBy.add("wt.identification");
    }
    if (thresholdNot) {
      selects += "    wt.notification notification,\n";
      groupBy.add("wt.notification");
    }
    String andNotNull = "";
    if (thresholdIdent && !thresholdNot) {
      andNotNull = "    AND wt.notification IS NOT NULL\n";
    }
    else if (thresholdNot && !thresholdIdent) {
      andNotNull = "    AND wt.identification IS NOT NULL\n";
    }
    else {
      andNotNull = "    AND (wt.identification IS NOT NULL OR wt.notification IS NOT NULL)";
    }
    
    entries.add(new WITHEntry("geoThresholdView",
        "  SELECT \n" + 
        selects +
        "    wt.id id,\n" + 
        "    apg.parent_geo_entity geo_entity,\n" + 
        "    ew.period epi_week,\n" + 
        "    ew.year_of_week epi_year,\n" + 
        "    de.planned_date AS threshold_date,\n" + 
        "    td.season season,\n" + 
        "    ms.disease disease\n" + 
        "  FROM\n" + 
        "    weekly_threshold wt\n" + 
        "    INNER JOIN threshold_data td ON wt.parent_id=td.id\n" + 
        "    INNER JOIN epi_week ew ON wt.child_id=ew.id\n" + 
        "    INNER JOIN malaria_season ms ON td.season=ms.id\n" + 
        "    CROSS JOIN dateExtrapolationView de\n" + 
        "    INNER JOIN allpaths_geo apg ON apg.child_geo_entity = td.geo_entity\n" + 
        "  WHERE \n" +
        "    ew.period = de.period\n" + 
        andNotNull +
        "    AND de.planned_date BETWEEN ms.start_date AND ms.end_date\n" + 
        "    AND apg.parent_universal = '" + universalId + "'\n" + 
        "  GROUP BY wt.id, apg.parent_geo_entity, de.planned_date, " + StringUtils.join(groupBy, ",") +  ", td.season, ms.disease, ew.period, ew.year_of_week\n"));
    
    /*
     * Build RolledThresholdsView
     */
    selects = "";
    if (thresholdIdent) {
      selects += "  get_threshold_by_geoid_and_epiweek('identification', '" + universalId + "', ValueQuery_2.parentGeoEntity, epi_week, disease, season) identification,\n";
    }
    if (thresholdNot) {
      selects += "  get_threshold_by_geoid_and_epiweek('notification', '" + universalId + "', ValueQuery_2.parentGeoEntity, epi_week, disease, season) notification,\n";
    }
    entries.add(new WITHEntry("rolledThresholds",
        "SELECT \n" + 
        selects + 
        "  epi_week,\n" + 
        "  geo_entity,\n" + 
        "  geo_id\n" + 
        "FROM \n" + 
        "  geoThresholdView,\n" + 
        "  geo_entity geo_entity_22 LEFT JOIN\n" + 
        "  (SELECT \n" + 
        "       geo_entity_3.geo_id AS geo_id_10,\n" + 
        "       allpaths_geo_11.child_geo_entity AS child_geo_entity_13,\n" + 
        "       (geo_entity_3.id) AS parentGeoEntity\n" + 
        "  FROM geo_entity geo_entity_3,\n" + 
        "       allpaths_geo allpaths_geo_11 \n" + 
        "  WHERE ((allpaths_geo_11.parent_universal = '" + universalId + "'\n" + 
        "  AND allpaths_geo_11.parent_geo_entity = geo_entity_3.id))\n" + 
        "  ) ValueQuery_2 ON geo_entity_22.id = ValueQuery_2.child_geo_entity_13\n" + 
        "WHERE\n" + 
        "  (geoThresholdView.geo_entity = geo_entity_22.id\n" + 
        "  AND (EXISTS (SELECT \n" + 
        "       (1) AS geoExistsConstant\n" + 
        "  FROM allpaths_geo allpaths_geo_28 \n" + 
        "  WHERE allpaths_geo_28.child_geo_entity = (geo_entity_22.id)\n" + 
        "  ) AND true) = (true))\n" + 
        "  GROUP BY ValueQuery_2.parentGeoEntity, geo_entity, geo_id, epi_week, disease, season\n"));
    
    /*
     * Build SummedThresholdsView
     */
    selects = "";
    if (thresholdIdent) {
      selects += "  ((SUM(identification) OVER(PARTITION BY rolledThresholds.epi_week, rolledThresholds.geo_id, rolledThresholds.geo_entity))) AS threshold_identification,\n";
    }
    if (thresholdNot) {
      selects += "  ((SUM(notification) OVER(PARTITION BY rolledThresholds.epi_week, rolledThresholds.geo_id, rolledThresholds.geo_entity))) AS threshold_notification,\n";
    }
    entries.add(new WITHEntry("summedThresholds", "SELECT DISTINCT\n" + 
        selects +
        "  rolledThresholds.epi_week + 1 AS epi_week, -- User wants epi_week displayed from 1 up, but its stored from 0 up in the DB\n" + 
        "  rolledThresholds.geo_id,\n" + 
        "  rolledThresholds.geo_entity,\n" + 
        "  geo_displayLabel.label AS geo_label\n" +
        "FROM rolledThresholds INNER JOIN geo_displayLabel ON geo_displayLabel.geo_id = rolledThresholds.geo_id"));
    
    return entries;
  }
}
