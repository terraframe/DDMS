package dss.vector.solutions.migration;

import java.lang.reflect.InvocationTargetException;

import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;

import dss.vector.solutions.general.MenuItemQuery;
import dss.vector.solutions.general.SystemURLQuery;

public class TestPatcher3883
{
  public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
  {
    doInRequest();
  }
  
  @Request
  public static void doInRequest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
  {
    long menuItems = getCount();
    if (menuItems == 0)
    {
//      throw new UnsupportedOperationException("Bad count! Your database is not in the correct state to test this patch (it is already patched).");
    }
    
    ApplicationPatcher.main(new String[]{"-f 8935", "-t 8936"});
    
    menuItems = getCount();
    if (menuItems != 0)
    {
      throw new RuntimeException("Patch did not work.");
    }
  }
  
  public static long getCount()
  {
    QueryFactory qf = new QueryFactory();
    SystemURLQuery suq = new SystemURLQuery(qf);
    MenuItemQuery miq = new MenuItemQuery(qf);
    
    suq.WHERE(suq.getKeyName().EQ("Report scheduler"));
    miq.WHERE(miq.getUrl().EQ(suq));
    
    return miq.getCount();
  }
}
