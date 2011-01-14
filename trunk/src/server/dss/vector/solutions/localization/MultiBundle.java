package dss.vector.solutions.localization;

import java.util.HashMap;
import java.util.Map;

import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.session.Session;

import dss.vector.solutions.util.LocaleDimension;

public class MultiBundle
{
  private Map<String, Bundle> bundles;
  
  public static final String BUNDLE_NAME = "MDSS";
  
  private MultiBundle()
  {
    bundles = new HashMap<String, Bundle>();
  }

  /**
   * Singleton is loaded on the first execution of MultiBundle.getInstance()
   * or the first access to Singleton.INSTANCE, not before.
   */
  private static class Singleton
  {
    public static final MultiBundle INSTANCE = new MultiBundle();
  }
  
  public static String get(String key)
  {
    synchronized (Singleton.INSTANCE)
    {
      Map<String, Bundle> cache = Singleton.INSTANCE.bundles;
      
      String locale = Session.getCurrentLocale().toString();
      MdDimensionDAOIF dimension = Session.getCurrentDimension();
      LocaleDimension ld = new LocaleDimension(locale, dimension);
      
      while (ld != null)
      {
//        Amazingly, containsKey doesn't appear to be working
        String lds = ld.toString();
        if (!cache.containsKey(lds))
        {
          cache.put(lds, new Bundle(BUNDLE_NAME, ld));
        }
        
//        if (bundle==null)
//        {
//          bundle = new Bundle(BUNDLE_NAME, ld);
//          cache.put(ld, bundle);
//        }
        
        Bundle bundle = cache.get(lds);
        String value = bundle.getValue(key);
        if (value!=null)
        {
          return value;
        }
        
        // This moves us up the bundle chain
        ld = ld.getParent();
        if (ld==null && dimension!=null)
        {
          // If dimension-specific bundles fail, start the loop over with the generic bundles
          ld = new LocaleDimension(locale);
        }
      }
      return "???_" + key + "_???";
    }
  }
  
  public static void reload()
  {
    synchronized (Singleton.INSTANCE)
    {
      Singleton.INSTANCE.bundles.clear();
    }
  }
}
