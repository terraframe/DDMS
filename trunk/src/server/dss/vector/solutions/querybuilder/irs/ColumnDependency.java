package dss.vector.solutions.querybuilder.irs;

import org.apache.commons.lang.StringUtils;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB.View;

public class ColumnDependency implements Reloadable
{
  /**
   * The Independent column that requires other columns or tables.
   */
  private Alias alias;
  
//  private TableDependency[] tables;
  
  private String provider;
  
  private Alias[] dependencies;
//  private ColumnDependency[] columns;
  
  private View[] views;
  
  /**
   * Unique key for each ColumnDependency combination. Aliases
   * can be referenced by different SQLProviders so this key must
   * be used to differentiate between them.
   */
  private String key;
  
  public ColumnDependency(SQLProvider provider, Alias alias, Alias[] dependencies, View[] views)
  {
    this.alias = alias;
//    this.tables = tables;
    this.dependencies = dependencies;
//    this.columns = columns;
    this.views = views;
    
    this.provider = provider.getClass().getSimpleName();
    this.key = provider+"_"+alias.name();
    
    for(Alias dep : dependencies)
    {
      provider.getRequiredAliases().add(dep);
    }
    
    for(View view : views)
    {
      provider.getIrsQB().addRequiredView(view);
    }
  }
  
  public String getKey()
  {
    return key;
  }
  
  @Override
  public String toString()
  {
    String str = this.getKey()+" - ";
//    str += "Tables: ["+StringUtils.join(this.tables, ",")+"] ";
    str += "Dependencies: ["+StringUtils.join(this.dependencies, ",")+"] ";
    str += "Views: ["+StringUtils.join(this.views, ",")+"]";
    
    return str;
  }
  
  public Alias getAlias()
  {
    return alias;
  }
  
//  public TableDependency[] getTables()
//  {
//    return tables;
//  }
  
  public String getProvider()
  {
    return provider;
  }
  
  public Alias[] getDependencies()
  {
    return dependencies;
  }
  
//  public ColumnDependency[] getColumns()
//  {
//    return columns;
//  }
  
  public View[] getViews()
  {
    return views;
  }
}
