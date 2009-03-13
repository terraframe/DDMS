package csu.mrc.ivcc.mdss.export.entomology;

import csu.mrc.ivcc.mdss.entomology.MosquitoCollection;
import csu.mrc.ivcc.mdss.geo.generated.GeoEntity;
import csu.mrc.ivcc.mdss.mo.CollectionMethod;

public class MosquitoCollectionView extends MosquitoCollectionViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236703946827L;
  
  private String id;
  
  public MosquitoCollectionView()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    GeoEntity entity = GeoEntity.searchByGeoId(this.getGeoEntity());
    CollectionMethod method = (CollectionMethod) CollectionMethod.searchByTermName(this.getCollectionMethod());    
    
    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(entity);
    collection.setCollectionMethod(method);
    collection.setDateCollected(this.getDateCollected());
    collection.apply();
    
    this.id = collection.getId();
  }  
  
  public String getCollectionId()
  {
    return id;
  }
}