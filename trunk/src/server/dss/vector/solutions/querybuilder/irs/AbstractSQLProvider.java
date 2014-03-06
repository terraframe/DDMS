package dss.vector.solutions.querybuilder.irs;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

public abstract class AbstractSQLProvider implements SQLProvider
{
  public static final String COLUMN_SUFFIX = ", \n";
  
  public static final String EMPTY = "''";

  public static final String AS    = "AS";

  public static final String NULL  = "NULL";

  public static final String ZERO  = "0";
  
  /**
   * The owning IRSQuery instance of this union. The variable is protected for
   * easy reading by the subclasses.
   */
  protected IRSQB         irsQB;
  
  protected boolean hasActual;
  protected boolean hasPlanned;
  
  public AbstractSQLProvider(IRSQB irsQB)
  {
    this.irsQB = irsQB;
    

    /*
     * The logic for detecting activity and planned targets varies depending
     * on whether this is a standalone query or one used to join activity and
     * planned targets.
     */
    if(this.irsQB.hasActivity() && this.irsQB.hasPlanned())
    {
      this.hasActual = this.irsQB.getAggregationType() == null;
      this.hasPlanned = this.irsQB.getAggregationType() != null;
    }
    else
    {
      this.hasActual = this.irsQB.hasActivity();
      this.hasPlanned = this.irsQB.hasPlanned();
    }
  }
  
  public String getSQL()
  {
    String sql = "";
    
    // print any helpful comments
    for(String comment : COMMENTS())
    {
      sql += "-- "+comment+" \n";
    }
    
    sql += "SELECT \n"+this.SELECT()+ " \n";
    sql += "FROM \n"+this.FROM()+" \n";
    
    String where = this.WHERE();
    if(where != null && where.length() > 0)
    {
      sql += "WHERE \n" + where;
    }
    
    return sql;
  }
  
  protected List<String> COMMENTS()
  {
    List<String> comments = new LinkedList<String>();
    comments.add("GENERATED BY: "+this.getClass().getSimpleName());
    return comments;
  }
  
  public String SELECT()
  {
    return "";
  }
  
  public String FROM()
  {
    return "";
  }
  
  public String WHERE()
  {
    return null;
  }
  
  /**
   * Returns the view that maps to this SQlProvider.
   * @return
   */
  protected abstract View getView();

  @Override
  public final Set<Alias> getRequiredAliases()
  {
    return this.irsQB.getRequiredAlias(this.getView());
  }
  
  public IRSQB getIrsQB()
  {
    return irsQB;
  }
  
  public String set(String table, Alias column, Alias alias)
  {
    return set(table, column.getAlias(), alias);
  }

  public String set(String table, String column, Alias alias)
  {
    return set(table + "." + column, alias);
  }
  
  public String column(String alias, String column)
  {
    return alias + "." + column;
  }
  
  public String set(String value, Alias alias)
  {
    String type = alias.getType();
    return value + " " + (type != null ? "::"+type+" ":"") + AS + " " + alias;
  }

  public String setEmpty(Alias alias)
  {
    return set(EMPTY, alias);
  }

  public String setNULL(Alias alias)
  {
    return set(NULL, alias);
  }

  public String setZero(Alias alias)
  {
    return set(ZERO, alias);
  }
}
