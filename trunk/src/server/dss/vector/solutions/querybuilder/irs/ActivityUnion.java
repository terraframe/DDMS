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

/**
 * Creates a union across all applicable spray levels.
 */
public class ActivityUnion extends AbstractSQLProvider implements Reloadable
{
  public static final String CLASS = "dss.vector.solutions.querybuilder.irs.ActivityUnion";

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
