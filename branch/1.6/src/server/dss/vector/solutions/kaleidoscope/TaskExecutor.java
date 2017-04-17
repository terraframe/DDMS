package dss.vector.solutions.kaleidoscope;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Wrapper around an ExecutorService to provide a means to perform non-time critical tasks. These tasks include deletion of database views for layers
 * that no longer exist, server side generation of thumbnail images, etc...
 * 
 * @author jsmethie
 */
public class TaskExecutor
{
  /**
   * Number of threads to use for task execution
   */
  private static final int       NUM_THREADS = 1;

  private static ExecutorService executor;

  static
  {
    executor = Executors.newFixedThreadPool(NUM_THREADS);
  }

  public static void addTask(Runnable task)
  {
    executor.submit(task);
  }

  public static void shutdown()
  {
    try
    {
      executor.shutdown();
      executor.awaitTermination(5, TimeUnit.MINUTES);
    }
    catch (InterruptedException e)
    {

      // Task Interrupted
    }
    finally
    {
      executor.shutdownNow();
    }
  }
}
