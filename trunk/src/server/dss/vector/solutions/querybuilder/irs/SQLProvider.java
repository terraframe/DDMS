package dss.vector.solutions.querybuilder.irs;

import java.util.Set;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;

public interface SQLProvider extends Reloadable
{
  public static final String FLOAT = "float";
  public static final String TEXT = "text";
  public static final String VARCHAR = "varchar";
  public static final String DATE = "date";
  public static final String DATETIME = "timestamp without time zone";
  
  /**
   * Subclasses must override this to customize their columns.
   */
  public void loadDependencies();
  
  public Set<Alias> getRequiredAliases();
  
  public String getSQL();
  
  public IRSQB getIrsQB();
}
