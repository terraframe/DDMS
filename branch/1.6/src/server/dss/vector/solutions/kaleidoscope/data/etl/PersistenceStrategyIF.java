package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.business.Mutable;

public interface PersistenceStrategyIF
{
  public void handle(Mutable mutable);
}
