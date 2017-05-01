package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AggregateFunction;
import com.runwaysdk.query.GeneratedTableClassQuery;
import com.runwaysdk.query.LeftJoinEq;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.RefusedResponse;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.intervention.monitor.Household;
import dss.vector.solutions.intervention.monitor.HouseholdQuery;
import dss.vector.solutions.intervention.monitor.ITNInstance;
import dss.vector.solutions.intervention.monitor.ITNInstanceQuery;
import dss.vector.solutions.intervention.monitor.SurveyPoint;
import dss.vector.solutions.intervention.monitor.SurveyPointQuery;
import dss.vector.solutions.intervention.monitor.SurveyedPerson;
import dss.vector.solutions.intervention.monitor.SurveyedPersonQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

public class SurveyQB extends AbstractQB implements Reloadable
{

  public SurveyQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageNumber, pageNumber, disease);
  }
  
  @Override
  protected String getAuditClassAlias()
  {
    return SurveyPoint.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedTableClassQuery> queryMap, String xml, JSONObject queryConfig)
  {
    SurveyPointQuery surveyPointQuery = (SurveyPointQuery) queryMap.get(SurveyPoint.CLASS);
    if (surveyPointQuery != null)
    {
      this.addGeoDisplayLabelQuery(surveyPointQuery);
    }

    HouseholdQuery householdQuery = (HouseholdQuery) queryMap.get(Household.CLASS);
    if (householdQuery != null)
    {
      valueQuery.WHERE(householdQuery.getSurveyPoint().EQ(surveyPointQuery.getId()));

      QueryUtil.joinTermAllpaths(valueQuery, Household.CLASS, householdQuery, this.getTermRestrictions(), this.getLayer());
      QueryUtil.joinEnumerationDisplayLabels(valueQuery, Household.CLASS, householdQuery);
    }

    SurveyedPersonQuery personQuery = (SurveyedPersonQuery) queryMap.get(SurveyedPerson.CLASS);
    if (personQuery != null)
    {
      if (householdQuery == null)
      {
        householdQuery = new HouseholdQuery(valueQuery);
        valueQuery.WHERE(householdQuery.getSurveyPoint().EQ(surveyPointQuery.getId()));
      }

      valueQuery.WHERE(householdQuery.surveyedPeople(personQuery));

      QueryUtil.joinTermAllpaths(valueQuery, SurveyedPerson.CLASS, personQuery, this.getTermRestrictions(), this.getLayer());
      QueryUtil.leftJoinEnumerationDisplayLabels(valueQuery, SurveyedPerson.CLASS, personQuery, personQuery.getTableAlias() + ".id");
      QueryUtil.getSingleAttribteGridSql(valueQuery, personQuery.getTableAlias());
    }

    if (personQuery == null && xml.indexOf("one__dss_vector_solutions_intervention_monitor_SurveyedPerson") > 0)
    {
      // we pass in a value query instead of a query factory so that we use a
      // normal join instead of IN()
      personQuery = new SurveyedPersonQuery(valueQuery);
      valueQuery.SELECT(personQuery.getId());
      QueryUtil.getSingleAttribteGridSql(valueQuery, personQuery.getTableAlias());
      if (householdQuery != null)
      {
        valueQuery.WHERE(householdQuery.surveyedPeople(personQuery));
      }

    }

    ITNInstanceQuery itnQuery = (ITNInstanceQuery) queryMap.get(ITNInstance.CLASS);

    if (itnQuery != null)
    {
      if (householdQuery == null)
      {
        householdQuery = new HouseholdQuery(valueQuery);
        valueQuery.WHERE(householdQuery.getSurveyPoint().EQ(surveyPointQuery.getId()));
      }
      if (personQuery == null)
      {
        // join against the house if there is no person
        valueQuery.WHERE(householdQuery.iTNs(itnQuery));
        QueryUtil.joinTermAllpaths(valueQuery, ITNInstance.CLASS, itnQuery, this.getTermRestrictions(), this.getLayer());
        QueryUtil.joinEnumerationDisplayLabels(valueQuery, ITNInstance.CLASS, itnQuery);
      }
      else
      {
        // left join against the person if person is in this query
        LeftJoinEq leftJoin = personQuery.getSleptUnderNet().LEFT_JOIN_EQ(itnQuery);
        valueQuery.WHERE(leftJoin);
        QueryUtil.leftJoinTermDisplayLabels(valueQuery, itnQuery, itnQuery.getId().getColumnAlias());  // TODO : Use joinTermAllpaths instead, its faster
        QueryUtil.leftJoinEnumerationDisplayLabels(valueQuery, ITNInstance.CLASS, itnQuery, itnQuery.getId().getColumnAlias());
      }

    }

    if (valueQuery.hasSelectableRef(QueryConstants.RDT_PREVALENCE) || valueQuery.hasSelectableRef(QueryConstants.BLOODSLIDE_PREVALENCE) || valueQuery.hasSelectableRef(QueryConstants.RDT_BLOODSLIDE_PREVALENCE))
    {
      if (personQuery == null)
      {

        // we pass in a value query instead of a query factory so that we use a
        // normal join instead of IN()
        personQuery = new SurveyedPersonQuery(valueQuery);
        if (householdQuery != null)
        {
          valueQuery.WHERE(householdQuery.surveyedPeople(personQuery));
        }
        else
        {
          householdQuery = new HouseholdQuery(valueQuery);
          valueQuery.WHERE(householdQuery.getSurveyPoint().EQ(surveyPointQuery.getId()));
        }

        valueQuery.WHERE(householdQuery.surveyedPeople(personQuery));
      }

      String tableAlias = personQuery.getTableAlias();
      MdEntityDAOIF md = personQuery.getMdClassIF();
      String performedRDTCol = QueryUtil.getColumnName(md, SurveyedPerson.PERFORMEDRDT);
      String performedBloodSlideCol = QueryUtil.getColumnName(md, SurveyedPerson.PERFORMEDBLOODSLIDE);
      String rdtResultCol = QueryUtil.getColumnName(md, SurveyedPerson.RDTRESULT);
      String bloodSlideResultCol = QueryUtil.getColumnName(md, SurveyedPerson.BLOODSLIDERESULT);

      String yesId = RefusedResponse.YES.getId();
      String rtdTested = "CASE " + tableAlias + "." + performedRDTCol + "_c WHEN '" + yesId + "' THEN 1 ELSE NULL END";
      String rdtPositive = "SUM(" + rdtResultCol + ")";
      String rdtTotal = "SUM(" + rtdTested + ")";
      String rdtPrevalance = rdtPositive + "/NULLIF(" + rdtTotal + ",0.0)::float*100.0";

      String bloodslidePostive = "SUM(" + bloodSlideResultCol + ")";
      String bloodslideTotal = "SUM(" + performedBloodSlideCol + ")";
      String bloodslidePrevalence = bloodslidePostive + "/NULLIF(" + bloodslideTotal + ",0.0)::float*100.0";

      // String totalTested = "SUM(COALESCE(" + rtdTested + "," +
      // performedBloodSlideCol + ",0))::FLOAT";
      // String totalPositive = "SUM(COALESCE(" + rdtResultCol + "," +
      // bloodSlideResultCol + ",0))::FLOAT";

      // if bs+ then add r
      // else rdt+ then add r(+)

      if (valueQuery.hasSelectableRef(QueryConstants.RDT_PREVALENCE))
      {
        ( (SelectableSQL) valueQuery.getSelectableRef(QueryConstants.RDT_PREVALENCE) ).setSQL(rdtPrevalance);
      }

      if (valueQuery.hasSelectableRef(QueryConstants.BLOODSLIDE_PREVALENCE))
      {
        ( (SelectableSQL) valueQuery.getSelectableRef(QueryConstants.BLOODSLIDE_PREVALENCE) ).setSQL(bloodslidePrevalence);
      }

      if (valueQuery.hasSelectableRef(QueryConstants.RDT_BLOODSLIDE_PREVALENCE))
      {
        String totalP = "SUM(CASE WHEN " + performedBloodSlideCol + " = 1 THEN " + bloodSlideResultCol + " ";
        totalP += "WHEN " + tableAlias + "." + performedRDTCol + "_c = '" + yesId + "' THEN " + rdtResultCol + " ELSE NULL END) \n";

        String totalT = "SUM(CASE WHEN " + performedBloodSlideCol + " = 1 THEN 1 \n";
        totalT += "WHEN " + tableAlias + "." + performedRDTCol + "_c = '" + yesId + "' THEN 1 ELSE NULL END) ";

        String rdtBloodslidePrevalence = totalP + "/NULLIF(" + totalT + ",0.0)::float*100.0";

        ( (SelectableSQL) valueQuery.getSelectableRef(QueryConstants.RDT_BLOODSLIDE_PREVALENCE) ).setSQL(rdtBloodslidePrevalence);
      }

    }

    if (valueQuery.hasSelectableRef("age"))
    {
      // valueQuery.hasSelectableRef
      SelectableSQLInteger dobSel;
      Selectable sel = valueQuery.getSelectableRef("age");
      if (sel instanceof AggregateFunction)
      {
        dobSel = (SelectableSQLInteger) ( (AggregateFunction) sel ).getSelectable();
      }
      else
      {
        dobSel = (SelectableSQLInteger) sel;
      }

      if (personQuery == null)
      {
        // we pass in a value query instead of a query factory so that we use a
        // normal join instead of IN()
        personQuery = new SurveyedPersonQuery(valueQuery);
        if (householdQuery == null)
        {
          householdQuery = new HouseholdQuery(valueQuery);
          valueQuery.WHERE(householdQuery.getSurveyPoint().EQ(surveyPointQuery.getId()));
        }
        valueQuery.WHERE(householdQuery.surveyedPeople(personQuery));
      }

      String personTableAlias = personQuery.getTableAlias();
      String surveyPointTableAlais = surveyPointQuery.getTableAlias();

      String surveyDateCol = QueryUtil.getColumnName(surveyPointQuery.getMdClassIF(), SurveyPoint.SURVEYDATE);
      String dobCol = QueryUtil.getColumnName(personQuery.getMdClassIF(), SurveyedPerson.DOB);

      String sql = "EXTRACT(year from AGE(" + surveyPointTableAlais + "." + surveyDateCol + ", " + personTableAlias + "." + dobCol + "))";
      dobSel.setSQL(sql);
    }

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, surveyPointQuery.getDisease());

    this.setNumericRestrictions(valueQuery, queryConfig);

    return valueQuery;
  }

}
