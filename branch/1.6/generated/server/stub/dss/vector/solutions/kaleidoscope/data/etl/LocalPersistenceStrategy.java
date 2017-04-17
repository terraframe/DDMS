package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.business.Mutable;

public class LocalPersistenceStrategy extends LocalPersistenceStrategyBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1545536551;

  public LocalPersistenceStrategy()
  {
    super();
  }

  @Override
  public void handle(Mutable mutable)
  {
    mutable.apply();
  }

}
