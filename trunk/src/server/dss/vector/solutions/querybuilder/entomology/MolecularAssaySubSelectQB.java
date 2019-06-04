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

import dss.vector.solutions.entomology.MolecularAssay;
import dss.vector.solutions.entomology.MolecularAssayQuery;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.AbstractQB;
import dss.vector.solutions.util.QueryUtil;

public class MolecularAssaySubSelectQB extends AbstractQB implements Reloadable
{

  public MolecularAssaySubSelectQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageNumber, pageNumber, disease);
  }
  
  @Override
  protected String getAuditClassAlias()
  {
    return MosquitoCollection.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedTableClassQuery> queryMap, String xml, JSONObject queryConfig)
  {
    MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);


    MolecularAssayQuery molecularQuery = (MolecularAssayQuery) queryMap.get(MolecularAssay.CLASS);
    
    
    if (molecularQuery == null && (xml.indexOf(">percent") > 0  ||  xml.indexOf(">frequency") > 0))
    {
      molecularQuery = new MolecularAssayQuery(queryFactory);
    }
    
    
    
    if (molecularQuery != null)
    {
      valueQuery.WHERE(molecularQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));
      QueryUtil.joinTermAllpaths(valueQuery, MolecularAssay.CLASS, molecularQuery, this.getTermRestrictions(), this.getLayer());
      
      String numberrrCol = "COALESCE(" + QueryUtil.getColumnName(molecularQuery.getMdClassIF(), MolecularAssay.NUMBERRR) + ",0)";
      String numberrsCol = "COALESCE(" + QueryUtil.getColumnName(molecularQuery.getMdClassIF(), MolecularAssay.NUMBERRS) + ",0)";
      String numberssCol = "COALESCE(" + QueryUtil.getColumnName(molecularQuery.getMdClassIF(), MolecularAssay.NUMBERSS) + ",0)";
      String numbersrpCol = "COALESCE(" + QueryUtil.getColumnName(molecularQuery.getMdClassIF(), MolecularAssay.NUMBERSRP) + ",0)";
      String numberrrpCol = "COALESCE(" + QueryUtil.getColumnName(molecularQuery.getMdClassIF(), MolecularAssay.NUMBERRRP) + ",0)";
      String numberrprpCol = "COALESCE(" + QueryUtil.getColumnName(molecularQuery.getMdClassIF(), MolecularAssay.NUMBERRPRP) + ",0)";
      
      if(xml.indexOf(">percentRR<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("percentRR");
        s.setSQL("100.0 * SUM("+numberrrCol+") / SUM("+numberrrCol+"+"+numberrsCol+"+"+numberssCol+"+"+numbersrpCol+"+"+numberrrpCol+"+"+numberrprpCol+")");
      }
      if(xml.indexOf(">percentRS<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("percentRS");
        s.setSQL("100.0 * SUM("+numberrsCol+") / SUM("+numberrrCol+"+"+numberrsCol+"+"+numberssCol+"+"+numbersrpCol+"+"+numberrrpCol+"+"+numberrprpCol+")");
      }
      if(xml.indexOf(">percentSS<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("percentSS");
        s.setSQL("100.0 * SUM("+numberssCol+") / SUM("+numberrrCol+"+"+numberrsCol+"+"+numberssCol+"+"+numbersrpCol+"+"+numberrrpCol+"+"+numberrprpCol+")");
      }
      if(xml.indexOf(">percentSRp<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("percentSRp");
        s.setSQL("100.0 * SUM("+numbersrpCol+") / SUM("+numberrrCol+"+"+numberrsCol+"+"+numberssCol+"+"+numbersrpCol+"+"+numberrrpCol+"+"+numberrprpCol+")");
      }
      if(xml.indexOf(">percentRRp<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("percentRRp");
        s.setSQL("100.0 * SUM("+numberrrpCol+") / SUM("+numberrrCol+"+"+numberrsCol+"+"+numberssCol+"+"+numbersrpCol+"+"+numberrrpCol+"+"+numberrprpCol+")");
      }
      if(xml.indexOf(">percentRpRp<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("percentRpRp");
        s.setSQL("100.0 * SUM("+numberrprpCol+") / SUM("+numberrrCol+"+"+numberrsCol+"+"+numberssCol+"+"+numbersrpCol+"+"+numberrrpCol+"+"+numberrprpCol+")");
      }
      // Frequency of R(AG) = ('SUM(RR) + 0.5 * SUM(RS) + 0.5 * SUM(R'R)) / (SUM(RR) + SUM(RS) + SUM(SS) + SUM(R'S) + SUM(R'R) + SUM(R'R'))
      if(xml.indexOf(">frequencyR<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("frequencyR");
        s.setSQL("(SUM("+numberrrCol+" +(0.5*"+numberrsCol+") + (0.5*"+numberrrpCol+") ) ) / SUM("+numberrrCol+"+"+numberrsCol+"+"+numberssCol+"+"+numbersrpCol+"+"+numberrrpCol+"+"+numberrprpCol+")");
      }
      // Frequency of S(AG) = ('SUM(SS) + 0.5 * SUM(RS) + 0.5 * SUM(R'S)) / (SUM(RR) + SUM(RS) + SUM(SS) + SUM(R'S) + SUM(R'R) + SUM(R'R'))
      if(xml.indexOf(">frequencyS<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("frequencyS");
        s.setSQL("(SUM("+numberssCol+" + (0.5*"+numberrsCol+") + (0.5*"+numbersrpCol+") ) ) / SUM("+numberrrCol+"+"+numberrsCol+"+"+numberssCol+"+"+numbersrpCol+"+"+numberrrpCol+"+"+numberrprpCol+")");
      }
      // Frequency of R'(AG) = ('SUM(R'R') + 0.5 * SUM(R'S) + 0.5 * SUM(R'R)) / (SUM(RR) + SUM(RS) + SUM(SS) + SUM(R'S) + SUM(R'R) + SUM(R'R'))
      if(xml.indexOf(">frequencyRp<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("frequencyRp");
        s.setSQL("(SUM("+numberrprpCol+" + (0.5*"+numbersrpCol+") + (0.5*"+numberrrpCol+") ) ) / SUM("+numberrrCol+"+"+numberrsCol+"+"+numberssCol+"+"+numbersrpCol+"+"+numberrrpCol+"+"+numberrprpCol+")");
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
