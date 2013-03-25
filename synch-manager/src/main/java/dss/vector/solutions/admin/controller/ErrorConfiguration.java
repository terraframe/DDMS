package dss.vector.solutions.admin.controller;

import java.io.InvalidClassException;

import net.sf.ehcache.CacheException;

import com.runwaysdk.manager.controller.ConfigurationAdapter;
import com.runwaysdk.manager.general.Localizer;

public abstract class ErrorConfiguration extends ConfigurationAdapter
{

  @Override
  public void handleError(Throwable throwable)
  {
    if (throwable instanceof CacheException)
    {
      if (throwable.getCause() instanceof InvalidClassException)
      {
        throwable = new RuntimeException(Localizer.getMessage("CACHE_MISMATCH"), throwable);
      }
    }

    this.getWindow().error(throwable);
  }

}
