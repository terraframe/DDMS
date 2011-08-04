package dss.vector.solutions.initializer;

import org.eclipse.core.runtime.IProgressMonitor;

import com.runwaysdk.dataaccess.transaction.ITaskListener;

public class MonitorTaskListener implements ITaskListener
{
  private IProgressMonitor monitor;

  public MonitorTaskListener(IProgressMonitor monitor)
  {
    this.monitor = monitor;
  }

  @Override
  public void done(boolean status)
  {
    this.monitor.done();
  }

  @Override
  public void start()
  {
  }

  @Override
  public void taskProgress(int amount)
  {
    this.monitor.worked(amount);
  }

  @Override
  public void taskStart(String name, int totalAmount)
  {
    this.monitor.beginTask(Localizer.getMessage(name), totalAmount);
  }

}
