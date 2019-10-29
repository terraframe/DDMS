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
import com.runwaysdk.query.LeftJoinEq;

public class IRSSpoofJoin extends LeftJoinEq implements Reloadable
{
  public IRSSpoofJoin(String attribute1, String tableName1, String tableAlias1, String attribute2, String tableName2, String tableAlias2)
  {
    super(attribute1, tableName1, tableAlias1, attribute2, tableName2, tableAlias2);
  }
  
  
  
  @Override
  protected String leftSideSQL()
  {
    return "";
  }
  
  @Override
  protected String getOperator()
  {
    return "";
  }
  
  @Override
  public String getSQL()
  {
    return "(SELECT NULL) spoofTable";
  }
}
