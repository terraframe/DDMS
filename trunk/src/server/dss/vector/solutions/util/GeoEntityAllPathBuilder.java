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

import java.util.ArrayList;
import java.util.List;

import com.runwaysdk.constants.ComponentInfo;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;

public class GeoEntityAllPathBuilder
{
  public static int BATCH_SIZE = 1000;

  /**
   * @param args
   */
  @Request
  public static void main(String[] args)
  {
    rebuildAllPaths();

    CacheShutdown.shutdown();
  }

  private static void rebuildAllPaths()
  {
    MdBusiness mdBusiness = MdBusiness.getMdBusiness(AllPaths.CLASS);

    mdBusiness.deleteAllTableRecords();

    GeoEntity.buildAllPathsFast();

    //updateAllPaths();
  }

  public static void updateAllPaths()
  {
    QueryFactory qf = new QueryFactory();

    GeoEntityQuery geoEntityQ = new GeoEntityQuery(qf);

    ValueQuery q = new ValueQuery(qf);
    q.SELECT(geoEntityQ.getId(ComponentInfo.ID));

    OIterator<ValueObject> i = q.getIterator();

    try
    {
      int applyCount = 0;
      ArrayList<String> ids = new ArrayList<String>(BATCH_SIZE);

      for (ValueObject valueObject : i)
      {
        String childId = valueObject.getValue(ComponentInfo.ID);
        ids.add(childId);
        if (ids.size() >= BATCH_SIZE) {
        	applyCount = updateBatchOfPaths(ids, applyCount);
        	ids = new ArrayList<String>(BATCH_SIZE);
        }
      }
      if (ids.size() > 0) {
    	  applyCount = updateBatchOfPaths(ids, applyCount);
      }
    }
    finally
    {
      i.close();
    }
  }

  @Transaction
  public static int updateBatchOfPaths(List<String> ids, int applyCount) {
	  for (String id: ids) {
		  applyCount = GeoEntity.updateAllPathForGeoEntity(id, false, true, applyCount);
	  }
	  return applyCount;
  }
}
