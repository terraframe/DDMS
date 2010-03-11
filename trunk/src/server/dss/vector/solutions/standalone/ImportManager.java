package dss.vector.solutions.standalone;

import java.util.Random;

import javax.swing.SwingWorker;

public class ImportManager extends SwingWorker<Void, Void>
{
  /*
   * Main task. Executed in background thread.
   */
  @Override
  public Void doInBackground()
  {
    Random random = new Random();
    int progress = 0;
    // Initialize progress property.
    setProgress(0);
    while (progress < 100)
    {
      // Sleep for up to one second.
      try
      {
        Thread.sleep(random.nextInt(1000));
      }
      catch (InterruptedException ignore)
      {
      }
      // Make random progress.
      progress += random.nextInt(10);
      setProgress(Math.min(progress, 100));
    }
    
    return null;
  }

  /*
   * Executed in event dispatching thread
   */
  @Override
  public void done()
  {
    setProgress(100);
  }
}
