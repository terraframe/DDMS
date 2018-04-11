package dss.vector.solutions.manager;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;

public class ToggleMenuManager extends MenuManager
{

  public ToggleMenuManager()
  {
    super();
  }

  public ToggleMenuManager(String text, ImageDescriptor image, String id)
  {
    super(text, image, id);
  }

  public ToggleMenuManager(String text, String id)
  {
    super(text, id);
  }

  public ToggleMenuManager(String text)
  {
    super(text);
  }

  public void setEnabled(boolean enabled)
  {
    IContributionItem[] items = this.getItems();

    for (IContributionItem item : items)
    {
      if (item instanceof ActionContributionItem)
      {
        ( (ActionContributionItem) item ).getAction().setEnabled(enabled);
      }
    }
  }

}
