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
 * Visitor and Interceptor class to detect if a query is restricting by Term criteria.
 */
public class TermInterceptor extends Visitor implements ParseInterceptor, Reloadable
{
  private Map<String, ValueQuery> termValueQueries;
  
  private boolean hasTermCriteria;
  
  public TermInterceptor(ValueQuery valueQuery)
  {
    super(valueQuery);
    
    this.hasTermCriteria = false;
    this.termValueQueries = new HashMap<String, ValueQuery>();
  }
  
  public ValueQuery getTermValueQuery(String entityAlias)
  {
    return this.termValueQueries.get(entityAlias);
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
    }
    
    super.visit(attribute);
  }

}
