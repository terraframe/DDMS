package dss.vector.solutions.geo;

import java.util.Map;

import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.AmbigiousGeoEntityException;
import dss.vector.solutions.UnknownGeoEntityException;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.util.GeoEntityAllPathBuilder;

public class AllPaths extends AllPathsBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245657919728L;

  public AllPaths()
  {
    super();
  }

  public static void regeneratePaths()
  {
    GeoEntityAllPathBuilder.main(new String[]{});
  }


  /**
   * Finds a geo entity match.
   * @param parentGeoEntityMap Key is the geo type, value is the geo name.
   * @param childGeoEntityType geo entity type of the child.
   * @param childGeoEntityName geo entity name of the child.
   */
  public static GeoEntity search(Map<String, String> parentGeoEntityMap, String childGeoEntityType, String childGeoEntityName)
  {
    MdBusiness childMdBusiness = MdBusiness.getMdBusiness(childGeoEntityType);

    QueryFactory qf = new QueryFactory();

    GeoEntityQuery childGeoEntityQuery = new GeoEntityQuery(qf);
    childGeoEntityQuery.
      WHERE(childGeoEntityQuery.getType().EQ(childGeoEntityType).
      AND(childGeoEntityQuery.getEntityName().EQ(childGeoEntityName)));

    ValueQuery geoEntityIdQuery = new ValueQuery(qf);
    geoEntityIdQuery.SELECT(childGeoEntityQuery.getId("child_id"));

    for (String parentEntityType : parentGeoEntityMap.keySet())
    {
      AllPathsQuery allPathsQuery = new AllPathsQuery(qf);
      MdBusiness parentMdBusiness = MdBusiness.getMdBusiness(parentEntityType);

      GeoEntityQuery parentGeoEntityQuery = new GeoEntityQuery(qf);
      parentGeoEntityQuery.
        WHERE(parentGeoEntityQuery.getType().EQ(parentEntityType).
        AND(parentGeoEntityQuery.getEntityName().EQ(parentGeoEntityMap.get(parentEntityType))));

      geoEntityIdQuery.
        AND(allPathsQuery.getParentUniversal().EQ(parentMdBusiness).
        AND(allPathsQuery.getParentGeoEntity().EQ(parentGeoEntityQuery.getId())).
        AND(allPathsQuery.getChildUniversal().EQ(childMdBusiness)).
        AND(allPathsQuery.getChildGeoEntity().EQ(childGeoEntityQuery.getId())));
    }

    OIterator<ValueObject> iterator = geoEntityIdQuery.getIterator();

    GeoEntity returnGeoEntity;

    try
    {
      if (iterator.hasNext())
      {
        returnGeoEntity = GeoEntity.get(iterator.next().getValue("child_id"));

        if (iterator.hasNext())
        {
          String msg = "Geo Entity ending with [" + childGeoEntityName + "] is ambiguous (It has more than one possible solution)";
          AmbigiousGeoEntityException e = new AmbigiousGeoEntityException(msg);
          e.setEntityName(childGeoEntityName);
          e.apply();
        }
      }
      else
      {
        String msg = "Unknown Geo Entity [" + childGeoEntityName + "]";
        UnknownGeoEntityException e =  new UnknownGeoEntityException(msg);
        e.setEntityName(childGeoEntityName);
        e.apply();
        throw e;
      }
    }
    finally
    {
      iterator.close();
    }
    return returnGeoEntity;
  }
}
