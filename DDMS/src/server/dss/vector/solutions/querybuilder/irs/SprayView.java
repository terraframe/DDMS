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
package dss.vector.solutions.querybuilder.irs;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

public class SprayView extends AbstractSQLProvider implements Reloadable
{
  public static final String CLASS = "dss.vector.solutions.querybuilder.irs.SprayView";
  
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
    
    if (this.irsQB.hasPlannedTargets())
    {
      this.getIrsQB().addRequiredAlias(View.SPRAY_VIEW, Alias.AGGREGATION_LEVEL);
    }
    
    if(this.irsQB.needsAreaPlanned())
    {
      this.irsQB.addRequiredView(IRSQB.View.PLANNED_AREA);
    }
    
    if(this.irsQB.needsTeamsPlanned())
    {
      this.irsQB.addRequiredView(IRSQB.View.PLANNED_TEAM);
      this.irsQB.addRequiredView(IRSQB.View.PLANNED_TEAM_ROLLUP);
      this.irsQB.addRequiredView(IRSQB.View.PLANNED_TEAM_RESULTS);
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
        joins.add(new AreaJoin(this.irsQB));
      }

      if (this.irsQB.needsTeamsPlanned())
      {
        joins.add(new TeamJoin(this.irsQB));
      }

      if (this.irsQB.needsOperatorPlanned())
      {
        joins.add(new OperatorJoin(this.irsQB));
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
