package dss.vector.solutions.entomology;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.generated.CollectionSite;
import dss.vector.solutions.geo.generated.GeoEntity;

public class MosquitoCollectionPoint extends MosquitoCollectionPointBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288154522L;

  public MosquitoCollectionPoint()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    if(this.getGeoEntity() != null && this.getDateCollected() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      
      return this.getGeoEntity().getGeoId() + "." + format.format(this.getDateCollected());
    }
    
    return this.getId();
  }


  @Override
  public void apply()
  {
    validateGeoEntity();
    validateUniqueness();

    super.apply();
  }
  
  public void validateUniqueness()
  {
    QueryFactory factory = new QueryFactory();
    MosquitoCollectionPointQuery query = new MosquitoCollectionPointQuery(factory);

    query.AND(query.getGeoEntity().EQ(this.getGeoEntity()));
    query.AND(query.getDateCollected().EQ(this.getDateCollected()));

    OIterator<? extends MosquitoCollectionPoint> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        String msg = "This mosquito collection point already exists";
        MosquitoCollectionAllreadyExistsException e = new MosquitoCollectionAllreadyExistsException(msg);
        e.apply();
        throw e;
      }

    }
    finally
    {
      iterator.close();
    }
  }


  @Override
  public void validateGeoEntity()
  {
    super.validateGeoEntity();

    GeoEntity geoEntity = this.getGeoEntity();

    if (! ( geoEntity instanceof CollectionSite))
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
    List<MosquitoCollectionPointView> list = new LinkedList<MosquitoCollectionPointView>();

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
        list.add((MosquitoCollectionPointView) iterator.next().getView());
      }
    }
    finally
    {
      iterator.close();
    }

    return list.toArray(new MosquitoCollectionPointView[list.size()]);
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
