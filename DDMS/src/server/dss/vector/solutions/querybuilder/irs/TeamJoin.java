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

public class TeamJoin extends TargetJoin implements Reloadable
{
  
  public TeamJoin(IRSQB irsQB)
  {
    super(irsQB);
  }
  
  @Override
  public void loadDependencies()
  {
    // HEY! THIS CODE NEVER GETS EXECUTED.
    // The reason is because the TeamJoin is used in the SprayView and this only gets new instanced when the SQL gets generated,
    // which is far too late for the dependencies to be loaded. If you want to modify the dependencies do it in the SprayView.
    
    super.loadDependencies();
    
    // Load aliases that will be in the JOIN clause
    Alias[] joinAliases = new Alias[]{Alias.TARGET, Alias.SPRAY_SEASON, Alias.SPRAY_TEAM_DEFAULT_LOCALE, Alias.DISEASE};
    this.irsQB.addRequiredAlias(View.ALL_ACTUALS, joinAliases);
    this.irsQB.addRequiredAlias(View.PLANNED_TEAM, joinAliases);
  }
  
  public String getLevel()
  {
    if (hasActual)
    {
      return null; // Default behavior is to grab it from the actuals
    }
    else
    {
      return "2";
    }
  }
  
  public final String FROM()
  {
    String a = IRSQB.View.ALL_ACTUALS + " " + TargetJoin.ACTUAL_ALIAS;
    String p = IRSQB.View.PLANNED_TEAM_RESULTS + " " + TargetJoin.PLANNED_ALIAS;

//    if (hasActual && hasPlanned)
//    {
//      String sql = "";
//
//      sql += a + dateGroupJoin(TargetJoin.ACTUAL_ALIAS, Alias.SPRAY_DATE.getAlias()) + " FULL OUTER JOIN " + p + " \n";
//      
//      
//      // NOTE: old code for reference
////      sql += "ON extract(YEAR FROM "+TargetJoin.ACTUAL_ALIAS+"."+Alias.SPRAY_DATE.getAlias()+") " +
////      "= extract(YEAR FROM "+TargetJoin.PLANNED_ALIAS+"."+Alias.PLANNED_DATE.getAlias()+") \n";
//      sql += "ON " + TargetJoin.PLANNED_ALIAS + "." + Alias.TARGET_WEEK + " = "
//          + View.DATE_GROUPS.getView()+"."+Alias.DATEGROUP_EPIWEEK+"::"+Alias.TARGET_WEEK.getType() + " \n";
//      
//      // FIXED: joined based on spray season instead of year + week
//      sql += "AND "+TargetJoin.PLANNED_ALIAS + "." + Alias.SPRAY_SEASON + " = "
//          + TargetJoin.ACTUAL_ALIAS + "." + Alias.SPRAY_SEASON + " \n";
//
//      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.SPRAY_TEAM_DEFAULT_LOCALE + " = "
//          + TargetJoin.ACTUAL_ALIAS + "." + Alias.SPRAY_TEAM_DEFAULT_LOCALE + " \n";
//      
//      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.DISEASE + " = " + TargetJoin.ACTUAL_ALIAS
//          + "." + Alias.DISEASE + " \n";
//      
//      sql += new DateGroups(irsQB, this, View.PLANNED_TEAM_RESULTS, TargetJoin.PLANNED_ALIAS, Alias.PLANNED_DATE).getOverrideSQL();
//      
//      return sql;
//    }
    if(hasPlanned)
    {
      return p + new DateGroups(irsQB, this, View.PLANNED_TEAM_RESULTS, TargetJoin.PLANNED_ALIAS, Alias.PLANNED_DATE).getOverrideSQL();
    }
    else
    {
      return a + dateGroupJoin(TargetJoin.ACTUAL_ALIAS, Alias.SPRAY_DATE.getAlias()) + this.GROUP_BY();
    }
  }

}
