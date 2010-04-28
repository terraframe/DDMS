package dss.vector.solutions.general;

import com.runwaysdk.query.QueryFactory;

public class MenuItemView extends MenuItemViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1731174277;
  
  public MenuItemView()
  {
    super();
  }
  
  /**
   * Returns all MenuItemViews for the given disease in the current session.
   * @return
   */
  public static MenuItemViewQuery getViewsForDisease()
  {
    QueryFactory f = new QueryFactory();
    MenuItemViewQuery q = new MenuItemViewQuery(f);
    
    return q;
  }
}
