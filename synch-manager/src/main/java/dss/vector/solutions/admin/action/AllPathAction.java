package dss.vector.solutions.admin.action;

import org.eclipse.jface.action.Action;

import com.runwaysdk.manager.general.Localizer;

import dss.vector.solutions.admin.MDSSModule;

public class AllPathAction extends Action
{
  private MDSSModule module;

  public AllPathAction(MDSSModule module)
  {
    super(Localizer.getMessage("REBUILD_ALL_PATH_TABLES"));

    this.module = module;
  }

  @Override
  public void run()
  {
    this.module.rebuildAllPathTables();
  }
}
