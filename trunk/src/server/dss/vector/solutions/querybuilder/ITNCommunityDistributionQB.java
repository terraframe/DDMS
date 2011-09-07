package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.intervention.monitor.ITNCommunityDistribution;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistributionQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class ITNCommunityDistributionQB extends AbstractQB implements Reloadable
{

  public ITNCommunityDistributionQB(String xml, String config, Layer layer)
  {
    super(xml, config, layer);
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    ITNCommunityDistributionQuery itnQuery = (ITNCommunityDistributionQuery) queryMap.get(ITNCommunityDistribution.CLASS);

    boolean hasNets = QueryUtil.getSingleAttribteGridSql(valueQuery, itnQuery.getTableAlias());

    this.addGeoDisplayLabelQuery(itnQuery);

    this.setNumericRestrictions(valueQuery, queryConfig);

    if(hasNets)
    {
      valueQuery.FROM(itnQuery.getMdClassIF().getTableName(), itnQuery.getTableAlias());
    }
    
    return QueryUtil.setQueryDates(xml, valueQuery, itnQuery,  itnQuery.getStartDate(), itnQuery.getEndDate(), itnQuery.getDisease());
  }

}
