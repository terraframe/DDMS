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
package dss.vector.solutions.util;

import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;

import dss.vector.solutions.ServerContext;

/**
 * This is used in the patching process to delete database views and functions.
 */
public class DatabaseViewCleanerPatcher
{
  public static void main(String[] args)
  {
    try
    {
      new ServerContext(true).doCleanup();
    }
    finally
    {
      CacheShutdown.shutdown();
    }
  }
}
