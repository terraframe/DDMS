package csu.mrc.ivcc.mdss.entomology;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import csu.mrc.ivcc.mdss.geo.generated.GeoEntity;
import csu.mrc.ivcc.mdss.geo.generated.PermanentWaterBodyIF;
import csu.mrc.ivcc.mdss.geo.generated.TrapIF;

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

    if (! ( geoEntity instanceof TrapIF || geoEntity instanceof PermanentWaterBodyIF ))
    {
      String msg = "The geoEntity of a mosquito collection must be a fixed trap or a permenent water body";

      InvalidMosquitoCollectionPointGeoEntityException e = new InvalidMosquitoCollectionPointGeoEntityException(
          msg);
      e.setGeoId(this.getGeoEntity().getGeoId());
      e.apply();

      throw e;
    }
  }

  public static csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPoint searchByGeoEntityAndDate(
      csu.mrc.ivcc.mdss.geo.generated.GeoEntity geoEntity, java.util.Date collectionDate)
  {
    MosquitoCollectionPoint collection = null;

    QueryFactory factory = new QueryFactory();
    MosquitoCollectionPointQuery query = new MosquitoCollectionPointQuery(factory);

    query.AND(query.getGeoEntity().getId().EQ(geoEntity.getId()));
    query.AND(query.getDateCollected().EQ(collectionDate));

    OIterator<? extends MosquitoCollectionPoint> iterator = query.getIterator();

    if (iterator.hasNext())
    {
      collection = iterator.next();
    }

    iterator.close();

    return collection;
  }

  public MosquitoCollectionPointView getView()
  {
    MosquitoCollectionPointView view = new MosquitoCollectionPointView();

    view.setCompositeCollection(this.getCompositeCollection());
    view.setCollectionId(this.getId());
    view.setDateCollected(this.getDateCollected());
    view.setGeoEntity(this.getGeoEntity());

    view.applyNoPersist();

    return view;
  }

}
