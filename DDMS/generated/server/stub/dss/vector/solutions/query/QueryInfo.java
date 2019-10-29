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
package dss.vector.solutions.query;

public class QueryInfo extends QueryInfoBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -723660218;
  
  private boolean hasThematicVariable;
  private boolean isThematicNumeric;
  
  public QueryInfo()
  {
    super();
    
    hasThematicVariable = false;
    isThematicNumeric = false;
  }
  
  public void setHasThematicVariable(boolean hasThematicVariable)
  {
    this.hasThematicVariable = hasThematicVariable; 
  }
  
  public void setIsThematicVariable(boolean isThematicVariable)
  {
    this.isThematicNumeric = isThematicVariable;
  }
  
  public boolean hasThematicVariable()
  {
    return this.hasThematicVariable;
  }
  
  public boolean isThematicNumeric()
  {
    return this.isThematicNumeric;
  }
  
}
