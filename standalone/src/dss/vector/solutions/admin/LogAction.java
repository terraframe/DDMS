package dss.vector.solutions.admin;

import org.eclipse.jface.action.Action;

import com.runwaysdk.logging.LogLevel;

import dss.vector.solutions.admin.controller.IModuleController;

public class LogAction extends Action
{
  private LogLevel          level;

  private IModuleController controller;

  public LogAction(String label, LogLevel level, IModuleController controller)
  {
    this.setText(label);

    this.level = level;
    this.controller = controller;
  }

  @Override
  public void run()
  {
    controller.setLogLevel(level);
  }

}
