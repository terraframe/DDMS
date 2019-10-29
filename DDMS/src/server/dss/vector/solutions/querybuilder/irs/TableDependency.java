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

public class TableDependency implements Reloadable
{
  private SQLProvider provider;
  
  private String tableAlias;
  
  private String sql;
  
  private Alias[] dependents;
  
  /**
   * A TableDependency that must be included before this one.
   */
  private TableDependency tableDependency;
  
  public TableDependency(SQLProvider provider, String tableAlias, Alias[] dependents, String sql)
  {
    this(provider, tableAlias, dependents, sql, null);
  }
  
  public String getTableAlias()
  {
    return tableAlias;
  }

  public TableDependency(SQLProvider provider, String tableAlias, Alias[] dependents, String sql, TableDependency tableDependency)
  {
    this.provider = provider;
    this.tableAlias = tableAlias;
    this.sql = sql;
    this.dependents = dependents;
    this.tableDependency = tableDependency;
  }
  
  public TableDependency getTableDependency()
  {
    return tableDependency;
  }

  /**
   * Checks to see if this table dependency's sql is needed by looking at the
   * list of aliases in the query and checking if this alias is mapped to the table.
   * @param requiredAliases
   * @return
   */
  public boolean isNeeded(Set<Alias> requiredAliases)
  {
    for(Alias dependent : dependents)
    {
      if(requiredAliases.contains(dependent))
      {
        return true;
      }
    }
    return false;
  }

  public String getSQL()
  {
    return sql;
  }
}
