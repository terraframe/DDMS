package dss.vector.solutions.querybuilder.irs;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

public class SprayView extends AbstractSQLProvider implements Reloadable
{
  
  public SprayView(IRSQB irsQB)
  {
    super(irsQB);
  }
  
  @Override
  protected View getView()
  {
    return View.SPRAY_VIEW;
  }

  /**
   * Loads the dependencies for the last-most query. All primary
   * queries (activity and planned) sprays are loaded here.
   */
  @Override
  public void loadDependencies()
  {
    // load all selectables from spray activity and planned
    for(Alias activity : this.irsQB.getRequiredAlias(View.ALL_ACTUALS))
    {
      this.irsQB.addRequiredAlias(View.SPRAY_VIEW, activity);
    }

    // FIXME need other planned selectables? Planned area has the same
    // selectables as the others
    for(Alias activity : this.irsQB.getRequiredAlias(View.PLANNED_AREA))
    {
      this.irsQB.addRequiredAlias(View.SPRAY_VIEW, activity);
    }
    
    // Load the activity (optimize by not loading all the time?)
    this.irsQB.addRequiredView(IRSQB.View.ALL_ACTUALS);
    
    if(this.irsQB.needsAreaPlanned())
    {
      this.irsQB.addRequiredView(IRSQB.View.PLANNED_AREA);
    }
    
    if(this.irsQB.needsTeamsPlanned())
    {
      this.irsQB.addRequiredView(IRSQB.View.PLANNED_TEAM);
      this.irsQB.addRequiredView(IRSQB.View.PLANNED_TEAM_ROLLUP);
      this.irsQB.addRequiredView(IRSQB.View.PLANNED_TEAM_RESULTS);
    }
    
    if(this.irsQB.needsTeamsPlanned())
    {
      this.irsQB.addRequiredView(IRSQB.View.PLANNED_OPERATOR);
    }
  }

  @Override
  public String getSQL()
  {
    String sql = "";

    List<TargetJoin> joins = new LinkedList<TargetJoin>();

    if (this.irsQB.hasPlannedTargets())
    {
      if (this.irsQB.needsAreaPlanned())
      {
        joins.add(new AreaJoin(this.irsQB, true, true));
      }

      if (this.irsQB.needsTeamsPlanned())
      {
        joins.add(new TeamJoin(this.irsQB, true, true));
      }

      if (this.irsQB.needsOperatorPlanned())
      {
        joins.add(new OperatorJoin(this.irsQB, true, true));
      }
    }
    else
    {
      joins.add(new ActualJoin(this.irsQB));
    }

    int count = 0;
    for (TargetJoin join : joins)
    {
      sql += join.getSQL();

      if (count < joins.size() - 1)
      {
        sql += "\n UNION \n";
      }

      count++;
    }

    return sql;
  }


}
