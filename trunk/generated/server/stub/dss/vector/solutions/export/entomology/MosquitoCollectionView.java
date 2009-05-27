package dss.vector.solutions.export.entomology;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

  private String id;

  public MosquitoCollectionView()
  {
    super();
  }

  @Override
  public void apply()
  {    
    List<GeoHierarchyView> political = Arrays.asList(GeoHierarchy.getPoliticalGeoHierarchiesByType(Country.CLASS));    
    List<SearchableHierarchy> hierarchy = new LinkedList<SearchableHierarchy>(political);

    //Sentinel and Non-Sentinel Sites are not part of the political hierarchy so I must add them manually,
    //This only works if Sentinel and Non-Sentinel Sites are not part of the political hierarchy.
    hierarchy.add(new GenericHierarchySearcher(new String[]{SentinelSite.CLASS, NonSentinelSite.CLASS}));
        
    GeoEntitySearcher searcher = new GeoEntitySearcher(hierarchy);    
    GeoEntity entity = searcher.getGeoEntity(this.getGeoEntityNames());        
    CollectionMethod method = (CollectionMethod) CollectionMethod.searchByTermName(this.getCollectionMethod());    

    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(entity);
    collection.setCollectionMethod(method);
    collection.setDateCollected(this.getDateCollected());
    collection.apply();

    this.id = collection.getId();
  }

  private List<String> getGeoEntityNames()
  {
    List<String> list = new LinkedList<String>();
    list.add(this.getGeoEntity_0());
    list.add(this.getGeoEntity_1());
    list.add(this.getGeoEntity_2());
    list.add(this.getGeoEntity_3());
    list.add(this.getGeoEntity_4());
    list.add(this.getGeoEntity_5());
    list.add(this.getGeoEntity_6());
    list.add(this.getGeoEntity_7());
    list.add(this.getGeoEntity_8());
    list.add(this.getGeoEntity_9());
    
    return list;
  }
  
  public String getCollectionId()
  {
    return id;
  }
}
