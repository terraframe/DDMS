package dss.vector.solutions.util;

import dss.vector.solutions.ServerContext;

/**
 * This is used in the patching process to delete database views and functions.
 */
public class DatabaseViewCleanerPatcher
{
  public static void main(String[] args)
  {
    new ServerContext(true).doCleanup();
  }
}
