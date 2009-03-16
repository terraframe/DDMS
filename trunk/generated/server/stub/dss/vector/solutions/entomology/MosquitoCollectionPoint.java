package dss.vector.solutions.entomology;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.entomology.MosquitoCollectionPointBase;
import dss.vector.solutions.entomology.MosquitoCollectionPointQuery;

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

    if (! ( geoEntity instanceof Trap || geoEntity instanceof PermanentWaterBody))
    {
      String msg = "The geoEntity of a mosquito collection must be a fixed trap or a permenent water body";

      InvalidMosquitoCollectionPointGeoEntityException e = new InvalidMosquitoCollectionPointGeoEntityException(
          msg);
      e.setGeoId(this.getGeoEntity().getGeoId());
      e.apply();

      throw e;
    }
  }

  public static dss.vector.solutions.entomology.MosquitoCollectionPoint searchByGeoEntityAndDate(
      dss.vector.solutions.geo.generated.GeoEntity geoEntity, java.util.Date collectionDate)
  {

    QueryFactory factory = new QueryFactory();
    MosquitoCollectionPointQuery query = new MosquitoCollectionPointQuery(factory);

    query.AND(query.getGeoEntity().getId().EQ(geoEntity.getId()));
    query.AND(query.getDateCollected().EQ(collectionDate));

    OIterator<? extends MosquitoCollectionPoint> iterator = query.getIterator();

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
    
  public List<MosquitoCollectionPointView> getViews()
  {
    List<MosquitoCollectionPointView> list = new LinkedList<MosquitoCollectionPointView>();
    
    for(MorphologicalSpecieGroupView group : this.getMorphologicalSpecieGroups())
    {
      MosquitoCollectionPointView view = new MosquitoCollectionPointView();
      
      view.setCollection(this);
      view.setDateCollected(this.getDateCollected());
      view.setGeoEntity(this.getGeoEntity());
      view.setGroupId(this.getId());
      view.setQuantity(group.getQuantity());
      view.setSpecie(group.getSpecie());
      view.setIdentificationMethod(group.getIdentificationMethod());
      
      view.applyNoPersist();
      
      list.add(view);
    }

    return list;
  }

}
