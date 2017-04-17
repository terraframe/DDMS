package dss.vector.solutions.kaleidoscope;

import com.runwaysdk.session.Request;

import dss.vector.solutions.util.DatabaseUtil;

public class DropViewTask implements Runnable
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
