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
