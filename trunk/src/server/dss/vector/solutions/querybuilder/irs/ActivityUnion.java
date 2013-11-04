package dss.vector.solutions.querybuilder.irs;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

/**
 * Creates a union across all applicable spray levels.
 */
public class ActivityUnion extends AbstractSQLProvider implements Reloadable
{

  private List<SQLProvider> union;
  
  public ActivityUnion(IRSQB irsQB)
  {
    super(irsQB);
    
    boolean level1 = irsQB.hasLevel1();
    boolean level2 = irsQB.hasLevel2();
    boolean level3 = irsQB.hasLevel3();
    
    union = new LinkedList<SQLProvider>();
    if(level1)
    {
      SQLProvider activity = new ActualOperatorSprayTarget(irsQB);
      
      union.add(activity);
    }
    
    if(level2)
    {
      SQLProvider activity = new ActualTeamSprayTarget(irsQB);
      
      union.add(activity);
    }

    if(level3)
    {
      SQLProvider activity = new ActualZoneSprayTarget(irsQB);
      
      union.add(activity);
    }
  }
  
  @Override
  protected View getView()
  {
    return View.ALL_ACTUALS;
  }

  @Override
  public void loadDependencies()
  {
    for(SQLProvider activityLevel : union)
    {
      activityLevel.loadDependencies();
    }
  }

  @Override
  public String getSQL()
  {
    String sql = "";
    int count = 0;
    for (SQLProvider activityLevel : union)
    {
      sql += activityLevel.getSQL();

      if (count < union.size() - 1)
      {
        sql += "\n UNION ALL \n";
      }

      count++;
    }

    return sql;
  }


}
