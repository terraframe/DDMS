package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.LeftJoinEq;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.form.business.FormBedNet;
import dss.vector.solutions.form.business.FormBedNetQuery;
import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.form.business.FormHouseholdQuery;
import dss.vector.solutions.form.business.FormPerson;
import dss.vector.solutions.form.business.FormPersonQuery;
import dss.vector.solutions.form.business.FormSurvey;
import dss.vector.solutions.form.business.FormSurveyQuery;
import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class FormSurveyQB extends AbstractQB implements Reloadable
{

  public FormSurveyQB(String xml, String config, Layer layer)
  {
    super(xml, config, layer);
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    FormSurveyQuery surveyQuery = (FormSurveyQuery) queryMap.get(FormSurvey.CLASS);
    FormHouseholdQuery householdQuery = (FormHouseholdQuery) queryMap.get(FormHousehold.CLASS);
    FormBedNetQuery bedNetQuery = (FormBedNetQuery) queryMap.get(FormBedNet.CLASS);
    FormPersonQuery personQuery = (FormPersonQuery) queryMap.get(FormPerson.CLASS);

    this.addGeoDisplayLabelQuery(surveyQuery);
    QueryUtil.joinTermAllpaths(valueQuery, surveyQuery.getClassType(), surveyQuery);
    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, surveyQuery.get(MdFormUtil.DISEASE));
    QueryUtil.getSingleAttribteGridSql(valueQuery, surveyQuery.getTableAlias());

    if (householdQuery != null)
    {
      valueQuery.WHERE(householdQuery.getSurvey().EQ(surveyQuery));

      this.addGeoDisplayLabelQuery(householdQuery);
      QueryUtil.joinTermAllpaths(valueQuery, householdQuery.getClassType(), householdQuery);
      QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, householdQuery.get(MdFormUtil.DISEASE));
      QueryUtil.getSingleAttribteGridSql(valueQuery, householdQuery.getTableAlias());
    }

    if (personQuery != null)
    {
      if (householdQuery == null)
      {
        householdQuery = new FormHouseholdQuery(valueQuery);
        valueQuery.WHERE(householdQuery.getSurvey().EQ(surveyQuery));
      }

      valueQuery.WHERE(personQuery.getHousehold().EQ(householdQuery));

      this.addGeoDisplayLabelQuery(personQuery);
      QueryUtil.joinTermAllpaths(valueQuery, personQuery.getClassType(), personQuery);
      QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, personQuery.get(MdFormUtil.DISEASE));
      QueryUtil.getSingleAttribteGridSql(valueQuery, personQuery.getTableAlias());
    }

    if (bedNetQuery != null)
    {
      if (householdQuery == null)
      {
        householdQuery = new FormHouseholdQuery(valueQuery);
        valueQuery.WHERE(householdQuery.getSurvey().EQ(surveyQuery));
      }
      if (personQuery == null)
      {
        // join against the house if there is no person
        valueQuery.WHERE(bedNetQuery.getHousehold().EQ(householdQuery));

        this.addGeoDisplayLabelQuery(bedNetQuery);
        QueryUtil.joinTermAllpaths(valueQuery, bedNetQuery.getClassType(), bedNetQuery);
        QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, bedNetQuery.get(MdFormUtil.DISEASE));
        QueryUtil.getSingleAttribteGridSql(valueQuery, bedNetQuery.getTableAlias());
      }
      else
      {
        // left join against the person if person is in this query
        LeftJoinEq leftJoin = personQuery.getNet().LEFT_JOIN_EQ(bedNetQuery);
        valueQuery.WHERE(leftJoin);

        this.addGeoDisplayLabelQuery(bedNetQuery);
        QueryUtil.leftJoinTermDisplayLabels(valueQuery, bedNetQuery, bedNetQuery.getId().getColumnAlias());
        QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, bedNetQuery.get(MdFormUtil.DISEASE));
        QueryUtil.getSingleAttribteGridSql(valueQuery, bedNetQuery.getTableAlias());
      }

    }
    this.setNumericRestrictions(valueQuery, queryConfig);

    valueQuery.FROM(surveyQuery.getMdClassIF().getTableName(), surveyQuery.getTableAlias());

    return valueQuery;
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
    return new FormSurveyQB(xml, config, layer).construct();
  }
}
