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
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.Person;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.intervention.monitor.ITNDistribution;
import dss.vector.solutions.intervention.monitor.ITNDistributionQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class ITNFacilityDistributionQB extends AbstractQB implements Reloadable
{

  public ITNFacilityDistributionQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageNumber, pageNumber, disease);
  }
  
  @Override
  protected String getAuditClassAlias()
  {
    return ITNDistribution.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedTableClassQuery> queryMap, String xml, JSONObject queryConfig)
  {
    ITNDistributionQuery itnQuery = (ITNDistributionQuery) queryMap.get(ITNDistribution.CLASS);

    this.addGeoDisplayLabelQuery(itnQuery);
    QueryUtil.joinTermAllpaths(valueQuery, ITNDistribution.CLASS, itnQuery, this.getTermRestrictions(), this.getLayer());
    QueryUtil.getSingleAttribteGridSql(valueQuery, itnQuery.getTableAlias());

    dss.vector.solutions.PersonQuery personQuery = (dss.vector.solutions.PersonQuery) queryMap.get(dss.vector.solutions.Person.CLASS);
    valueQuery.WHERE(personQuery.getItnRecipientDelegate().EQ(itnQuery.getRecipient()));
    QueryUtil.joinTermAllpaths(valueQuery, dss.vector.solutions.Person.CLASS, personQuery, this.getTermRestrictions(), this.getLayer());
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

    this.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, itnQuery.getDisease());

    return valueQuery;

  }

}
