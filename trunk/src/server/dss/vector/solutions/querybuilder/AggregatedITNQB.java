package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.dataaccess.RelationshipDAOIF;
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

  public AggregatedITNQB(String xml, String config, Layer layer)
  {
    super(xml, config, layer);
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    ITNDataQuery itnQuery = (ITNDataQuery) queryMap.get(ITNData.CLASS);

    boolean hasNets = QueryUtil.getSingleAttribteGridSql(valueQuery,itnQuery.getTableAlias(), RelationshipDAOIF.CHILD_ID_COLUMN,
        RelationshipDAOIF.PARENT_ID_COLUMN); 
    
    this.addGeoDisplayLabelQuery(itnQuery);
    
    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);
    
    if(hasNets)
    {
      valueQuery.FROM(itnQuery.getMdClassIF().getTableName(), itnQuery.getTableAlias());
    }
   
    return QueryUtil.setQueryDates(xml, valueQuery, itnQuery, itnQuery.getStartDate(), itnQuery.getEndDate(), itnQuery.getDisease());
  }

}
