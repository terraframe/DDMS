package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.dataaccess.BusinessDAO;

public class LocalPersistenceStrategy extends LocalPersistenceStrategyBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1545536551;

  public LocalPersistenceStrategy()
  {
    super();
  }

  @Override
  public void handle(BusinessDAO mutable)
  {
    mutable.apply();
  }

}
