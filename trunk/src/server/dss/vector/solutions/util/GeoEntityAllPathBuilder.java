package dss.vector.solutions.util;

import com.terraframe.mojo.constants.ComponentInfo;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;

public class GeoEntityAllPathBuilder
{

  /**
   * @param args
   */
  @StartSession
  public static void main(String[] args)
  {
    rebuildAllPaths();
  }

  @Transaction
  private static void rebuildAllPaths()
  {
    MdBusiness mdBusiness = MdBusiness.getMdBusiness(AllPaths.CLASS);

    mdBusiness.deleteAllTableRecords();

    updateAllPaths();
  }

  @Transaction
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

      for (ValueObject valueObject : i)
      {
        String childId = valueObject.getValue(ComponentInfo.ID);

        applyCount = GeoEntity.updateAllPathForGeoEntity(childId, true, applyCount);
      }
    }
    finally
    {
      i.close();
    }
  }

}
