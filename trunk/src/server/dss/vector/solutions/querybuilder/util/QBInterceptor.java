package dss.vector.solutions.querybuilder.util;

import java.util.HashMap;
import java.util.Map;

import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.AttributeReference;
import com.runwaysdk.query.Component;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.Visitor;
import com.runwaysdk.query.ValueQueryParser.InterceptorChain;
import com.runwaysdk.query.ValueQueryParser.ParseInterceptor;

import dss.vector.solutions.ontology.AllPaths;

/**
 * Visitor and Interceptor class to detect if a query is restricting by Term/Geo criteria.
 */
public class QBInterceptor extends Visitor implements ParseInterceptor, Reloadable
{
  private Map<String, ValueQuery> termValueQueries;
  private Map<String, Condition> geoConditions;
  
  private boolean hasTermCriteria;
  private boolean hasGeoCriteria;
  
  public QBInterceptor(ValueQuery valueQuery)
  {
    super(valueQuery);
    
    this.hasTermCriteria = false;
    this.hasGeoCriteria = false;
    this.termValueQueries = new HashMap<String, ValueQuery>();
    this.geoConditions = new HashMap<String, Condition>();
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
    }
    else if(this.hasGeoCriteria)
    {
      // Create a new ValueQuery that represents criteria on the Geo's AllPaths table
      this.geoConditions.put(entityAlias, condition);
      this.hasGeoCriteria = false;
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

}
