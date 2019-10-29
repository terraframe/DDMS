/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.general;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.runwaysdk.generation.loader.Reloadable;

public class EpiCache implements Reloadable
{
  private HashMap<EpiDate, EpiWeek> weekCache;
  
  private HashMap<Date, EpiDate> dateCache;

  private Set<Long>                         participatingThreadIDs;

  // Private constructor prevents instantiation from other classes
  private EpiCache()
  {
    weekCache = new HashMap<EpiDate, EpiWeek>();
    dateCache = new HashMap<Date, EpiDate>();
    participatingThreadIDs = new HashSet<Long>();
  }

  /**
   * Singleton is loaded on the first execution of EpiWeekCache.getInstance()
   * or the first access to Singleton.INSTANCE, not before.
   */
  private static class Singleton implements Reloadable
  {
    public static final EpiCache INSTANCE = new EpiCache();
  }

  private static HashMap<EpiDate, EpiWeek> getWeekCache()
  {
    return Singleton.INSTANCE.weekCache;
  }
  
  private static HashMap<Date, EpiDate> getDateCache()
  {
    return Singleton.INSTANCE.dateCache;
  }
  
  public static EpiDate getDate(Date date)
  {
    synchronized (Singleton.INSTANCE)
    {
      boolean cachingEnabled = Singleton.INSTANCE.participatingThreadIDs.contains(Thread.currentThread().getId());
      
      // First handle the case with no caching
      if (!cachingEnabled)
      {
        return EpiDate.getEpiWeek(date);
      }
      
      // If we get here, then caching is enabled for this thread.
      EpiDate epiDate = getDateCache().get(date);
      
      if (epiDate == null)
      {
        epiDate = EpiDate.getEpiWeek(date);
        getDateCache().put(date, epiDate);
      }
      return epiDate;
    }
  }

  public static EpiWeek getWeek(Date date)
  {
    synchronized (Singleton.INSTANCE)
    {
      EpiDate epiDate = getDate(date);
      return getWeek(epiDate);
    }
  }
  
  public static EpiWeek getWeek(EpiDate date)
  {
    synchronized (Singleton.INSTANCE)
    {
      boolean cachingEnabled = Singleton.INSTANCE.participatingThreadIDs.contains(Thread.currentThread().getId());
      
      // First handle the case with no caching
      if (!cachingEnabled)
      {
        return EpiWeek.getEpiWeek(date);
      }
      
      // If we get here, then caching is enabled for this thread.
      EpiWeek week = getWeekCache().get(date);
      
      if (week == null)
      {
        week = EpiWeek.getEpiWeek(date);
        getWeekCache().put(date, week);
      }
      return week;
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
        Singleton.INSTANCE.weekCache.clear();
        Singleton.INSTANCE.dateCache.clear();
      }
    }
  }
}
