package dss.vector.solutions.admin.shapefile;

import com.runwaysdk.dataaccess.transaction.ITaskListener;

public class BuildLocatedInFacade
{

  public void buildLocatedIn(LocatedInBean bean, ITaskListener... listeners)
  {
    for (ITaskListener listener : listeners)
    {
      listener.taskStart("Building located in", 100);

      for (int i = 0; i < 100; i++)
      {
        listener.taskProgress(i);

        try
        {
          Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }

      listener.done(true);
    }
  }

}
