package dss.vector.solutions.export.entomology;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.NonSentinelSite;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.mo.CollectionMethod;
import dss.vector.solutions.util.ColumnListener;
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
    List<SearchableHierarchy> hierarchy = MosquitoCollectionView.getHierarchy();
        
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
  
  private static List<SearchableHierarchy> getHierarchy()
  {
    List<GeoHierarchyView> political = Arrays.asList(GeoHierarchy.getPoliticalGeoHierarchiesByType(Country.CLASS));    
    List<SearchableHierarchy> hierarchy = new LinkedList<SearchableHierarchy>(political);

    //Sentinel and Non-Sentinel Sites are not part of the political hierarchy so I must add them manually,
    //This only works if Sentinel and Non-Sentinel Sites are not part of the political hierarchy.
    hierarchy.add(new GenericHierarchySearcher(new String[]{SentinelSite.CLASS, NonSentinelSite.CLASS}));
    
    return hierarchy;
  }
  
  private static List<MdAttributeDAOIF> getGeoEntityAttributes()
  {
    List<MdAttributeDAOIF> list = new LinkedList<MdAttributeDAOIF>();
    list.add(getGeoEntity_0Md());
    list.add(getGeoEntity_01Md());
    list.add(getGeoEntity_02Md());
    list.add(getGeoEntity_03Md());
    list.add(getGeoEntity_04Md());
    list.add(getGeoEntity_05Md());
    list.add(getGeoEntity_06Md());
    list.add(getGeoEntity_07Md());
    list.add(getGeoEntity_08Md());
    list.add(getGeoEntity_09Md());
    list.add(getGeoEntity_10Md());
    
    return list;
  }
  
  public static void setupExcelListener(ExcelExporter exporter, String...params)
  {
    Map<String, String> map = new HashMap<String, String>(); 
    List<SearchableHierarchy> hierarchy = MosquitoCollectionView.getHierarchy();
    List<MdAttributeDAOIF> attributes = MosquitoCollectionView.getGeoEntityAttributes();
    
    int size = Math.min(hierarchy.size(), attributes.size());
    
    for(int i = 0; i < size; i++)
    {
      String key = attributes.get(i).getId();
      String displayLabel = hierarchy.get(i).getDisplayLabel();

      map.put(key, displayLabel);
    }
    
    exporter.addListener(new ColumnListener(map));    
  }
}
