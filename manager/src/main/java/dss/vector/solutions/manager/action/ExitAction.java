package dss.vector.solutions.manager.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.ApplicationWindow;

import dss.vector.solutions.manager.Localizer;

public class ExitAction extends Action
{
  private ApplicationWindow window;

  public ExitAction(ApplicationWindow window)
  {
    this.window = window;
    this.setText(Localizer.getMessage("EXIT"));
  }

  public void run()
  {
    window.close();
  }
}