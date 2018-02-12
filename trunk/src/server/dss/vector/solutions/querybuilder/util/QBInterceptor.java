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
package dss.vector.solutions.querybuilder.util;

import java.util.HashMap;
import java.util.Map;

import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.dataaccess.AttributeDoesNotExistException;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.AttributeReference;
import com.runwaysdk.query.Component;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.ValueQueryParser.InterceptorChain;
import com.runwaysdk.query.ValueQueryParser.ParseInterceptor;
import com.runwaysdk.query.Visitor;

import dss.vector.solutions.ontology.AllPaths;

/**
 * Visitor and Interceptor class to detect if a query is restricting by Term/Geo criteria.
 */
public class QBInterceptor extends Visitor implements ParseInterceptor, Reloadable
{
  private Map<String, ValueQuery> termValueQueries;
  private Map<String, Condition> geoConditions;
  
  /**
   * Flag that denotes if term criteria is being processed. This should be checked between
   * calls to visit(Attribute) and interceptCondition() because the value will be toggled to false
   * after the latter call.
   */
  private boolean hasTermCriteria;
  
  /**
   * 
   */
  private boolean hasGeoCriteria;
  
  private int termCriteriaProcessed;
  
  private int geoCriteriaProcessed;
  
  public QBInterceptor(ValueQuery valueQuery)
  {
    super(valueQuery);
    
    this.hasTermCriteria = false;
    this.hasGeoCriteria = false;
    this.termValueQueries = new HashMap<String, ValueQuery>();
    this.geoConditions = new HashMap<String, Condition>();
    this.termCriteriaProcessed = 0;
    this.geoCriteriaProcessed = 0;
  }
  
  public int getGeoCriteriaProcessed()
  {
    return geoCriteriaProcessed;
  }
  
  public int getTermCriteriaProcessed()
  {
    return termCriteriaProcessed;
  }
  
  protected boolean hasTermCriteria()
  {
    return this.hasTermCriteria;
  }
  
  protected boolean hasGeoCriteria()
  {
    return this.hasGeoCriteria;
  }
  
  public ValueQuery getTermValueQuery(String entityAlias)
  {
    return this.termValueQueries.get(entityAlias);
  }

  public Condition getGeoCondition(String entityAlias)
  {
    return this.geoConditions.get(entityAlias);
  }

  @Override
  public void interceptCondition(InterceptorChain chain, ValueQuery valueQuery, String entityAlias, Condition condition)
  {
    condition.accept(this);
    
    if(this.hasTermCriteria)
    {
      // Create a new ValueQuery that represents criteria on the Term's AllPaths table
      ValueQuery termVQ = new ValueQuery(this.getComponentQuery().getQueryFactory());
      termVQ.WHERE(condition);
      
      this.termValueQueries.put(entityAlias, termVQ);
      this.hasTermCriteria = false;
      this.termCriteriaProcessed++;
    }
    else if(this.hasGeoCriteria)
    {
      // Create a new ValueQuery that represents criteria on the Geo's AllPaths table
      this.geoConditions.put(entityAlias, condition);
      this.hasGeoCriteria = false;
      this.geoCriteriaProcessed++;
    }
    else
    {
      chain.interceptCondition(valueQuery, entityAlias, condition);
    }
  }
  
  public void visit(Component component)
  {
    if (this.hasVisitedComponent(component))
    {
      return;
    }
    else
    {
      this.addVisitedComponent(component);
    }
  }

  public void visit(Attribute attribute)
  {
    if (attribute instanceof AttributeReference && attribute.getRootQuery() instanceof BusinessQuery)
    {
      BusinessQuery query = (BusinessQuery) attribute.getRootQuery();
      if (attribute._getAttributeName().equals(AllPaths.PARENTTERM)
          && query.getType().equals(AllPaths.CLASS))
      {
        this.hasTermCriteria = true;
      }
      else if (attribute._getAttributeName().equals(dss.vector.solutions.geo.AllPaths.PARENTGEOENTITY)
          && query.getType().equals(dss.vector.solutions.geo.AllPaths.CLASS))
      {
        this.hasGeoCriteria = true;
      }
    }
    
    super.visit(attribute);
  }

  @Override
  public void interceptSelectable(InterceptorChain chain, ValueQuery valueQuery, String entityAlias,
      Selectable selectable, String attributeName, AttributeDoesNotExistException t)
  {
    chain.interceptSelectable(valueQuery, entityAlias, selectable, attributeName, t);
  }

}
