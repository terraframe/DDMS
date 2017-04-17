package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.generation.loader.Reloadable;

public abstract class PersistenceStrategy extends PersistenceStrategyBase implements Reloadable, PersistenceStrategyIF
{
  private static final long serialVersionUID = 769267976;
  
  public PersistenceStrategy()
  {
    super();
  }
  
}
