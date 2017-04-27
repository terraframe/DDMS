package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.generation.loader.Reloadable;

public class ExclusionException extends RuntimeException implements Reloadable
{
  /**
   * 
   */
  private static final long serialVersionUID = -5764187692708424880L;

  public ExclusionException(String msg)
  {
    super(msg);
  }
}
