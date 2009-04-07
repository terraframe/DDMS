package dss.vector.solutions.entomology;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.PermanentWaterBody;
import dss.vector.solutions.geo.generated.Trap;

public class MosquitoCollectionPoint extends MosquitoCollectionPointBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288154522L;

  public MosquitoCollectionPoint()
  {
    super();
  }

  @Override
  public void apply()
  {
    validateGeoEntity();

    super.apply();
  }

  @Override
  public void validateGeoEntity()
  {
    super.validateGeoEntity();

    GeoEntity geoEntity = this.getGeoEntity();

    if (! ( geoEntity instanceof Trap || geoEntity instanceof PermanentWaterBody ))
    {
      String msg = "The geoEntity of a mosquito collection must be a fixed trap or a permenent water body";

      InvalidMosquitoCollectionPointGeoEntityException e = new InvalidMosquitoCollectionPointGeoEntityException(
          msg);
      e.setGeoId(this.getGeoEntity().getGeoId());
      e.apply();

      throw e;
    }
  }

  public static MorphologicalSpecieGroupView[] searchByGeoEntityAndDate(GeoEntity geoEntity,
      Date startDate, Date endDate)
  {
    List<MorphologicalSpecieGroupView> list = new LinkedList<MorphologicalSpecieGroupView>();

    QueryFactory factory = new QueryFactory();
    MosquitoCollectionPointQuery collectionQuery = new MosquitoCollectionPointQuery(factory);
    MorphologicalSpecieGroupQuery specieQuery = new MorphologicalSpecieGroupQuery(factory);

    collectionQuery.WHERE(collectionQuery.getGeoEntity().EQ(geoEntity));
    collectionQuery.AND(collectionQuery.getDateCollected().GE(startDate));
    collectionQuery.AND(collectionQuery.getDateCollected().LE(endDate));

    specieQuery.WHERE(specieQuery.getCollection().EQ(collectionQuery));

    OIterator<? extends MorphologicalSpecieGroup> iterator = specieQuery.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        list.add(iterator.next().getView());
      }
    }
    finally
    {
      iterator.close();
    }

    return list.toArray(new MorphologicalSpecieGroupView[list.size()]);
  }

  public static MosquitoCollectionPoint findOrCreate(GeoEntity geoEntity, Date dateCollected)
  {
    QueryFactory factory = new QueryFactory();
    MosquitoCollectionPointQuery query = new MosquitoCollectionPointQuery(factory);

    query.AND(query.getGeoEntity().getId().EQ(geoEntity.getId()));
    query.AND(query.getDateCollected().EQ(dateCollected));

    OIterator<? extends MosquitoCollectionPoint> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      else
      {
        MosquitoCollectionPoint point = new MosquitoCollectionPoint();
        point.setGeoEntity(geoEntity);
        point.setDateCollected(dateCollected);
        point.apply();
        
        return point;
      }
    }
    finally
    {
      iterator.close();
    }
  }

}
