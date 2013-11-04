package dss.vector.solutions.querybuilder.irs;

import java.util.Set;

import com.runwaysdk.generation.loader.Reloadable;

public class TableDependency implements Reloadable
{
  private SQLProvider provider;
  
  private String tableAlias;
  
  private String sql;
  
  private Alias[] dependents;
  
  public TableDependency(SQLProvider provider, String tableAlias, Alias[] dependents, String sql)
  {
    this.provider = provider;
    this.tableAlias = tableAlias;
    this.sql = sql;
    this.dependents = dependents;
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
