package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.intervention.monitor.ITNData;
import dss.vector.solutions.intervention.monitor.ITNDataQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class AggregatedITNQB extends AbstractQB implements Reloadable
{

  public AggregatedITNQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize)
  {
    super(xml, config, layer, pageSize, pageSize);
  }
  
  @Override
  protected String getAuditClassAlias()
  {
    return ITNData.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    ITNDataQuery itnQuery = (ITNDataQuery) queryMap.get(ITNData.CLASS);

    boolean hasNets = QueryUtil.getSingleAttribteGridSql(valueQuery,itnQuery.getTableAlias()); 
    
    this.addGeoDisplayLabelQuery(itnQuery);
    
    this.setNumericRestrictions(valueQuery, queryConfig);
    
    if(hasNets)
    {
      valueQuery.FROM(itnQuery.getMdClassIF().getTableName(), itnQuery.getTableAlias());
    }
   
    return QueryUtil.setQueryDates(xml, valueQuery, itnQuery, itnQuery.getStartDate(), itnQuery.getEndDate(), itnQuery.getDisease());
  }

}
