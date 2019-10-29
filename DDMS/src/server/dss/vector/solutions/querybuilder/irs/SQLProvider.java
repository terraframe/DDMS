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

import java.util.Set;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;

public interface SQLProvider extends Reloadable
{
  // Use float in case aggregation function is used
  public static final String FLOAT = "float";
//  public static final String INTEGER = "integer";
  public static final String TEXT = "text";
  public static final String VARCHAR = "varchar";
  public static final String DATE = "date";
  public static final String DATETIME = "timestamp without time zone";
  public static final String INTEGER = "integer";
  
  /**
   * Subclasses must override this to customize their columns.
   */
  public void loadDependencies();
  
  public Set<Alias> getRequiredAliases();
  
  public String getSQL();
  
  public IRSQB getIrsQB();
}
