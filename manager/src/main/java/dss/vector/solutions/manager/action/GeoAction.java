package dss.vector.solutions.manager.action;

import java.lang.Thread.UncaughtExceptionHandler;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import dss.vector.solutions.manager.Localizer;
import dss.vector.solutions.manager.ManagerContextBean;

public class GeoAction extends Action implements UncaughtExceptionHandler
{
  private ManagerContextBean context;

  public GeoAction(ManagerContextBean context)
  {
    super(Localizer.getMessage("GIS"), ImageDescriptor.createFromURL(Object.class.getResource("/icons/globe.png")));
    this.setToolTipText(Localizer.getMessage("GIS"));

    this.context = context;
  }

  @Override
  public void run()
  {
    if (context.hasApplication())
    {
    }
  }

  @Override
  public void uncaughtException(Thread thread, Throwable t)
  {
    t.printStackTrace();
  }
}
