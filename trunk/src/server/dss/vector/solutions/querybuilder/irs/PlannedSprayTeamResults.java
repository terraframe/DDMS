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

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

public class PlannedSprayTeamResults extends AbstractSQLProvider implements Reloadable
{
  public static final String CLASS = "dss.vector.solutions.querybuilder.irs.PlannedSprayTeamResults";
  
  public PlannedSprayTeamResults(IRSQB irsQB)
  {
    super(irsQB);
  }

  @Override
  public void loadDependencies()
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  protected View getView()
  {
    return View.PLANNED_TEAM_RESULTS;
  }

  @Override
  public String getSQL()
  {
    String sql = "";
    
    sql += "SELECT * FROM "+IRSQB.View.PLANNED_TEAM+" \n";
    sql += "UNION ALL \n";
    sql += "( \n";
    sql += "  SELECT * FROM "+IRSQB.View.PLANNED_TEAM_ROLLUP+" ptr  \n";
    sql += "  EXCEPT \n";
    sql += "  SELECt ptr.* FROM "+IRSQB.View.PLANNED_TEAM_ROLLUP+" ptr  \n";
    sql += "  INNER JOIN "+IRSQB.View.PLANNED_TEAM+" pt \n";
    sql += "  ON ptr."+Alias.SPRAY_TEAM.getAlias()+" = pt."+Alias.SPRAY_TEAM.getAlias()+"  \n";
    sql += "  AND ptr."+Alias.SPRAY_SEASON.getAlias()+" = pt."+Alias.SPRAY_SEASON.getAlias()+" \n";
    sql += "  AND ptr."+Alias.DISEASE.getAlias()+" = pt."+Alias.DISEASE.getAlias()+" \n";
//    sql += "  AND ptr."+Alias.TARGET_WEEK.getAlias()+" = pt."+Alias.TARGET_WEEK.getAlias()+" \n";
    sql += "  AND ptr."+Alias.SPRAY_TEAM.getAlias()+" = pt."+Alias.TARGET.getAlias()+" \n";
    sql += ") \n";
    
    return sql;
  }
}
