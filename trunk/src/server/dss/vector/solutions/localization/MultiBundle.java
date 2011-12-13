package dss.vector.solutions.localization;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.session.Session;
import java.util.Locale;

import dss.vector.solutions.util.LocaleDimension;

public class MultiBundle
{
  private Map<String, Bundle> bundles;
  
  private Set<String> masterKeySet;
  
  public static String BUNDLE_NAME = "MDSS";
  
  private MultiBundle()
  {
    bundles = new HashMap<String, Bundle>();
    masterKeySet = new TreeSet<String>();
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
    return get(key, Session.getCurrentLocale());
  }
  
  public static String get(String key, Locale locale)
  {
    synchronized (Singleton.INSTANCE)
    {
      String localeName = locale.toString();
      MdDimensionDAOIF dimension = Session.getCurrentDimension();
      LocaleDimension ld = new LocaleDimension(localeName, dimension);
      
      return smartGet(key, localeName, dimension, ld);
    }
  }

  /**
   * The heart of the MultiBundle, smartGet will traverse up the bundle hierarchy until it finds a value 
   * 
   * @param key
   * @param locale
   * @param dimension
   * @param ld
   * @return
   */
  private static String smartGet(String key, String locale, MdDimensionDAOIF dimension, LocaleDimension ld)
  {
    while (ld != null)
    {
//        Amazingly, containsKey doesn't appear to be working
      String value = getExactly(key, ld);
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
        dimension=null;
      }
    }
    return "???_" + key + "_???";
  }
  
  public static Map<String, String> getAll()
  {
    synchronized (Singleton.INSTANCE)
    {
      String locale = Session.getCurrentLocale().toString();
      MdDimensionDAOIF dimension = Session.getCurrentDimension();
      LocaleDimension ld = new LocaleDimension(locale, dimension);
      
      Map<String, String> map = new TreeMap<String, String>();
      for (String key : Singleton.INSTANCE.masterKeySet)
      {
        String value = smartGet(key, locale, dimension, ld);
        map.put(key, value);
      }
      
      return map;
    }
  }

  /**
   * Returns the value for this key-LocaleDimension pair exactly, without traversing up the hierarchy of other bundles
   * 
   * @param key
   * @param ld
   * @return
   */
  private static String getExactly(String key, LocaleDimension ld)
  {
    synchronized (Singleton.INSTANCE)
    {
      Map<String, Bundle> cache = Singleton.INSTANCE.bundles;
      String lds = ld.toString();
      if (!cache.containsKey(lds))
      {
        Bundle newBundle = new Bundle(BUNDLE_NAME, ld);
        cache.put(lds, newBundle);
        Singleton.INSTANCE.masterKeySet.addAll(newBundle.getKeySet());
      }
      
      Bundle bundle = cache.get(lds);
      String value = bundle.getValue(key);
      return value;
    }
  }
  
  public static void setBundle(String bundleName)
  {
    synchronized (Singleton.INSTANCE)
    {
      MultiBundle.BUNDLE_NAME = bundleName;
    }
  }
  
  public static void reload()
  {
    synchronized (Singleton.INSTANCE)
    {
      Singleton.INSTANCE.bundles.clear();
      Singleton.INSTANCE.masterKeySet.clear();
    }
  }
}
