/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedTableClassQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.intervention.monitor.AggregatedIPT;
import dss.vector.solutions.intervention.monitor.AggregatedIPTQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class AggregatedIPTQB extends AbstractQB implements Reloadable
{

  public AggregatedIPTQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageSize, pageSize, disease);
  }

  @Override
  protected String getAuditClassAlias()
  {
    return AggregatedIPT.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedTableClassQuery> queryMap, String xml, JSONObject config)
  {
    AggregatedIPTQuery aggregatedIPTQuery = (AggregatedIPTQuery) queryMap.get(AggregatedIPT.CLASS);

    // this is a hack to force valueQuery to include the aggreated cases table
    valueQuery.WHERE(aggregatedIPTQuery.id().NE("0"));

    this.addGeoDisplayLabelQuery(aggregatedIPTQuery);

    QueryUtil.getSingleAttribteGridSql(valueQuery, aggregatedIPTQuery.getTableAlias());

    this.setNumericRestrictions(valueQuery, config);

    valueQuery.AND(aggregatedIPTQuery.getDisease().EQ(this.getDisease()));

    return QueryUtil.setQueryDates(xml, valueQuery, aggregatedIPTQuery, aggregatedIPTQuery.getStartDate(), aggregatedIPTQuery.getEndDate(), aggregatedIPTQuery.getDisease());

  }

}
