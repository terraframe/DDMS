package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.Person;
import dss.vector.solutions.intervention.monitor.ITNDistribution;
import dss.vector.solutions.intervention.monitor.ITNDistributionQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class ITNFacilityDistributionQB extends AbstractQB implements Reloadable
{

  public ITNFacilityDistributionQB(String xml, String config, Layer layer)
  {
    super(xml, config, layer);
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    ITNDistributionQuery itnQuery = (ITNDistributionQuery) queryMap.get(ITNDistribution.CLASS);

    this.addGeoDisplayLabelQuery(itnQuery);
    QueryUtil.joinTermAllpaths(valueQuery, ITNDistribution.CLASS, itnQuery);
    QueryUtil.getSingleAttribteGridSql(valueQuery, itnQuery.getTableAlias(), RelationshipDAOIF.CHILD_ID_COLUMN,
        RelationshipDAOIF.PARENT_ID_COLUMN);

    dss.vector.solutions.PersonQuery personQuery = (dss.vector.solutions.PersonQuery) queryMap.get(dss.vector.solutions.Person.CLASS);
    valueQuery.WHERE(personQuery.getItnRecipientDelegate().EQ(itnQuery.getRecipient()));
    QueryUtil.joinTermAllpaths(valueQuery, dss.vector.solutions.Person.CLASS, personQuery);
    this.addGeoDisplayLabelQuery(personQuery);

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

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, itnQuery.getDisease());

    return valueQuery;

  }

}
