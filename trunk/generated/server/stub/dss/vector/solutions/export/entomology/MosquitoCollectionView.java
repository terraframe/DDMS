package dss.vector.solutions.export.entomology;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.NonSentinelSite;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.mo.CollectionMethod;
import dss.vector.solutions.util.GenericHierarchySearcher;
import dss.vector.solutions.util.GeoEntitySearcher;
import dss.vector.solutions.util.SearchableHierarchy;

public class MosquitoCollectionView extends MosquitoCollectionViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236703946827L;

  private String concreteId;

  public MosquitoCollectionView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {    
    CollectionMethod method = null;
    List<GeoHierarchyView> political = Arrays.asList(GeoHierarchy.getPoliticalGeoHierarchiesByType(Country.CLASS));    
    List<SearchableHierarchy> hierarchy = new LinkedList<SearchableHierarchy>(political);

    //Sentinel and Non-Sentinel Sites are not part of the political hierarchy so I must add them manually,
    //This only works if Sentinel and Non-Sentinel Sites are not part of the political hierarchy.
    hierarchy.add(new GenericHierarchySearcher(new String[]{SentinelSite.CLASS, NonSentinelSite.CLASS}));
        
    GeoEntitySearcher searcher = new GeoEntitySearcher(hierarchy);    
    GeoEntity entity = searcher.getGeoEntity(this.getGeoEntityNames());   
    
    if(this.hasCollectionMethod())
    {
      method = (CollectionMethod) CollectionMethod.validateByDisplayLabel(this.getCollectionMethod());     
    }

    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(entity);
    collection.setCollectionMethod(method);
    collection.setDateCollected(this.getDateCollected());
    collection.apply();

    this.populateView(collection);
  }

  private boolean hasCollectionMethod()
  {
    return this.getCollectionMethod() != null && !this.getCollectionMethod().equals("");
  }

  private void populateView(MosquitoCollection collection)
  {
    this.setConcreteId(collection.getId());
    this.setDateCollected(collection.getDateCollected());

    if(collection.getCollectionMethod() != null)
    {
      this.setCollectionMethod(collection.getCollectionMethod().getTermName());
    }    
  }

  private List<String> getGeoEntityNames()
  {
    List<String> list = new LinkedList<String>();
    list.add(this.getGeoEntity_0());
    list.add(this.getGeoEntity_01());
    list.add(this.getGeoEntity_02());
    list.add(this.getGeoEntity_03());
    list.add(this.getGeoEntity_04());
    list.add(this.getGeoEntity_05());
    list.add(this.getGeoEntity_06());
    list.add(this.getGeoEntity_07()); 
    list.add(this.getGeoEntity_08());
    list.add(this.getGeoEntity_09());
    list.add(this.getGeoEntity_10());
    
    return list;
  }
  
  public void setConcreteId(String concreteId)
  {
    this.concreteId = concreteId;
  }
  
  public String getConcreteId()
  {
    return concreteId;
  }
  
  @Transaction
  public void deleteConcrete()
  {
    if(hasConcreteId())
    {
      MosquitoCollection.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcreteId()
  {
    return this.concreteId != null && !this.concreteId.equals("");
  }
}
