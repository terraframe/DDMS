package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.intervention.monitor.AggregatedIPT;
import dss.vector.solutions.intervention.monitor.AggregatedIPTQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class AggregatedIPTQB extends AbstractQB implements Reloadable
{

  public AggregatedIPTQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize)
  {
    super(xml, config, layer, pageSize, pageSize);
  }
  
  @Override
  protected String getAuditClassAlias()
  {
    return AggregatedIPT.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    AggregatedIPTQuery aggregatedIPTQuery = (AggregatedIPTQuery) queryMap.get(AggregatedIPT.CLASS);
    
    
    //this is a hack to force valueQuery to include the aggreated cases table
    valueQuery.WHERE(aggregatedIPTQuery.id().NE("0"));
    
    this.addGeoDisplayLabelQuery(aggregatedIPTQuery);
    
    QueryUtil.getSingleAttribteGridSql(valueQuery,aggregatedIPTQuery.getTableAlias());
    
    this.setNumericRestrictions(valueQuery, queryConfig);
    
    Disease disease = Disease.getCurrent();
    valueQuery.AND(aggregatedIPTQuery.getDisease().EQ(disease));
    
    return QueryUtil.setQueryDates(xml, valueQuery, aggregatedIPTQuery, aggregatedIPTQuery.getStartDate(), aggregatedIPTQuery.getEndDate(), aggregatedIPTQuery.getDisease());

  }

}
