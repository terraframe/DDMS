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
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.Person;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.intervention.monitor.IndividualIPT;
import dss.vector.solutions.intervention.monitor.IndividualIPTCase;
import dss.vector.solutions.intervention.monitor.IndividualIPTCaseQuery;
import dss.vector.solutions.intervention.monitor.IndividualIPTQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class IndividualIPTQB extends AbstractQB implements Reloadable
{

  public IndividualIPTQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageSize, pageSize, disease);
  }
  
  @Override
  protected String getAuditClassAlias()
  {
    return IndividualIPT.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedTableClassQuery> queryMap, String xml, JSONObject queryConfig)
  {

    IndividualIPTQuery individualIPTQuery = (IndividualIPTQuery) queryMap.get(IndividualIPT.CLASS);
    IndividualIPTCaseQuery individualIPTCaseQuery = (IndividualIPTCaseQuery) queryMap.get(IndividualIPTCase.CLASS);
    dss.vector.solutions.PersonQuery personQuery = (dss.vector.solutions.PersonQuery) queryMap.get(dss.vector.solutions.Person.CLASS);

    if(individualIPTCaseQuery != null)
    {
      valueQuery.WHERE(individualIPTQuery.getIptCase().EQ(individualIPTCaseQuery.getId()));
      
      if (personQuery != null)
      {
        valueQuery.WHERE(personQuery.getIptRecipientDelegate().EQ(individualIPTCaseQuery.getPatient()));
        QueryUtil.joinTermAllpaths(valueQuery,dss.vector.solutions.Person.CLASS,personQuery, this.getTermRestrictions(), this.getLayer());
        
        this.addGeoDisplayLabelQuery(personQuery); 
      }
      
    }
    

    try
    {
      SelectableSQLInteger dobSel = (SelectableSQLInteger) valueQuery.getSelectableRef("age");

      String personTableAlias = personQuery.getTableAlias();
      String serviceDateCol = QueryUtil.getColumnName(individualIPTQuery.getMdClassIF(), IndividualIPT.SERVICEDATE);
      String dateOfBirthCol = QueryUtil.getColumnName(personQuery.getMdClassIF(), Person.DATEOFBIRTH);
      
      String sql = "EXTRACT(year from AGE( "+serviceDateCol+", " + personTableAlias + "."+dateOfBirthCol+"))";
      dobSel.setSQL(sql);
    }
    catch (QueryException e)
    {
      // Person.DOB not included in query.
    }

    this.addGeoDisplayLabelQuery(individualIPTQuery);
    
    QueryUtil.joinTermAllpaths(valueQuery,IndividualIPT.CLASS,individualIPTQuery, this.getTermRestrictions(), this.getLayer());  

    this.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, individualIPTCaseQuery.getDisease());
    
    valueQuery.AND(individualIPTCaseQuery.getDisease().EQ(this.getDisease()));
    
    return valueQuery;

  }

}
