package dss.vector.solutions.util;

import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;

import dss.vector.solutions.ServerContext;

/**
 * This is used in the patching process to delete database views and functions.
 */
public class DatabaseViewCleanerPatcher
{
  public static void main(String[] args)
  {
    try
    {
      new ServerContext(true).doCleanup();
    }
    finally
    {
      CacheShutdown.shutdown();
    }
  }
}
