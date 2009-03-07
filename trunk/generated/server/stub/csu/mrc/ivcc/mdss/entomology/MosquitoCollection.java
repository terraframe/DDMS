package csu.mrc.ivcc.mdss.entomology;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import csu.mrc.ivcc.mdss.geo.generated.AbstractSite;

public class MosquitoCollection extends MosquitoCollectionBase implements
    com.terraframe.mojo.generation.loader.Reloadable
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

    super.apply();
  }

  @Override
  public void validateGeoEntity()
  {
    super.validateGeoEntity();

    if (! (this.getGeoEntity() instanceof AbstractSite))
    {
      String msg = "The geoEntity of a mosquito collection must be a (non)sentinel site";

      InvalidMosquitoCollectionGeoEntityException e = new InvalidMosquitoCollectionGeoEntityException(
          msg);
      e.setGeoId(this.getGeoEntity().getGeoId());
      e.apply();

      throw e;
    }
  }

  public static csu.mrc.ivcc.mdss.entomology.MosquitoCollection searchByGeoEntityAndDate(
      csu.mrc.ivcc.mdss.geo.generated.GeoEntity geoEntity, java.util.Date collectionDate)
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
}
