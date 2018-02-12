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
import dss.vector.solutions.intervention.monitor.ITNData;
import dss.vector.solutions.intervention.monitor.ITNDataQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class AggregatedITNQB extends AbstractQB implements Reloadable
{

  public AggregatedITNQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageSize, pageSize, disease);
  }

  @Override
  protected String getAuditClassAlias()
  {
    return ITNData.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedTableClassQuery> queryMap, String xml, JSONObject config)
  {
    ITNDataQuery itnQuery = (ITNDataQuery) queryMap.get(ITNData.CLASS);

    boolean hasNets = QueryUtil.getSingleAttribteGridSql(valueQuery, itnQuery.getTableAlias());

    this.addGeoDisplayLabelQuery(itnQuery);

    this.setNumericRestrictions(valueQuery, config);

    if (hasNets)
    {
      valueQuery.FROM(itnQuery.getMdClassIF().getTableName(), itnQuery.getTableAlias());
    }

    return QueryUtil.setQueryDates(xml, valueQuery, itnQuery, itnQuery.getStartDate(), itnQuery.getEndDate(), itnQuery.getDisease());
  }

}
