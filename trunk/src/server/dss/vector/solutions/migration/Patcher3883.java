package dss.vector.solutions.migration;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.general.MenuItem;
import dss.vector.solutions.general.MenuItemQuery;
import dss.vector.solutions.general.SystemURLQuery;

public class Patcher3883 implements DDMSPatchIF
{
  @Override
  public Integer getPatchVersion()
  {
    return 8936;
  }

  @Override
  public void doIt()
  {
    QueryFactory qf = new QueryFactory();
    SystemURLQuery suq = new SystemURLQuery(qf);
    MenuItemQuery miq = new MenuItemQuery(qf);
    
    suq.WHERE(suq.getKeyName().EQ("Report scheduler"));
    miq.WHERE(miq.getUrl().EQ(suq));
    
    OIterator<? extends MenuItem> it = miq.getIterator();
    try
    {
      for (MenuItem item : it)
      {
        item.delete();
      }
    }
    finally
    {
      it.close();
    }
  }

  @Override
  public void undoIt()
  {
    throw new UnsupportedOperationException();
  }
}
