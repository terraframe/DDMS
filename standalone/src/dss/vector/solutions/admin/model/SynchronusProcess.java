package dss.vector.solutions.admin.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronusProcess
{
  private volatile boolean executing;

  private ReentrantLock    lock;

  private StringBuffer     buffer;

  public SynchronusProcess()
  {
    this.executing = false;
    this.buffer = new StringBuffer();
    this.lock = new ReentrantLock();
  }

  public boolean isExecuting()
  {
    return executing;
  }

  public void setExecuting(boolean executing)
  {
    lock.lock();

    try
    {
      this.executing = executing;
    }
    finally
    {
      lock.unlock();
    }
  }

  public String run(final String command) throws IOException
  {
    this.setExecuting(true);
    this.buffer = new StringBuffer();

    Runtime rt = Runtime.getRuntime();
    final Process pr = rt.exec(command);

    Thread thread = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        try
        {
          BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

          String line = null;

          while ( ( line = input.readLine() ) != null)
          {
            buffer.append(line);
          }

          pr.waitFor();

          setExecuting(false);
        }
        catch (Exception e)
        {
        }
      }
    });

    thread.setDaemon(true);
    thread.start();

    while (this.isExecuting())
    {
      Thread.yield();
    }

    return this.buffer.toString();
  }
}
