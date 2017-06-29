package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.dataaccess.BusinessDAO;
import com.runwaysdk.generation.loader.Reloadable;

public interface PersistenceStrategyIF extends Reloadable
{
  public void handle(BusinessDAO mutable);
}
