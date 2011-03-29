package dss.vector.solutions.querybuilder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.F;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SUM;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSQLFloat;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;

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

  public IndividualCaseQB(String xml, String config, Layer layer)
  {
    super(xml, config, layer);
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
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

    QueryUtil.joinTermAllpaths(valueQuery, dss.vector.solutions.Person.CLASS, personQuery);

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
    ;

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
      String deathSum = QueryUtil.sumColumnForId(instanceQuery.getTableAlias(), idCol, null, diedInFacCol);
      String sql = "(" + deathSum + "::float/NULLIF((" + adjustedCases + "),0))*100.0";
      calc.setSQL(sql);
    }

    calculateIncidence(valueQuery, caseQuery, instanceQuery, queryConfig, xml, 100, diagnosisAliases);
    calculateIncidence(valueQuery, caseQuery, instanceQuery, queryConfig, xml, 1000, diagnosisAliases);
    calculateIncidence(valueQuery, caseQuery, instanceQuery, queryConfig, xml, 10000, diagnosisAliases);
    calculateIncidence(valueQuery, caseQuery, instanceQuery, queryConfig, xml, 100000, diagnosisAliases);
    calculateIncidence(valueQuery, caseQuery, instanceQuery, queryConfig, xml, 1000000, diagnosisAliases);

    QueryUtil.getSingleAttribteGridSql(valueQuery, instanceQuery.getTableAlias(), RelationshipDAOIF.CHILD_ID_COLUMN, RelationshipDAOIF.PARENT_ID_COLUMN);

    this.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, caseQuery.getDisease());

    Disease disease = Disease.getCurrent();
    valueQuery.AND(caseQuery.getDisease().EQ(disease));

    return valueQuery;

  }

  private void calculateIncidence(ValueQuery valueQuery, IndividualCaseQuery caseQuery, IndividualInstanceQuery instanceQuery, JSONObject queryConfig, String xml, Integer multiplier, Map<String, String> diagnosisAliases)
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
        if (universals.length() > 0 && attributeKey.equals(IndividualCase.CLASS + '.' + IndividualCase.PROBABLESOURCE))
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
      throw new IncidenceProbableSourceException();
    }

    String columnAlias = s.getDbQualifiedName();

    // String tableAlias = caseQuery.getTableAlias();

    // MdEntityDAOIF mdIndInst =
    // MdEntityDAO.getMdEntityDAO(IndividualInstance.CLASS);
    // String tableName = mdIndInst.getTableName();
    // String indCaseCol = QueryUtil.getColumnName(mdIndInst,
    // IndividualInstance.INDIVIDUALCASE);
    String onset = QueryUtil.getColumnName(caseQuery.getMdClassIF(), IndividualCase.SYMPTOMONSET);

    String sql = "(" + getTotalCasesSQL(caseQuery, valueQuery, diagnosisAliases) + ")";
    sql += "/NULLIF(AVG(get_" + timePeriod + "_population_by_geoid_and_date(" + columnAlias + ", " + onset + ")),0.0)*" + multiplier;

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
      innerQuery.WHERE(iQuery.getIndividualCase().getDisease().EQ(Disease.getCurrent()));

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
    String posSum = QueryUtil.sumColumnForId(caseAlias, idCol, null, diagnosisAliases.get(posCases));
    String negSum = QueryUtil.sumColumnForId(caseAlias, idCol, null, diagnosisAliases.get(negCases));
    String clinSum = QueryUtil.sumColumnForId(caseAlias, idCol, null, diagnosisAliases.get(clinCases));
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

}
