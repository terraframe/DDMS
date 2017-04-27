package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.generation.loader.Reloadable;

public interface PersistenceStrategyIF extends Reloadable
{
  public void handle(Mutable mutable);
}
