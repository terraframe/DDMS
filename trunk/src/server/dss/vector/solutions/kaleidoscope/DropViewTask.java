package dss.vector.solutions.kaleidoscope;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Request;

import dss.vector.solutions.util.DatabaseUtil;

public class DropViewTask implements Runnable, Reloadable
{
  private String viewName;

  public DropViewTask(String viewName)
  {
    this.viewName = viewName;
  }

  @Override
  @Request
  public void run()
  {
    DatabaseUtil.dropView(this.viewName, "", false);
  }
}
