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

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.MdssLog;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

/**
 * Represents spray, both planned targets and spray activity.
 */
public abstract class AbstractSprayProvider extends AbstractSQLProvider implements Reloadable
{

  public AbstractSprayProvider(IRSQB irsQB)
  {
    super(irsQB);
  }

  @Override
  public void loadDependencies()
  {
    Set<Alias> selectAliases = this.irsQB.getSelectAliases();
    View view = this.getView();
    
    // operator, leader, supervisor
    if(selectAliases.contains(Alias.SPRAY_OPERATOR_DEFAULT_LOCALE) || selectAliases.contains(Alias.SPRAY_OPERATOR_SEX))
    {
      this.irsQB.addRequiredAlias(view, Alias.SPRAY_OPERATOR_PERSON);
    }
  
    if(selectAliases.contains(Alias.SPRAY_LEADER_DEFAULT_LOCALE) || selectAliases.contains(Alias.SPRAY_LEADER_SEX))
    {
      this.irsQB.addRequiredAlias(view, Alias.SPRAY_LEADER_PERSON);
    }
  
    if(selectAliases.contains(Alias.ZONE_SUPERVISOR_DEFAULT_LOCALE) || selectAliases.contains(Alias.ZONE_SUPERVISOR_SEX))
    {
      this.irsQB.addRequiredAlias(view, Alias.ZONE_SUPERVISOR_PERSON);
    }
  }
  
  public List<TableDependency> loadTableDependencies()
  {
    return new LinkedList<TableDependency>();
  }
  
  protected void preProcess(Alias alias)
  {
    // do nothing by default
  }
  
  public String postProcess(Alias alias, String sql)
  {
    // return the original sql by default
    return sql;
  }
  
  /**
   * Generates the SQL for this UNION component and optimizes the SELECT clause
   * to include only what's needed.
   */
  public final String SELECT()
  {
    Set<Alias> requiredAliases = this.getRequiredAliases();

    String sql = "";
    
    Class<? extends SQLProvider> klass = this.getClass();
    List<String> columns = new LinkedList<String>();
    for (Alias alias : requiredAliases)
    {
      // not every alias has a method representing it (eg, custom
      // pass-through and calculations), so
      // only invoke the method if one exists.
      String methodName = alias.getMethod();
      if (methodName != null)
      {
        try
        {
          try
          {
            this.preProcess(alias);
            Method m = klass.getMethod(methodName, Alias.class);
            Object o = m.invoke(this, alias);
            if(o != null && o instanceof String)
            {
              String colSQL = this.postProcess(alias, (String)o);
              columns.add(colSQL);
            }
          }
          catch(NoSuchMethodException e)
          {
            // this is normal. Some aliases are defined on planned targetes but not
            // spray activity and visa-versa. This should be cleaned up, though.
          }
        }
        catch (Throwable t)
        {
          String msg = "IRS QB: Unable to process alias [" + alias.getAlias() + "] with method ["
              + methodName + "] on class [" + klass.getName() + "].";

          MdssLog.error(msg, t);

          throw new ProgrammingErrorException(t);
        }
      }
    }

    sql += StringUtils.join(columns, COLUMN_SUFFIX) + " \n";

    return sql;
  }
  
  /**
   * Generates the SQL for this UNION component and optimizes the FROM clause
   * to include only what's needed.
   */
  public String FROM()
  {
    Set<Alias> requiredAliases = this.getRequiredAliases();
    
    String sql = "";
    Set<String> addedTables = new HashSet<String>();
    List<TableDependency> tables = this.loadTableDependencies();
    if (tables.size() > 0)
    {
      for (TableDependency table : tables)
      {
        if (table.isNeeded(requiredAliases))
        {
          // Check if this TableDependency requires another one prior for JOINs.
          TableDependency prior = table.getTableDependency();
          if(prior != null && !addedTables.contains(prior.getTableAlias()))
          {
            sql += prior.getSQL();
            addedTables.add(prior.getTableAlias());
          }
          
          sql += table.getSQL();
          addedTables.add(table.getTableAlias());
        }
      }
    }
    
    return sql;
  }
}
