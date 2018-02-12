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
package dss.vector.solutions.querybuilder.entomology;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedTableClassQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.entomology.InfectionAssay;
import dss.vector.solutions.entomology.InfectionAssayQuery;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.AbstractQB;
import dss.vector.solutions.util.QueryUtil;

public class InfectionAssaySubSelectQB extends AbstractQB implements Reloadable
{

  public InfectionAssaySubSelectQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageSize, pageSize, disease);
  }

  @Override
  protected String getAuditClassAlias()
  {
    return MosquitoCollection.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedTableClassQuery> queryMap, String xml, JSONObject queryConfig)
  {
    MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);

    InfectionAssayQuery infectionQuery = (InfectionAssayQuery) queryMap.get(InfectionAssay.CLASS);

    if (infectionQuery == null && xml.indexOf(">prevalence<") > 0)
    {
      infectionQuery = new InfectionAssayQuery(queryFactory);
    }

    if (infectionQuery != null)
    {
      valueQuery.WHERE(infectionQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));
      QueryUtil.joinTermAllpaths(valueQuery, InfectionAssay.CLASS, infectionQuery, this.getTermRestrictions(), this.getLayer());

      if (xml.indexOf(">prevalence<") > 0)
      {
        String numberPositiveCol = QueryUtil.getColumnName(infectionQuery.getMdClassIF(), InfectionAssay.NUMBERPOSITIVE);
        String numberTestedCol = QueryUtil.getColumnName(infectionQuery.getMdClassIF(), InfectionAssay.NUMBERTESTED);

        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("prevalence");
        s.setSQL("100.0 * SUM(" + numberPositiveCol + ") / SUM(" + numberTestedCol + ")");
      }

    }

    this.addGeoDisplayLabelQuery(mosquitoCollectionQuery);

    QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery, this.getTermRestrictions(), this.getLayer());

    QueryUtil.joinEnumerationDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    this.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, mosquitoCollectionQuery.getDisease());

    return valueQuery;
  }

}
