package dss.vector.solutions.ontology;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

public class TermRootCache implements Reloadable
{
  private HashMap<MdAttributeDAOIF, Term[]> cache;

  private Set<Long>                         participatingThreadIDs;

  // Private constructor prevents instantiation from other classes
  private TermRootCache()
  {
    cache = new HashMap<MdAttributeDAOIF, Term[]>();
    participatingThreadIDs = new HashSet<Long>();
  }

  /**
   * Singleton is loaded on the first execution of TermRootCache.getInstance()
   * or the first access to Singleton.INSTANCE, not before.
   */
  private static class Singleton implements Reloadable
  {
    public static final TermRootCache INSTANCE = new TermRootCache();
  }

  private static HashMap<MdAttributeDAOIF, Term[]> getCache()
  {
    return Singleton.INSTANCE.cache;
  }

  public static Term[] getRoots(MdAttributeDAOIF mdAttribute)
  {
    synchronized (Singleton.INSTANCE)
    {
      boolean cachingEnabled = Singleton.INSTANCE.participatingThreadIDs.contains(Thread.currentThread().getId());
      
      // First handle the case with no caching
      if (!cachingEnabled)
      {
        return Term.getRootChildren(mdAttribute);
      }
      
      // If we get here, then caching is enabled for this thread.
      Term[] roots = getCache().get(mdAttribute);
      
      if (roots == null)
      {
        roots = Term.getRootChildren(mdAttribute);
        getCache().put(mdAttribute, roots);
      }
      return roots;
    }
  }

  /**
   * Enables caching for the current Thread. Formally, adds
   * Thread.currentThread().getId() to a list of threads participating in the
   * cache.
   */
  public static void start()
  {
    synchronized (Singleton.INSTANCE)
    {
      long id = Thread.currentThread().getId();
      Singleton.INSTANCE.participatingThreadIDs.add(id);
    }
  }

  /**
   * Stops caching for the current Thread. Formally, removes
   * Thread.currentThread().getId() from list of threads participating in the
   * cache. If there are no participating threads after removal, the cache is
   * cleared.
   */
  public static void stop()
  {
    synchronized (Singleton.INSTANCE)
    {
      long id = Thread.currentThread().getId();
      Singleton.INSTANCE.participatingThreadIDs.remove(id);
      
      if (Singleton.INSTANCE.participatingThreadIDs.isEmpty())
      {
        Singleton.INSTANCE.cache.clear();
      }
    }
  }
}
