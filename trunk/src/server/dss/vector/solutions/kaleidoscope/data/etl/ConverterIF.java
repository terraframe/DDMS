package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.business.Transient;
import com.runwaysdk.generation.loader.Reloadable;

public interface ConverterIF extends Reloadable
{
  public void create(Transient source);
}
