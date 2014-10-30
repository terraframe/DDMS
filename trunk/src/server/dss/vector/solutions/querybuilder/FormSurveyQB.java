package dss.vector.solutions.querybuilder;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.metadata.MdRelationshipDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Function;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.LeftJoinEq;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.form.business.FormBedNet;
import dss.vector.solutions.form.business.FormBedNetQuery;
import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.form.business.FormHouseholdQuery;
import dss.vector.solutions.form.business.FormPerson;
import dss.vector.solutions.form.business.FormPersonQuery;
import dss.vector.solutions.form.business.FormSurvey;
import dss.vector.solutions.form.business.FormSurveyQuery;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class FormSurveyQB extends AbstractQB implements Reloadable
{

  public FormSurveyQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageSize, pageSize, disease);
  }

  @Override
  protected String getAuditClassAlias()
  {
    return FormSurvey.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    this.prepareQueryMap(queryFactory, valueQuery, queryMap);

    FormSurveyQuery surveyQuery = (FormSurveyQuery) queryMap.get(FormSurvey.CLASS);
    FormHouseholdQuery householdQuery = (FormHouseholdQuery) queryMap.get(FormHousehold.CLASS);
    FormBedNetQuery bedNetQuery = (FormBedNetQuery) queryMap.get(FormBedNet.CLASS);
    FormPersonQuery personQuery = (FormPersonQuery) queryMap.get(FormPerson.CLASS);

    this.addGeoDisplayLabelQuery(surveyQuery);
    QueryUtil.joinTermAllpaths(valueQuery, surveyQuery.getClassType(), surveyQuery, this.getTermRestrictions());
    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, surveyQuery.get(MdFormUtil.DISEASE));
    this.getSingleAttributeGridSql(valueQuery, surveyQuery, "id");

    if (householdQuery != null)
    {
      valueQuery.WHERE(householdQuery.getSurvey().EQ(surveyQuery));

      this.addGeoDisplayLabelQuery(householdQuery);
      QueryUtil.joinTermAllpaths(valueQuery, householdQuery.getClassType(), householdQuery, this.getTermRestrictions());
      QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, householdQuery.get(MdFormUtil.DISEASE));
      this.getSingleAttributeGridSql(valueQuery, householdQuery, "id");
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
      QueryUtil.joinTermAllpaths(valueQuery, personQuery.getClassType(), personQuery, this.getTermRestrictions());
      QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, personQuery.get(MdFormUtil.DISEASE));
      this.getSingleAttributeGridSql(valueQuery, personQuery, "id");
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
        QueryUtil.joinTermAllpaths(valueQuery, bedNetQuery.getClassType(), bedNetQuery, this.getTermRestrictions());
        QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, bedNetQuery.get(MdFormUtil.DISEASE));
        this.getSingleAttributeGridSql(valueQuery, bedNetQuery, "id");
      }
      else
      {
        // left join against the person if person is in this query
        LeftJoinEq leftJoin = personQuery.getNet().LEFT_JOIN_EQ(bedNetQuery);
        valueQuery.WHERE(leftJoin);

        this.addGeoDisplayLabelQuery(bedNetQuery);
        QueryUtil.leftJoinTermDisplayLabels(valueQuery, bedNetQuery, bedNetQuery.getId().getColumnAlias());
        QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, bedNetQuery.get(MdFormUtil.DISEASE));
        this.getSingleAttributeGridSql(valueQuery, bedNetQuery, bedNetQuery.getId().getColumnAlias());
      }

    }
    this.setNumericRestrictions(valueQuery, queryConfig);

    valueQuery.FROM(surveyQuery.getMdClassIF().getTableName(), surveyQuery.getTableAlias());

    return valueQuery;
  }

  private void prepareQueryMap(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap)
  {
    Collection<String> parentClasses = this.getGridParentClasses(valueQuery);

    if (parentClasses.contains(FormHousehold.CLASS) && !queryMap.containsKey(FormHousehold.CLASS))
    {
      queryMap.put(FormHousehold.CLASS, new FormHouseholdQuery(queryFactory));
    }
    if (parentClasses.contains(FormBedNet.CLASS) && !queryMap.containsKey(FormBedNet.CLASS))
    {
      queryMap.put(FormBedNet.CLASS, new FormBedNetQuery(queryFactory));
    }
    if (parentClasses.contains(FormPerson.CLASS) && !queryMap.containsKey(FormPerson.CLASS))
    {
      queryMap.put(FormPerson.CLASS, new FormPersonQuery(queryFactory));
    }
  }

  public Collection<String> getGridParentClasses(ValueQuery valueQuery)
  {
    Set<String> set = new TreeSet<String>();

    for (Selectable s : valueQuery.getSelectableRefs())
    {
      while (s instanceof Function)
      {
        Function f = (Function) s;
        s = f.getSelectable();
      }

      if (s instanceof SelectableSQL)
      {
        String gridAlias = s.getUserDefinedAlias();
        int index1 = gridAlias.indexOf("__");
        int index2 = gridAlias.lastIndexOf("__");
        if (index1 > 0 && index2 > 0 && index1 != index2)
        {

          String attrib = gridAlias.substring(0, index1);

          // here we make a dummy value when the relationship has no ammount
          if (attrib.equals(QueryUtil.DUMMY_RELATIONSHIP_VALUE_ONE))
          {
            attrib = QueryUtil.DUMMY_RELATIONSHIP_VALUE_COL;
          }

          String klass = gridAlias.substring(index1 + 2, index2).replace("_", ".");

          MdRelationshipDAOIF mdRel = MdRelationshipDAO.getMdRelationshipDAO(klass);

          set.add(mdRel.getParentMdBusiness().definesType());
        }
      }
    }

    return set;
  }

  /**
   * Gets the grid attributes for the given GeneratedEntityQuery. This also checks that the given query actually defines the grid, whereas the version defined in QueryUtil doesn't do that.
   * 
   * @param valueQuery
   * @param query
   * @param idColumnAlias
   *          TODO
   * @return
   */
  private boolean getSingleAttributeGridSql(ValueQuery valueQuery, GeneratedEntityQuery query, String idColumnAlias)
  {
    boolean foundGrid = false;

    for (Selectable s : valueQuery.getSelectableRefs())
    {
      while (s instanceof Function)
      {
        Function f = (Function) s;
        s = f.getSelectable();
      }

      if (s instanceof SelectableSQL)
      {
        String gridAlias = s.getUserDefinedAlias();
        int index1 = gridAlias.indexOf("__");
        int index2 = gridAlias.lastIndexOf("__");
        if (index1 > 0 && index2 > 0 && index1 != index2)
        {
          foundGrid = true;

          String attrib = gridAlias.substring(0, index1);

          // here we make a dummy value when the relationship has no ammount
          if (attrib.equals(QueryUtil.DUMMY_RELATIONSHIP_VALUE_ONE))
          {
            attrib = QueryUtil.DUMMY_RELATIONSHIP_VALUE_COL;
          }

          String klass = gridAlias.substring(index1 + 2, index2).replace("_", ".");
          String term_id = gridAlias.substring(index2 + 2, gridAlias.length());

          MdRelationshipDAOIF mdRel = MdRelationshipDAO.getMdRelationshipDAO(klass);

          // make sure the given query is the parent type in the relationship
          // FIXME does not take inheritance into account
          String parentClass = mdRel.getParentMdBusiness().definesType();
          String queryClass = query.getClassType();
          if (!parentClass.equals(queryClass))
          {
            continue;
          }

          String table = mdRel.getTableName();

          String attrCol;
          if (attrib.equals(QueryUtil.DUMMY_RELATIONSHIP_VALUE_COL))
          {
            attrCol = QueryUtil.DUMMY_RELATIONSHIP_VALUE_COL;
          }
          else
          {
            attrCol = QueryUtil.getColumnName(mdRel, attrib);
          }

          // The default convention is that the child in the relationship is the
          // Term class
          String tableAlias = query.getTableAlias();
          String sql = "SELECT " + attrCol + " FROM " + table + " WHERE " + RelationshipDAOIF.CHILD_ID_COLUMN + " = '" + term_id + "' " + "AND " + RelationshipDAOIF.PARENT_ID_COLUMN + " = " + tableAlias + "." + idColumnAlias;

          ( (SelectableSQL) s ).setSQL(sql);
        }
      }
    }
    return foundGrid;
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    return new FormSurveyQB(xml, config, layer, pageSize, pageSize, disease).construct();
  }
}
