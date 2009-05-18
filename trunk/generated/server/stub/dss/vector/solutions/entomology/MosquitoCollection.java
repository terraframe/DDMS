package dss.vector.solutions.entomology;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.generated.AbstractSite;
import dss.vector.solutions.mo.CollectionMethod;

public class MosquitoCollection extends MosquitoCollectionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234285245712L;

  public MosquitoCollection()
  {
    super();
  }

  @Override
  public void apply()
  {
    validateGeoEntity();
    validateUniqueness();

    super.apply();
  }

  @Override
  public void validateGeoEntity()
  {
    super.validateGeoEntity();

    if (! ( this.getGeoEntity() instanceof AbstractSite ))
    {
      String msg = "The geoEntity of a mosquito collection must be a (non)sentinel site";

      InvalidMosquitoCollectionGeoEntityException e = new InvalidMosquitoCollectionGeoEntityException(msg);
      e.setGeoId(this.getGeoEntity().getGeoId());
      e.apply();

      throw e;
    }
  }

  public void validateUniqueness()
  {
    QueryFactory factory = new QueryFactory();
    MosquitoCollectionQuery query = new MosquitoCollectionQuery(factory);

    query.AND(query.getGeoEntity().getId().EQ(this.getGeoEntity().getId()));
    query.AND(query.getDateCollected().EQ(this.getDateCollected()));
    query.AND(query.getCollectionMethod().EQ(this.getCollectionMethod()));
    if (this.getId() != null)
    {
      query.AND(query.getCollectionMethod().NE(this.getId()));
    }

    OIterator<? extends MosquitoCollection> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        String msg = "This mosquito collection allready exists";
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

  public static dss.vector.solutions.entomology.MosquitoCollection searchByGeoEntityAndDate(dss.vector.solutions.geo.generated.GeoEntity geoEntity, java.util.Date collectionDate)
  {
    QueryFactory factory = new QueryFactory();
    MosquitoCollectionQuery query = new MosquitoCollectionQuery(factory);

    query.AND(query.getGeoEntity().getId().EQ(geoEntity.getId()));
    query.AND(query.getDateCollected().EQ(collectionDate));

    OIterator<? extends MosquitoCollection> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  public static dss.vector.solutions.entomology.MosquitoCollection searchByGeoEntityAndDateAndCollectionMethod(dss.vector.solutions.geo.generated.GeoEntity geoEntity, java.util.Date collectionDate, CollectionMethod collectionMethod)
  {
    QueryFactory factory = new QueryFactory();
    MosquitoCollectionQuery query = new MosquitoCollectionQuery(factory);

    query.AND(query.getGeoEntity().getId().EQ(geoEntity.getId()));
    query.AND(query.getDateCollected().EQ(collectionDate));
    query.AND(query.getCollectionMethod().EQ(collectionMethod));

    OIterator<? extends MosquitoCollection> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }
}
