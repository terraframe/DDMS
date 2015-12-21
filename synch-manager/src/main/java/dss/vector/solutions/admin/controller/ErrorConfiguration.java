package dss.vector.solutions.admin.controller;

import com.runwaysdk.manager.controller.ConfigurationAdapter;

public abstract class ErrorConfiguration extends ConfigurationAdapter
{

  @Override
  public void handleError(Throwable throwable)
  {
//    if (throwable instanceof CacheException)
//    {
//      if (throwable.getCause() instanceof InvalidClassException)
//      {
//        throwable = new RuntimeException(Localizer.getMessage("CACHE_MISMATCH"), throwable);
//      }
//    }

    this.getWindow().error(throwable);
  }

}
