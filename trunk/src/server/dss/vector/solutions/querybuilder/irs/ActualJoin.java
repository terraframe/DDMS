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

public class ActualJoin extends TargetJoin implements Reloadable
{

  public ActualJoin(IRSQB irsQB)
  {
    super(irsQB);
  }

  @Override
  public String FROM()
  {
    String sql = IRSQB.View.ALL_ACTUALS + " " + TargetJoin.ACTUAL_ALIAS + dateGroupJoin(TargetJoin.ACTUAL_ALIAS, Alias.SPRAY_DATE.getAlias());
    
    // As far as I can tell, there is no reason why we're grouping here. The query already does a group/sum at the end (if necessary).
    // This logic only serves to force an aggregation (or aggregate twice) when none is necessary.
    // #4010 - IRS QB and SP3 CRUD are wonky
//    sql += this.GROUP_BY();        
    
    return sql;
  }
  
  @Override
  public String postProcess(Alias alias, String sql)
  {
    // Override introduced in:
    // #4010 - IRS QB and SP3 CRUD are wonky
    
    return sql;
  }

}
