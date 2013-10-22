package dss.vector.solutions.querybuilder.irs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.runwaysdk.dataaccess.AttributeDoesNotExistException;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Component;
import com.runwaysdk.query.ComponentQuery;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.StatementPrimitive;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.ValueQueryParser.InterceptorChain;
import com.runwaysdk.query.ValueQueryParser.ParseInterceptor;
import com.runwaysdk.query.Visitor;

/**
 * Tracks different criteria on the IRS QB so that restrictions can be added to
 * the WITH clauses, thus reducing the overall amount of rows.
 */
public class CriteriaInterceptor extends Visitor implements ParseInterceptor, Reloadable
{

  private boolean hasLevel1;

  private boolean hasLevel2;

  private boolean hasLevel3;
  
  private boolean firstEncounter;
  
  private boolean levelDetected;
  
  private static final Pattern p = Pattern.compile("'(\\d)'");

  public CriteriaInterceptor(ComponentQuery query)
  {
    super(query);

    // All levels are allowed unless we reach the first counter of level criteria
    this.hasLevel1 = true;
    this.hasLevel2 = true;
    this.hasLevel3 = true;
    
    this.firstEncounter = true;
    
    this.levelDetected = false;
  }
  
  @Override
  public void visit(Component component)
  {
    if (this.hasVisitedComponent(component))
    {
      return;
    }
    else
    {
      if(this.levelDetected && component instanceof StatementPrimitive)
      {
        StatementPrimitive sp = (StatementPrimitive) component;
        String value = sp.getSQL();
        Matcher m = p.matcher(value);
        if(m.matches())
        {
          int level = Integer.parseInt(m.group(1));
          switch (level)
          {
            case 1:
              this.hasLevel1 = true;
              break;
            case 2:
              this.hasLevel2 = true;
              break;
            case 3:
              this.hasLevel3 = true;
              break;
          }
        }
      }
      
      this.addVisitedComponent(component);
    }
  }

  @Override
  public void interceptCondition(InterceptorChain chain, ValueQuery v, String entityAlias, Condition cond)
  {
    String sql = cond.getSQL();
    if(sql.contains("("+Alias.AGGREGATION_LEVEL.getAlias()+")"))
    {
      if(this.firstEncounter)
      {
        this.hasLevel1 = false;
        this.hasLevel2 = false;
        this.hasLevel3 = false;
        
        this.firstEncounter = false;
      }
      
      this.levelDetected = true;
      
      // visit the SelectablePrimitive (the criteria value)
      cond.accept(this);
      
      this.levelDetected = false;
    }

    chain.interceptCondition(v, entityAlias, cond);
  }

  @Override
  public void interceptSelectable(InterceptorChain chain, ValueQuery valueQuery, String entityAlias,
      Selectable selectable, String attributeName, AttributeDoesNotExistException t)
  {
    chain.interceptSelectable(valueQuery, entityAlias, selectable, attributeName, t);
  }

  public boolean hasLevel1()
  {
    return hasLevel1;
  }

  public boolean hasLevel2()
  {
    return hasLevel2;
  }

  public boolean hasLevel3()
  {
    return hasLevel3;
  }

}
